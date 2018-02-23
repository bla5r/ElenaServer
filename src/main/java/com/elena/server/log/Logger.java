package com.elena.server.log;

/**
 * Created by bla5r on 12/03/2017.
 */

public class Logger {

    static public void info(String message) {
        System.err.println("[INFO] " + message);
    }

    static public void warning(String message) {
        System.err.println("[WARNING] " + message);
    }

    static public void error(String message) {
        System.err.println("[ERROR] " + message);
    }

}
