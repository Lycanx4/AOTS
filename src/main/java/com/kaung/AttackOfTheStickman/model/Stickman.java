package com.kaung.AttackOfTheStickman.model;

import lombok.Data;

@Data
public class Stickman {
    private String id;
    private int stage;
    private boolean isAlive;

    public Stickman(String id){
        this.id = id;
        this.stage = 0;
        this.isAlive = true;
    }

    public Stickman(){
        this.id = "Dead";
        this.stage = -1;
        this.isAlive = false;
    }
    public boolean stageUp() {
        if(stage<8){
            stage++;
            return true;
        }
        return false;
    }

    public boolean stageDown() {
        if(stage==8){
            stage--;
            return true;
        }
        return false;
    }

    public String getId() {
        return id;
    }

    public int getStage() {
        return stage;
    }

    public boolean isAlive() {
        return isAlive;
    }
}
