package com.company;

import java.util.List;
import java.util.stream.IntStream;


public class Game {
    public void play() throws InterruptedException {
        GameState state = new GameState();
        List<PlayerThread> players = IntStream
                .rangeClosed(1,456)
                .mapToObj((x) -> new PlayerThread(state,x))
                .peek(Thread::start)
                .toList();
        GirlThread girl = new GirlThread(state,players);
        girl.start();
        girl.join();
        System.out.println(players.stream().filter((x)->x.isAlive).count());
    }
}
