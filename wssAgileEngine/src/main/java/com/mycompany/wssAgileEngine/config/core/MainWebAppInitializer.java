package com.mycompany.wssAgileEngine.config.core;

import com.mycompany.wssAgileEngine.model.Account;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import net.sf.ehcache.Cache;
import net.sf.ehcache.Element;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.WebApplicationInitializer;

public class MainWebAppInitializer implements WebApplicationInitializer{

//     @Bean
//    public RestTemplate restTemplate() {
//        return new RestTemplate();
//    }
//    
    
    @Autowired
    Cache accountCache; 
    
    @Override
    public void onStartup(ServletContext sc) throws ServletException {
        Account userAccount = new Account(1L, "User Test", 1000.0);
        accountCache.put(new Element(1L, userAccount));
    }
    
}
