package com.dtorres.api.pokedex.commons.log;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class LogErrorManager {

    private static final Logger LOGGER = LoggerFactory.getLogger(LogErrorManager.class);

    public static void error(Throwable throwable) {
        error(throwable.getMessage(), throwable);
    }

    public static void error(String message, Throwable throwable) {
        LOGGER.error(message, throwable);
    }

}
