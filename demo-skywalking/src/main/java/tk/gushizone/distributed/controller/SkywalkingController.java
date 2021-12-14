package tk.gushizone.distributed.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import tk.gushizone.distributed.domain.Item;
import tk.gushizone.distributed.service.ItemService;

/**
 * @author gushizone@gmail.com
 * @date 2021/12/10 5:43 下午
 */
@Slf4j
@RestController
@RequestMapping
public class SkywalkingController {

    @Autowired
    private ItemService itemService;

    @GetMapping("/db")
    public Page<Item> db() {

        Page<Item> list = itemService.find();
        return list;
    }


}
