package app.components;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;


@Controller
public class ParseController {

    @GetMapping("/parse")
    public String readQr(Model model) {
        String content = (String) model.getAttribute("content");
        if (content != null){
            model.addAttribute("receipt", ParseService.parseContent(content));
        }
        ///exception
        return "redirect:";
    }
}
