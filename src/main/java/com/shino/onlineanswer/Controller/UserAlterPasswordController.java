package com.shino.onlineanswer.Controller;

import com.shino.onlineanswer.Pojo.User;
import com.shino.onlineanswer.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/alterpassword")
public class UserAlterPasswordController {
    @Autowired
    UserService userService;
    @RequestMapping(method = RequestMethod.GET)
    public String AlterPasswordReturn(){
        return "alterpassword";
    }
    @RequestMapping(method = RequestMethod.POST)
    public String AlterPasswordAlter(
            @RequestParam("userAlt") String userAlt,
            @RequestParam("passwordOri") String passwordOri,
            @RequestParam("passwordNew") String passwordNew,
            @RequestParam("passwordNewC") String passwordNewC
    )
    {
        User user=userService.getById(userAlt);
        if(!user.getPassword().equals(passwordOri))
        {
            return "error";
        }
        if(!passwordNew.equals(passwordNewC))
        {
            return "error";
        }
        if(passwordNew.equals(passwordNewC))
        {
            user.setPassword(passwordNewC);
            userService.saveOrUpdate(user);
            return "redirect:/login";
        }
        return "error";
    }
}
