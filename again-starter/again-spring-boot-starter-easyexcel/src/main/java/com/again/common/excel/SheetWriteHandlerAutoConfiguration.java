package com.again.common.excel;

import com.again.common.excel.config.ExcelConfigProperties;
import com.again.common.excel.handler.ManySheetWriteHandler;
import com.again.common.excel.handler.SingleSheetWriteHandler;
import com.alibaba.excel.converters.Converter;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.ObjectProvider;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;

@RequiredArgsConstructor
@Configuration(proxyBeanMethods = false)
public class SheetWriteHandlerAutoConfiguration {

    private final ExcelConfigProperties configProperties;

    private final ObjectProvider<List<Converter<?>>> converterProvider;


    /**
     * 单sheet 写入处理器
     */
    @Bean
    @ConditionalOnMissingBean
    public SingleSheetWriteHandler singleSheetWriteHandler() {
        return new SingleSheetWriteHandler(configProperties, converterProvider);
    }

    /**
     * 多sheet 写入处理器
     */
    @Bean
    @ConditionalOnMissingBean
    public ManySheetWriteHandler manySheetWriteHandler() {
        return new ManySheetWriteHandler(configProperties, converterProvider);
    }

}
