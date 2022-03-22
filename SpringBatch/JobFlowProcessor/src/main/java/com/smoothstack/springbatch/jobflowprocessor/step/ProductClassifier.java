package com.smoothstack.springbatch.jobflowprocessor.step;

import com.smoothstack.springbatch.jobflowprocessor.model.Product;
import org.springframework.batch.core.ExitStatus;
import org.springframework.batch.core.StepExecution;
import org.springframework.batch.core.annotation.BeforeStep;
import org.springframework.batch.core.listener.ItemListenerSupport;
import org.springframework.batch.item.ItemProcessor;

public class ProductClassifier extends ItemListenerSupport<Product, Integer>
        implements ItemProcessor<Product, Integer> {
    private StepExecution stepExecution;

    @BeforeStep
    public void beforeStep(StepExecution stepExecution) {
        this.stepExecution = stepExecution;
        //this.stepExecution.setExitStatus(new ExitStatus(ProductDecider.QUIET));
    }

    @Override
    public void afterProcess(Product product, Integer result) {
        super.afterProcess(product, result);
        if (product.getProdName().equals("dell")) {
            stepExecution.setExitStatus(new ExitStatus(ProductDecider.NOTIFY));
        }
    }

    @Override
    public Integer process(Product product) throws Exception {
        return Integer.valueOf(product.getProductId());
    }
}
