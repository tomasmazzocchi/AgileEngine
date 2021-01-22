package com.mycompany.wssAgileEngine.controllers;

import com.mycompany.wssAgileEngine.dtos.TransactionDTO;
import com.mycompany.wssAgileEngine.requestBodies.TransactionRequestBody;
import com.mycompany.wssAgileEngine.services.transaction.TransactionService;
import static com.mycompany.wssAgileEngine.enumaration.TypeTransactionEnum.CREDIT;
import com.mycompany.wssAgileEngine.model.Account;
import com.mycompany.wssAgileEngine.model.Transaction;
import com.mycompany.wssAgileEngine.services.account.AccountService;
import com.mycompany.wssAgileEngine.utils.CalendarUtilidades;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author tomas mazzocchi
 */
@RestController
@RequestMapping(value = "transaction")

public class TransactionController {

    @Autowired
    TransactionService transactionService;
    @Autowired
    AccountService accountService;

    @RequestMapping(value = "", method = RequestMethod.GET)
    public ResponseEntity<?> getTransactions() {
        List<TransactionDTO> transactionDTOList = new ArrayList<>();

        return ResponseEntity.ok(transactionDTOList);
    }

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public ResponseEntity<?> getTransactionById(@RequestParam(value = "idTransaction") String idTransaction) {
        TransactionDTO transactionDTO = new TransactionDTO();

        return ResponseEntity.ok(transactionDTO);
    }

    @RequestMapping(value = "", method = RequestMethod.POST)
    public ResponseEntity<?> postTransaction(@RequestBody TransactionRequestBody body) {
        TransactionDTO transactionDTO = null;
        try {
            if (body.getType().equals(CREDIT.label)) {
                transactionDTO = this.manageTransaction(Long.parseLong(body.getAccountId()), body.getAmount(), body.getType());
            } else {
                Account account = accountService.obtenerPorId(Long.parseLong(body.getAccountId()));
                if(!account.hasEnoughBalance(body.getAmount())) return ResponseEntity.status(HttpStatus.NOT_ACCEPTABLE).header("msg", "Insufficient balance.").body(null);
                transactionDTO = this.manageTransaction(Long.parseLong(body.getAccountId()), body.getAmount(), body.getType());
            }
        } catch (Exception ex) {
            Logger.getLogger(TransactionController.class.getName()).log(Level.SEVERE, null, ex);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).header("msg", "There was a problem :( ... please try again.").body(null);
        }

        return ResponseEntity.status(HttpStatus.OK).header("msg", "Treansaction stored.").body(transactionDTO);
    }

    private TransactionDTO manageTransaction(Long accountId, Float amount, String type) throws Exception {
        Account account = accountService.obtenerPorId(accountId);
        if(type.equals(CREDIT.label)) {
            account.creditAmount(amount);
        } else {
            account.deditAmount(amount);
        }
        account = accountService.agregarOActualizar(account);
        Transaction transaction = new Transaction(type, amount, CalendarUtilidades.obtenerFechaDelDia());
        transactionService.agregarOActualizar(transaction);

        return new TransactionDTO(transaction.getId(), transaction.getType(), account.getTotalAmount(), transaction.getEffectiveDate());
    }
}
