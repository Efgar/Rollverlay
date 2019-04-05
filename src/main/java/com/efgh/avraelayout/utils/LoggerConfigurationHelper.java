package com.efgh.avraelayout.utils;

import org.apache.log4j.FileAppender;
import org.apache.log4j.Level;
import org.apache.log4j.Logger;
import org.apache.log4j.PatternLayout;

public class LoggerConfigurationHelper {
    public static void initializeLogger() {
        PatternLayout layout = new PatternLayout();
        String conversionPattern = "%-7p %d [%t] %c %x - %m%n";
        layout.setConversionPattern(conversionPattern);

        FileAppender fileAppender = new FileAppender();
        fileAppender.setFile("rollverlay.log");
        fileAppender.setLayout(layout);
        fileAppender.activateOptions();

        Logger rootLogger = Logger.getRootLogger();
        rootLogger.setLevel(Level.DEBUG);
        rootLogger.addAppender(fileAppender);
    }
}
