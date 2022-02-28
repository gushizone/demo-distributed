package tk.gushizone.distributed;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

/**
 * @author gushizone@gmail.com
 * @date 2022/2/28 10:29 上午
 */
@EnableFeignClients
@EnableDiscoveryClient
@SpringBootApplication
public class GatewayClient1Application {

    public static void main(String[] args) {
        SpringApplication.run(GatewayClient1Application.class, args);
    }
}
