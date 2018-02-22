package de.simon.bedwars.gamestates;

import de.simon.bedwars.main.Main;
import org.bukkit.Bukkit;

public class IngameState extends GameState {


    @Override
    public void start() {
        Bukkit.broadcastMessage(Main.PREFIX + "§aDer Countdown ist beendet!");
        Bukkit.broadcastMessage(Main.PREFIX + "§6Alle Spieler werden teleportiert!");

    }

    @Override
    public void stop() {

    }




}
