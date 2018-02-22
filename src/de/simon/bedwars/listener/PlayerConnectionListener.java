package de.simon.bedwars.listener;

import de.simon.bedwars.gamestates.GameStateManager;
import de.simon.bedwars.gamestates.LobbyState;
import de.simon.bedwars.main.Main;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;

public class PlayerConnectionListener implements Listener {

    private GameStateManager gameStateManager;

    public PlayerConnectionListener(GameStateManager gameStateManager) {
        this.gameStateManager = gameStateManager;
    }


    @EventHandler
    public void handelPlayerJoin(PlayerJoinEvent e) {
        Player p = e.getPlayer();
        if(gameStateManager.getCurrentGameState() instanceof LobbyState) {
            LobbyState lobbyState = (LobbyState) gameStateManager.getCurrentGameState();
            Main.getPlugin().getPlayers().add(p);
            e.setJoinMessage(Main.PREFIX + "§6" + p.getName() + "§a hat das Spiel betreten. §7[" +
                            Main.getPlugin().getPlayers().size() + "/" + LobbyState.MAX_PLAYERS + "]");

            if(Main.getPlugin().getPlayers().size() >= LobbyState.MIN_PLAYERS) {
                if(!lobbyState.getLobbyCountdown().isRunning()) {
                    if(lobbyState.getLobbyCountdown().isIdling())
                        lobbyState.getLobbyCountdown().cancelIdle();;
                        lobbyState.getLobbyCountdown().run();
                }
            }
        }
    }

    @EventHandler
    public void handelPlayerQuit(PlayerQuitEvent e) {
        Player p = e.getPlayer();
        if(gameStateManager.getCurrentGameState() instanceof LobbyState) {
            LobbyState lobbyState = (LobbyState) gameStateManager.getCurrentGameState();
            Main.getPlugin().getPlayers().remove(p);
            e.setQuitMessage(Main.PREFIX + "§6" + p.getName() + "§a hat das Spiel verlassen. §7[" +
                            Main.getPlugin().getPlayers().size() + "/" + LobbyState.MAX_PLAYERS + "]");

            if(Main.getPlugin().getPlayers().size() < LobbyState.MAX_PLAYERS) {
                if(lobbyState.getLobbyCountdown().isRunning()) {
                    lobbyState.getLobbyCountdown().cancel();
                    if(!lobbyState.getLobbyCountdown().isIdling())
                        lobbyState.getLobbyCountdown().idle();
                }
             }
        }
    }

}
