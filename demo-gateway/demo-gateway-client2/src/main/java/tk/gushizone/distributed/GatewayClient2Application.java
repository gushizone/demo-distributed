package tk.gushizone.distributed;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

/**
 * @author gushizone@gmail.com
 * @date 2022/2/28 10:30 上午
 */
@EnableDiscoveryClient
@SpringBootApplication
public class GatewayClient2Application {

    public static void main(String[] args) {
        SpringApplication.run(GatewayClient2Application.class, args);
    }
}
