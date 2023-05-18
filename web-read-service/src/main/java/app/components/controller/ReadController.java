package app.components.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import java.io.File;

@Controller
@RequestMapping("/receipts/read")
public class ReadController {

    @PostMapping("/{idUser}")
    public void readReceipt(@RequestBody File file, @PathVariable Long idUser){

        //save file to s3
        //path + idUser -> receipt load
        //redirect to queue with receipt load
    }
}
