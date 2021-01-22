package com.mycompany.wssAgileEngine.services.transaction;

import com.mycompany.wssAgileEngine.model.Transaction;
import com.mycompany.wssAgileEngine.services.generic.GenericServiceImpl;
import org.springframework.stereotype.Service;

/**
 *
 * @author tomas mazzocchi
 */
@Service
public class TransactionServiceImpl extends GenericServiceImpl<Transaction,  Long> implements TransactionService {

}
