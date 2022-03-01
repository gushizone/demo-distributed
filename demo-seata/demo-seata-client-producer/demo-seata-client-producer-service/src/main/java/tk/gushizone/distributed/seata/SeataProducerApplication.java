package tk.gushizone.distributed.seata;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

/**
 * @author gushizone@gmail.com
 * @date 2022/3/1 11:51 上午
 */
@EnableFeignClients
@EnableDiscoveryClient
@SpringBootApplication
@MapperScan("tk.gushizone.**.mapper")
public class SeataProducerApplication {

    public static void main(String[] args) {
        SpringApplication.run(SeataProducerApplication.class, args);
    }
}
