package FileWork;


public class ConfigFile {
    private String discordToken;

    public void setDiscordToken(String discordToken) {
        this.discordToken = discordToken;
    }

    public String getDiscordToken() {
        return discordToken;
    }

    ConfigFile(String discordToken) {
        this.discordToken = discordToken;
    }
}
