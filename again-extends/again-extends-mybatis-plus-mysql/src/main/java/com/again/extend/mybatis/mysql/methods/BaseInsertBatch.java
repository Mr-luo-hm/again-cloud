package com.again.extend.mybatis.mysql.methods;

import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.core.injector.AbstractMethod;
import com.baomidou.mybatisplus.core.metadata.TableInfo;
import com.baomidou.mybatisplus.core.metadata.TableInfoHelper;
import org.apache.ibatis.executor.keygen.Jdbc3KeyGenerator;
import org.apache.ibatis.executor.keygen.KeyGenerator;
import org.apache.ibatis.executor.keygen.NoKeyGenerator;
import org.apache.ibatis.mapping.MappedStatement;
import org.apache.ibatis.mapping.SqlSource;

public abstract class BaseInsertBatch extends AbstractMethod {

	@Override
	public MappedStatement injectMappedStatement(Class<?> mapperClass, Class<?> modelClass, TableInfo tableInfo) {
		SqlSource sqlSource = languageDriver.createSqlSource(configuration, String.format(getSql(),
				tableInfo.getTableName(), prepareFieldSql(tableInfo), prepareValuesSqlForMysqlBatch(tableInfo)),
				modelClass);

		// === mybatis 主键逻辑处理：主键生成策略，以及主键回填=======
		KeyGenerator keyGenerator = new NoKeyGenerator();
		String keyColumn = null;
		String keyProperty = null;
		// 如果需要回填主键
		if (backFillKey() && StrUtil.isNotEmpty(tableInfo.getKeyProperty())) {
			// 表包含主键处理逻辑,如果不包含主键当普通字段处理
			if (tableInfo.getIdType() == IdType.AUTO) {
				/* 自增主键 */
				keyGenerator = new Jdbc3KeyGenerator();
				keyProperty = getKeyProperty(tableInfo);
				keyColumn = tableInfo.getKeyColumn();
			}
			else {
				if (null != tableInfo.getKeySequence()) {
					keyGenerator = TableInfoHelper.genKeyGenerator(getId(), tableInfo, builderAssistant);
					keyProperty = getKeyProperty(tableInfo);
					keyColumn = tableInfo.getKeyColumn();
				}
			}
		}

		return this.addInsertMappedStatement(mapperClass, modelClass, getId(), sqlSource, keyGenerator, keyProperty,
				keyColumn);
	}

	private String getKeyProperty(TableInfo tableInfo) {
		return "collection." + tableInfo.getKeyProperty();
	}

	/**
	 * 是否回填主键
	 *
	 * @author lingting 2020-08-26 22:14
	 */
	public boolean backFillKey() {
		return false;
	}

	protected String prepareFieldSql(TableInfo tableInfo) {
		StringBuilder fieldSql = new StringBuilder();
		fieldSql.append(tableInfo.getKeyColumn()).append(",");
		tableInfo.getFieldList().forEach(x -> fieldSql.append(x.getColumn()).append(","));
		fieldSql.delete(fieldSql.length() - 1, fieldSql.length());
		fieldSql.insert(0, "(");
		fieldSql.append(")");
		return fieldSql.toString();
	}

	/**
	 * 获取注册的脚本
	 * @return java.lang.String
	 * @author lingting 2020-06-09 20:38:54
	 */
	abstract protected String getSql();

	/**
	 * 获取脚本id 即 方法名
	 * @return java.lang.String
	 * @author lingting 2020-06-09 20:39:30
	 */
	abstract protected String getId();

	protected String prepareValuesSqlForMysqlBatch(TableInfo tableInfo) {
		return prepareValuesBuildSqlForMysqlBatch(tableInfo).toString();
	}

	protected StringBuilder prepareValuesBuildSqlForMysqlBatch(TableInfo tableInfo) {
		final StringBuilder valueSql = new StringBuilder();
		valueSql.append(
				"<foreach collection=\"collection\" item=\"item\" index=\"index\" open=\"(\" separator=\"),(\" close=\")\">");
		valueSql.append("#{item.").append(tableInfo.getKeyProperty()).append("},");
		tableInfo.getFieldList().forEach(field -> valueSql.append("#{item.").append(field.getProperty()).append("},"));
		valueSql.delete(valueSql.length() - 1, valueSql.length());
		valueSql.append("</foreach>");
		return valueSql;
	}

}
