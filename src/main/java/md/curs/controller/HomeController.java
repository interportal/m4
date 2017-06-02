package md.curs.controller;

import md.curs.model.User;
import md.curs.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

/**
 * http://localhost:8080/users**
 * <p>
 * Created by MG
 */
@Controller
@RequestMapping("/")
public class HomeController {

    /**
     * @return home page
     */
    @RequestMapping(method = RequestMethod.GET)
    public String userList() {
        return "index";
    }
}
