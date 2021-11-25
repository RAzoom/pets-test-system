package razoom.configuration;

import liquibase.integration.spring.SpringLiquibase;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.DependsOn;

import javax.sql.DataSource;

@Configuration
public class LiquibaseConfigurer {

    @Autowired
    private DataSource oltpDataSource;

    @Bean
    @DependsOn
    public SpringLiquibase liquibase() {
        SpringLiquibase liquibase = new SpringLiquibase();
        liquibase.setChangeLog("classpath:db/changeLog.xml");
        liquibase.setDataSource(oltpDataSource);
        return liquibase;
    }
}