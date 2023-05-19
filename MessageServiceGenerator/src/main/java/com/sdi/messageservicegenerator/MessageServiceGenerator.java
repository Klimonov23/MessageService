package com.sdi.messageservicegenerator;

import com.google.gson.Gson;
import org.apache.http.client.fluent.Content;
import org.apache.http.client.fluent.Request;
import org.apache.http.entity.ContentType;

import java.io.IOException;
import java.util.Random;
import java.util.Scanner;

public class MessageServiceGenerator {
    private static final Gson GSON=new Gson();
    public static String getRandomString(int length) {
        String alphabet = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz";
        StringBuilder sb = new StringBuilder();
        Random random = new Random();

        for (int i = 0; i < length; i++) {
            int index = random.nextInt(alphabet.length());
            char randomChar = alphabet.charAt(index);
            sb.append(randomChar);
        }
        return sb.toString();
    }
    public static void main(String[] args) throws IOException {
        Scanner scanner=new Scanner(System.in);
        Random random = new Random();
        System.out.println("Enter the number of applications");
        int number=scanner.nextInt();
        for (int i = 0; i < number; i++) {
            String message=getRandomString(random.nextInt(10));
            String key="priority";
            Integer priority=random.nextInt(10);
            Integer delay=random.nextInt(1000);
            Boolean hasCallBack=random.nextBoolean();
            Message mess=new Message(message,key,priority,delay,hasCallBack);
            final Content postResult = Request.Post("http://localhost:8080/emit")
                    .bodyString(GSON.toJson(mess), ContentType.APPLICATION_JSON)
                    .execute().returnContent();
        }
        scanner.close();
    }
}
