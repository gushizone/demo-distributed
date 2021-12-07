package tk.gushizone.distributed.config;

import com.atomikos.icatch.jta.UserTransactionImp;
import com.atomikos.icatch.jta.UserTransactionManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.transaction.jta.JtaTransactionManager;

/**
 * @author gushizone@gmail.com
 * @date 2021/12/7 4:57 下午
 */
@Configuration
public class JtaConfig {

    @Bean
    public JtaTransactionManager jtaTransactionManager() {
        UserTransactionImp userTransaction = new UserTransactionImp();
        UserTransactionManager userTransactionManager = new UserTransactionManager();
        return new JtaTransactionManager(userTransaction, userTransactionManager);
    }

}
