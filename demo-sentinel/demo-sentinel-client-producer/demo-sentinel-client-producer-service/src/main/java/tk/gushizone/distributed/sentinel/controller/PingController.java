package tk.gushizone.distributed.sentinel.controller;

import org.springframework.web.bind.annotation.RestController;
import tk.gushizone.distributed.sentinel.api.SentinelProducerApi;

/**
 * @author gushizone@gmail.com
 * @date 2022/3/1 4:07 下午
 */
@RestController
public class PingController implements SentinelProducerApi {

    @Override
    public String ping() {
        int i = 1/0;
        return "OK";
    }
}
