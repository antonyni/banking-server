package com.codedifferently.cdbankapi.domain.account.services;

import com.codedifferently.cdbankapi.domain.account.exceptions.AccountException;
import com.codedifferently.cdbankapi.domain.account.models.Account;
import com.codedifferently.cdbankapi.domain.account.repos.AccountRepo;
import com.codedifferently.cdbankapi.domain.enums.Type;
import com.codedifferently.cdbankapi.domain.deposit.models.Deposit;
import com.codedifferently.cdbankapi.domain.withdrawal.models.Withdrawal;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentMatchers;
import org.mockito.BDDMockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@SpringBootTest
@ExtendWith(SpringExtension.class)
public class AccountServiceImplTest {

    @MockBean
    private AccountRepo mockAccountRepo;

    @Autowired
    private AccountService accountService;

    private Account inputAccount;
    private Account mockResponseAccountEntity;

    private Account mockProcess;

    private List<Deposit> depositList;

    private List<Withdrawal> withdrawalList;

    @BeforeEach
    public void setUp(){
        depositList = new ArrayList<>();
        withdrawalList = new ArrayList<>();

        inputAccount = new Account(Type.SAVINGS, "Savings Account", 300L, "John Doe", "password", depositList, withdrawalList);

        mockResponseAccountEntity = new Account(Type.CHECKINGS, "Checking Account",300L, "Jane Doe", "password", depositList, withdrawalList);
        mockResponseAccountEntity.setId(1L);

        mockProcess = new Account();
        mockProcess.setId(2L);
        mockProcess.setBalance(100L);
    }

    @Test
    @DisplayName("Account Service: Create Account - Success")
    public void createAccountTestSuccess() throws AccountException {
        BDDMockito.doReturn(Optional.empty()).when(mockAccountRepo).findByNickname(inputAccount.getNickname());
        BDDMockito.doReturn(mockResponseAccountEntity).when(mockAccountRepo).save(ArgumentMatchers.any());

        Account returnedAccount = accountService.create(inputAccount);

        Assertions.assertNotNull(returnedAccount, "Account should not be null");
        Assertions.assertEquals(1L, returnedAccount.getId());
    }

    @Test
    @DisplayName("Account Service: Create Account - Fail (Account already exists)")
    public void createAccountTestFailAccountExists() {
        BDDMockito.doReturn(Optional.of(mockResponseAccountEntity)).when(mockAccountRepo).findByNickname(inputAccount.getNickname());

        Assertions.assertThrows(AccountException.class, () -> {
            accountService.create(inputAccount);
        });
    }

    @Test
    @DisplayName("Account Service: Get Account by Id - Success")
    public void getAccountByIdTestSuccess() throws AccountException {
        BDDMockito.doReturn(Optional.of(mockResponseAccountEntity)).when(mockAccountRepo).findById(1L);

        Account foundAccount = accountService.getAccountById(1L);

        Assertions.assertEquals(mockResponseAccountEntity.toString(), foundAccount.toString());
    }

    @Test
    @DisplayName("Account Service: Get Account by Id - Fail")
    public void getAccountByIdTestFail() {
        BDDMockito.doReturn(Optional.empty()).when(mockAccountRepo).findById(1L);

        Assertions.assertThrows(AccountException.class, () -> {
            accountService.getAccountById(1L);
        });
    }

    @Test
    @DisplayName("Account Service: Get All Accounts - Success")
    public void getAllAccountsTestSuccess(){
        List<Account> accountEntities = new ArrayList<>();
        accountEntities.add(mockResponseAccountEntity);
        BDDMockito.doReturn(accountEntities).when(mockAccountRepo).findAll();

        List<Account> responseAccountEntities = accountService.getAllAccounts();

        Assertions.assertIterableEquals(accountEntities, responseAccountEntities);
    }

    @Test
    @DisplayName("Account Service: Update Account - Success")
    public void updateAccountTestSuccess() throws AccountException {
        Account expectedAccountUpdateEntity = new Account(Type.SAVINGS, "Savings Account",500L,  "Updated John Doe", "updatedpassword", depositList, withdrawalList);

        BDDMockito.doReturn(Optional.of(mockResponseAccountEntity)).when(mockAccountRepo).findById(1L);
        BDDMockito.doReturn(expectedAccountUpdateEntity).when(mockAccountRepo).save(ArgumentMatchers.any());

        Account actualAccount = accountService.updateAccount(1L, expectedAccountUpdateEntity);

        Assertions.assertEquals(expectedAccountUpdateEntity.toString(), actualAccount.toString());
    }

