package tk.gushizone.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author gushizone@gmail.com
 * @date 2022/2/17 2:11 下午
 */
@RestController
@RequestMapping("/api")
public class ApiController {


    @GetMapping
    public String api() {
        return "OK";
    }

}
