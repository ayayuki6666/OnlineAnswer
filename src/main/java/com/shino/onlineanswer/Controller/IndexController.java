package com.shino.onlineanswer.Controller;

import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Log4j2
@Controller
public class IndexController {
    @RequestMapping("/")
    public String IndexReturn()
    {
        return "index";
    }
}
