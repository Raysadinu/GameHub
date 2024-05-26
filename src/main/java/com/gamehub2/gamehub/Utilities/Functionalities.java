package com.gamehub2.gamehub.Utilities;

import com.gamehub2.gamehub.common.Games.GameDetailsDto;
import com.gamehub2.gamehub.common.Games.PriceDetailsDto;
import com.gamehub2.gamehub.entities.Games.GamePG;

import java.time.LocalDate;
import java.time.Period;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class Functionalities {

    // Method to calculate the age of the user
    public static int calculateAge(LocalDate birthDate) {
        LocalDate currentDate = LocalDate.now();
        return Period.between(birthDate, currentDate).getYears();
    }

    public static List<GamePG.PGType> getValidPG(int userAge){
        List<GamePG.PGType> validPG = new ArrayList<>();

        if(userAge>=3){
            validPG.add(GamePG.PGType.PEGI3);
        }
        if(userAge>=7){
            validPG.add(GamePG.PGType.PEGI7);
        }
        if(userAge>=12){
            validPG.add(GamePG.PGType.PEGI12);
        }
        if(userAge>=16){
            validPG.add(GamePG.PGType.PEGI16);
        }
        if(userAge>=18){
            validPG.add(GamePG.PGType.PEGI18);
        }

        return  validPG;
    }
    public static Map<Long, Double[]> calculateGamePrices(List<GameDetailsDto> games, List<PriceDetailsDto> priceList) {
        Map<Long, Double[]> gamePrices = new HashMap<>();

        for (GameDetailsDto gameDetail : games) {
            Long gameId = gameDetail.getGameId();
            Double[] priceInfo = new Double[3];

            for (PriceDetailsDto priceDetail : priceList) {
                if (priceDetail.getGameId().equals(gameId)) {
                    if (priceDetail.getDiscount() > 0) {
                        priceInfo[0] = priceDetail.getPrice();
                        priceInfo[1] = priceDetail.getDiscount_price();
                        priceInfo[2] = priceDetail.getDiscount();
                    } else {
                        priceInfo[0] = priceDetail.getPrice();
                        priceInfo[1] = 0.0;
                        priceInfo[2] = 0.0;
                    }
                    break;
                }
            }
            gamePrices.put(gameId, priceInfo);
        }

        return gamePrices;
    }



}

