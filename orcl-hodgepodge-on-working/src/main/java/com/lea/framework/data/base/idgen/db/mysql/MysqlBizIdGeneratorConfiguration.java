package com.lea.framework.data.base.idgen.db.mysql;

import com.lea.framework.data.base.idgen.BizIdGenerator;
import com.lea.framework.data.base.idgen.db.DatabaseBizIdGenerator;
import com.lea.framework.data.base.idgen.db.KeyStoreFactory;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author lea
 * @description
 * @history 2023-04-10 17:55 created by lea
 * @since 2023-04-10 17:55
 */
@Configuration
public class MysqlBizIdGeneratorConfiguration {

    @Bean
    @ConditionalOnProperty(name = "jlc.data.idgen.use-default-id-generator", havingValue = "true", matchIfMissing = true)
    public BizIdGenerator bizIdGenerator() {
        return new DatabaseBizIdGenerator();
    }

    @Bean
    @ConditionalOnProperty(name = "jlc.data.idgen.use-default-id-generator", havingValue = "true", matchIfMissing = true)
    public KeyStoreFactory keyStoreFactory() {
        return new MysqlKeyStoreFactory();
    }

}
