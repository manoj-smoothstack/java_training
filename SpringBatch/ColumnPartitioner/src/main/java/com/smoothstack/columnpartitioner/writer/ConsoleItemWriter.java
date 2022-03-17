package com.smoothstack.columnpartitioner.writer;

import org.springframework.batch.item.support.AbstractItemStreamItemWriter;

import java.util.List;

public class ConsoleItemWriter  extends AbstractItemStreamItemWriter {
    @Override
    public void write(List items) throws Exception {
        items.forEach(System.out::println);
    }
}
