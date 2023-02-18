package com.codeup.codeupspringblog;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
public class RollDiceController {

    @GetMapping("/roll-dice")
    public String getRollDice() {
        return "roll-dice";
    }

    @GetMapping(path = "/roll-dice/{guess}")
    public String rollDiceResult(Model model, @PathVariable int guess) {
        if (guess < 1 || guess > 6) {
            return "roll-dice";
        }
        model.addAttribute("guess", guess);
        int diceRoll = (int) (Math.random() * 6) + 1;
        model.addAttribute("diceRoll", diceRoll);
        return "roll-dice-results";
    }

}
