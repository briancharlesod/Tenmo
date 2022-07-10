package com.techelevator.tenmo.services;

import com.techelevator.tenmo.model.AuthenticatedUser;
import com.techelevator.tenmo.model.Transfer;
import com.techelevator.tenmo.model.User;
import com.techelevator.util.BasicLogger;
import org.springframework.http.*;
import org.springframework.web.client.ResourceAccessException;
import org.springframework.web.client.RestClientResponseException;
import org.springframework.web.client.RestTemplate;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class TransferService {

    private final String apiBaseUrl;
    private final RestTemplate restTemplate;
    private final AuthenticatedUser authenticatedUser;

    public TransferService(AuthenticatedUser authenticatedUser, String apiBaseUrl, RestTemplate restTemplate) {
        this.apiBaseUrl = apiBaseUrl;
        this.restTemplate = restTemplate;
        this.authenticatedUser = authenticatedUser;
    }

    public Map<Integer, Transfer> viewTransferHistory(HttpEntity<Void> authEntity) {
        Transfer[] transfers = null;
        try {
            ResponseEntity<Transfer[]> response = restTemplate.exchange(apiBaseUrl + "transfers", HttpMethod.GET, authEntity, Transfer[].class);
            transfers = response.getBody();
        } catch (RestClientResponseException | ResourceAccessException e) {
            BasicLogger.log(e.getMessage());
        }
        Map<Integer, Transfer> transferIdMap = new LinkedHashMap<>();
        transferIdMap.put(0, null);
        if (transfers != null) {
            for (Transfer transfer : transfers) {
                transferIdMap.put(transfer.getTransfer_id(), transfer);
            }
            System.out.println("\n");
            for (Integer transferId : transferIdMap.keySet()) {
                try {
                    Transfer transfer = transferIdMap.get(transferId);
                    System.out.println("ID " + transfer.getTransfer_id() + " | FROM: " + transfer.getUsername_from() + " | TO: " + transfer.getUsername_to() + " | $" + transfer.getAmount());
                } catch (Exception e) {
                }
            }
            System.out.println("\n");
            return transferIdMap;
        }
        return null;
    }

    private HttpEntity<Transfer> makeTransferEntity(Transfer transfer) {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.setBearerAuth(authenticatedUser.getToken());
        return new HttpEntity<>(transfer, headers);
    }

    public Transfer makeTransfer(String strId, String strAmount, BigDecimal currentBalance, HttpEntity<Void> authEntity) {
        Transfer transfer = null;
        try {
            int userToId = Integer.parseInt(strId);
            double amount = Double.parseDouble(strAmount);
            int accountToId = userToAccountId(userToId, authEntity);
            double compareAmount = currentBalance.doubleValue();

            if (amount > compareAmount) {
                throw new NumberFormatException();
            }
            if (amount <= 0) {
                throw new NumberFormatException();
            }
            int userFromId = authenticatedUser.getUser().getId().intValue();
            int accountFromId = userToAccountId(userFromId, authEntity);
            transfer = new Transfer();
            transfer.setAccount_from(accountFromId);
            transfer.setAccount_to(accountToId);
            transfer.setAmount(amount);
            transfer.setTransfer_status_id(2);
            transfer.setTransfer_type_id(2);
        } catch (NumberFormatException e) {
            System.out.println("Encountered an error.");
        }
        return transfer;
    }

    public Transfer addTransfer(Transfer transfer) {
        if (transfer.getTransfer_status_id() != 2) {
            return null;
        }
        Transfer newTransfer = null;
        try {
            newTransfer = restTemplate.postForObject(apiBaseUrl + "transfers",
                    makeTransferEntity(transfer), Transfer.class);
            System.out.println("Transaction Complete");
        } catch (RestClientResponseException | ResourceAccessException e) {
            BasicLogger.log(e.getMessage());
        }
        return newTransfer;
    }

    public int userToAccountId(int userId, HttpEntity<Void> authEntity) {
        int accountId = 0;
        try {
            ResponseEntity<Integer> response =
                    restTemplate.exchange(apiBaseUrl + "accounts/" + userId,
                            HttpMethod.GET, authEntity, Integer.class);
            accountId = response.getBody();
        } catch (RestClientResponseException | ResourceAccessException e) {
            BasicLogger.log(e.getMessage());
        }
        return accountId;
    }

    public void listUsers(HttpEntity<Void> authEntity) {
        User[] users = null;
        try {
            ResponseEntity<User[]> response =
                    restTemplate.exchange(apiBaseUrl + "users",
                            HttpMethod.GET, authEntity, User[].class);
            users = response.getBody();
        } catch (RestClientResponseException | ResourceAccessException e) {
            BasicLogger.log(e.getMessage());
        }
        if (users != null) {
            for (User user : users) {
                if (authenticatedUser.getUser().getUsername().equals(user.getUsername())) {
                } else {
                    System.out.println(user);
                }
            }
        }
    }

}







