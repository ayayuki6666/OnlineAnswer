package com.shino.onlineanswer.Controller;

import com.shino.onlineanswer.Service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
public class TestRestController {
    @Autowired
    StudentService studentService;
    @RequestMapping(value = "/test1",method = RequestMethod.DELETE)
    public String testDelete(){
        return "delete";
    }

}
