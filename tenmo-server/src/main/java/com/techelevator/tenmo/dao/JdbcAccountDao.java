package com.techelevator.tenmo.dao;

import com.techelevator.tenmo.model.Account;
<<<<<<< HEAD
import com.techelevator.tenmo.model.User;
=======
>>>>>>> 55fc7d9f494389ad8d4eeb76dd03a60ed00b1471
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
<<<<<<< HEAD
@Component
public class JdbcAccountDao implements AccountDao {

    private JdbcTemplate jdbcTemplate;

    public JdbcAccountDao(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
=======

@Component
public class JdbcAccountDao implements AccountDao{

    private JdbcTemplate jdbcTemplate;
    private UserDao userDao;

    public JdbcAccountDao(JdbcTemplate jdbcTemplate, UserDao userDao){
        this.jdbcTemplate = jdbcTemplate;
        this.userDao = userDao;
>>>>>>> 55fc7d9f494389ad8d4eeb76dd03a60ed00b1471
    }

    public BigDecimal getBalance(int id) {
        Account account = new Account();
<<<<<<< HEAD
        String url = "SELECT balance FROM account WHERE user_id = ?";
=======
        String url = "SELECT * FROM account WHERE user_id = ?";
>>>>>>> 55fc7d9f494389ad8d4eeb76dd03a60ed00b1471
        SqlRowSet results = jdbcTemplate.queryForRowSet(url, id);
        if (results.next()) {
            account = mapRowToAccount(results);
        }
        return account.getBalance();
    }

<<<<<<< HEAD

=======
>>>>>>> 55fc7d9f494389ad8d4eeb76dd03a60ed00b1471
    private Account mapRowToAccount(SqlRowSet rs) {
        Account account = new Account();
        account.setAccount_id(rs.getLong("account_id"));
        account.setUser_id(rs.getLong("user_id"));
        account.setBalance(rs.getBigDecimal("balance"));
        return account;
    }
<<<<<<< HEAD

=======
>>>>>>> 55fc7d9f494389ad8d4eeb76dd03a60ed00b1471
}
