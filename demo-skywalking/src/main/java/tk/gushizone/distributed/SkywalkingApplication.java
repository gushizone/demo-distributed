package tk.gushizone.distributed;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * <pre>
 * 为应用添加 JVM 启动参数, 需要在 -jar 之前
 * -javaagent:<绝对路径>/agent/skywalking-agent.jar
 * 通过系统属性配置
 * -Dskywalking.agent.service_name=demo-skywalking
 * -Dskywalking.collector.backend_service=127.0.0.1:11800
 *
 * trace 忽略
 * -Dskywalking.trace.ignore_path=Mysql/JDBI/**
 *
 * 采集 sql 参数，限制展示的参数长度
 * -Dskywalking.plugin.jdbc.trace_sql_parameters=true
 * -Dskywalking.plugin.jdbc.sql_parameters_max_length=200
 *
 * 每3秒采样次数，<=0 表示不采样，若不采样在所有数据都会被记录，可能会影响性能。
 * -Dskywalking.agent.sample_n_per_3_secs=1
 * </pre>
 *
 * @author gushizone@gmail.com
 * @date 2021/12/10 5:41 下午
 */
@SpringBootApplication
@MapperScan("tk.gushizone.**.mapper")
public class SkywalkingApplication {

    public static void main(String[] args) {
        SpringApplication.run(SkywalkingApplication.class, args);
    }

}
