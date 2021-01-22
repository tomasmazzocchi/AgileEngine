package com.mycompany.wssAgileEngine.config;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.mycompany.wssAgileEngine.client.RestTemplateRequest;
import com.mycompany.wssAgileEngine.dao.generic.AccountDao;
import com.mycompany.wssAgileEngine.dao.generic.GenericDao;
import com.mycompany.wssAgileEngine.dao.generic.TransactionDao;
import com.mycompany.wssAgileEngine.model.Account;
import com.mycompany.wssAgileEngine.services.account.AccountService;
import com.mycompany.wssAgileEngine.services.generic.GenericService;
import com.mycompany.wssAgileEngine.services.generic.GenericServiceImpl;
import com.mycompany.wssAgileEngine.services.transaction.TransactionService;
import java.nio.charset.StandardCharsets;
import javax.naming.NamingException;
import javax.sql.DataSource;
import net.sf.ehcache.Cache;
import net.sf.ehcache.CacheManager;
import net.sf.ehcache.Element;
import net.sf.ehcache.config.CacheConfiguration;
import net.sf.ehcache.config.PersistenceConfiguration;
import net.sf.ehcache.config.PersistenceConfiguration.Strategy;
import net.sf.ehcache.store.MemoryStoreEvictionPolicy;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.http.converter.StringHttpMessageConverter;
import org.springframework.http.converter.json.Jackson2ObjectMapperBuilder;
import org.springframework.jdbc.datasource.lookup.JndiDataSourceLookup;
import org.springframework.orm.hibernate4.HibernateTransactionManager;
import org.springframework.orm.hibernate4.LocalSessionFactoryBuilder;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.web.client.RestTemplate;

@Configuration
@ComponentScan(basePackages = {"com.mycompany.wssAgileEngine"})
@EnableTransactionManagement
@EnableAspectJAutoProxy(proxyTargetClass = true)
@EnableScheduling
public class ContextConfiguration {

    private static final String DATASOURCE = "jdbc/pool/agileEnginePool";
    private static final String TRANSACTION_CACHE = "transactionCache";
    private static final String ACCOUNT_CACHE = "accountCache";
    private static final String PROP_SHOW_SQL = "hibernate.show_sql";
    private static final String PROP_SECOND_CACHE = "hibernate.cache.use_second_level_cache";
    private static final String PROP_CACHE_PROVIDER = "hibernate.cache.provider_class";
    private static final String CACHE_PROVIDER = "org.hibernate.cache.EhCacheProvider";

    public ContextConfiguration() {
        super();
    }

    @Bean
    public CacheManager getCacheManager() {
        CacheManager manager = CacheManager.create();
        Cache transactionCache = new Cache(
                new CacheConfiguration(TRANSACTION_CACHE, 1000)
                        .memoryStoreEvictionPolicy(MemoryStoreEvictionPolicy.LFU)
                        .eternal(false)
                        .timeToLiveSeconds(60)
                        .timeToIdleSeconds(30)
                        .diskExpiryThreadIntervalSeconds(0)
                        .persistence(new PersistenceConfiguration().strategy(Strategy.LOCALTEMPSWAP)));
        Cache accountCache = new Cache(
                new CacheConfiguration(ACCOUNT_CACHE, 1000)
                        .memoryStoreEvictionPolicy(MemoryStoreEvictionPolicy.LFU)
                        .eternal(false)
                        .timeToLiveSeconds(60)
                        .timeToIdleSeconds(30)
                        .diskExpiryThreadIntervalSeconds(0)
                        .persistence(new PersistenceConfiguration().strategy(Strategy.LOCALTEMPSWAP)));
        if (manager.getCache(TRANSACTION_CACHE) == null) {
            manager.addCache(transactionCache);
        }
        if (manager.getCache(ACCOUNT_CACHE) == null) {
            manager.addCache(accountCache);
        }
        return manager;
    }

    @Bean(name = "accountCache")
    public Cache getAccountCache(CacheManager cacheManager) {
        return cacheManager.getCache(ACCOUNT_CACHE);
    }

    @Bean(name = "transactionCache")
    public Cache getTransactionCache(CacheManager cacheManager) {
        return cacheManager.getCache(TRANSACTION_CACHE);
    }

    /*@Bean
    public DataSource getDataSource() throws NamingException {
        JndiDataSourceLookup dsLookup = new JndiDataSourceLookup();
        DataSource dataSource = dsLookup.getDataSource(DATASOURCE);
        return dataSource;
    }

    @Bean(name = "sessionFactory")
    public SessionFactory getSessionFactory(DataSource dataSource) {

        LocalSessionFactoryBuilder sessionBuilder = new LocalSessionFactoryBuilder(dataSource);

        sessionBuilder.setProperty(PROP_SHOW_SQL, Boolean.TRUE.toString());
        sessionBuilder.setProperty(PROP_SECOND_CACHE, Boolean.FALSE.toString());
        sessionBuilder.setProperty(PROP_CACHE_PROVIDER, CACHE_PROVIDER);

        return sessionBuilder.buildSessionFactory();
    }

    @Bean
    public HibernateTransactionManager getTransactionManager(SessionFactory sessionFactory) {
        HibernateTransactionManager transactionManager = new HibernateTransactionManager(sessionFactory);
        return transactionManager;
    }

    @Bean
    public Jackson2ObjectMapperBuilder objectMapperBuilder() {
        Jackson2ObjectMapperBuilder builder = new Jackson2ObjectMapperBuilder();
        builder.serializationInclusion(JsonInclude.Include.NON_NULL);
        builder.failOnUnknownProperties(false);
        return builder;
    }

    @Autowired
    public void accountService(AccountService accountService, AccountDao accountDao) {
        initializeService(accountService, accountDao);
    }

    @Autowired
    public void transactionService(TransactionService transactionService, TransactionDao transactioDao) {
        initializeService(transactionService, transactioDao);
    }

    private GenericService initializeService(GenericService service, GenericDao genericDao) {
        ((GenericServiceImpl) service).setGenericDao(genericDao);
        return service;
    }*/

    @Autowired
    public void restTemplateRequest(RestTemplateRequest restTemplateRequest) {
        RestTemplate restTemplate = restTemplateRequest;
        restTemplate.getMessageConverters().add(0, new StringHttpMessageConverter(StandardCharsets.UTF_8));
    }
}
