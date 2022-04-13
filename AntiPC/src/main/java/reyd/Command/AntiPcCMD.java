package reyd.Command;

import cn.nukkit.Player;
import cn.nukkit.command.Command;
import cn.nukkit.command.CommandSender;
import cn.nukkit.utils.Config;
import reyd.AntiPc;

import java.io.File;
import java.util.List;

public class AntiPcCMD extends Command {

    public AntiPcCMD(String cmd, String descr){
        super(cmd, descr);
    }

    @Override
    public boolean execute(CommandSender commandSender, String s, String[] args) {

        Config playerconfig = new Config(new File(AntiPc.getInstance().getDataFolder(), "/playerconfig.yml"), Config.YAML);
        playerconfig.reload();

        List<String> playersarray = playerconfig.getStringList("Auction");

        if (commandSender instanceof Player){
            Player player = (Player) commandSender;

            if (!player.hasPermission("reyd.antipc")){
                player.sendMessage(AntiPc.getAuctionConfig().permission());
                return true;
            }

            if (args.length == 1){
                String first = args[0].toLowerCase();

                if (first.equals("info")){
                    player.sendMessage("§b[§aAntiPC§b] §fThe owner of this plugin is §aDaniel Reydovich §b(§eGommeAWM§b) §7// §cxxtdaniel");
                    return true;
                }

                if (!playersarray.contains(first)) {
                    playersarray.add(first);
                    playerconfig.set("Auction", playersarray);
                    playerconfig.save();
                    player.sendMessage(AntiPc.getAuctionConfig().join().replace("{name}", first));
                } else {
                    playersarray.remove(first);
                    playerconfig.set("Auction", playersarray);
                    playerconfig.save();
                    commandSender.sendMessage(AntiPc.getAuctionConfig().quit().replace("{name}", first));
                }

            } else {
                player.sendMessage(AntiPc.getAuctionConfig().usg());
            }

        } else {

            if (args.length == 1){
                String first = args[0].toLowerCase();

                if (!playersarray.contains(first)) {
                    playersarray.add(first);
                    playerconfig.set("Auction", playersarray);
                    playerconfig.save();
                    commandSender.sendMessage(AntiPc.getAuctionConfig().join().replace("{name}", first));
                } else {
                    playersarray.remove(first);
                    playerconfig.set("Auction", playersarray);
                    playerconfig.save();
                    commandSender.sendMessage(AntiPc.getAuctionConfig().quit().replace("{name}", first));
                }

            } else {
                commandSender.sendMessage(AntiPc.getAuctionConfig().usg());
            }

        }

        return true;
    }
}
