package com.again.common.excel.annotation;

import com.again.common.excel.head.HeadGenerator;
import com.alibaba.excel.converters.Converter;
import com.alibaba.excel.support.ExcelTypeEnum;
import com.alibaba.excel.write.handler.WriteHandler;

import java.lang.annotation.*;

@Documented
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface ResponseExcel {
    /**
     * 文件名称
     *
     * @return string
     */
    String name() default "";

    /**
     * 文件类型 （xlsx xls）
     *
     * @return string
     */
    ExcelTypeEnum suffix() default ExcelTypeEnum.XLSX;

    /**
     * 文件密码
     *
     * @return password
     */
    String password() default "";

    /**
     * sheet 名称，支持多个
     *
     * @return String[]
     */
    String[] sheet() default {"Sheet1"};

    /**
     * 内存操作
     *
     * @return
     */
    boolean inMemory() default false;

    /**
     * excel 模板
     *
     * @return String
     */
    String template() default "";

    /**
     * + 包含字段
     *
     * @return String[]
     */
    String[] include() default {};

    /**
     * 排除字段
     *
     * @return String[]
     */
    String[] exclude() default {};

    /**
     * 拦截器，自定义样式等处理器
     *
     * @return WriteHandler[]
     */
    Class<? extends WriteHandler>[] writeHandler() default {};

    /**
     * 转换器
     *
     * @return Converter[]
     */
    Class<? extends Converter>[] converter() default {};

    /**
     * 自定义Excel头生成器
     *
     * @return HeadGenerator
     */
    Class<? extends HeadGenerator> headGenerator() default HeadGenerator.class;

}
