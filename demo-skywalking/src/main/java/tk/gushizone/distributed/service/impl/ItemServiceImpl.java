package tk.gushizone.distributed.service.impl;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.google.common.collect.Lists;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import tk.gushizone.distributed.domain.Item;
import tk.gushizone.distributed.mapper.ItemMapper;
import tk.gushizone.distributed.service.ItemService;

/**
 *
 */
@Slf4j
@Service
public class ItemServiceImpl extends ServiceImpl<ItemMapper, Item> implements ItemService {

    @Override
    public Page<Item> find() {

        Page<Item> page = this.lambdaQuery()
                .in(Item::getId, Lists.newArrayList(1L, 2L, 3L))
                .page(new Page<>(1, 20));
        log.info("list: {}", page.getRecords());

        return page;
    }


    public static void main(String[] args) {


        System.out.println(new Page<>(1, 20));
    }
}




