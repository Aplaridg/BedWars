package de.simon.bedwars.countdowns;

import de.simon.bedwars.gamestates.GameState;
import de.simon.bedwars.gamestates.LobbyState;
import de.simon.bedwars.main.Main;
import org.bukkit.Bukkit;

public class LobbyCountdown extends Countdown {

    private final int RESET_SECONDS =  10;
    private final int IDLE_SECONDS = 20;

    private int idleID;
    private boolean isRunning = false, isIdling = false, isStarting = false;
    private int seconds = 15;

    @Override
    public void run() {
        isRunning = true;

        taskID = Bukkit.getScheduler().scheduleSyncRepeatingTask(Main.getPlugin(), new Runnable() {
            @Override
            public void run() {

                switch(seconds) {
                    case 60: case 30: case 15: case 10: case 5: case 4: case 3: case 2:
                        Bukkit.broadcastMessage(Main.PREFIX + "§aDas Spiel startet in §6" + seconds + " Sekunden§a!");
                        if(seconds == 3)
                            isStarting = true;
                        break;
                    case 1:
                        Bukkit.broadcastMessage(Main.PREFIX + "§aDas Spiel startet in §6einer Sekunde§a!");
                        break;
                    case 0:
                        Main.getPlugin().getGameStateManager().setGameState(GameState.INGAME_STATE);
                        break;

                        default:
                            break;
                }


                seconds--;
            }
        }, 0, 20 * 1);
    }

    @Override
    public void cancel() {
        isRunning = false;

        Bukkit.getScheduler().cancelTask(taskID);
        seconds = RESET_SECONDS;

    }

    public void idle() {
        isIdling = true;

       idleID= Bukkit.getScheduler().scheduleSyncRepeatingTask(Main.getPlugin(), new Runnable() {

           @Override
           public void run() {
               int missingPlayers = LobbyState.MIN_PLAYERS - Main.getPlugin().getPlayers().size();
               if(missingPlayers != 1)
                   Bukkit.broadcastMessage(Main.PREFIX + "§7Es fehlen noch §6" + missingPlayers + " Spieler §7bis zum Start");
               else
                   Bukkit.broadcastMessage(Main.PREFIX + "§7Es fehlt noch §6ein Spieler §7bis zum Start");

           }
       }, IDLE_SECONDS * 20, IDLE_SECONDS *20);
    }

    public void cancelIdle() {
        isIdling = false;

        Bukkit.getScheduler().cancelTask(idleID);
    }


    public boolean isRunning() {
        return isRunning;
    }

    public boolean isIdling() {
        return isIdling;
    }

    public boolean isStarting() {
        return isStarting;
    }

    public void setSeconds(int seconds) {
        this.seconds = seconds;
    }


    public void setStarting(boolean starting) {
        isStarting = starting;
    }
}
