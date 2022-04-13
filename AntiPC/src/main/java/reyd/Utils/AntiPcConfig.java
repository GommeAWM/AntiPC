package reyd.Utils;

import cn.nukkit.utils.Config;
import reyd.AntiPc;

import java.io.File;

public class AntiPcConfig {

    private AntiPc antiPc;
    private File file;
    private Config config;

        public AntiPcConfig(AntiPc antiPc){
        this.antiPc = AntiPc.getInstance();
        this.file = new File(antiPc.getDataFolder(), "/config.yml");
        this.config = new Config(this.file, Config.YAML);
    }

    public void createDefaultConfig(){
        this.addDefault("options.messages.add", "§aYou have given the player §e{name} §aa license to play on PC!");
        this.addDefault("options.messages.remove", "§cYou have successfully deactivated the license for §e{name}");
        this.addDefault("options.messages.usg", "§c/Usage: §f/antipc <Nickname>");
        this.addDefault("options.messages.license", "§cYou don't have license to join Server on PC");
        this.addDefault("options.messages.permission", "§cYou need Permission");
        this.addDefault("options.command.description", "§cBow Teleportation");
    }


    public String join() {
        return this.config.getString("options.messages.add");
    }

    public String quit() {
        return this.config.getString("options.messages.remove");
    }

    public String usg() {
        return this.config.getString("options.messages.usg");
    }

    public String license() {
        return this.config.getString("options.messages.license");
    }

    public String permission() {
        return this.config.getString("options.messages.permission");
    }

    public String descr() {
        return this.config.getString("options.command.description");
    }

    public void addDefault(String path, Object object){
        if(!this.config.exists(path)){
            this.config.set(path, object);
            this.config.save(this.file);
        }
    }

}
