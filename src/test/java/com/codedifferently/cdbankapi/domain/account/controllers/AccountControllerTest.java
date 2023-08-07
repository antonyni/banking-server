package com.codedifferently.cdbankapi.domain.account.controllers;

import com.codedifferently.cdbankapi.domain.account.exceptions.AccountException;
import com.codedifferently.cdbankapi.domain.account.models.Account;
import com.codedifferently.cdbankapi.domain.account.services.AccountService;
import com.codedifferently.cdbankapi.domain.deposit.models.Deposit;
import com.codedifferently.cdbankapi.domain.enums.Type;
import com.codedifferently.cdbankapi.domain.withdrawal.models.Withdrawal;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.BDDMockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

import java.util.ArrayList;
import java.util.List;

import static com.codedifferently.cdbankapi.domain.account.BaseControllerTest.asJsonString;
import static org.hamcrest.core.Is.is;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(AccountController.class)
@ExtendWith(SpringExtension.class)
public class AccountControllerTest {

    @MockBean
    private AccountService mockAccountService;

    @Autowired
    private MockMvc mockMvc;

    private Account inputAccount;
    private Account mockResponseAccount1Entity ;
    private Account mockResponseAccount2Entity;

    private List<Deposit> depositList;

    private List<Withdrawal> withdrawalList;

    @BeforeEach
    public void setUp(){
        depositList = new ArrayList<>();
        withdrawalList = new ArrayList<>();

        inputAccount = new Account(Type.CHECKINGS, "Checking Account",300L, "Jane Doe", "password", depositList, withdrawalList);

        mockResponseAccount1Entity = new Account(Type.SAVINGS, "Savings Account", 300L, "John Doe", "password", depositList, withdrawalList);
        mockResponseAccount1Entity.setId(1L);

        mockResponseAccount2Entity = new Account(Type.CHECKINGS, "Checking Account",300L, "Jane Doe", "password", depositList, withdrawalList);
        mockResponseAccount2Entity.setId(2L);
    }

    @Test
    @DisplayName("Account Post: /api/v1/AccountRequests - success")
    public void createAccountRequestSuccess() throws Exception {
        BDDMockito.doReturn(mockResponseAccount1Entity).when(mockAccountService).create(any());

        mockMvc.perform(post("/api/v1/AccountRequests")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(asJsonString(inputAccount)))

                .andExpect(status().isCreated())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.id", is(1)));
    }

    @Test
    @DisplayName("GET /api/v1/AccountRequests - Found")
    public void getAllAccountsTestSuccess() throws Exception{
        List<Account> accountEntities = new ArrayList<>();
        accountEntities.add(mockResponseAccount1Entity);
        accountEntities.add(mockResponseAccount2Entity);

        BDDMockito.doReturn(accountEntities).when(mockAccountService).getAllAccounts();

        mockMvc.perform(get("/api/v1/AccountRequests"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$[0].id", is(1)))
                .andExpect(jsonPath("$[1].id", is(2)));
    }

    @Test
    @DisplayName("GET /api/v1/AccountRequests/1 - Found")
    public void getAccountByIdTestSuccess() throws Exception{
        BDDMockito.doReturn(mockResponseAccount1Entity).when(mockAccountService).getAccountById(anyLong());

        mockMvc.perform(get("/api/v1/AccountRequests/{id}", 1L))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.id", is(1)));
    }

    @Test
    @DisplayName("GET /api/v1/AccountRequests/1 - Not Found")
    public void getAccountByIdTestFail() throws Exception {
        BDDMockito.doThrow(new AccountException("Not Found")).when(mockAccountService).getAccountById(anyLong());
        mockMvc.perform(get("/api/v1/AccountRequests/{id}", 1L))
                .andExpect(status().isNotFound());
    }

    @Test
    @DisplayName("PUT /api/v1/AccountRequests/1 - Success")
    public void updateAccountTestSuccess() throws Exception{
        Account expectedAccountUpdateEntity = new Account(/* set properties here */);
        expectedAccountUpdateEntity.setId(1L);

        BDDMockito.doReturn(expectedAccountUpdateEntity).when(mockAccountService).updateAccount(anyLong(), any());

        mockMvc.perform(put("/api/v1/AccountRequests/{id}", 1L)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(asJsonString(inputAccount)))

                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.id", is(1)));
    }

    @Test
    @DisplayName("PUT /api/v1/AccountRequests/1 - Not Found")
    public void updateAccountTestNotFound() throws Exception{
        BDDMockito.doThrow(new AccountException("Not Found")).when(mockAccountService).updateAccount(anyLong(), any());
        mockMvc.perform(put("/api/v1/AccountRequests/{id}", 1L)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(asJsonString(inputAccount)))
                .andExpect(status().isNotFound());
    }

    @Test
    @DisplayName("DELETE /api/v1/AccountRequests/1 - Success")
    public void deleteAccountTestSuccess() throws Exception{
        BDDMockito.doNothing().when(mockAccountService).deleteUser(anyLong());
        mockMvc.perform(delete("/api/v1/AccountRequests/{id}", 1L))
                .andExpect(status().isNoContent());
    }

    @Test
    @DisplayName("DELETE /api/v1/AccountRequests/1 - Not Found")
    public void deleteAccountTestNotFound() throws Exception{
        BDDMockito.doThrow(new AccountException("Not Found")).when(mockAccountService).deleteUser(anyLong());
        mockMvc.perform(delete("/api/v1/AccountRequests/{id}", 1L))
                .andExpect(status().isNotFound());
    }
}
