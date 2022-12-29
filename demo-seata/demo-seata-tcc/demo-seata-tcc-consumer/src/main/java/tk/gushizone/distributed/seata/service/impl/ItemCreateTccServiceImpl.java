package tk.gushizone.distributed.seata.service.impl;

import cn.hutool.json.JSONUtil;
import io.seata.rm.tcc.api.BusinessActionContext;
import io.seata.rm.tcc.api.BusinessActionContextUtil;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections4.CollectionUtils;
import org.redisson.api.RedissonClient;
import org.springframework.stereotype.Service;
import tk.gushizone.distributed.seata.domain.Item;
import tk.gushizone.distributed.seata.service.ItemCreateTccService;
import tk.gushizone.distributed.seata.service.ItemService;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

/**
 * @author gushizone@gmail.com
 * @date 2022/3/1 3:30 下午
 */
@Slf4j
@Service
public class ItemCreateTccServiceImpl implements ItemCreateTccService {

    @Resource
    private ItemService itemService;
    @Resource
    private RedissonClient redisson;

    @Override
    public void prepare(BusinessActionContext context, Item item) {
        String actionName = context.getActionName();
        String xid = context.getXid();
        long branchId = context.getBranchId();
        log.info("{} 一阶段开始执行, xid={}, branchId={}", actionName, xid, branchId);

        // todo 锁
        List<Item> list = itemService.lambdaQuery()
                .eq(Item::getName, item.getName())
                .list();
        if (CollectionUtils.isNotEmpty(list)) {
            throw new RuntimeException("item 已存在: " + item.getName());
        }
        item.setRemark("[准备中]");
        itemService.save(item);

        // 上下文内容新增
        BusinessActionContextUtil.addContext("itemId", item.getId());
    }

    @Override
    public void commit(BusinessActionContext context) {
        String actionName = context.getActionName();
        String xid = context.getXid();
        long branchId = context.getBranchId();
        log.info("{} 二阶段 commit 开始执行, xid={}, branchId={}", actionName, xid, branchId);

        // 默认 value 是 json String
        Map<String, Object> actionContext = context.getActionContext();
        log.info("actionContext: {}", actionContext);

        // 参数不会有 id
        Item itemParam = JSONUtil.toBean(JSONUtil.toJsonStr(actionContext.get("item")), Item.class);
        log.info("itemParam: {}", itemParam);

        Long itemId = context.getActionContext("itemId", Long.class);
        Item item = itemService.lambdaQuery()
                .eq(Item::getId, itemId)
                .one();

        item.setRemark("[已备货]");
        itemService.updateById(item);
    }

    @Override
    public void rollback(BusinessActionContext context) {
        String actionName = context.getActionName();
        String xid = context.getXid();
        long branchId = context.getBranchId();
        log.info("{} 二阶段 rollback 开始执行, xid={}, branchId={}", actionName, xid, branchId);

        Long itemId = context.getActionContext("itemId", Long.class);
        Item item = itemService.lambdaQuery()
                .eq(Item::getId, itemId)
                .one();
        item.setRemark("[备货失败]");
        itemService.updateById(item);
    }
}
