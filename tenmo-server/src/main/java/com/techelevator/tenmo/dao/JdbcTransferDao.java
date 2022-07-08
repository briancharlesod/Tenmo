package com.techelevator.tenmo.dao;

import com.techelevator.tenmo.model.Transfer;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class JdbcTransferDao {

    private JdbcTemplate jdbcTemplate;


    public List<Transfer> getTransferHistory(int id) {
        List<Transfer> transferHistory = new ArrayList<>();
        String url = "SELECT * FROM transfer JOIN account ON transfer.account_from = account.account_id WHERE account_from = ?";
        SqlRowSet results = jdbcTemplate.queryForRowSet(url, id);
        while (results.next()) {
            transferHistory.add(mapRowToTransfer(results));
        }
        url = "SELECT * FROM transfer JOIN account ON transfer.account_to = account.account_id WHERE account_to = ?";
        results = jdbcTemplate.queryForRowSet(url, id);
        while (results.next()) {
            transferHistory.add(mapRowToTransfer(results));
        }
        return transferHistory;
    }



    public boolean createSend(Transfer transfer) {
        String sql = "INSERT INTO transfer(transfer_id, transfer_type_id, transfer_status_id, account_from, account_to, ammount) " +
                "VALUES(DEFAULT, ?, ?, ?, ?, ?)";
        jdbcTemplate.update(sql, transfer.getTransfer_type_id(), transfer.getTransfer_status_id(), transfer.getAccount_from(), transfer.getAccount_to(), transfer.getAmount());

        sql = "UPDATE account SET balance = balance + ? WHERE account_id = ?";
        jdbcTemplate.update(sql, transfer.getAmount(), transfer.getAccount_to());

        sql = "UPDATE account SET balance = balance - ? WHERE account_id = ?";
        jdbcTemplate.update(sql, transfer.getAmount(), transfer.getAccount_from());
        return true;
    }



    private Transfer mapRowToTransfer(SqlRowSet rs) {
        Transfer transfer = new Transfer();
        transfer.setTransfer_id(rs.getInt("transfer_id"));
        transfer.setTransfer_type_id(rs.getInt("transfer_type_id"));
        transfer.setTransfer_status_id(rs.getInt("transfer_status_id"));
        transfer.setAccount_from(rs.getInt("account_from"));
        transfer.setAccount_to(rs.getInt("account_to"));
        transfer.setAmount(rs.getDouble("amount"));
        return transfer;
    }
}
