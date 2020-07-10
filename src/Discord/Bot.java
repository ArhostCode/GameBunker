package Discord;

import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.JDABuilder;

import javax.security.auth.login.LoginException;


public class Bot extends Thread{

    private String token;

    public Bot(String token){
        this.token = token;
    }


    @Override
    public void run() {
        System.out.println(token);
        JDA jda = null;
        try {
            jda = JDABuilder.createDefault(token).build();
        } catch (LoginException e) {
            e.printStackTrace();
        }
        assert jda != null;
        jda.addEventListener(new MessageListener());
    }
}
