package com.techelevator.tenmo.controller;





import com.techelevator.tenmo.dao.AccountDao;
import com.techelevator.tenmo.dao.JdbcAccountDao;
import com.techelevator.tenmo.dao.JdbcUserDao;
import com.techelevator.tenmo.dao.UserDao;

import com.techelevator.tenmo.dao.*;

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




    public AccountController(AccountDao accountDao, UserDao userDao){

        this.accountDao = accountDao;
        this.userDao = userDao;

    }



    @RequestMapping(path = "/accounts", method = RequestMethod.GET)
    public BigDecimal getBalance(Principal principal) {
        return accountDao.getBalance(userDao.findIdByUsername(principal.getName()));
    }





}


