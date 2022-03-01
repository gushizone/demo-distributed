package tk.gushizone.distributed.sentinel;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

/**
 * @author gushizone@gmail.com
 * @date 2022/3/1 4:15 下午
 */
@EnableDiscoveryClient
@SpringBootApplication
public class SentinelProducerApplication {

    public static void main(String[] args) {
        SpringApplication.run(SentinelProducerApplication.class);
    }

}
