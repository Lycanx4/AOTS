package com.kaung.AttackOfTheStickman.controller;

import com.kaung.AttackOfTheStickman.model.GameBoard;
import com.kaung.AttackOfTheStickman.model.Stickman;
import com.kaung.AttackOfTheStickman.model.Team;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.ArrayList;
import java.util.List;

@Controller
public class HomeController {
    GameBoard gameBoard = new GameBoard();
    @GetMapping("/")
    public String home(Model model){
        List<List<Stickman>> teams = new ArrayList<>();

        for(int i=0; i<6; i++){
            List<Stickman> stickmens = new ArrayList<>();
            String blueGuy = Team.B.name() + (i+1);
            String redGuy = Team.R.name() + (i+1);
            stickmens.add(gameBoard.getBlueArmy().get(blueGuy));
            stickmens.add(gameBoard.getRedArmy().get(redGuy));
            teams.add(stickmens);
        }
        model.addAttribute("turn", gameBoard.getCurrentTurnName());
        model.addAttribute("teams", teams);
        return "index";
    }

    @GetMapping("/roll_dice")
    public String rolleDice(Model model){
        gameBoard.rollDice(gameBoard.getTurn());

        gameBoard.changeTurn();

        return home(model);
    }

}
