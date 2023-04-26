package app.components;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.io.File;
import java.io.IOException;

@Controller
@RequestMapping("/qr")
public class ReadController {
    private final ReadService readService;
    @Autowired
    public ReadController(ReadService readService) {
        this.readService = readService;
    }

    @GetMapping("/read")
    public String readQr(Model model) throws IOException {
        File qr = (File) model.getAttribute("file");
        model.addAttribute("content", readService.readQRCode(qr));
        return "redirect:";
    }

}
