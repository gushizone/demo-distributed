package tk.gushizone.distributed.seata.service;

import com.baomidou.mybatisplus.extension.service.IService;
import tk.gushizone.distributed.seata.domain.Stock;

/**
 *
 */
public interface StockService extends IService<Stock> {

    void atSave(Long itemId);
}
