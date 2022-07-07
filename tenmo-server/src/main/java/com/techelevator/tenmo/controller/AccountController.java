package com.techelevator.tenmo.controller;

import com.techelevator.tenmo.dao.AccountDao;
<<<<<<< HEAD
import com.techelevator.tenmo.dao.JdbcUserDao;
import com.techelevator.tenmo.dao.UserDao;
import com.techelevator.tenmo.model.LoginDTO;
import com.techelevator.tenmo.model.User;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.security.Principal;
import java.util.List;

@RestController
public class AccountController {

    private UserDao userDao ;
    private AccountDao accountDao;

public AccountController(UserDao userDao, AccountDao accountDao) {
    this.userDao = userDao;
    this.accountDao = accountDao;
}


    @RequestMapping(path = "/accounts")
    public double getBalance(Principal principal) {
       userDao.findIdByUsername(principal.getName()){

    }


=======
import com.techelevator.tenmo.dao.JdbcAccountDao;
import com.techelevator.tenmo.dao.JdbcUserDao;
import com.techelevator.tenmo.dao.UserDao;
import com.techelevator.tenmo.model.Account;
import com.techelevator.tenmo.model.LoginDTO;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;
import java.security.Principal;

@RestController
@PreAuthorize("isAuthenticated()")
public class AccountController {

    private AccountDao accountDao;
    private UserDao userDao;

    public AccountController(AccountDao accountDao, UserDao userDao){
        this.accountDao = accountDao;
        this.userDao = userDao;
    }

//    public AccountController() {
//        this.userDao = new JdbcUserDao(new JdbcTemplate());
//        this.accountDao = new JdbcAccountDao(new JdbcTemplate(),userDao);
//    }

    @RequestMapping(path = "/accounts", method = RequestMethod.GET)
    public BigDecimal getBalance(Principal principal) {
        return accountDao.getBalance(userDao.findIdByUsername(principal.getName()));
    }

>>>>>>> 55fc7d9f494389ad8d4eeb76dd03a60ed00b1471
}
