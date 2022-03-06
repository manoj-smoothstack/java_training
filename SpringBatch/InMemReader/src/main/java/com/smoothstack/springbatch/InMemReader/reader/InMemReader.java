package com.smoothstack.springbatch.InMemReader.reader;

import org.springframework.batch.item.NonTransientResourceException;
import org.springframework.batch.item.ParseException;
import org.springframework.batch.item.UnexpectedInputException;
import org.springframework.batch.item.support.AbstractItemStreamItemReader;

import java.util.Arrays;
import java.util.List;

public class InMemReader extends AbstractItemStreamItemReader {

    Integer[] intArray ={1,2,3,4,5,6,7,8,9,10};
    List<Integer> myList = Arrays.asList(intArray);

    int index =0;

    @Override
    public Object read() throws Exception, UnexpectedInputException, ParseException, NonTransientResourceException {
        Integer nextItem = null;
        if ( index < myList.size()){
            nextItem = myList.get(index);
            index++;
        }else{
            index= 0;
        }

        return nextItem;
    }
}