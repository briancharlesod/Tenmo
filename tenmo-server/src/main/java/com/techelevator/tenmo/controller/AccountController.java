package com.techelevator.tenmo.controller;

<<<<<<< HEAD



import com.techelevator.tenmo.dao.AccountDao;
import com.techelevator.tenmo.dao.JdbcAccountDao;
import com.techelevator.tenmo.dao.JdbcUserDao;
import com.techelevator.tenmo.dao.UserDao;
=======
import com.techelevator.tenmo.dao.*;
>>>>>>> 366c176875cddd7211f10ac8a2a6fdb68fb9622c
import com.techelevator.tenmo.model.Account;
import com.techelevator.tenmo.model.LoginDTO;
import com.techelevator.tenmo.model.Transfer;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;
import java.security.Principal;
import java.util.List;

@RestController
@PreAuthorize("isAuthenticated()")
public class AccountController {

    private AccountDao accountDao;
    private UserDao userDao;
    private TransferDao transferDao;

<<<<<<< HEAD
    public AccountController(AccountDao accountDao, UserDao userDao) {
=======
    public AccountController(AccountDao accountDao, UserDao userDao, TransferDao transferDao){
>>>>>>> 366c176875cddd7211f10ac8a2a6fdb68fb9622c
        this.accountDao = accountDao;
        this.userDao = userDao;
        this.transferDao = transferDao;
    }

//    public AccountController() {
//        this.userDao = new JdbcUserDao(new JdbcTemplate());
//        this.accountDao = new JdbcAccountDao(new JdbcTemplate(),userDao);
//    }

    @RequestMapping(path = "/accounts", method = RequestMethod.GET)
    public BigDecimal getBalance(Principal principal) {
        return accountDao.getBalance(userDao.findIdByUsername(principal.getName()));
    }
<<<<<<< HEAD
=======

    @RequestMapping(path = "/accounts/transfers", method = RequestMethod.GET)
    public List<Transfer> getTransferHistory(Principal principal) {
        return transferDao.getTransferHistory(userDao.findIdByUsername(principal.getName()));
    }

>>>>>>> 366c176875cddd7211f10ac8a2a6fdb68fb9622c
}


