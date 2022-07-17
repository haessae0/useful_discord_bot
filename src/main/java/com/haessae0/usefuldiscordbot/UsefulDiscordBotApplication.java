package com.haessae0.usefuldiscordbot;

import com.haessae0.usefuldiscordbot.listener.CommandListener;
import net.dv8tion.jda.api.OnlineStatus;
import net.dv8tion.jda.api.entities.Activity;
import net.dv8tion.jda.api.sharding.DefaultShardManagerBuilder;
import net.dv8tion.jda.api.sharding.ShardManager;

import javax.security.auth.login.LoginException;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class UsefulDiscordBotApplication {

    public ShardManager shardMan;

    public UsefulDiscordBotApplication() throws LoginException, IllegalArgumentException {
//        JDABuilder builder = JDABuilder.createDefault(Token.token);
        DefaultShardManagerBuilder builder = DefaultShardManagerBuilder.createDefault(Token.token);

        builder.setActivity(Activity.listening("도움말 명령어는 hi!help"));
        builder.setStatus(OnlineStatus.ONLINE);

        builder.addEventListeners(new CommandListener());

        shardMan = builder.build();
        System.out.println("온라인");

        shutdown();
    }

    public static void main(String[] args) {
        try {
            new UsefulDiscordBotApplication();
        } catch (LoginException | IllegalArgumentException e) {
            e.printStackTrace();
        }
    }

    public void shutdown() {
        new Thread(() -> {

            String line = "";
            BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
            try {
                while ((line = reader.readLine()) != null) {
                    if (line.equalsIgnoreCase("exit")) {
                        if (shardMan != null) {
                            shardMan.setStatus(OnlineStatus.OFFLINE);
                            shardMan.shutdown();
                            System.out.println("오프라인");
                        }
                    } else {
                        System.out.println("exit으로 종료하세요");
                    }
                    reader.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }

        }).start();
    }
}
