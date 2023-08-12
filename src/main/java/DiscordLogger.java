import java.io.IOException;

public class DiscordLogger {

    private static String webhookUrl = "https://your_webhook_url";

    private static DiscordLogger instance;

    private DiscordLogger() {}

    protected static DiscordLogger instance() {
        if (instance == null) {
            instance = new DiscordLogger();
        }
        return instance;
    }

    private void send(String message) {
        try {
            LogProvider.instance().build(message);
        } catch (IOException e) {
            System.out.println("e = " + e);
        }
    }

    private void send(String title, String message) {
        try {
            LogProvider.instance().build(title, message);
        } catch (IOException e) {
            System.out.println("e = " + e);
        }
    }

    private void send(String message, Scope scope) {
        try {
            LogProvider.instance().build(message, scope);
        } catch (IOException e) {
            System.out.println("e = " + e);
        }
    }

    private void send(String title, String message, Scope scope) {
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
