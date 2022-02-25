package tk.gushizone.distributed.controller;

import lombok.SneakyThrows;
import org.springframework.web.bind.annotation.RestController;
import tk.gushizone.distributed.api.PingApi;

/**
 * @author gushizone@gmail.com
 * @date 2022/2/25 10:30 上午
 */
@RestController
public class PingController implements PingApi {


    @Override
    public String ping() {
        return "OK";
    }

    @Override
    @SneakyThrows
    public String timeout(Long timeout) {
        Thread.sleep(timeout);
        return "OK";
    }
}
