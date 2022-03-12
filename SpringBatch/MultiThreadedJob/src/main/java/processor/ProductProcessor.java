package processor;

import com.smoothstack.springbatch.multithreaded.model.Product;
import org.springframework.batch.item.ItemProcessor;

public class ProductProcessor implements ItemProcessor<Product, Product> {
    @Override
    public Product process(Product item) throws Exception {
        Thread.sleep(300);
        item.setProductDesc(item.getProductDesc().toUpperCase());
        return item;
    }
}
