package tk.gushizone.distributed.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import tk.gushizone.distributed.server.Client2Server;

import javax.annotation.Resource;

/**
 * @author gushizone@gmail.com
 * @date 2021/12/10 5:43 下午
 */
@RestController
@RequestMapping("/ping")
public class PingController {

    @Resource
    private Client2Server client2Server;

    @GetMapping
    public String ping() {

        return "pong";
    }

    @GetMapping("/c1-c2")
    public String c1Tc2() {
        return client2Server.ping();
    }
}
