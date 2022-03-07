package processor;

import com.smoothstack.springbatch.simpleclient.model.Product;
import org.springframework.batch.item.ItemProcessor;

public class ProductProcessor implements ItemProcessor<Product, Product> {
    @Override
    public Product process(Product item) throws Exception {
         if (item.getProductId() == 2)
            throw new RuntimeException("Beacuse ID is 2");
         else
            item.setProductDesc(item.getProductDesc().toUpperCase());

        return item;
    }
}
