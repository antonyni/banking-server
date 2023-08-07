package com.codedifferently.cdbankapi.deposit;
import com.codedifferently.cdbankapi.domain.deposit.controllers.DepositController;
import com.codedifferently.cdbankapi.domain.deposit.exceptions.DepositException;
import com.codedifferently.cdbankapi.domain.deposit.models.Deposit;
import com.codedifferently.cdbankapi.domain.deposit.services.DepositService;
import com.codedifferently.cdbankapi.domain.enums.Medium;
import com.codedifferently.cdbankapi.domain.enums.Status;
import com.codedifferently.cdbankapi.domain.enums.Type;
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
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

@SpringBootTest
@AutoConfigureMockMvc
@ExtendWith(SpringExtension.class)
public class DepositControllerTest {

    @MockBean
    private DepositService mockDepositService;

    @InjectMocks
    private DepositController depositController;

    @Autowired
    private MockMvc mockMvc;

    private Deposit inputDeposit;
    private Deposit mockResponseDeposit1;

    @BeforeEach
    public void setUp() throws ParseException {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date date = sdf.parse("2022-01-15 12:30:45");

        inputDeposit = new Deposit(1L,1L,Type.CHECKINGS, date, Status.COMPLETED, "John", Medium.BALANCE, 1000, "Finished");

        mockResponseDeposit1 = new Deposit();
        mockResponseDeposit1.setId(2L);
    }

    @Test
    public void createDepositSuccess() throws Exception {
        BDDMockito.doReturn(mockResponseDeposit1).when(mockDepositService).createDeposit(inputDeposit);

        mockMvc.perform(MockMvcRequestBuilders.post("/deposit")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(asJsonString(inputDeposit)))
                .andExpect(MockMvcResultMatchers.status().isCreated());
    }

    @Test
    public void getDepositByIdTestSuccess() throws Exception {
        long id = 1L;
        BDDMockito.doReturn(mockResponseDeposit1).when(mockDepositService).getDepositById(id);

        mockMvc.perform(MockMvcRequestBuilders.get("/deposit/{id}", id))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON));
    }

    @Test
    public void getDepositByIdTestFail() throws Exception {
        long id = 1L;
        BDDMockito.doThrow(new DepositException("Not found")).when(mockDepositService).getDepositById(id);

        mockMvc.perform(MockMvcRequestBuilders.get("/deposit/{id}", id))
                .andExpect(MockMvcResultMatchers.status().isNotFound());
    }

    @Test
    public void updateDepositSuccess() throws Exception {
        long id = 1L;
        BDDMockito.doReturn(mockResponseDeposit1).when(mockDepositService).updateDeposit(inputDeposit, id);

        mockMvc.perform(MockMvcRequestBuilders.put("/deposit/{id}", id)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(asJsonString(inputDeposit)))
                .andExpect(MockMvcResultMatchers.status().isOk());
    }

    @Test
    public void updateDepositFail() throws Exception {
        long id = 1L;
        BDDMockito.doThrow(new DepositException("Not found")).when(mockDepositService).updateDeposit(inputDeposit, id);

        mockMvc.perform(MockMvcRequestBuilders.put("/deposit/{id}", id)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(asJsonString(inputDeposit)))
                .andExpect(MockMvcResultMatchers.status().isNotFound());
    }

    @Test
    public void deleteDepositSuccess() throws Exception {
        long id = 1L;
        BDDMockito.doReturn(true).when(mockDepositService).deleteDepositById(id);

        mockMvc.perform(MockMvcRequestBuilders.delete("/deposit/{id}", id))
                .andExpect(MockMvcResultMatchers.status().isNoContent())
                .andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON));
    }

    @Test
    public void deleteDepositTestNotFound() throws Exception {
        long id = 1L;
        BDDMockito.doThrow(new DepositException("Not found")).when(mockDepositService).deleteDepositById(id);

        mockMvc.perform(MockMvcRequestBuilders.delete("/deposit/{id}", id))
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
