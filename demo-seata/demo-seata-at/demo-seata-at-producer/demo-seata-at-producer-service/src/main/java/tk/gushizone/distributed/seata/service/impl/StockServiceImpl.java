package tk.gushizone.distributed.seata.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import tk.gushizone.distributed.seata.domain.Stock;
import tk.gushizone.distributed.seata.mapper.StockMapper;
import tk.gushizone.distributed.seata.service.StockService;

/**
 *
 */
@Service
public class StockServiceImpl extends ServiceImpl<StockMapper, Stock> implements StockService {


    @Override
    public void atSave(Long itemId) {

        this.save(Stock.builder()
                .itemId(itemId)
                .stock(100)
                .build());

//         todo
//        int i = 1/0;
    }
}




