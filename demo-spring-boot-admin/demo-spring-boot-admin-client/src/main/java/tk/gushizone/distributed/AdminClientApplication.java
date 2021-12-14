package tk.gushizone.distributed;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

/**
 * @author gushizone@gmail.com
 * @date 2021/12/14 3:46 下午
 */
@SpringBootApplication
@EnableDiscoveryClient
@MapperScan("tk.gushizone.**.mapper")
public class AdminClientApplication {

    public static void main(String[] args) {
        SpringApplication.run(AdminClientApplication.class, args);
    }
}
