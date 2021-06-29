package com.again.common.excel;

import com.again.common.excel.aop.DynamicNameAspect;
import com.again.common.excel.aop.ResponseExcelReturnValueHandler;
import com.again.common.excel.config.ExcelConfigProperties;
import com.again.common.excel.handler.SheetWriteHandler;
import com.again.common.excel.processor.NameProcessor;
import com.again.common.excel.processor.NameSpelExpressionProcessor;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.web.method.support.HandlerMethodReturnValueHandler;
import org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerAdapter;

import java.util.ArrayList;
import java.util.List;


@Import(SheetWriteHandlerAutoConfiguration.class)
@RequiredArgsConstructor
@Configuration(proxyBeanMethods = false)
@EnableConfigurationProperties(ExcelConfigProperties.class)
public class ResponseExcelAutoConfiguration implements InitializingBean {
    private final RequestMappingHandlerAdapter handlerAdapter;

    private final List<SheetWriteHandler> sheetWriteHandlerList;

    /**
     * SPEL 解析处理器
     *
     * @return NameProcessor excel名称解析器
     */
    @Bean
    @ConditionalOnMissingBean
    public NameProcessor nameProcessor() {
        return new NameSpelExpressionProcessor();
    }

    /**
     * Excel名称解析处理切面
     *
     * @param nameProcessor SPEL 解析处理器
     * @return DynamicNameAspect
     */
    @Bean
    @ConditionalOnMissingBean
    public DynamicNameAspect dynamicNameAspect(NameProcessor nameProcessor) {
        return new DynamicNameAspect(nameProcessor);
    }

    @Override
    public void afterPropertiesSet() {
        List<HandlerMethodReturnValueHandler> returnValueHandlers = handlerAdapter.getReturnValueHandlers();

        List<HandlerMethodReturnValueHandler> newHandlers = new ArrayList<>();
        newHandlers.add(new ResponseExcelReturnValueHandler(sheetWriteHandlerList));
        assert returnValueHandlers != null;
        newHandlers.addAll(returnValueHandlers);
        handlerAdapter.setReturnValueHandlers(newHandlers);
    }

}
