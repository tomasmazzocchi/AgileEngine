package com.mycompany.wssAgileEngine.controllers;

import com.mycompany.wssAgileEngine.dtos.AccountDTO;
import com.mycompany.wssAgileEngine.model.Account;
import net.sf.ehcache.Cache;
import net.sf.ehcache.Element;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author tomas mazzocchi
 */
@RestController
@RequestMapping(value = "account")

public class AccountController {

    @Autowired
    Cache accountCache; 

    @RequestMapping(value = "", method = RequestMethod.GET)
    public ResponseEntity<?> getAccount() {
        
        Account userAccount = new Account(1L, "User Test", 1000.0);
        accountCache.put(new Element(1L, userAccount));
        return ResponseEntity.ok(new AccountDTO(1L, "User Test", 1000.0));
        
    }

}
