package com.company;

import java.util.List;
import java.util.Random;
import java.util.stream.Stream;

public class GirlThread extends Thread{
    final GameState state;
    List<PlayerThread> players;
    public GirlThread(GameState state, List<PlayerThread> players) {
        this.state = state;
        this.players = players;
    }
    private void sayStatus(WatchStatus status){
        this.state.setStatus(status);
        System.out.println(status.name());
    }
    public void run(){
        while(players.stream().anyMatch((x)->!x.isInterrupted())){
            try {
                sayStatus(WatchStatus.GREEN_LIGHT);
                sleep(new Random().nextLong(1000,4000));
                sayStatus(WatchStatus.RED_LIGHT);
                sleep(500);
                long killedCount = players.stream().filter((x)->x.isMoving).peek(PlayerThread::kill).count();
                System.out.println(killedCount + " убиты");
                System.out.println(players.stream().filter((x)->x.isAlive).count() + " живы");
                System.out.println(players.stream().filter((x)->x.isWin).count() + " победили");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

        }

    }
}
