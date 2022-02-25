package tk.gushizone.distributed.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.cloud.client.loadbalancer.LoadBalancerClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import javax.annotation.Resource;

/**
 * @author gushizone@gmail.com
 * @date 2022/2/25 10:30 上午
 */
@Slf4j
@RestController
@RequestMapping("/eureka")
public class EurekaController {

    @Resource
    private LoadBalancerClient loadBalancerClient;

    @Resource
    private RestTemplate simpleRest;

    @Resource
    private RestTemplate loadBalanceRest;

    public static final String CLIENT_PRODUCER = "demo-eureka-client-producer";

    /**
     * RestTemplate - 显式 loadBalancer
     */
    @GetMapping("/c1-c2/lb")
    public String c1Tc2V1() {
        // 选取服务
        ServiceInstance instance = loadBalancerClient.choose(CLIENT_PRODUCER);
        if (instance == null) {
            return "No available instances";
        }

        // 拼装请求参数
        String target = String.format("http://%s:%s/ping",
                instance.getHost(), instance.getPort());
        log.info("url is {}", target);

        // 请求
        String response = simpleRest.getForObject(target, String.class);
        log.info("response is {}", response);
        return response;
    }

    /**
     * RestTemplate - 隐式 loadBalance
     */
    @GetMapping("/c1-c2/v2/lb")
    public String c1Tc2V2() {
        return loadBalanceRest.getForObject("http://" + CLIENT_PRODUCER + "/ping", String.class);
    }

}
