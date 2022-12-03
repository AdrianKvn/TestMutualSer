package com.adrian.piggiBank.interfaces;


import com.adrian.piggiBank.domain.dto.CoinDto;
import com.adrian.piggiBank.services.PiggiBankService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(SpringExtension.class)
@WebAppConfiguration
@ContextConfiguration(classes = PiggiBankController.class)
@AutoConfigureMockMvc
@WebMvcTest(PiggiBankController.class)
public class TestPiggiBankController {

  final String MODULE_PATH = "/piggi-bank";

  @Autowired
  ObjectMapper objectMapper;

  @Autowired
  MockMvc mockMvc;

  @MockBean
  PiggiBankService piggiBankService;

  CoinDto coinDto50;

  @BeforeEach
  public void SetUp() {
    coinDto50 = CoinDto.builder().valor(50).build();
  }

  @Test
  public void testAddCoins() throws Exception {
    when(piggiBankService.addCoin(any(CoinDto.class))).thenReturn(true);
    mockMvc.perform(post(MODULE_PATH + "/addCoin")
        .contentType(MediaType.APPLICATION_JSON_UTF8_VALUE)
        .content(objectMapper.writeValueAsString(coinDto50)))
        .andExpect(status()
            .isAccepted())
        .andDo(print());
  }

  @Test
  public void testCountCointPiggiBank() throws Exception {
    when(piggiBankService.countCoin()).thenReturn(1);
    mockMvc.perform(get(MODULE_PATH + "/countCoins"))
        .andExpect(status()
            .isOk())
        .andDo(print());
  }
  @Test
  public void testCountCoinsByDenomination() throws Exception {
    when(piggiBankService.countCountByDenomination(any(CoinDto.class))).thenReturn(1);
    mockMvc.perform(post(MODULE_PATH + "/countCoinsByDenomination")
        .accept(MediaType.APPLICATION_JSON_UTF8)
        .contentType(MediaType.APPLICATION_JSON_UTF8)
        .content(objectMapper.writeValueAsString(coinDto50)))
        .andExpect(status()
            .isOk())
        .andDo(print());
  }

  @Test
  public void testSumCoins() throws Exception {
    when(piggiBankService.sumAllCoins()).thenReturn(50);
    mockMvc.perform(get(MODULE_PATH + "/sumCoins"))
        .andExpect(status()
            .isOk())
        .andDo(print());
  }

  @Test
  public void testDeleteCoins() throws Exception {
    mockMvc.perform(get(MODULE_PATH + "/cleanCoins"))
        .andExpect(status()
            .isOk())
        .andDo(print());
  }


}
