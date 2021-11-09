package com.company;

public class GameState {
    WatchStatus status = WatchStatus.RED_LIGHT;
    final int aimDistance = 50;

    public void setStatus(WatchStatus status) {
        this.status = status;
    }
}
