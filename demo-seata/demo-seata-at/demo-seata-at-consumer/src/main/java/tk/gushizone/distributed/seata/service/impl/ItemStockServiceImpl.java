package tk.gushizone.distributed.seata.service.impl;

import io.seata.spring.annotation.GlobalTransactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tk.gushizone.distributed.seata.api.SeataProducerApi;
import tk.gushizone.distributed.seata.domain.Item;
import tk.gushizone.distributed.seata.service.ItemService;
import tk.gushizone.distributed.seata.service.ItemStockService;

/**
 * @author gushizone@gmail.com
 * @date 2021/8/13 3:40 下午
 */
@Service
public class ItemStockServiceImpl implements ItemStockService {

    @Autowired
    private ItemService itemService;
    @Autowired
    private SeataProducerApi seataProducerApi;

    @Override
    @GlobalTransactional(name = "item-stock", rollbackFor = Exception.class)
    public void save() {

        itemService.save(Item.builder()
                .name("foo")
                .remark("remark")
                .build());

        seataProducerApi.save();
    }
}
