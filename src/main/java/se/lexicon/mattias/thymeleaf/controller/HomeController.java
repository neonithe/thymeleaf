package se.lexicon.mattias.thymeleaf.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.Arrays;
import java.util.List;

@Controller
public class HomeController {

    List<String> texts = Arrays.asList("This", "Should", "Be", "Displyed", "In", "A", "Table");

    @RequestMapping(path = "/", method = RequestMethod.GET) // (Default values)
    public String index(){
        return "index.html";
    }


    // @RequestMapping(path = "/welcome", method = RequestMethod.GET) //Same
    @GetMapping("/welcome")
    public String anotherWelcomeMessage(){
        return "welcome";
    }

    @GetMapping("/showtext")
    public String showAListOfText(Model model) {

        model.addAttribute("allText", texts);

        return "textList";
    }

    @GetMapping("/redirect")
    public String redirect()
    {
        return "/messages/redirect";
    }

    @GetMapping("/search")
    public String search()
    {
        return "/search";
    }

}
