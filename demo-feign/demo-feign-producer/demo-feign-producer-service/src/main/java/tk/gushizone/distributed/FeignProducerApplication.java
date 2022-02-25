package tk.gushizone.distributed;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

/**
 * @author gushizone@gmail.com
 * @date 2022/2/25 10:05 上午
 */
@EnableDiscoveryClient
@SpringBootApplication
public class FeignProducerApplication {

    public static void main(String[] args) {
        SpringApplication.run(FeignProducerApplication.class, args);
    }
}
