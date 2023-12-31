package com.github.archan0621;

import java.io.IOException;

public class DiscordLogger {

    public static String webhookUrl = "https://your_webhook_url";

    private static DiscordLogger instance;

    private DiscordLogger() {}

    public static DiscordLogger instance() {
        if (instance == null) {
            instance = new DiscordLogger();
        }
        return instance;
    }

    public void send(String message) {
        try {
            LogProvider.instance().build(message);
        } catch (IOException e) {
            System.out.println("e = " + e);
        }
    }

    public void send(String title, String message) {
        try {
            LogProvider.instance().build(title, message);
        } catch (IOException e) {
            System.out.println("e = " + e);
        }
    }

    public void send(String message, Scope scope) {
        try {
            LogProvider.instance().build(message, scope);
        } catch (IOException e) {
            System.out.println("e = " + e);
        }
    }

    public void send(String title, String message, Scope scope) {
        try {
            LogProvider.instance().build(title, message, scope);
        } catch (IOException e) {
            System.out.println("e = " + e);
        }
    }


    public static class Url {
        public static String getUrl() {
            return webhookUrl;
        }
    }

}
