package tk.gushizone.distributed.sharding.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import tk.gushizone.distributed.sharding.domain.Order;
import tk.gushizone.distributed.sharding.mapper.OrderMapper;
import tk.gushizone.distributed.sharding.service.OrderService;

/**
 *
 */
@Service
public class OrderServiceImpl extends ServiceImpl<OrderMapper, Order> implements OrderService {

}




