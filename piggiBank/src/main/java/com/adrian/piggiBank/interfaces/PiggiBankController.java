package com.adrian.piggiBank.interfaces;

import com.adrian.piggiBank.domain.dto.CoinDto;
import com.adrian.piggiBank.services.PiggiBankService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;

import static java.util.Objects.isNull;

@RestController
@RequestMapping("/piggi-bank")
@Api(value = "Users microservice", description = "This API has a CRUD for users")
public class PiggiBankController {

    @Autowired
    PiggiBankService piggiBankService;


    @ApiOperation(value = "Agregar Moneda", notes = "Recibe un formato JSON con los datos del valor de la moneda")
    @PostMapping(value = "/addCoin", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> addCointPiggyBank(@RequestBody CoinDto coinDto) throws Exception {
        if (!isNull(coinDto.getValor())) {
            boolean add = piggiBankService.addCoin(coinDto);
            if (add) {
                return new ResponseEntity<>("Moneda Agregada", HttpStatus.ACCEPTED);
            }
            return new ResponseEntity<>("Valor de la moneda no aceptada", HttpStatus.NOT_ACCEPTABLE);
        }
        return new ResponseEntity<>("debe enviar un valor valido para la moneda", HttpStatus.CONFLICT);
    }

    @GetMapping(value = "/countCoins")
    public ResponseEntity<Integer> countCointPiggiBank() {
        return new ResponseEntity<>(piggiBankService.countCoin(), HttpStatus.OK);
    }

    @PostMapping(value = "/countCoinsByDenomination", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Integer> countCointsByDenomination(@RequestBody CoinDto coinDto) throws IOException {
        return new ResponseEntity<>(piggiBankService.countCountByDenomination(coinDto), HttpStatus.OK);
    }

    @GetMapping(value = "/sumCoins")
    public ResponseEntity<Integer> sumCoins() {
        return new ResponseEntity<>(piggiBankService.sumAllCoins(), HttpStatus.OK);
    }

    @PostMapping(value = "/sumCoinsByDenomination", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Integer> sumCoinsByDenomination(@RequestBody CoinDto coinDto) throws IOException {
        if (!isNull(coinDto)) {
            return new ResponseEntity<>(piggiBankService.sumCoinByDenomination(coinDto), HttpStatus.OK);
        }
        throw new IOException("inserte un valor de la moneda");
    }

    @GetMapping(value = "/cleanCoins")
    public ResponseEntity<String> cleanCoins() {
        piggiBankService.cleanPiggyBank();
        return new ResponseEntity<>("alcancia limpia", HttpStatus.OK);
    }
}
