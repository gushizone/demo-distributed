package tk.gushizone.distributed.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import tk.gushizone.distributed.domain.Item;
import tk.gushizone.distributed.service.ItemService;
import tk.gushizone.distributed.mapper.ItemMapper;
import org.springframework.stereotype.Service;

/**
 *
 */
@Service
public class ItemServiceImpl extends ServiceImpl<ItemMapper, Item>
implements ItemService{

}




