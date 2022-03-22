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

/**
 *
 *
 * batch1: 1, 2, 3 ---> process total amt summation ---> A1
 * batch2: 4, 5, 6 ---> process                     ---> A2
 * .....
 *
 *
 * batchn:                                               An
 * ---------------------------------------------------------
 * Total (aggr batc                                     A1+A2....An = E1 (1 day)
 * * batch1: 1, 2, 3 ---> process total amt summation ---> A1
 *  * batch2: 4, 5, 6 ---> process                     ---> A2
 *  * .....
 *  *
 *  *
 *  * batchn:                                               An
 *  * ---------------------------------------------------------
 *  * Total (aggr batc                                     A1+A2....An = E2 (1 day)
 *  * batch1: 1, 2, 3 ---> process total amt summation ---> A1
 *  * batch2: 4, 5, 6 ---> process                     ---> A2
 *  * .....
 *  *
 *  *
 *  * batchn:                                               An
 *  * ---------------------------------------------------------
 *  * Total (aggr batc                                     A1+A2....An = E3 (1 day)
 */
