package com.adrian.piggiBank.services.impl;


import com.adrian.piggiBank.domain.assembler.IAssemblerCoin;
import com.adrian.piggiBank.domain.dto.CoinDto;
import com.adrian.piggiBank.domain.enums.coinType.CoinValor;
import com.adrian.piggiBank.domain.model.Coin;
import com.adrian.piggiBank.services.PiggiBankService;
import org.mapstruct.factory.Mappers;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Service
public class DefaultPiggiBankService implements PiggiBankService {

  List<Coin> coinList50 = new ArrayList();
  List<Coin> coinList100 = new ArrayList();
  List<Coin> coinList200 = new ArrayList();
  List<Coin> coinList500 = new ArrayList();
  List<Coin> coinList1000 = new ArrayList();

  IAssemblerCoin iAssemblerCoin = Mappers.getMapper(IAssemblerCoin.class);

  @Override
  public boolean addCoin(CoinDto coinDto) throws Exception {

    if (coinDto.getValor().toString().equals(CoinValor.CINCUENTA.getId())) {
      coinList50.add(iAssemblerCoin.toEntity(coinDto));
      return true;
    } else if (coinDto.getValor().toString().equals(CoinValor.CIEN.getId())) {
      coinList100.add(iAssemblerCoin.toEntity(coinDto));
      return true;
    } else if (coinDto.getValor().toString().equals(CoinValor.DOSCIENTO.getId())) {
      coinList200.add(iAssemblerCoin.toEntity(coinDto));
      return true;
    } else if (coinDto.getValor().toString().equals(CoinValor.QUINIENTO.getId())) {
      coinList500.add(iAssemblerCoin.toEntity(coinDto));
      return true;
    } else if (coinDto.getValor().toString().equals(CoinValor.MIL.getId())) {
      coinList1000.add(iAssemblerCoin.toEntity(coinDto));
      return true;
    } else {
      return false;
    }
  }

  @Override
  public Integer countCoin() {
    Integer nroCoins =
        (coinList50.size() + coinList100.size() + coinList200.size()
            + coinList500.size()
            + coinList1000.size());
    return nroCoins;
  }

  @Override
  public Integer countCountByDenomination(CoinDto coinDto) throws IOException {
    if (coinDto.getValor().toString().equals(CoinValor.CINCUENTA.getId())) {
      return coinList50.size();
    } else if (coinDto.getValor().toString().equals(CoinValor.CIEN.getId())) {
      return coinList100.size();
    } else if (coinDto.getValor().toString().equals(CoinValor.DOSCIENTO.getId())) {
      return coinList200.size();
    } else if (coinDto.getValor().toString().equals(CoinValor.QUINIENTO.getId())) {
      return coinList500.size();
    } else if (coinDto.getValor().toString().equals(CoinValor.MIL.getId())) {
      return coinList1000.size();
    }
    throw new IOException("El valor de la moneda no es valido");
  }

  @Override
  public Integer sumCoinByDenomination(CoinDto coinDto) throws IOException {

    if (coinDto.getValor().toString().equals(CoinValor.CINCUENTA.getId())) {
      return coinList50.stream().mapToInt(coin -> coin.getValor()).sum();
    } else if (coinDto.getValor().toString().equals(CoinValor.CIEN.getId())) {
      return coinList100.stream().mapToInt(coin -> coin.getValor()).sum();
    } else if (coinDto.getValor().toString().equals(CoinValor.DOSCIENTO.getId())) {
      return coinList200.stream().mapToInt(coin -> coin.getValor()).sum();
    } else if (coinDto.getValor().toString().equals(CoinValor.QUINIENTO.getId())) {
      return coinList500.stream().mapToInt(coin -> coin.getValor()).sum();
    } else if (coinDto.getValor().toString().equals(CoinValor.MIL.getId())) {
      return coinList1000.stream().mapToInt(coin -> coin.getValor()).sum();
    }
    throw new IOException("El valor de la moneda no es valido");

  }

  @Override
  public Integer sumAllCoins() {
    return coinList50.stream().mapToInt(coin -> coin.getValor()).sum() +
        coinList100.stream().mapToInt(coin -> coin.getValor()).sum() +
        coinList200.stream().mapToInt(coin -> coin.getValor()).sum() +
        coinList500.stream().mapToInt(coin -> coin.getValor()).sum() +
        coinList1000.stream().mapToInt(coin -> coin.getValor()).sum();
  }

  @Override
  public void cleanPiggyBank() {
    coinList50.clear();
    coinList100.clear();
    coinList200.clear();
    coinList500.clear();
    coinList1000.clear();
  }
}
