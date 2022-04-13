package reyd;

import cn.nukkit.command.SimpleCommandMap;
import cn.nukkit.plugin.PluginBase;
import cn.nukkit.plugin.PluginManager;
import cn.nukkit.utils.Config;
import reyd.Command.AntiPcCMD;
import reyd.Listener.AntiPcListener;
import reyd.Utils.AntiPcConfig;

import java.io.File;

public class AntiPc extends PluginBase {

    private static AntiPcConfig antiPcConfig;

    private static AntiPc instance;

    @Override
    public void onEnable() {
        antiPcConfig = new AntiPcConfig(this);
        antiPcConfig.createDefaultConfig();
        instance = this;
        register();
        this.getLogger().info("§fEnable: §aAntiPC");

        Config playerconfig = new Config(new File(AntiPc.getInstance().getDataFolder(), "/playerconfig.yml"), Config.YAML);
        playerconfig.reload();
    }

    @Override
    public void onDisable() {
        this.getLogger().info("§fDisable: §cAntiPC");
    }

    private void register(){
        SimpleCommandMap simpleCommandMap = getServer().getCommandMap();
        simpleCommandMap.register("help", new AntiPcCMD("antipc", AntiPc.antiPcConfig.descr()));

        PluginManager pluginManager = getServer().getPluginManager();
        pluginManager.registerEvents(new AntiPcListener(), this);
    }

    public static AntiPc getInstance(){
        return instance;
    }

    public static AntiPcConfig getAuctionConfig(){
        return antiPcConfig;
    }

}
