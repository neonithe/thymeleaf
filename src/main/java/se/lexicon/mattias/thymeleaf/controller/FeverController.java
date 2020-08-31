package se.lexicon.mattias.thymeleaf.controller;


import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller //, method = {RequestMethod.GET, RequestMethod.PUT}
@RequestMapping("/fever")
public class FeverController {

    private String output;

    @GetMapping("/feverInput")
    public String test() {
        return "/service/fevertest.html";
    }

    @PostMapping("/feverInput")
    public String feverInput(@RequestParam("inputNumber") String inputNumber) {

        String tempString = inputNumber.replace(",", ".");
        Double result = Double.parseDouble(tempString);

        if(result == 35.0) { output=result + "°C is normal temperature"; }
        if(result > 35.0)  { output=result + "°C is to warm"; }
        if(result < 35.0)  { output=result + "°C is to cold"; }

        System.out.println(output);

        return "redirect:/fever/feverResult";
    }

    @GetMapping("/feverResult")
    public String testpage(Model model) {

        model.addAttribute("resultFever", output);

        return "/service/fevertest.html";
    }

}
