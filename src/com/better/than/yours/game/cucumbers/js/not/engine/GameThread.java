package com.better.than.yours.game.cucumbers.js.not.engine;



/**
 * Created by mati on 2016-05-12.
 */
public class GameThread implements Runnable {

    Engine engine;

    //constructor accept Engine as parameter, because is thread only for that Engine;
    GameThread(Engine engine)
    {

        this.engine = engine;
    }

    @Override
    public void run(){
        gameThread();
    }

    //main game thread;
    void gameThread(){
        int pauseTime = 1000;
        int timeBeforeStable = 400;
        //need change = isRunning is always true, we only kill the Thread;
        while (engine.isRunning){
            engine.checkEach();
            engine.observer.push();
            engine.view.draw();
            try {
                System.out.println(engine.getPopulation());
                Thread.sleep(timeBeforeStable);
                engine.view.drawStable();
                Thread.sleep(pauseTime - timeBeforeStable);
                if (engine.view != null){
                    engine.view.refresh();
                }
            } catch (InterruptedException e) { //why i catch it? Ask Intellij;
                //show what goes wrong, good idea;
                e.printStackTrace();
                //or no, it is unused;
            }
        }
    }

}
