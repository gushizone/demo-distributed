package tk.gushizone.distributed.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import tk.gushizone.distributed.domain.Item;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 *
 */
public interface ItemService extends IService<Item> {

    Page<Item> find();
}
