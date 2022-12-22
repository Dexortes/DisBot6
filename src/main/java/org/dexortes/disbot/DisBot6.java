package org.dexortes.disbot;

import javax.security.auth.login.LoginException;
import io.github.cdimascio.dotenv.Dotenv;
import org.dexortes.disbot.Listeners.EventListener;
import org.dexortes.disbot.Commands.CommandManager;
import net.dv8tion.jda.api.OnlineStatus;
import net.dv8tion.jda.api.entities.Activity;
import net.dv8tion.jda.api.requests.GatewayIntent;
import net.dv8tion.jda.api.sharding.DefaultShardManagerBuilder;
import net.dv8tion.jda.api.sharding.ShardManager;
import net.dv8tion.jda.api.utils.ChunkingFilter;
import net.dv8tion.jda.api.utils.MemberCachePolicy;
import net.dv8tion.jda.api.utils.cache.CacheFlag;
import org.dexortes.disbot.Listeners.ReactionRoles;

import javax.security.auth.login.LoginException;
public class DisBot6 {
    private final Dotenv config;
    private final ShardManager shardManager;

    public DisBot6() throws LoginException {
        config = Dotenv.configure().load();
        String token = config.get("TOKEN");
        DefaultShardManagerBuilder builder = DefaultShardManagerBuilder.createDefault(token);
        builder.setActivity(Activity.playing("Spawning Pool Party!"))
                .setStatus(OnlineStatus.ONLINE)
                .enableIntents(GatewayIntent.MESSAGE_CONTENT, GatewayIntent.GUILD_MEMBERS)
                .setMemberCachePolicy(MemberCachePolicy.ALL)
                .setChunkingFilter(ChunkingFilter.ALL)
                .enableCache(CacheFlag.EMOJI);

        shardManager = builder.build();

        shardManager.addEventListener(new EventListener(), new CommandManager(), new ReactionRoles());
    }

    public Dotenv getConfig(){
        return config;
    }

    public ShardManager getShardManager() {
        return shardManager;
    }

    public static void main(String[] args) {
        try{
            DisBot6 bot = new DisBot6();
        } catch (LoginException e) {
            System.out.println("ERROR: Token is invalid");
        }
    }
}