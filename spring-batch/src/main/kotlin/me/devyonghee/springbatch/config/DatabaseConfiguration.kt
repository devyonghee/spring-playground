package me.devyonghee.springbatch.config

import org.apache.ibatis.session.SqlSessionFactory
import org.mybatis.spring.SqlSessionFactoryBean
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.data.jpa.repository.config.EnableJpaAuditing
import javax.sql.DataSource


@Configuration
@EnableJpaAuditing
class DatabaseConfiguration {

    @Bean
    fun sqlSessionFactory(dataSource: DataSource): SqlSessionFactory? {
        return SqlSessionFactoryBean().apply {
            setDataSource(dataSource)
        }.`object`
    }
}