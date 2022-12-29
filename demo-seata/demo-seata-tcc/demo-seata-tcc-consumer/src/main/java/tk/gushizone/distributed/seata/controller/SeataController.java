package tk.gushizone.distributed.seata.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import tk.gushizone.distributed.seata.service.ItemStockService;

/**
 * @author gushizone@gmail.com
 * @date 2021/8/13 3:40 下午
 */
@RestController
@RequestMapping("/seata")
public class SeataController {

    @Autowired
    private ItemStockService itemStockService;

    @PostMapping("/itemStock")
    public String itemStock() {

        itemStockService.save();

        return "OK";
    }

    @PostMapping("/tcc/itemStock")
    public String itemStockTcc() {

        itemStockService.tccSave();

        return "OK";
    }
}