    @Test
    @DisplayName("Account Service: Update Account - Fail")
    public void updateAccountTestFail() {
        Account expectedAccountUpdateEntity = new Account(Type.SAVINGS, "Savings Account", 600L,"Updated John Doe", "updatedpassword", depositList, withdrawalList);

        BDDMockito.doReturn(Optional.empty()).when(mockAccountRepo).findById(1L);

        Assertions.assertThrows(AccountException.class, ()-> {
            accountService.updateAccount(1L, expectedAccountUpdateEntity);
        });
    }

    @Test
    @DisplayName("Account Service: Login Checker - Success (Valid login)")
    public void loginCheckerTestSuccessValidLogin() throws AccountException {
        BDDMockito.doReturn(Optional.of(mockResponseAccountEntity)).when(mockAccountRepo).findByNickname("Jane Doe");

        BDDMockito.doReturn(Optional.of(mockResponseAccountEntity)).when(mockAccountRepo).findById(1L);

        Integer loginResult = accountService.loginChecker("Jane Doe", "password");

        Assertions.assertEquals(1, loginResult);
    }

    @Test
    @DisplayName("Account Service: Login Checker - Success (Invalid login)")
    public void loginCheckerTestSuccessInvalidLogin() throws AccountException {
        BDDMockito.doReturn(Optional.empty()).when(mockAccountRepo).findByNickname("John Doe");

        Integer loginResult = accountService.loginChecker("John Doe", "wrongpassword");

        Assertions.assertEquals(0, loginResult);
    }

    @Test
    @DisplayName("Account Service: Delete Account - Success")
    public void deleteUserTestSuccess() throws AccountException {
        BDDMockito.doReturn(Optional.of(mockResponseAccountEntity)).when(mockAccountRepo).findById(1L);

        accountService.deleteUser(1L);

        // No assertion needed as the test is to ensure the delete operation is successful
    }

    @Test
    @DisplayName("Account Service: Delete Account - Fail")
    public void deleteUserTestFail() {
        BDDMockito.doReturn(Optional.empty()).when(mockAccountRepo).findById(1L);

        Assertions.assertThrows(AccountException.class, ()-> {
            accountService.deleteUser(1L);
        });
    }

    @Test
    @DisplayName("Account Service: Process Withdrawal - Success")
    public void processWithdrawalTestSuccess() throws AccountException {
        Long withdrawalAmount = 20L;

        BDDMockito.doReturn(Optional.of(mockProcess)).when(mockAccountRepo).findById(2L);

        BDDMockito.doReturn(mockProcess).when(mockAccountRepo).save(mockProcess);

        accountService.processWithdrawal(2L, withdrawalAmount);

        Assertions.assertEquals(80L, mockProcess.getBalance()); // Updated balance
    }

    @Test
    @DisplayName("Account Service: Process Withdrawal - Insufficient Funds")
    public void processWithdrawalTestInsufficientFunds() throws AccountException {
        Long withdrawalAmount = 200L;

        BDDMockito.doReturn(Optional.of(mockProcess)).when(mockAccountRepo).findById(2L);

        BDDMockito.doReturn(mockProcess).when(mockAccountRepo).save(mockProcess);

        AccountException accountException = Assertions.assertThrows(AccountException.class, () -> {
            accountService.processWithdrawal(2L, withdrawalAmount);
        });

        Assertions.assertEquals("insufficient funds",accountException.getMessage());
    }

    @Test
    @DisplayName("Account Service: Process Deposit - Success")
    public void processDepositTestSuccess() throws AccountException {
        Long depositAmount = 20L;

        BDDMockito.doReturn(Optional.of(mockProcess)).when(mockAccountRepo).findById(2L);

        BDDMockito.doReturn(mockProcess).when(mockAccountRepo).save(mockProcess);

        accountService.processDeposit(2L, depositAmount);

        Assertions.assertEquals(120L, mockProcess.getBalance()); // Updated balance
    }
}
