package tk.gushizone.distributed;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.circuitbreaker.EnableCircuitBreaker;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

/**
 * @author gushizone@gmail.com
 * @date 2022/2/25 4:02 下午
 */
@EnableFeignClients
@EnableCircuitBreaker
@EnableDiscoveryClient
@SpringBootApplication
public class HystrixConsumerApplication {

    public static void main(String[] args) {
        SpringApplication.run(HystrixConsumerApplication.class, args);
    }
}
