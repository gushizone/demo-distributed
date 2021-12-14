package tk.gushizone.distributed.actuate;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.actuate.health.AbstractHealthIndicator;
import org.springframework.boot.actuate.health.Health;
import org.springframework.stereotype.Component;

import java.util.Date;

/**
 * 自定义健康检查指示器
 * 参考 DataSourceHealthIndicator
 *
 * <pre>
 * {
 *     "client": {
 *         "status": "UP",
 *         "details": {
 *             "timestamp": "2021-12-14T08:17:55.810+0000"
 *         }
 *     }
 * }
 * </pre>
 *
 * @author gushizone@gmail.com
 * @date 2021/12/14 4:10 下午
 */
@Slf4j
@Component
public class ClientHealthIndicator extends AbstractHealthIndicator {

    @Override
    protected void doHealthCheck(Health.Builder builder) throws Exception {
        try {
            builder.up().withDetail("timestamp", new Date());
        } catch (Exception e) {
            builder.down();
            throw e;
        }
    }
}
