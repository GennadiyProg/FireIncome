package com.example.fireincome.util;

import com.example.fireincome.model.User;
import ru.homyakin.iuliia.Schemas;
import ru.homyakin.iuliia.Translator;

import java.util.Random;

public class ModelUtils {
    public static String generateUsername(User user) {
        Translator translator = new Translator(Schemas.ICAO_DOC_9303);
        Random random = new Random();
        String username = translator.translate(user.getSurname() + "_" + user.getFirstName().charAt(0));
        int randomNumber = random.nextInt(9999);
        while (randomNumber < 1000) {
            randomNumber *= 10;
        }
        username = username.toLowerCase() + "_" + randomNumber;
        return username;
    }
}
