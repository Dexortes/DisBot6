package org.dexortes.disbot.Commands;

import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;

public class CommandManager extends ListenerAdapter {
    @Override
    public void onSlashCommandInteraction(SlashCommandInteractionEvent event) {
        String command = event.getName();
        if(command.equals("/greetings!")){
            String userTag = event.getUser().getAsTag();
            event.reply("Hey-hey, **" + userTag + "**").queue();
        } else if (command.equals("/charge")) {
            event.reply("Rolling... ka-boom!").queue();
        }
    }
}

