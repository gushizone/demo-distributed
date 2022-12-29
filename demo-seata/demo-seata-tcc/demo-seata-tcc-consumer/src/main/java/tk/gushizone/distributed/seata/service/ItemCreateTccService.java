package tk.gushizone.distributed.seata.service;

import io.seata.rm.tcc.api.BusinessActionContext;
import io.seata.rm.tcc.api.BusinessActionContextParameter;
import io.seata.rm.tcc.api.LocalTCC;
import io.seata.rm.tcc.api.TwoPhaseBusinessAction;
import tk.gushizone.distributed.seata.domain.Item;

/**
 * @author gushizone@gmail.com
 * @date 2022/3/1 3:30 下午
 */
@LocalTCC
public interface ItemCreateTccService {


    /**
     * 定义两阶段提交 name = 该tcc的bean名称,全局唯一 commitMethod = commit 为二阶段确认方法 rollbackMethod = rollback 为二阶段取消方法
     * useTCCFence=true 为开启防悬挂
     * BusinessActionContextParameter注解 传递参数到二阶段中
     */
    @TwoPhaseBusinessAction(name = "itemCreateTccService.prepare", commitMethod = "commit", rollbackMethod = "rollback", useTCCFence = true)
    void prepare(BusinessActionContext context, @BusinessActionContextParameter(paramName = "item") Item item);

    /**
     * 确认方法、可以另命名，但要保证与commitMethod一致 context可以传递try方法的参数
     */
    void commit(BusinessActionContext context);

    /**
     * 二阶段取消方法
     */
    void rollback(BusinessActionContext context);


}
