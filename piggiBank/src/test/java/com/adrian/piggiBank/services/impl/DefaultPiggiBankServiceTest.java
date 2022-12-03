package com.adrian.piggiBank.services.impl;


import com.adrian.piggiBank.domain.dto.CoinDto;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
public class DefaultPiggiBankServiceTest {


  @InjectMocks
  DefaultPiggiBankService defaultPiggiBankService;

  CoinDto coinDto50;
  CoinDto coinDto100;
  CoinDto coinDto200;
  CoinDto coinDto500;
  CoinDto coinDto1000;


  @BeforeEach
  public void SetUp() {
    coinDto50 = CoinDto.builder().valor(50).build();
    coinDto100 = CoinDto.builder().valor(100).build();
    coinDto200 = CoinDto.builder().valor(200).build();
    coinDto500 = CoinDto.builder().valor(500).build();
    coinDto1000 = CoinDto.builder().valor(1000).build();
  }

  @Test
  public void testAddCoin() throws Exception {
    boolean add50 = defaultPiggiBankService.addCoin(coinDto50);
    boolean add100 = defaultPiggiBankService.addCoin(coinDto100);
    boolean add200 = defaultPiggiBankService.addCoin(coinDto200);
    boolean add500 = defaultPiggiBankService.addCoin(coinDto500);
    boolean add1000 = defaultPiggiBankService.addCoin(coinDto1000);

    assertEquals(true, add50);
    assertEquals(true, add100);
    assertEquals(true, add200);
    assertEquals(true, add500);
    assertEquals(true, add1000);
  }

  @Test
  public void testCountCoin() throws Exception {
    defaultPiggiBankService.addCoin(coinDto50);
    defaultPiggiBankService.addCoin(coinDto100);
    defaultPiggiBankService.addCoin(coinDto200);

    Integer size = defaultPiggiBankService.countCoin();
    assertEquals(3, size);
  }

  @Test
  public void testCountByDenomination () throws Exception {
    defaultPiggiBankService.addCoin(coinDto50);
    Integer coin50 = defaultPiggiBankService.countCountByDenomination(coinDto50);
    Integer coin100 = defaultPiggiBankService.countCountByDenomination(coinDto100);
    Integer coin200 = defaultPiggiBankService.countCountByDenomination(coinDto200);
    Integer coin500 = defaultPiggiBankService.countCountByDenomination(coinDto500);
    Integer coin1000 = defaultPiggiBankService.countCountByDenomination(coinDto1000);

    assertEquals(1, coin50);
    assertEquals(0, coin100);
    assertEquals(0, coin200);
    assertEquals(0, coin500);
    assertEquals(0, coin1000);
  }


  @Test
  public void testCleanPiggyBank () throws Exception {
    defaultPiggiBankService.addCoin(coinDto50);
    defaultPiggiBankService.addCoin(coinDto50);

    defaultPiggiBankService.cleanPiggyBank();

    Integer coins = defaultPiggiBankService.countCoin();

    assertEquals(0, coins);
  }

}
