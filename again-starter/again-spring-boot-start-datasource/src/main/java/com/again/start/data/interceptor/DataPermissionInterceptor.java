package com.again.start.data.interceptor;

import com.again.start.data.DataScope;
import com.again.start.data.handler.DataPermissionHandler;
import com.again.start.data.processor.DataScopeSqlProcessor;
import com.again.start.data.util.BasePluginUtils;
import lombok.RequiredArgsConstructor;
import org.apache.ibatis.executor.statement.StatementHandler;
import org.apache.ibatis.mapping.MappedStatement;
import org.apache.ibatis.mapping.SqlCommandType;
import org.apache.ibatis.plugin.*;

import java.sql.Connection;
import java.util.List;
import java.util.Properties;

/**
 * 数据权限拦截器
 *
 * @version 1.0
 */
@RequiredArgsConstructor
@Intercepts({
		@Signature(type = StatementHandler.class, method = "prepare", args = { Connection.class, Integer.class }) })
public class DataPermissionInterceptor implements Interceptor {

	private final DataScopeSqlProcessor dataScopeSqlProcessor;

	private final DataPermissionHandler dataPermissionHandler;

	@Override
	public Object intercept(Invocation invocation) throws Throwable {
		// 第一版，测试用
		Object target = invocation.getTarget();
		StatementHandler sh = (StatementHandler) target;
		BasePluginUtils.MPStatementHandler mpSh = BasePluginUtils.mpStatementHandler(sh);
		MappedStatement ms = mpSh.mappedStatement();
		SqlCommandType sct = ms.getSqlCommandType();
		BasePluginUtils.MPBoundSql mpBs = mpSh.mPBoundSql();

		// TODO 根据注解进行一些此次 sql 执行中需要忽略的点
		// 根据用户权限判断是否需要拦截，例如管理员可以查看所有，则直接放行
		if (dataPermissionHandler.ignorePermissionControl()) {
			return invocation.proceed();
		}
		List<DataScope> dataScopes = dataPermissionHandler.dataScopes();
		if (dataScopes == null || dataScopes.size() == 0) {
			return invocation.proceed();
		}

		// 根据 DataScopes 进行数据权限的 sql 处理
		if (sct == SqlCommandType.SELECT) {
			mpBs.sql(dataScopeSqlProcessor.parserSingle(mpBs.sql(), dataScopes));
		}
		else if (sct == SqlCommandType.INSERT || sct == SqlCommandType.UPDATE || sct == SqlCommandType.DELETE) {
			mpBs.sql(dataScopeSqlProcessor.parserMulti(mpBs.sql(), dataScopes));
		}

		return invocation.proceed();
	}

	@Override
	public Object plugin(Object target) {
		if (target instanceof StatementHandler) {
			return Plugin.wrap(target, this);
		}
		return target;
	}

	@Override
	public void setProperties(Properties properties) {

	}

}
