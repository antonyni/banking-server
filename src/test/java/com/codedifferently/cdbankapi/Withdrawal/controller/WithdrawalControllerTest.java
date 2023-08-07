package com.codedifferently.cdbankapi.Withdrawal.controller;

import com.codedifferently.cdbankapi.domain.enums.Medium;
import com.codedifferently.cdbankapi.domain.enums.Status;
import com.codedifferently.cdbankapi.domain.enums.Type;
import com.codedifferently.cdbankapi.domain.withdrawl.controller.WithdrawalController;
import com.codedifferently.cdbankapi.domain.withdrawl.exceptions.WithdrawalException;
import com.codedifferently.cdbankapi.domain.withdrawl.models.Withdrawal;
import com.codedifferently.cdbankapi.domain.withdrawl.service.WithdrawalService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.BDDMockito;
import org.mockito.InjectMocks;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

@SpringBootTest
@AutoConfigureMockMvc
@ExtendWith(SpringExtension.class)
public class WithdrawalControllerTest {

    @MockBean
    private WithdrawalService mockWithdrawalService;

    @InjectMocks
    private WithdrawalController withdrawalController;

    @Autowired
    private MockMvc mockMvc;

    private Withdrawal inputWithdrawal;
    private Withdrawal mockResponseWithdrawal1;
    private Withdrawal mockResponseWithdrawal2;

    private Date date;

    @BeforeEach
    public void setUp() throws ParseException {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        date = sdf.parse("2022-01-15 12:30:45");

        inputWithdrawal = new Withdrawal(1L, Type.CHECKINGS, date, Status.COMPLETED, "John", Medium.BALANCE, 1000, "Finished");

        mockResponseWithdrawal1 = new Withdrawal();
        mockResponseWithdrawal2 = new Withdrawal();
        mockResponseWithdrawal1.setId(2L);
        mockResponseWithdrawal2.setId(3L);
    }

    @Test
    public void createWithdrawalSuccess() throws Exception {

        BDDMockito.doReturn(mockResponseWithdrawal1).when(mockWithdrawalService).createWithdrawal(inputWithdrawal);

        mockMvc.perform(MockMvcRequestBuilders.post("/withdrawal")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(asJsonString(inputWithdrawal)))

                .andExpect(MockMvcResultMatchers.status().isCreated());
    }

    @Test
    public void getWithdrawalByIdTestSuccess() throws Exception {
        long id = 1L;
        BDDMockito.doReturn(mockResponseWithdrawal1).when(mockWithdrawalService).getWithdrawalById(id);

        mockMvc.perform(MockMvcRequestBuilders.get("/withdrawal/{id}", id))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON));

    }

    @Test
    public void getWithdrawalByIdTestFail() throws Exception {
        long id = 1L;
        BDDMockito.doThrow(new WithdrawalException("not found")).when(mockWithdrawalService).getWithdrawalById(id);

        mockMvc.perform(MockMvcRequestBuilders.get("/withdrawal/{id}",id))
                .andExpect(MockMvcResultMatchers.status().isNotFound());

    }

    @Test
    public void updateWithdrawalSuccess() throws Exception {
        long id = 1L;
        BDDMockito.doReturn(mockResponseWithdrawal1).when(mockWithdrawalService).updateWithdrawal(inputWithdrawal, id);

        mockMvc.perform(MockMvcRequestBuilders.put("/withdrawal/{id}",id)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(asJsonString(inputWithdrawal)))

                .andExpect(MockMvcResultMatchers.status().isOk());
    }

    @Test
    public void updateWithdrawalFail() throws Exception {
        long id = 1L;
       BDDMockito.doThrow(new WithdrawalException("Not found")).when(mockWithdrawalService).updateWithdrawal(inputWithdrawal, id);
       //BDDMockito.doThrow(new WithdrawalException("Not Found")).when(mockWithdrawalService).deleteWithdrawalById(id);
        mockMvc.perform(MockMvcRequestBuilders.put("/withdrawal/{id}",id)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(asJsonString(inputWithdrawal)))
                .andExpect(MockMvcResultMatchers.status().isNotFound());
    }

    @Test
    public void deleteWithdrawalSuccess() throws Exception {
        long id = 1L;
        BDDMockito.doReturn(true).when(mockWithdrawalService).deleteWithdrawalById(id);

        mockMvc.perform(MockMvcRequestBuilders.delete("/withdrawal/{id}",1L))
                .andExpect(MockMvcResultMatchers.status().isNoContent())
                .andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON));
    }

    @Test
    public void deleteWithdrawalTestNotFound() throws Exception{
        long id = 1L;
        BDDMockito.doThrow(new WithdrawalException("Not Found")).when(mockWithdrawalService).deleteWithdrawalById(id);
        mockMvc.perform(MockMvcRequestBuilders.delete("/withdrawal/{id}", 1L))
                .andExpect(MockMvcResultMatchers.status().isNotFound());
    }


    private static String asJsonString(final Object obj) {
        try {
            return new ObjectMapper().writeValueAsString(obj);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
