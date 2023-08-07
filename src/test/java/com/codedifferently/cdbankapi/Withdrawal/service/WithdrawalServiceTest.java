package com.codedifferently.cdbankapi.Withdrawal.service;

import com.codedifferently.cdbankapi.domain.enums.Medium;
import com.codedifferently.cdbankapi.domain.enums.Status;
import com.codedifferently.cdbankapi.domain.enums.Type;
import com.codedifferently.cdbankapi.domain.withdrawal.exceptions.WithdrawalException;
import com.codedifferently.cdbankapi.domain.withdrawal.models.Withdrawal;
import com.codedifferently.cdbankapi.domain.withdrawal.repos.WithdrawalRepo;
import com.codedifferently.cdbankapi.domain.withdrawal.service.WithdrawalServiceImpl;
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
public class WithdrawalServiceTest {

    @Mock
    private WithdrawalRepo withdrawalRepo;

    @InjectMocks
    private WithdrawalServiceImpl withdrawalService;

    private Withdrawal inputWithdrawal;
    private Withdrawal mockResponseWithdrawal1;
    private Withdrawal mockResponseWithdrawal2;

    private Date date;

    @BeforeEach
    public void setUp() throws ParseException {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        date = sdf.parse("2022-01-15 12:30:45");

        inputWithdrawal = new Withdrawal(1L,Type.CHECKINGS, date, Status.COMPLETED, "John", Medium.BALANCE, 1000, "Finished");

        mockResponseWithdrawal1 = new Withdrawal();
        mockResponseWithdrawal2 = new Withdrawal();
        mockResponseWithdrawal1.setId(2L);
        mockResponseWithdrawal2.setId(3L);
    }

    @Test
    public void createWithdrawalSuccess(){
        BDDMockito.doReturn(mockResponseWithdrawal1).when(withdrawalRepo).save(ArgumentMatchers.any());
        Withdrawal returnedWithdrawal = withdrawalService.createWithdrawal(inputWithdrawal);
        Assertions.assertNotNull(returnedWithdrawal, "Withdrawal Should not be null");
        Assertions.assertEquals(2L, returnedWithdrawal.getId());
    }

    @Test
    public void getWithdrawalByIdSuccess() throws WithdrawalException {
        BDDMockito.doReturn(Optional.of(mockResponseWithdrawal1)).when(withdrawalRepo).findById(ArgumentMatchers.any());
        Withdrawal returnedWithdrawal = withdrawalService.getWithdrawalById(1L);
        Assertions.assertNotNull(returnedWithdrawal, "Withdrawal Should not be null");
        Assertions.assertEquals(2L, returnedWithdrawal.getId());
    }

    @Test
    public void getAllWithdrawalsSuccess(){
        List<Withdrawal> withdrawalList = new ArrayList<>();
        withdrawalList.add(mockResponseWithdrawal1);
        withdrawalList.add(mockResponseWithdrawal2);

        BDDMockito.doReturn(withdrawalList).when(withdrawalRepo).findAll();
        List<Withdrawal> returnedWithdrawals = withdrawalService.getAllWithdrawals();
        Assertions.assertNotNull(returnedWithdrawals, "Withdrawal Should not be null");
        Assertions.assertIterableEquals(withdrawalList, returnedWithdrawals);
    }

    @Test
    public void deleteWithdrawalSuccess() throws WithdrawalException {
        BDDMockito.doReturn(Optional.of(mockResponseWithdrawal1)).when(withdrawalRepo).findById(ArgumentMatchers.any());
        Boolean result = withdrawalService.deleteWithdrawalById(1L);
        Assertions.assertTrue(result);
    }
    @Test
    public void deleteWithdrawalByIdFail(){
        BDDMockito.doReturn(Optional.empty()).when(withdrawalRepo).findById(ArgumentMatchers.any());
        Assertions.assertThrows(WithdrawalException.class, () ->{
            withdrawalService.deleteWithdrawalById(1L);
        });
    }

    @Test
    public void getWithdrawalByIdFail(){
        BDDMockito.doReturn(Optional.empty()).when(withdrawalRepo).findById(ArgumentMatchers.any());
        Assertions.assertThrows(WithdrawalException.class, () ->{
            withdrawalService.getWithdrawalById(1L);
        });
    }
    @Test
    public void updateWithdrawalSuccess() throws WithdrawalException {
        BDDMockito.doReturn(Optional.of(mockResponseWithdrawal1)).when(withdrawalRepo).findById(ArgumentMatchers.any());
        Withdrawal withdrawal = withdrawalService.updateWithdrawal(inputWithdrawal,1L);
        Assertions.assertEquals(withdrawal, mockResponseWithdrawal1);
    }

    @Test
    public void updateWithdrawalFail(){
        BDDMockito.doReturn(Optional.empty()).when(withdrawalRepo).findById(ArgumentMatchers.any());
        Assertions.assertThrows(WithdrawalException.class, () ->{
            withdrawalService.updateWithdrawal(inputWithdrawal,1L);
        });
    }


}
