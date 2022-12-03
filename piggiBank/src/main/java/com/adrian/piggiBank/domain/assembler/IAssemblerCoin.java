package com.adrian.piggiBank.domain.assembler;


import com.adrian.piggiBank.domain.dto.CoinDto;
import com.adrian.piggiBank.domain.model.Coin;
import org.mapstruct.Mapper;

@Mapper
public interface IAssemblerCoin {

    CoinDto toDto(Coin coin);

    Coin toEntity(CoinDto coinDto);


}
