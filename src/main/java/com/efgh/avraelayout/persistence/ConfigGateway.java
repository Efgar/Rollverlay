package com.efgh.avraelayout.persistence;

import com.efgh.avraelayout.entities.DiceRoll;
import org.apache.commons.lang3.StringUtils;

import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
import java.util.stream.Collectors;

public class ConfigGateway {
    private static Properties appProperties = new Properties();
    private static final String appConfigPath = "rollverlay.properties";
    private static final String SEPARATOR = ";";

    private static final String SAVED_ROLLS_PROPERTY = "com.efgh.rollverlay.storage.rolls";
    private static List<DiceRoll> savedRolls = new ArrayList<>();

    public static void initializeConfiguration() throws IOException {
        appProperties.load(new FileInputStream(appConfigPath));

        //Initialize saved rolls
        String[] savedRollsCfg = StringUtils.defaultIfEmpty(appProperties.getProperty(SAVED_ROLLS_PROPERTY), "").split(SEPARATOR);
        for (String savedRoll : savedRollsCfg) {
            if (StringUtils.isNotEmpty(savedRoll)) {
                savedRolls.add(new DiceRoll(savedRoll));
            }
        }

    }

    public static List<DiceRoll> getDiceRolls() {
        return savedRolls;
    }

    public static void addDiceRoll(DiceRoll rollToSave) throws IOException {
        savedRolls.add(rollToSave);
        appProperties.setProperty(SAVED_ROLLS_PROPERTY, getDiceRollSavingString());
        updateConfigurationFile();
    }

    private static String getDiceRollSavingString() {
        return savedRolls.stream().map(DiceRoll::getSavedRollString).filter(StringUtils::isNotEmpty).collect(Collectors.joining(SEPARATOR));
    }

    private static void updateConfigurationFile() throws IOException {
        appProperties.store(new FileWriter(appConfigPath), "Updating configuration files");
    }
}
