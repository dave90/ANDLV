package it.unical.mat.andlv.utils;

import java.util.Calendar;
import java.util.logging.Logger;

/**
 * Created by Stefano on 16/09/2015.
 */
public class TimeTracker {

    private final static Logger LOGGER = Logger.getLogger(TimeTracker.class.getName());

    private final static boolean ACTIVE = true;

    public static void logTime(String message) {
        if (ACTIVE)
//            LOGGER.info(message + ":" + Calendar.getInstance().get(Calendar.MILLISECOND));
            LOGGER.info(message + ":" + System.nanoTime());
    }

}
