package com.util;

import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.LogManager;

import java.util.Random;

public class TestService {
    private Logger logger = LogManager.getLogger(TestService.class.getName());

    private String[] messages = new String[] {
            "Hello, World",
            "Goodbye Cruel World",
            "You had me at hello"
    };
    private Random rand = new Random(1);

    public void setMessages(String[] messages) {
        logger.traceEntry();
        this.messages = messages;
        logger.traceExit();
    }

    public String[] getMessages() {
        logger.traceEntry();
        return logger.traceExit(messages);
    }

    public String retrieveMessage() {
        logger.entry();

        String testMsg = getMessage(getKey());

        return logger.exit(testMsg);
    }

    public void exampleException() {
        logger.entry();
        try {
            String msg = messages[messages.length];
            logger.error("An exception should have been thrown");
        } catch (Exception ex) {
            logger.error("1111",ex);
            ex.printStackTrace();
        }
        logger.exit();
    }

    public String getMessage(int key) {
        logger.entry(key);

        String value = messages[key];

        return logger.exit(value);
    }

    private int getKey() {
        logger.entry();
        int key = rand.nextInt(messages.length);
        return logger.exit(key);
    }
}