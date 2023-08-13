package com.github.archan0621;

import java.io.*;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;

public class LogProvider {

    private static LogProvider instance;

    private LogProvider() {}

    public static LogProvider instance() {
        if (instance == null) {
            instance = new LogProvider();
        }
        return instance;
    }

    String host = DiscordLogger.Url.getUrl();

    public void build(String message) throws IOException {
        URL url = new URL(host);

        String param = "content=" + URLEncoder.encode(message, "UTF-8");

        URLConnection conn = url.openConnection();
        conn.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
        conn.setRequestProperty("Content-Length", Integer.toString(param.length()));
        conn.setRequestProperty("Accept-Charset", "UTF-8");

        conn.setDoOutput(true); // POST 요청을 보낼 것임을 설정

        try (DataOutputStream dos = new DataOutputStream(conn.getOutputStream())) {
            dos.writeBytes(param);
            conn.getInputStream();
        }
    }

    public void build(String message, Scope scope) throws IOException {
        URL url = new URL(host);

        String param = "content=@" + scope.toString() + " " + URLEncoder.encode(message, "UTF-8");

        URLConnection conn = url.openConnection();
        conn.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
        conn.setRequestProperty("Content-Length", Integer.toString(param.length()));

        conn.setDoOutput(true); // POST 요청을 보낼 것임을 설정

        try (DataOutputStream dos = new DataOutputStream(conn.getOutputStream())) {
            dos.writeBytes(param);
            conn.getInputStream();
        }
    }

    public void build(String title, String message) throws IOException {
        URL url = new URL(host);

        String param = "{\n" +
                "  \"embeds\": [{\n" +
                "    \"title\": \""+ title +"\",\n" +
                "    \"description\": \""+ message + "\"\n" +
                "  }]\n" +
                "}";

        URLConnection conn = url.openConnection();
        conn.setRequestProperty("Content-Type", "application/json");
        conn.setRequestProperty("Content-Length", Integer.toString(param.length()));

        conn.setDoOutput(true); // Enable POST request

        try (OutputStream os = conn.getOutputStream()) {
            byte[] input = param.getBytes("utf-8");
            os.write(input, 0, input.length);
        }

        try (InputStream responseStream = conn.getInputStream()) {
            // You can read and process the response here if needed
        }
    }

    public void build(String title, String message, Scope scope) throws IOException {
        URL url = new URL(host);

        String param = "{\n" +
                "  \"embeds\": [{\n" +
                "    \"title\": \"" + title +"\",\n" +
                "    \"description\": \"@" + scope.toString() + " " + message + "\"\n" +
                "  }]\n" +
                "}";

        URLConnection conn = url.openConnection();
        conn.setRequestProperty("Content-Type", "application/json");
        conn.setRequestProperty("Content-Length", Integer.toString(param.length()));

        conn.setDoOutput(true); // Enable POST request

        try (OutputStream os = conn.getOutputStream()) {
            byte[] input = param.getBytes("utf-8");
            os.write(input, 0, input.length);
        }

        try (InputStream responseStream = conn.getInputStream()) {
            // You can read and process the response here if needed
        }
    }

}
