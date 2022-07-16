package com.haessae0.usefuldiscordbot;

import net.dv8tion.jda.api.JDABuilder;
import net.dv8tion.jda.api.OnlineStatus;
import net.dv8tion.jda.api.entities.Activity;

import javax.security.auth.login.LoginException;

public class UsefulDiscordBotApplication {
    public UsefulDiscordBotApplication() throws LoginException, IllegalArgumentException {
        JDABuilder builder = JDABuilder.createDefault(MYTOKEN);

        builder.setActivity(Activity.listening("도움말 명령어는 hi!help"));
        builder.setStatus(OnlineStatus.ONLINE);

        builder.build();
    }

    public static void main(String[] args) {
        try {
            new UsefulDiscordBotApplication();
        } catch (LoginException | IllegalArgumentException e) {
            e.printStackTrace();
        }
    }
}
