package com.codedifferently.cdbankapi.deposit;

import com.codedifferently.cdbankapi.domain.deposit.exceptions.DepositException;
import com.codedifferently.cdbankapi.domain.deposit.models.Deposit;
import com.codedifferently.cdbankapi.domain.deposit.repos.DepositRepo;
import com.codedifferently.cdbankapi.domain.deposit.services.DepositServiceImpl;
import com.codedifferently.cdbankapi.domain.enums.Medium;
import com.codedifferently.cdbankapi.domain.enums.Status;
import com.codedifferently.cdbankapi.domain.enums.Type;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentMatchers;
import org.mockito.BDDMockito;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@ExtendWith(MockitoExtension.class)
public class DepositServiceTest {

    @Mock
    private DepositRepo depositRepo;

    @InjectMocks
    private DepositServiceImpl depositService;

    private Deposit inputDeposit;
    private Deposit mockResponseDeposit1;
    private Deposit mockResponseDeposit2;

    private Date date;

    @BeforeEach
    public void setUp() throws ParseException {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        date = sdf.parse("2022-01-15 12:30:45");

        inputDeposit = new Deposit(1L, Type.CHECKINGS, date, Status.COMPLETED, "John", Medium.BALANCE, 1000, "Finished");

        mockResponseDeposit1 = new Deposit();
        mockResponseDeposit2 = new Deposit();
        mockResponseDeposit1.setId(2L);
        mockResponseDeposit2.setId(3L);
    }

    @Test
    public void createDepositSuccess() throws DepositException {
        BDDMockito.doReturn(mockResponseDeposit1).when(depositRepo).save(ArgumentMatchers.any());
        Deposit returnedDeposit = depositService.createDeposit(inputDeposit);
        Assertions.assertNotNull(returnedDeposit, "Deposit should not be null");
        Assertions.assertEquals(2L, returnedDeposit.getId());
    }

    @Test
    public void getDepositByIdSuccess() throws DepositException {
        BDDMockito.doReturn(Optional.of(mockResponseDeposit1)).when(depositRepo).findById(ArgumentMatchers.any());
        Deposit returnedDeposit = depositService.getDepositById(1L);
        Assertions.assertNotNull(returnedDeposit, "Deposit should not be null");
        Assertions.assertEquals(2L, returnedDeposit.getId());
    }

    @Test
    public void getAllDepositsSuccess() {
        List<Deposit> depositList = new ArrayList<>();
        depositList.add(mockResponseDeposit1);
        depositList.add(mockResponseDeposit2);

        BDDMockito.doReturn(depositList).when(depositRepo).findAll();
        List<Deposit> returnedDeposits = depositService.getAllDeposits();
        Assertions.assertNotNull(returnedDeposits, "Deposits should not be null");
        Assertions.assertIterableEquals(depositList, returnedDeposits);
    }

    @Test
    public void deleteDepositSuccess() throws DepositException {
        BDDMockito.doReturn(Optional.of(mockResponseDeposit1)).when(depositRepo).findById(ArgumentMatchers.any());
        Boolean result = depositService.deleteDepositById(1L);
        Assertions.assertTrue(result);
    }

    @Test
    public void deleteDepositByIdFail() {
        BDDMockito.doReturn(Optional.empty()).when(depositRepo).findById(ArgumentMatchers.any());
        Assertions.assertThrows(DepositException.class, () -> {
            depositService.deleteDepositById(1L);
        });
    }

    @Test
    public void getDepositByIdFail() {
        BDDMockito.doReturn(Optional.empty()).when(depositRepo).findById(ArgumentMatchers.any());
        Assertions.assertThrows(DepositException.class, () -> {
            depositService.getDepositById(1L);
        });
    }

    @Test
    public void updateDepositSuccess() throws DepositException {
        BDDMockito.doReturn(Optional.of(mockResponseDeposit1)).when(depositRepo).findById(ArgumentMatchers.any());
        Deposit deposit = depositService.updateDeposit(inputDeposit, 1L);
        Assertions.assertEquals(deposit, mockResponseDeposit1);
    }

    @Test
    public void updateDepositFail() {
        BDDMockito.doReturn(Optional.empty()).when(depositRepo).findById(ArgumentMatchers.any());
        Assertions.assertThrows(DepositException.class, () -> {
            depositService.updateDeposit(inputDeposit, 1L);
        });
    }
}
