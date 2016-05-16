package com.better.than.yours.game.cucumbers.js.not.controllers;

import com.better.than.yours.game.cucumbers.js.not.display.Display;
import com.better.than.yours.game.cucumbers.js.not.models.Position;

import java.util.HashMap;
import java.util.HashSet;

/**
 * Created by mati on 2016-05-14.
 */
public class GameViewController {
    private Display display;
    private HashMap<Integer, Position> prevIds;
    private HashMap<Integer, Position> actualIds;
    private HashSet<Position> prevRedraw;
    private HashSet<Position> actualRedraw;
    public GameViewController(Display display){
        this.display = display;
        this.prevIds = new HashMap<>();
        this.actualIds = new HashMap<>();
        this.prevRedraw = new HashSet<>();
        this.actualRedraw = new HashSet<>();
    }
    public void add(Position position, int id){
        if (prevIds.containsKey(id)){
            position.setIsNew(false);
        } else {
            position.setIsNew(true);
            actualRedraw.add(position);
        }
        actualIds.put(id, position);
    }

    public void draw() {
        prevIds.forEach((k, v) ->
                display.draw(v.getX(), v.getY(), v.isNew()));
    }

    public void drawStable() {
        prevRedraw.forEach((e) ->
                display.draw(e.getX(), e.getY(), false)
        );
    }

    public void refresh() {
        display.clear();
        prevIds = new HashMap<>(actualIds);
        actualIds = new HashMap<>();
        prevRedraw = new HashSet<>(actualRedraw);
        actualRedraw = new HashSet<>();
    }
}
