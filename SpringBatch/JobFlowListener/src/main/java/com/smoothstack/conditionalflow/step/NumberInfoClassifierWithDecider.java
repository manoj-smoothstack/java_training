package com.smoothstack.conditionalflow.step;

import com.smoothstack.conditionalflow.model.NumberInfo;
import org.springframework.batch.core.listener.ItemListenerSupport;
import org.springframework.batch.item.ItemProcessor;

public class NumberInfoClassifierWithDecider extends ItemListenerSupport<NumberInfo, Integer> implements ItemProcessor<NumberInfo, Integer> {

    @Override
    public Integer process(NumberInfo numberInfo) throws Exception {
        return Integer.valueOf(numberInfo.getNumber());
    }
}
