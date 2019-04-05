package com.efgh.avraelayout.utils;

import org.apache.log4j.Level;
import org.apache.log4j.Logger;
import org.apache.log4j.PatternLayout;
import org.apache.log4j.RollingFileAppender;

public class LoggerConfigurationHelper {
    public static void initializeLogger() {
        PatternLayout layout = new PatternLayout();
        String conversionPattern = "%-7p %d [%t] %c %x - %m%n";
        layout.setConversionPattern(conversionPattern);

        RollingFileAppender fileAppender = new RollingFileAppender();
        fileAppender.setFile("rollverlay.log");
        fileAppender.setLayout(layout);
        fileAppender.setMaxFileSize("5MB");
        fileAppender.setMaxBackupIndex(1);
        fileAppender.activateOptions();

        Logger rootLogger = Logger.getRootLogger();
        rootLogger.setLevel(Level.DEBUG);
        rootLogger.addAppender(fileAppender);
    }
}
