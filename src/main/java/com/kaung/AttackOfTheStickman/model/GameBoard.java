package com.kaung.AttackOfTheStickman.model;

import lombok.Data;

import java.util.*;

@Data
public class GameBoard {
    private Map<String,Stickman> blueArmy = new HashMap<>();
    private Map<String,Stickman> redArmy = new HashMap<>();

    private Team turn;
    
    private int winner = 0;
    
    private boolean isGameEnd;

    public Map<String, Stickman> getBlueArmy() {
        return blueArmy;
    }

    public Map<String, Stickman> getRedArmy() {
        return redArmy;
    }

    private final String  TEAM_BLUE = Team.B.name();
    private final String  TEAM_RED = Team.R.name();

    public GameBoard(){
        isGameEnd = false;
        this.turn = Team.B;
        for(int i = 1; i<=6; i++){
            String blueId = TEAM_BLUE + i;
            String redId = TEAM_RED + i;
            blueArmy.put(blueId, new Stickman(blueId));
            redArmy.put(redId, new Stickman(redId));
        }
    }

    public Team getTurn() {
        return turn;
    }

    public String getCurrentTurnName() {
        if(turn.equals(Team.B)){
            return "Blue";
        }
        return "Red";
    }

    public boolean isGameEnd() {
        return isGameEnd;
    }

    public String getTEAM_BLUE() {
        return TEAM_BLUE;
    }

    public String getTEAM_RED() {
        return TEAM_RED;
    }

    public int rollDice(Enum team){
        Random random = new Random();
        int dice = random.nextInt(6) +1;
        String id = team.name() + dice;
        Stickman stickman;
        if(team.equals(Team.B) && blueArmy.containsKey(id)){
            stickman = blueArmy.get(id);
        }else if(redArmy.containsKey(id)){
            stickman = redArmy.get(id);
        }else{
            return -1;
        }
        if(stickman.stageUp()){
            return 0;
        }else if(stickman.stageDown()){
            return dice;
        }
        return -2;
    }
    public boolean shootTheBlue(String blueId){
        if(blueArmy.containsKey(blueId)){
            blueArmy.remove(blueId);
            return true;
        }
        return false;
    }
    public boolean shootTheRed(String redId){
        if(redArmy.containsKey(redId)){
            redArmy.remove(redId);
            return true;
        }
        return false;
    }
    public void changeTurn(){
        if(turn.equals(Team.B)){
            turn = Team.R;
        }else{
            turn = Team.B;
        }
    }
    public int getWinner() {
        if(blueArmy.size() == 0){
            winner = 2;
        }if(redArmy.size() == 0){
            winner = 1;
        }
        return winner;
    }
}
