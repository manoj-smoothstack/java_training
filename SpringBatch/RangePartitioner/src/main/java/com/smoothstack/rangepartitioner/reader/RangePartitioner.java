package com.smoothstack.rangepartitioner.reader;

import org.springframework.batch.core.partition.support.Partitioner;
import org.springframework.batch.item.ExecutionContext;

import java.util.HashMap;
import java.util.Map;

public class RangePartitioner implements Partitioner {
    @Override
    public Map<String, ExecutionContext> partition(int gridSize) {

        Map<String, ExecutionContext> result= new HashMap<>();

        int range =10;
        int fromId =1;
        int toId = range;

        for (int i =1; i<=gridSize ; i++){
            ExecutionContext value = new ExecutionContext();
            value.put("minValue", fromId);;
            value.put("maxValue", toId);

            result.put("partition" + i, value);

            fromId = toId +1;
            toId += range;

        }

        return result;
    }
}
