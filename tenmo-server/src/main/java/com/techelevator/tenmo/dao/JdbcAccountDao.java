package com.techelevator.tenmo.dao;

import com.techelevator.tenmo.model.Account;
<<<<<<< HEAD

import com.techelevator.tenmo.model.User;

=======
import com.techelevator.tenmo.model.Transfer;
>>>>>>> 366c176875cddd7211f10ac8a2a6fdb68fb9622c
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;


@Component
public class JdbcAccountDao implements AccountDao{

    private JdbcTemplate jdbcTemplate;

<<<<<<< HEAD
    public JdbcAccountDao(JdbcTemplate jdbcTemplate, UserDao userDao) {
=======
    public JdbcAccountDao(JdbcTemplate jdbcTemplate){
>>>>>>> 366c176875cddd7211f10ac8a2a6fdb68fb9622c
        this.jdbcTemplate = jdbcTemplate;
    }


    public BigDecimal getBalance(int id) {
        Account account = new Account();



        String url = "SELECT * FROM account WHERE user_id = ?";

        SqlRowSet results = jdbcTemplate.queryForRowSet(url, id);
        if (results.next()) {
            account = mapRowToAccount(results);
        }
        return account.getBalance();
    }


    private Account mapRowToAccount(SqlRowSet rs) {
        Account account = new Account();
        account.setAccount_id(rs.getLong("account_id"));
        account.setUser_id(rs.getLong("user_id"));
        account.setBalance(rs.getBigDecimal("balance"));
        return account;
    }

}
