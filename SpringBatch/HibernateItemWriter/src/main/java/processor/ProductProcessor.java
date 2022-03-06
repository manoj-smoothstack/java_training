package processor;

import com.smoothstack.springbatch.hibernateitemwriter.model.Product;
import org.springframework.batch.item.ItemProcessor;

public class ProductProcessor implements ItemProcessor<Product, Product> {
    @Override
    public Product process(Product item) throws Exception {
        item.setProductDesc(item.getProductDesc().toUpperCase());

        return item;
    }
}
