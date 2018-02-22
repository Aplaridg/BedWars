package de.simon.bedwars.main;

import de.simon.bedwars.commands.StartCommand;
import de.simon.bedwars.gamestates.GameState;
import de.simon.bedwars.gamestates.GameStateManager;
import de.simon.bedwars.listener.PlayerConnectionListener;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;
import org.omg.CORBA.NO_PERMISSION;

import java.util.ArrayList;

public class Main extends JavaPlugin {

    public static final String PREFIX = "§7[§cBedWars§7] ",
                               NO_PERMISSION = Main.PREFIX + "§cDazu hast du keine Rechte!";


    private static Main plugin;

    private GameStateManager gameStateManager;
    private ArrayList<Player> players;

    public void onEnable() {
        plugin = this;
        gameStateManager = new GameStateManager();
        players = new ArrayList<>();
        gameStateManager.setGameState(GameState.LOBBY_STATE);

        init();
        System.out.println("[Bedwars] wurde gestartet!");

    }

    private void init() {
        getCommand("start").setExecutor(new StartCommand());

        PluginManager pm = Bukkit.getPluginManager();
        pm.registerEvents(new PlayerConnectionListener(gameStateManager), this);

    }

    public static Main getPlugin() {
        return plugin;
    }

    public GameStateManager getGameStateManager() {
        return gameStateManager;
    }

    public ArrayList<Player> getPlayers() {
        return players;
    }
}
