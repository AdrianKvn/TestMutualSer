package com.adrian.piggiBank.domain.enums.coinType;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;

@Converter
public class CoinValorConverter implements AttributeConverter<CoinValor, String> {
    @Override
    public String convertToDatabaseColumn(CoinValor coinValor) {
        if (coinValor != null) {
            return coinValor.getId();
        }
        return null;
    }

    @Override
    public CoinValor convertToEntityAttribute(String dbData) {
        return (CoinValor) CoinValor.findByPrimaryKey(dbData);
    }
}
