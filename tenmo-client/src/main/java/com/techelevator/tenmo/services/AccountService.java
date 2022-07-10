package com.techelevator.tenmo.services;

import com.techelevator.tenmo.model.AuthenticatedUser;
import com.techelevator.util.BasicLogger;
import org.springframework.http.*;
import org.springframework.web.client.ResourceAccessException;
import org.springframework.web.client.RestClientResponseException;
import org.springframework.web.client.RestTemplate;

import java.math.BigDecimal;

public class AccountService {

    private final String apiBaseUrl;
    private final RestTemplate restTemplate;
    private final AuthenticatedUser authenticatedUser;

    public AccountService(AuthenticatedUser authenticatedUser, String apiBaseUrl, RestTemplate restTemplate) {
        this.apiBaseUrl = apiBaseUrl;
        this.restTemplate = restTemplate;
        this.authenticatedUser = authenticatedUser;
    }

    public BigDecimal viewBalance(HttpEntity<Void> authEntity){
        BigDecimal balance = null;
        try {
            ResponseEntity<BigDecimal> response =
                    restTemplate.exchange(apiBaseUrl + "accounts",
                            HttpMethod.GET, authEntity, BigDecimal.class);
            balance = response.getBody();
        } catch (RestClientResponseException | ResourceAccessException e) {
            BasicLogger.log(e.getMessage());
        }
        return balance;
    }

}
