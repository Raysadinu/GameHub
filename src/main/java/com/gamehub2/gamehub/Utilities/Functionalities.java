package com.gamehub2.gamehub.Utilities;

import com.gamehub2.gamehub.entities.Games.GamePG;

import java.time.LocalDate;
import java.time.Period;
import java.util.ArrayList;
import java.util.List;

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
}
