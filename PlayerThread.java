package com.company;

import java.util.Random;

public class PlayerThread extends Thread{
    final GameState state;
    public boolean isAlive;
    public int currentDistance;
    public int name;
    public boolean isMoving;
    public boolean isWin;

    public PlayerThread(GameState state, int name) {
        this.state = state;
        isAlive = true;
        currentDistance = 0;
        isMoving = false;
        this.name = name;
        isWin = false;
    }
    private void startMove(){
        isMoving = true;
        currentDistance += new Random().nextInt(2);
        if(currentDistance>state.aimDistance){
            isMoving = false;
            isWin = true;
            interrupt();
        }
    }
    private void stopMove() throws InterruptedException {
        sleep(new Random().nextLong(150,250));
        isMoving = false;

    }
    public void kill(){
        isAlive = false;
        isMoving = false;
        interrupt();
    }
    public void run(){
        while (!isInterrupted()){
            try {
                if(state.status == WatchStatus.GREEN_LIGHT){
                    startMove();
                } else {
                    stopMove();
                }
                sleep(300);
            } catch (InterruptedException e) {
                interrupt();
            }
        }
    }
}
