package tk.gushizone.distributed.seata.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;
import tk.gushizone.distributed.seata.api.SeataProducerApi;
import tk.gushizone.distributed.seata.domain.Stock;
import tk.gushizone.distributed.seata.service.StockCreateTccService;
import tk.gushizone.distributed.seata.service.StockService;

/**
 * @author gushizone@gmail.com
 * @date 2021/8/20 11:08 上午
 */
@Slf4j
@RestController
public class SeataController implements SeataProducerApi {

    @Autowired
    private StockService stockService;
    @Autowired
    private StockCreateTccService stockCreateTccService;

    @Override
    public String save(Long id) {


        stockService.save(Stock.builder()
                .itemId(id)
                .stock(100)
                .build());



        // todo
        int i = 1/0;

        return "OK";
    }

    @Override
    public String tccSave(Long id) {

        stockCreateTccService.prepare(null, id);

//        // todo
//        int i = 1/0;

        return "OK";
    }
}
