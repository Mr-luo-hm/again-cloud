package com.again.common.excel.handler;

import com.again.common.excel.annotation.ResponseExcel;
import com.again.common.excel.aop.DynamicNameAspect;
import com.again.common.excel.converters.LocalDateStringConverter;
import com.again.common.excel.converters.LocalDateTimeStringConverter;
import com.again.common.excel.head.HeadGenerator;
import com.again.common.excel.kit.ExcelException;
import com.alibaba.excel.EasyExcel;
import com.alibaba.excel.ExcelWriter;
import com.alibaba.excel.converters.Converter;
import com.alibaba.excel.write.builder.ExcelWriterBuilder;
import com.alibaba.excel.write.handler.WriteHandler;
import lombok.SneakyThrows;
import org.springframework.beans.BeanUtils;
import org.springframework.core.io.ClassPathResource;
import org.springframework.http.HttpHeaders;
import org.springframework.util.StringUtils;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;

import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.InputStream;
import java.net.URLEncoder;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;


public abstract class AbstractSheetWriteHandler implements SheetWriteHandler {

    @Override
    public void check(ResponseExcel responseExcel) {
        if (!StringUtils.hasText(responseExcel.name())) {
            throw new ExcelException("@ResponseExcel name 配置不合法");
        }

        if (responseExcel.sheet().length == 0) {
            throw new ExcelException("@ResponseExcel sheet 配置不合法");
        }
    }

    @Override
    @SneakyThrows
    public void export(Object o, HttpServletResponse response, ResponseExcel responseExcel) {
        check(responseExcel);
        RequestAttributes requestAttributes = RequestContextHolder.getRequestAttributes();
        String name = (String) Objects.requireNonNull(requestAttributes).getAttribute(DynamicNameAspect.EXCEL_NAME_KEY,
                RequestAttributes.SCOPE_REQUEST);
        String fileName = String.format("%s%s", URLEncoder.encode(name, "UTF-8"), responseExcel.suffix().getValue());
        response.setContentType("application/vnd.ms-excel");
        response.setCharacterEncoding("utf-8");
        response.setHeader(HttpHeaders.CONTENT_DISPOSITION, "attachment;filename=" + fileName);
        write(o, response, responseExcel);
    }

    /**
     * 通用的获取ExcelWriter方法
     *
     * @param response      HttpServletResponse
     * @param responseExcel ResponseExcel注解
     * @param list          Excel数据
     * @param templatePath  模板地址
     * @return ExcelWriter
     */
    @SneakyThrows
    public ExcelWriter getExcelWriter(HttpServletResponse response, ResponseExcel responseExcel, List list,
                                      String templatePath) {
        // 数据类型
        Class<?> dataClass = list.get(0).getClass();
        ExcelWriterBuilder writerBuilder = EasyExcel.write(response.getOutputStream(), dataClass)
                .registerConverter(LocalDateStringConverter.INSTANCE)
                .registerConverter(LocalDateTimeStringConverter.INSTANCE).autoCloseStream(true)
                .excelType(responseExcel.suffix()).inMemory(responseExcel.inMemory());

        // Excel头信息增强
        Class<? extends HeadGenerator> headEnhancerClass = responseExcel.headGenerator();
        if (!headEnhancerClass.isInterface()) {
            HeadGenerator headGenerator = BeanUtils.instantiateClass(headEnhancerClass);
            writerBuilder.head(headGenerator.head(writerBuilder, dataClass));
        }

        if (StringUtils.hasText(responseExcel.password())) {
            writerBuilder.password(responseExcel.password());
        }

        if (responseExcel.include().length != 0) {
            writerBuilder.includeColumnFiledNames(Arrays.asList(responseExcel.include()));
        }

        if (responseExcel.exclude().length != 0) {
            writerBuilder.excludeColumnFiledNames(Arrays.asList(responseExcel.include()));
        }

        if (responseExcel.writeHandler().length != 0) {
            for (Class<? extends WriteHandler> clazz : responseExcel.writeHandler()) {
                writerBuilder.registerWriteHandler(BeanUtils.instantiateClass(clazz));
            }
        }

        // 自定义注入的转换器
        registerCustomConverter(writerBuilder);

        if (responseExcel.converter().length != 0) {
            for (Class<? extends Converter> clazz : responseExcel.converter()) {
                writerBuilder.registerConverter(BeanUtils.instantiateClass(clazz));
            }
        }

        if (StringUtils.hasText(responseExcel.template())) {
            ClassPathResource classPathResource = new ClassPathResource(
                    templatePath + File.separator + responseExcel.template());
            InputStream inputStream = classPathResource.getInputStream();
            writerBuilder.withTemplate(inputStream);
        }

        return writerBuilder.build();
    }

    /**
     * 自定义注入转换器 如果有需要，子类自己重写
     *
     * @param builder ExcelWriterBuilder
     */
    public void registerCustomConverter(ExcelWriterBuilder builder) {
        // do nothing
    }

}
