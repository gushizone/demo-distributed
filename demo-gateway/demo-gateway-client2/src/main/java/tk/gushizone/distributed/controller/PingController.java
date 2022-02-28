package tk.gushizone.distributed.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author gushizone@gmail.com
 * @date 2021/12/10 5:43 下午
 */
@RestController
@RequestMapping("/ping")
public class PingController {

    @GetMapping
    public String ping() {

        return "pong";
    }


}
