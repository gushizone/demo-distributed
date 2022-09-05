package tk.gushizone.controller;

import cn.hutool.json.JSONObject;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import tk.gushizone.common.util.RequestUtil;

import javax.servlet.http.HttpServletRequest;

/**
 * @author gushizone@gmail.com
 * @date 2022/2/17 2:11 下午
 */
@Slf4j
@RestController
@RequestMapping("/rec")
public class RecController {


    @GetMapping
    public String get(HttpServletRequest request) {

        JSONObject result = RequestUtil.readRequest(request);
        log.warn("get rec is {}", result);
        return result.toString();
    }

    @PostMapping
    public String post(HttpServletRequest request) {

        JSONObject result = RequestUtil.readRequest(request);
        log.warn("post rec is {}", result);
        return result.toString();
    }

}
