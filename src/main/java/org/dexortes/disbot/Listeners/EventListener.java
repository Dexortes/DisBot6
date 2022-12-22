package org.dexortes.disbot.Listeners;

import net.dv8tion.jda.api.entities.Member;
import net.dv8tion.jda.api.entities.MessageReaction;
import net.dv8tion.jda.api.entities.Role;
import net.dv8tion.jda.api.entities.User;
import net.dv8tion.jda.api.entities.channel.Channel;
import net.dv8tion.jda.api.events.guild.GuildJoinEvent;
import net.dv8tion.jda.api.events.guild.GuildReadyEvent;
import net.dv8tion.jda.api.events.guild.member.GuildMemberRoleAddEvent;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import net.dv8tion.jda.api.events.message.react.MessageReactionAddEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import net.dv8tion.jda.api.entities.channel.concrete.TextChannel;
import net.dv8tion.jda.api.interactions.commands.build.CommandData;
import net.dv8tion.jda.api.interactions.commands.build.Commands;
import net.dv8tion.jda.api.requests.GatewayIntent;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;


public class EventListener extends ListenerAdapter {
//    @Override
//    public void onMessageReactionAdd(MessageReactionAddEvent event) {
//        //Souper reaction is here! in #general
//        User currentUser = event.getUser();
//        String emoji = event.getReaction().getEmoji().getAsReactionCode();
//        String channelMention = event.getChannel().getAsMention();
//        Channel channel = event.getChannel();
//        String jumpLink = event.getJumpUrl();
//        assert currentUser != null;
//        String message = currentUser.getAsMention() + " you've reacted with " + emoji + " in the channel " + channelMention;
//        //event.getGuild().getDefaultChannel().sendMessage(message); //doesn't work
//        event.getGuild().getTextChannelsByName("main", true).get(0).sendMessage(message).queue();
//    }

    @Override
    public void onMessageReceived(MessageReceivedEvent event) {
        String message = event.getMessage().getContentRaw();
        if(message.contains("$tudy")){
            event.getChannel().sendMessage("$oup!").queue();
        }
        String userMention = Objects.requireNonNull(event.getMember()).getAsMention();
        switch(message){
            case "Charge!" -> event.getChannel().sendMessage(userMention + " ordered to attack. Banelings are charging towards the target!").queue();
            case "Move" -> event.getChannel().sendMessage(userMention + " ordered to move. Banelings grouped up and moving to the destination.").queue();
            case "Borrow" -> event.getChannel().sendMessage(userMention + " ordered to borrow. Banelings borrowed and wait to explode").queue();
//            case "Help" -> event.getChannel().sendMessage("Hey " + userMention + "! If you want help you may check " +
//                     Objects.requireNonNull(event.getGuild().getTextChannelById("878232879329583134")).getAsMention() + " channel").queue();
            case "!help" -> event.getChannel().sendMessage("Hey " + userMention + "! If you're just arrived check out our " +
                    Objects.requireNonNull(event.getGuild().getTextChannelById("1043022332551254056")).getAsMention() + " channel").queue();
            case "!rules" -> event.getChannel().sendMessage("Hey " + userMention + "! If you want to check our rules go to " +
                    Objects.requireNonNull(event.getGuild().getTextChannelById("988498600818540594")).getAsMention() + " channel").queue();
            case "!qotd" -> event.getChannel().sendMessage("Wanna know about Question of the Day? Click on " + Objects.requireNonNull(event.getGuild().getTextChannelById("1047357341340807220")).getAsMention()).queue();
            case "!charge" -> event.getChannel().sendMessage(userMention + " ordered to charge!").queue();
        }
    }

    @Override
    public void onGuildJoin(GuildJoinEvent event) {
        List<CommandData> commandsList = new ArrayList<>();
        commandsList.add(Commands.slash("greetings", "Get welcomed by the bot"));
        commandsList.add(Commands.slash("charge", "Banes are charging towards the target"));
        event.getGuild().updateCommands().addCommands(commandsList).queue();
    }

    @Override
    public void onGuildReady(GuildReadyEvent event) {
        List<CommandData> commandsList = new ArrayList<>();
        commandsList.add(Commands.slash("/greetings", "Get welcomed by the bot"));
        commandsList.add(Commands.slash("charge", "Banes are charging towards the target"));
        event.getGuild().updateCommands().addCommands(commandsList).queue();


    }
}
