package tk.gushizone.distributed.seata.service.impl;

import io.seata.rm.tcc.api.BusinessActionContext;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections4.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tk.gushizone.distributed.seata.domain.Stock;
import tk.gushizone.distributed.seata.service.StockCreateTccService;
import tk.gushizone.distributed.seata.service.StockService;

import java.util.List;
import java.util.Map;

/**
 * @author gushizone
 * @date 2022/12/29 15:26
 */
@Slf4j
@Service
public class StockCreateTccServiceImpl implements StockCreateTccService {

    @Autowired
    private StockService stockService;

    @Override
    public void prepare(BusinessActionContext context, Long itemId) {
        String actionName = context.getActionName();
        String xid = context.getXid();
        long branchId = context.getBranchId();
        log.info("{} 一阶段开始执行, xid={}, branchId={}", actionName, xid, branchId);

        stockService.save(Stock.builder()
                .itemId(itemId)
                .stock(0)
                .build());
    }

    @Override
    public void commit(BusinessActionContext context) {
        String actionName = context.getActionName();
        String xid = context.getXid();
        long branchId = context.getBranchId();
        log.info("{} 二阶段 commit 开始, xid={}, branchId={}", actionName, xid, branchId);

        Map<String, Object> actionContext = context.getActionContext();
        log.info("actionContext: {}",  actionContext);

        Long itemId = context.getActionContext("itemId", Long.class);
        List<Stock> list = stockService.lambdaQuery()
                .eq(Stock::getItemId, itemId)
                .list();
        if (CollectionUtils.isEmpty(list)) {
            throw new RuntimeException("item 不存在: " + itemId);
        }
        Stock stock = list.get(0);
        stock.setStock(100);
        stockService.updateById(stock);
        log.info("{} 二阶段 commit 结束, xid={}, branchId={}", actionName, xid, branchId);
    }

    @Override
    public void rollback(BusinessActionContext context) {
        String actionName = context.getActionName();
        String xid = context.getXid();
        long branchId = context.getBranchId();
        log.info("{} 二阶段 rollback 开始, xid={}, branchId={}", actionName, xid, branchId);

        Map<String, Object> actionContext = context.getActionContext();
        log.info("actionContext: {}",  actionContext);

        Long itemId = context.getActionContext("itemId", Long.class);
        List<Stock> list = stockService.lambdaQuery()
                .eq(Stock::getItemId, itemId)
                .list();
        if (CollectionUtils.isEmpty(list)) {
            throw new RuntimeException("item 不存在: " + itemId);
        }
        Stock stock = list.get(0);
        stock.setStock(-1);
        stockService.updateById(stock);

        log.info("{} 二阶段 rollback 结束, xid={}, branchId={}", actionName, xid, branchId);
    }
}
