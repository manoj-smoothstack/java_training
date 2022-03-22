package com.smoothstack.springbatch.processtransactions.listener;

import org.springframework.batch.core.annotation.OnSkipInRead;
import org.springframework.batch.item.file.FlatFileParseException;

import java.io.FileOutputStream;
import java.io.IOException;

public class ProductSkipListener {

    private String readerrFileName ="error/read_skipped";


    @OnSkipInRead
    public void onSkipRead(Throwable t){
        if ( t instanceof FlatFileParseException){
            FlatFileParseException ffep = (FlatFileParseException) t;
            onSkip(ffep.getInput(),readerrFileName );
        }
    }

    public void onSkip(Object o, String fname){
        FileOutputStream fos = null;

        try {
            fos = new FileOutputStream(fname, true);
            fos.write(o.toString().getBytes());
            fos.write("\r\n".getBytes());
            fos.close();
        } catch (IOException e){
            e.printStackTrace();
        }


    }

}
