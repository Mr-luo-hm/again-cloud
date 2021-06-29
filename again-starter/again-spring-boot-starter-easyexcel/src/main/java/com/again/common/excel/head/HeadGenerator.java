package com.again.common.excel.head;

import com.alibaba.excel.write.builder.ExcelWriterBuilder;

import java.util.List;

public interface HeadGenerator {
    /**
     * <p>
     * 自定义头部信息
     * </p>
     * 实现类根据数据的class信息，定制Excel头<br/>
     * 具体方法使用参考：https://www.yuque.com/easyexcel/doc/write#b4b9de00
     *
     * @param writerBuilder ExcelWriterBuilder
     * @param clazz         当前sheet的数据类型
     * @return List<List < String>> Head头信息
     */
    List<List<String>> head(ExcelWriterBuilder writerBuilder, Class<?> clazz);


}
