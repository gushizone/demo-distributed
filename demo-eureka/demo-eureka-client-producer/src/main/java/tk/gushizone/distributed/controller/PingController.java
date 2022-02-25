package tk.gushizone.distributed.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author gushizone@gmail.com
 * @date 2022/2/25 10:30 上午
 */
@RestController
@RequestMapping("/ping")
public class PingController {


    @GetMapping
    public String ping() {
        return "OK";
    }

}
