package htr.Controller;

import htr.Service.UserService;
import htr.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@Controller
public class UserController {
    @Autowired
    private UserService userService;

    @RequestMapping("/login")
    public String login() {
        return "login";
    }

    @RequestMapping("/login2")
    public String login2(String username, String password, HttpServletRequest request) {
        User user = userService.login(username, password);
        if (user == null) {
            return "login";
        } else {
            HttpSession session = request.getSession();
            session.setAttribute("user", user);
            return "/";
        }
    }
}
