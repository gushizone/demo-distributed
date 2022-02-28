package tk.gushizone.distributed.sharding;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @author gushizone@gmail.com
 * @date 2021/12/1 4:54 下午
 */
@SpringBootApplication
@MapperScan("tk.gushizone.**.mapper")
public class ShardingJdbcApplication {

    public static void main(String[] args) {
        SpringApplication.run(ShardingJdbcApplication.class);
    }
}
