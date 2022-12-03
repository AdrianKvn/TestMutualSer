package com.adrian.piggiBank.services;

import com.adrian.piggiBank.domain.dto.CoinDto;

import java.io.IOException;

public interface PiggiBankService {
  boolean addCoin(CoinDto coinDto) throws Exception;
  Integer countCoin();
  Integer countCountByDenomination(CoinDto coinDto) throws IOException;
  Integer sumAllCoins();
  Integer sumCoinByDenomination(CoinDto coinDto) throws IOException;
  void cleanPiggyBank();
}
