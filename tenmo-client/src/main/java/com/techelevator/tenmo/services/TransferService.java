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
import java.util.List;

public class TransferService {


    private static final String API_BASE_URL = "http://localhost:8080/";
    private final RestTemplate restTemplate = new RestTemplate();
    private final AuthenticatedUser authenticatedUser = new AuthenticatedUser();
    private String authToken = null;

    public void setAuthToken(String authToken) {
        this.authToken = authToken;
    }







}





//    public Transfer addTransfer(Transfer newTransfer) {
//        Transfer returnedTransfer = null;
//        try {
//            returnedTransfer = restTemplate.postForObject(API_BASE_URL + "transfers",
//                    makeTransferEntity(newTransfer), Transfer.class);
//        } catch (RestClientResponseException | ResourceAccessException e) {
//            BasicLogger.log(e.getMessage());
//        }
//        return returnedTransfer;
//    }
//
//    private HttpEntity<Transfer> makeTransferEntity(Transfer transfer) {
//        HttpHeaders headers = new HttpHeaders();
//        headers.setContentType(MediaType.APPLICATION_JSON);
//        headers.setBearerAuth(authToken);
//        return new HttpEntity<>(transfer, headers);
//    }



//
//    public User[] getUsers() {
//        String url = API_BASE_URL + "users";
//        User[] list = null;
//
//        try {
//            ResponseEntity<User[]> response = restTemplate.exchange(API_BASE_URL + "users", HttpMethod.GET, makeAuthEntity(), User[].class);
//            list = response.getBody();
//        } catch (RestClientResponseException | ResourceAccessException e) {
//            BasicLogger.log(e.getMessage());
//        }
//        System.out.println(list);
//        return list;
//
//    }
//    }

