package com.adrian.piggiBank.domain.dto;



import com.adrian.piggiBank.domain.enums.coinType.CoinValorConverter;
import lombok.*;
import lombok.experimental.FieldDefaults;

import javax.persistence.Convert;

@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class CoinDto {

    @Convert(converter = CoinValorConverter.class)
    private Integer valor;

}
