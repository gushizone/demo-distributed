package tk.gushizone.distributed;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @author gushizone@gmail.com
 * @date 2021/12/15 3:16 下午
 */
@SpringBootApplication
@MapperScan("tk.gushizone.**.mapper")
public class PrometheusApplication {

    public static void main(String[] args) {
        SpringApplication.run(PrometheusApplication.class, args);
    }
}
