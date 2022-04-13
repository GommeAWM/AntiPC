package reyd.Listener;

import cn.nukkit.Player;
import cn.nukkit.Server;
import cn.nukkit.event.EventHandler;
import cn.nukkit.event.Listener;
import cn.nukkit.event.player.PlayerJoinEvent;
import cn.nukkit.event.player.PlayerLoginEvent;
import cn.nukkit.event.player.PlayerPreLoginEvent;
import cn.nukkit.form.element.ElementButton;
import cn.nukkit.scheduler.Task;
import cn.nukkit.scheduler.TaskHandler;
import cn.nukkit.utils.Config;
import reyd.AntiPc;

import java.io.File;
import java.util.Map;

public class AntiPcListener implements Listener {
    @EventHandler
    public void onJoin(PlayerPreLoginEvent event){
        Player player = event.getPlayer();

        if (isPC(player) && !checkList(player)){
            kick(player);
        }

    }

    public void kick(Player player){
        player.kick(AntiPc.getAuctionConfig().license());
    }

    public boolean checkList(Player player){
        Config playerconfig = new Config(new File(AntiPc.getInstance().getDataFolder(), "/playerconfig.yml"), Config.YAML);
        playerconfig.reload();

        for(String entry : playerconfig.getStringList("Auction")){
            if (entry.equals(player.getName().toLowerCase())){
                return true;
            }
        }

        return false;
    }

    public boolean isPC(Player player){

        if (player.getLoginChainData().getDeviceOS() == 7 || player.getLoginChainData().getDeviceOS() == 8){
            return true;
        }

        return false;
    }

}
