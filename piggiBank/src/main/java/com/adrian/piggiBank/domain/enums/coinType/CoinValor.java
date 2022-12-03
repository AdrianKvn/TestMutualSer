package com.adrian.piggiBank.domain.enums.coinType;

import com.fasterxml.jackson.annotation.JsonValue;
import lombok.Getter;

import java.util.Collection;
import java.util.HashMap;

@Getter
public enum CoinValor {

    CINCUENTA(CoinValor.COIN_VALUE_50, "CINCUENTA"), CIEN(CoinValor.COIN_VALUE_100, "CIEN"), DOSCIENTO(CoinValor.COIN_VALUE_200, "DOSCIENTO"), QUINIENTO(CoinValor.COIN_VALUE_500, "QUINIENTO"), MIL(CoinValor.COIN_VALUE_1000, "MIL");


    private static final String COIN_VALUE_50 = "50";
    private static final String COIN_VALUE_100 = "100";
    private static final String COIN_VALUE_200 = "200";
    private static final String COIN_VALUE_500 = "500";
    private static final String COIN_VALUE_1000 = "1000";


    private static final HashMap<String, CoinValor> ENUM_MAP_BY_CODE = new HashMap<>();


    @JsonValue
    private final String id;
    private final String description;

    static {
        ENUM_MAP_BY_CODE.put(COIN_VALUE_50, CINCUENTA);
        ENUM_MAP_BY_CODE.put(COIN_VALUE_100, CIEN);
        ENUM_MAP_BY_CODE.put(COIN_VALUE_200, DOSCIENTO);
        ENUM_MAP_BY_CODE.put(COIN_VALUE_500, QUINIENTO);
        ENUM_MAP_BY_CODE.put(COIN_VALUE_1000, MIL);
    }

    CoinValor(String id, String descripcion) {
        this.id = id;
        this.description = descripcion;
    }

    public static Object findByPrimaryKey(String id) {
        if (id != null && ENUM_MAP_BY_CODE.containsKey(id)) {
            return ENUM_MAP_BY_CODE.get(id);
        }
        return null;
    }

    public static Collection<CoinValor> getList() {
        return ENUM_MAP_BY_CODE.values();
    }
}
