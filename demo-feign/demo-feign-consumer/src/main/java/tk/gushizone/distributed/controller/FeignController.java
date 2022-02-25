package tk.gushizone.distributed.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import tk.gushizone.distributed.api.PingApi;

import javax.annotation.Resource;

/**
 * @author gushizone@gmail.com
 * @date 2022/2/25 2:10 下午
 */
@RestController
@RequestMapping("/feign")
public class FeignController {

    @Resource
    private PingApi pingApi;

    @GetMapping("/c1-c2")
    public String c1Tc2() {
        return pingApi.ping();
    }

    /**
     * 超时重试，结合多生产者测试
     */
    @GetMapping("/retry")
    public String retry(@RequestParam("timeout") Long timeout) {

        return pingApi.timeout(timeout);
    }


}
