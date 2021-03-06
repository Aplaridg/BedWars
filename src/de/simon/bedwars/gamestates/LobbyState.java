package de.simon.bedwars.gamestates;

import de.simon.bedwars.countdowns.LobbyCountdown;

public class LobbyState extends GameState {

    public static final int MIN_PLAYERS = 1,
                            MAX_PLAYERS = 4;

    private LobbyCountdown lobbyCountdown;


    @Override
    public void start() {
        lobbyCountdown = new LobbyCountdown();
        lobbyCountdown.idle();

    }

    @Override
    public void stop() {

    }

    public LobbyCountdown getLobbyCountdown() {
        return lobbyCountdown;
    }
}
