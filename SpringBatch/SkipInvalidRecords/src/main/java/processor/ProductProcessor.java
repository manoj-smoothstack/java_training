package processor;

import com.smoothstack.springbatch.skipinvalid.model.Product;
import org.springframework.batch.item.ItemProcessor;

public class ProductProcessor implements ItemProcessor<Product, Product> {
    @Override
    public Product process(Product item) throws Exception {
         if (item.getProductId() == 2)
             return null;
         else
            item.setProductDesc(item.getProductDesc().toUpperCase());

        return item;
    }
}
