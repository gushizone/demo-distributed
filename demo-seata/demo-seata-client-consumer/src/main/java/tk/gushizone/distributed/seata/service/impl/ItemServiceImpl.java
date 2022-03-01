package tk.gushizone.distributed.seata.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import tk.gushizone.distributed.seata.domain.Item;
import tk.gushizone.distributed.seata.mapper.ItemMapper;
import tk.gushizone.distributed.seata.service.ItemService;

/**
 *
 */
@Service
public class ItemServiceImpl extends ServiceImpl<ItemMapper, Item> implements ItemService {

}




