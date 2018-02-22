package de.simon.bedwars.commands;

import de.simon.bedwars.gamestates.LobbyState;
import de.simon.bedwars.main.Main;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class StartCommand implements CommandExecutor{

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {

        if(sender instanceof Player) {

            Player p = (Player) sender;
            if(p.hasPermission("bw.start")) {
                if(args.length == 0) {

                    if(Main.getPlugin().getGameStateManager().getCurrentGameState() instanceof LobbyState) {
                        LobbyState ls = (LobbyState) Main.getPlugin().getGameStateManager().getCurrentGameState();
                        if(ls.getLobbyCountdown().isRunning()) {
                            if(!ls.getLobbyCountdown().isStarting()) {
                                ls.getLobbyCountdown().setSeconds(3);
                                ls.getLobbyCountdown().setStarting(true);

                            } else
                                p.sendMessage(Main.PREFIX + "§cDas Spiel §6startet §cbereits!");
                        } else
                            p.sendMessage(Main.PREFIX + "§cEs fehlen noch §6Spieler §cbis zum Start des Countdowns!");
                    } else
                        p.sendMessage(Main.PREFIX + "§cBitte führe diesen §6Command §cnur in der Lobby aus!");

                } else
                    p.sendMessage(Main.PREFIX + "§cBitte benutze §6/start §c!");
            } else {
                p.sendMessage(Main.NO_PERMISSION);
            }

        }



        return false;
    }
}
