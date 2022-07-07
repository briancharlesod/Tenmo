package com.techelevator.tenmo.controller;

import com.techelevator.tenmo.dao.AccountDao;
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


}
