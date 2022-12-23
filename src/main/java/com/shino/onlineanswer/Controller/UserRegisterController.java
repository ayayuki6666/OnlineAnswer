package com.shino.onlineanswer.Controller;

import com.shino.onlineanswer.Pojo.Student;
import com.shino.onlineanswer.Pojo.User;
import com.shino.onlineanswer.Service.StudentService;
import com.shino.onlineanswer.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/register")
public class UserRegisterController {
    @Autowired
    private UserService userService;
    @Autowired
    private StudentService studentService;
    @RequestMapping(method = RequestMethod.GET)
    public String registerReturn()
    {
        return "register";
    }
    @RequestMapping(method = RequestMethod.POST)
    public String registerReg(@RequestParam("userReg") String userReg,
                              @RequestParam("stuID") Integer stuID,
                              @RequestParam("name") String name,
                              @RequestParam("passwordReg") String passwordReg,
                              @RequestParam("passwordRegC") String passwordRegC)
    {
        User user = new User(userReg,passwordRegC,"student");
        Student student=new Student(stuID,name,userReg);
        if(passwordReg.equals(passwordRegC))
        {
            userService.save(user);
            studentService.save(student);
            return "redirect:/login";
        }
        if(!passwordReg.equals(passwordRegC))
            return "error";
        return "error";
    }
}
