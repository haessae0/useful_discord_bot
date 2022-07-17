package com.haessae0.usefuldiscordbot.listener;

import net.dv8tion.jda.api.entities.*;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;

import java.text.SimpleDateFormat;
import java.util.Calendar;

public class CommandListener extends ListenerAdapter {

    @Override
    public void onMessageReceived(MessageReceivedEvent e) {
        User author = e.getAuthor();
        Message message = e.getMessage();

        String msg = message.getContentDisplay();

        if (e.isFromType(ChannelType.TEXT)) {
            TextChannel channel = e.getTextChannel();
            Guild guild = e.getGuild();
            Member member = e.getMember();

            String name;

            //!tut arg0 arg1 ....
            if (msg.startsWith("hi!tut ")) {
                String[] args = msg.substring(7).split(" ");

                if (args.length == 1) {
                    if (args[0].equalsIgnoreCase("hi")) {
                        channel.sendMessage("안녕 " + e.getMember().getAsMention() + "!").queue();
                    } else if (args[0].equalsIgnoreCase("time")) {
                        Calendar cal = Calendar.getInstance();
                        SimpleDateFormat sdf = new SimpleDateFormat("YYYY-MM-dd HH-mm-ss");

                        channel.sendMessage("지금 시간은 " + sdf.format(cal.getTime()) + "!!").queue();
                    }
                }
            }

            if (message.isWebhookMessage()) {
                name = author.getName();
            } else {
                assert member != null;
                name = member.getEffectiveName();
            }
            System.out.printf("(%s)<%s>: %s\n", guild.getName(), name, msg);
        } else if (e.isFromType(ChannelType.PRIVATE)) {
            System.out.printf("<%s>: %s\n", author.getName(), msg);
        }
    }
}
