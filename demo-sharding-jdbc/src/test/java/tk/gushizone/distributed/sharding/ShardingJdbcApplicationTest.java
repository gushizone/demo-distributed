package tk.gushizone.distributed.sharding;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.google.common.collect.Lists;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import tk.gushizone.distributed.sharding.domain.Order;
import tk.gushizone.distributed.sharding.domain.SysDict;
import tk.gushizone.distributed.sharding.service.OrderService;
import tk.gushizone.distributed.sharding.service.SysDictService;

import java.util.ArrayList;
import java.util.List;

@Slf4j
@RunWith(SpringRunner.class)
@SpringBootTest(classes = ShardingJdbcApplication.class)
public class ShardingJdbcApplicationTest {

    @Autowired
    private OrderService orderService;
    @Autowired
    private SysDictService sysDictService;
    @Autowired

    @Test
    public void clear() {

        orderService.remove(new LambdaQueryWrapper<>());

    }

    @Test
    public void sid() {

    }

    /**
     * 分片保存
     */
    @Test
    public void shardingSave() {

        ArrayList<Order> list = Lists.newArrayList();

        list.add(new Order(1L, 101L, null));
        list.add(new Order(2L, 101L, null));
        list.add(new Order(3L, 102L, null));
        list.add(new Order(4L, 102L, null));

//        list.add(new Order(null, 101L, null));
//        list.add(new Order(null, 101L, null));
//        list.add(new Order(null, 102L, null));
//        list.add(new Order(null, 102L, null));

        orderService.saveBatch(list);
    }

    /**
     * 分片查询
     */
    @Test
    public void shardingQuery() {

        List<Order> list = orderService.lambdaQuery()
                .eq(Order::getId, 1L)
                .eq(Order::getUserId, 101L)
                .list();

        log.info("list: {}", list);
    }

    /**
     * 广播保存
     * 每个库都会录入数据
     */
    @Test
    public void broadcastSave() {

        sysDictService.save(new SysDict(null, "area", "上海", "101"));

    }

    /**
     * 广播查询
     * 使用负载均衡
     */
    @Test
    public void broadcastQuery() {

        for (int i = 0; i <10; i++) {
            List<SysDict> list = sysDictService.list();

            log.info("list: {}", list);
        }

    }


}