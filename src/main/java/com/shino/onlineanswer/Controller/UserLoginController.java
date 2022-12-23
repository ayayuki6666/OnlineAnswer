package com.shino.onlineanswer.Controller;

import com.shino.onlineanswer.Pojo.User;
import com.shino.onlineanswer.Service.UserService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping(value = "/login")
public class UserLoginController {
    @Autowired
    private UserService userService;
    @RequestMapping(method = RequestMethod.GET)
    public String LoginReturn()
    {
        return "login";
    }


    @RequestMapping(method = RequestMethod.POST)
    public String LoginLog(
            @RequestParam("userLogin") String userLogin,
            @RequestParam("password") String password,
            HttpSession session
    )
    {
        User user =new User();
        user.setUsername(userLogin);
        user.setPassword(password);
        User userSelect=userService.getById(user.getUsername());
        if(userSelect==null)
            return "error";
        if(!userSelect.getPassword().equals(password))
            return "error";
        if(userSelect.getPassword().equals(password))
        {
            session.setAttribute("user",userSelect);
            if(userSelect.getPermission().equals("student")||userSelect.getPermission().equals("teacher"))
            {
                return "redirect:/courselist";
            }


            if(userSelect.getPermission().equals("admin"))
            {
                return "redirect:/courseInfo";
            }
        }
            return "redirect:/error";
    }
}
