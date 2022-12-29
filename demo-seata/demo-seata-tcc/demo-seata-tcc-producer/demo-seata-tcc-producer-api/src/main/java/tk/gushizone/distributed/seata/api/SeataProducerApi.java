package tk.gushizone.distributed.seata.api;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * @author gushizone@gmail.com
 * @date 2021/8/20 3:25 下午
 */
@FeignClient("demo-tcc-producer")
public interface SeataProducerApi {

    @PostMapping("/seata/stock/save")
    String save(@RequestParam("id") Long id);

    @PostMapping("/seata/tcc/stock/save")
    String tccSave(@RequestParam("id") Long id);
}
