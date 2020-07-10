package Discord;

import Core.GameCore;
import net.dv8tion.jda.api.entities.MessageChannel;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;

public class MessageListener extends ListenerAdapter {

    private static ArrayList<MessageChannel> messageChannels;

    MessageListener() {
        messageChannels = new ArrayList<>();
    }

    @Override
    public void onMessageReceived(@NotNull MessageReceivedEvent event) {

        if (event.getMessage().getContentRaw().toLowerCase().equals("play")) {
            messageChannels.add(event.getMessage().getChannel());
            System.out.println(event.getMessage().getContentRaw());
        }
        if (event.getMessage().getAuthor().isBot())
            return;
        if(event.getMessage().getContentRaw().equals("start")){
            GameCore gameCore = new GameCore("GamePresets");
            Thread game = new Thread(gameCore);
            game.start();
        }

    }

    public static ArrayList<MessageChannel> getMessageChannels() {
        return messageChannels;
    }

}
