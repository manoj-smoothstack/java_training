package com.smoothstack.springbatch.HibernateCursorItemReader.writer;

import org.springframework.batch.item.support.AbstractItemStreamItemWriter;

import java.util.List;

public class ConsoleItemWriter extends AbstractItemStreamItemWriter {
    @Override
    public void write(List items) throws Exception {
        items.stream().forEach(System.out::println);
        System.out.println(" ************ writing each chunk ***********");
    }
}
