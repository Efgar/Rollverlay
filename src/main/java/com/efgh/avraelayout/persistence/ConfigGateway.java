package com.efgh.avraelayout.persistence;

import com.efgh.avraelayout.entities.DiceRoll;
import com.efgh.avraelayout.ui.css.Themes;
import org.apache.commons.lang3.ObjectUtils;
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
    private static final String SAVED_EXPRESSIONS_PROPERTY = "com.efgh.rollverlay.storage.expressions";
    private static List<DiceRoll> savedRolls = new ArrayList<>();

    public static void initializeConfiguration() throws IOException {
        appProperties.load(new FileInputStream(appConfigPath));

        String[] savedRollsCfg = StringUtils.defaultIfEmpty(appProperties.getProperty(SAVED_ROLLS_PROPERTY), "").split(SEPARATOR, -1);
        for (String savedRoll : savedRollsCfg) {
            if (StringUtils.isNotEmpty(savedRoll)) {
                savedRolls.add(new DiceRoll(savedRoll));
            }
        }
    }

    public static Themes getConfiguredTheme(){
        return ObjectUtils.defaultIfNull(Themes.getTheme(appProperties.getProperty("com.efgh.rollverlay.theme")), Themes.TRADITIONAL);
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

    public static void addCustomRollExpression(String rollExpressionName, String rollExpressionValue) throws IOException {
        if (StringUtils.isBlank(rollExpressionName) || StringUtils.isBlank(rollExpressionValue)) {
            throw new IOException("Invalid values for the custom roll");
        }
        //TODO custom expression handling
        //savedRolls.add(rollToSave);
        appProperties.setProperty(SAVED_EXPRESSIONS_PROPERTY, getDiceRollSavingString());
        updateConfigurationFile();
    }
}
