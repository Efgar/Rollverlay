package com.efgh.avraelayout.persistence;

import com.efgh.avraelayout.Rollverlay;
import com.efgh.avraelayout.entities.CustomExpression;
import com.efgh.avraelayout.entities.DiceRoll;
import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;

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
    private static final String SEPARATOR = "@@@";

    private static final String SAVED_ROLLS_PROPERTY = "com.efgh.rollverlay.storage.rolls";
    private static final String SAVED_EXPRESSIONS_PROPERTY = "com.efgh.rollverlay.storage.expressions";
    private static List<DiceRoll> savedRolls = new ArrayList<>();
    private static List<CustomExpression> savedExpressions = new ArrayList<>();

    public static void initializeConfiguration() {
        try {
            appProperties.load(new FileInputStream(appConfigPath));
            initializeSavedRolls();
            initializeSavedExpressions();
        } catch (IOException e) {
            Logger.getRootLogger().error("ERROR READING SAVED CONFIGURATION", e);
            Rollverlay.showSnackBar("ERROR READING SAVED CONFIGURATION", true);
        }
    }

    private static void initializeSavedRolls() {
        String[] savedRollsCfg = StringUtils.defaultIfEmpty(appProperties.getProperty(SAVED_ROLLS_PROPERTY), "").split(SEPARATOR, -1);
        for (String savedRoll : savedRollsCfg) {
            if (StringUtils.isNotEmpty(savedRoll)) {
                savedRolls.add(new DiceRoll(savedRoll));
            }
        }
    }

    private static void initializeSavedExpressions() {
        String[] savedExpressionsCfg = StringUtils.defaultIfEmpty(appProperties.getProperty(SAVED_EXPRESSIONS_PROPERTY), "").split(SEPARATOR, -1);
        for (String savedExpression : savedExpressionsCfg) {
            if (StringUtils.isNotEmpty(savedExpression)) {
                savedExpressions.add(new CustomExpression(savedExpression));
            }
        }
    }

    public static String getConfiguredTheme() {
        return appProperties.getProperty("com.efgh.rollverlay.theme");
    }

    public static List<DiceRoll> getDiceRolls() {
        return savedRolls;
    }

    public static void addDiceRoll(DiceRoll rollToSave) {
        savedRolls.add(rollToSave);
        updateConfigurationFile();
    }

    private static String getDiceRollSavingString() {
        return savedRolls.stream().map(DiceRoll::getSavedRollString).filter(StringUtils::isNotEmpty).collect(Collectors.joining(SEPARATOR));
    }

    public static List<CustomExpression> getCustomRollExpressions() {
        return savedExpressions;
    }

    public static void addCustomRollExpression(CustomExpression customExpression) throws IOException {
        customExpression.validate();
        savedExpressions.add(customExpression);
        updateConfigurationFile();
    }

    private static String getCustomExpressionsSavingString() {
        return savedExpressions.stream().map(CustomExpression::getSavedExpressionString).filter(StringUtils::isNotEmpty).collect(Collectors.joining(SEPARATOR));
    }

    public static void updateConfigurationFile() {
        try {
            appProperties.setProperty(SAVED_ROLLS_PROPERTY, getDiceRollSavingString());
            appProperties.setProperty(SAVED_EXPRESSIONS_PROPERTY, getCustomExpressionsSavingString());
            appProperties.store(new FileWriter(appConfigPath), "Updating configuration files");
        } catch (IOException e) {
            Rollverlay.showSnackBar("ERROR SAVING CONFIGURATION", true);
        }
    }
}
