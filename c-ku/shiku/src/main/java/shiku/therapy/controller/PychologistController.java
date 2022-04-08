package shiku.therapy.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class PychologistController {

    @GetMapping("/pyschology")
    public String getPyschologist(){
        return "pychologist";
    }
}
