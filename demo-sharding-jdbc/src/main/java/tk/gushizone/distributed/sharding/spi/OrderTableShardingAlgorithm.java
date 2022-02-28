package tk.gushizone.distributed.sharding.spi;

import lombok.extern.slf4j.Slf4j;
import org.apache.shardingsphere.sharding.algorithm.sharding.inline.InlineShardingAlgorithm;
import org.apache.shardingsphere.sharding.api.sharding.standard.PreciseShardingValue;
import org.apache.shardingsphere.sharding.api.sharding.standard.RangeShardingValue;
import org.apache.shardingsphere.sharding.api.sharding.standard.StandardShardingAlgorithm;

import java.util.Collection;
import java.util.Properties;

/**
 * 自定义分片算法 SPI
 * 可参考 {@link InlineShardingAlgorithm}
 *
 * @author gushizone@gmail.com
 * @date 2021/12/6 3:12 下午
 */
@Slf4j
public class OrderTableShardingAlgorithm implements StandardShardingAlgorithm<Long> {

    private Properties props = new Properties();

    /**
     * 精确分片
     *
     * @param availableTargetNames e.g. [order_1, order_2]
     * @param shardingValue        e.g. PreciseShardingValue(logicTableName=order, columnName=id, value=1)
     * @return e.g. order_1
     */
    @Override
    public String doSharding(Collection<String> availableTargetNames, PreciseShardingValue<Long> shardingValue) {

        Long id = shardingValue.getValue();

        String[] tables = availableTargetNames.toArray(new String[0]);

        int mode = (int) ((id + 1L) % tables.length);
        return tables[mode];
    }

    /**
     * 范围分片
     */
    @Override
    public Collection<String> doSharding(Collection<String> availableTargetNames, RangeShardingValue<Long> shardingValue) {

        System.out.println("===2");
        return null;
    }

    @Override
    public void init() {
        log.info("初始化order表分片算法");
    }

    /**
     * 分片key
     */
    @Override
    public String getType() {
        return "CUSTOM_ORDER_TABLE_SHARDING";
    }

    @Override
    public Properties getProps() {
        return props;
    }

    @Override
    public void setProps(Properties props) {
        this.props = props;
    }
}
