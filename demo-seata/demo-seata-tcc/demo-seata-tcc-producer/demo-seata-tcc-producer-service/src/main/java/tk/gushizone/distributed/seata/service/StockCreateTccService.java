package tk.gushizone.distributed.seata.service;

import io.seata.rm.tcc.api.BusinessActionContext;
import io.seata.rm.tcc.api.BusinessActionContextParameter;
import io.seata.rm.tcc.api.LocalTCC;
import io.seata.rm.tcc.api.TwoPhaseBusinessAction;

/**
 * @author gushizone
 * @date 2022/12/29 15:26
 */
@LocalTCC
public interface StockCreateTccService {

    /**
     * 定义两阶段提交 name = 该tcc的bean名称,全局唯一 commitMethod = commit 为二阶段确认方法 rollbackMethod = rollback 为二阶段取消方法
     * useTCCFence=true 为开启防悬挂
     * BusinessActionContextParameter注解 传递参数到二阶段中
     */
    @TwoPhaseBusinessAction(name = "stackCreateTccService.prepare", commitMethod = "commit", rollbackMethod = "rollback", useTCCFence = true)
    void prepare(BusinessActionContext context, @BusinessActionContextParameter(paramName = "itemId") Long itemId);

    /**
     * 确认方法、可以另命名，但要保证与commitMethod一致 context可以传递try方法的参数
     */
    void commit(BusinessActionContext context);

    /**
     * 二阶段取消方法
     */
    void rollback(BusinessActionContext context);


}
