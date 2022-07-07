package com.techelevator.tenmo.dao;

import com.techelevator.tenmo.model.Account;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;

@Component
public class JdbcAccountDao implements AccountDao{

    private JdbcTemplate jdbcTemplate;
    private UserDao userDao;

    public JdbcAccountDao(JdbcTemplate jdbcTemplate, UserDao userDao){
        this.jdbcTemplate = jdbcTemplate;
        this.userDao = userDao;
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
