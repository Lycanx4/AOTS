package com.kaung.AttackOfTheStickman.controller;

import com.kaung.AttackOfTheStickman.model.GameBoard;
import com.kaung.AttackOfTheStickman.model.Stickman;
import com.kaung.AttackOfTheStickman.model.Team;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

@Controller
public class HomeController {
    GameBoard gameBoard = new GameBoard();
    @GetMapping("/")
    public String home(Model model){
        List<List<Stickman>> teams = new ArrayList<>();
        Stickman deadGuy = new Stickman();

        for(int i=0; i<gameBoard.getArmySize(); i++){
            List<Stickman> stickmens = new ArrayList<>();
            String blueGuy = Team.B.name() + (i+1);
            String redGuy = Team.R.name() + (i+1);
            Stickman blue = gameBoard.getBlueArmy().containsKey(blueGuy) ? gameBoard.getBlueArmy().get(blueGuy) :deadGuy;
            Stickman red = gameBoard.getRedArmy().containsKey(redGuy) ? gameBoard.getRedArmy().get(redGuy) : deadGuy;
            stickmens.add(blue);
            stickmens.add(red);
            teams.add(stickmens);
        }
        String turn = gameBoard.getCurrentTurnName();
        model.addAttribute("turn", turn);
        model.addAttribute("teams", teams);
        return "index";
    }

    @GetMapping("/roll_dice")
    public String rolleDice(Model model){
        int result = gameBoard.rollDice(gameBoard.getTurn());
        if(result == -1){
            //TODO: nothing happen because the guy you choose is dead
        } else if (result >0) {
            //Todo: shoot the opponent as you are fully loaded
            Random random = new Random();
            int dice = random.nextInt(gameBoard.getArmySize()) +1;
            if(gameBoard.getTurn() == Team.B){
                while (!gameBoard.shootTheRed(Team.R.name() + dice)){
                    dice = (dice+1)% gameBoard.getArmySize();
                }
            }else{
                while (!gameBoard.shootTheBlue(Team.B.name() + dice)){
                    dice = (dice+1)%gameBoard.getArmySize();
                }
            }
        }

        String message = "";
        if(gameBoard.getWinner() == 1){
            message = "Blue Team";
            gameBoard.setGameEnd(true);
        }else if(gameBoard.getWinner() == 2){
            message = "Red Team";
            gameBoard.setGameEnd(true);
        }

        if(gameBoard.isGameEnd()){
            model.addAttribute("message", message);
            return "win";
        }
        gameBoard.changeTurn();

        return home(model);
    }

    @GetMapping("/new_game")
    public String startNewGame(Model model){
        gameBoard = new GameBoard();
        return home(model);
    }

}
