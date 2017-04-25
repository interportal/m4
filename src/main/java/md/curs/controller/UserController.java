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
@RequestMapping("/users")
@SessionAttributes({"authUser"})
public class UserController {

    private UserService userService;

    @Autowired
    public UserController(UserService service) {
        this.userService = service;
    }

    @ModelAttribute("authUser")
    public User getAuthUser() {
        System.out.println("Setting authUser Attribute...");
        return new User("Auth-User", "Auth-Surname", 50);
    }

    /**
     * http://localhost:8080/users?q=test
     *
     * @param query
     * @param model
     * @return
     */
    @RequestMapping(method = RequestMethod.GET)
    public String userList(@RequestParam(value = "q", defaultValue = "") String query, Model model) {
        List<User> users = userService.findUsers(query);
        long minorsCount = userService.getMinorsCount();

        model.addAttribute("users", users);
        model.addAttribute("minorsCount", minorsCount);

        return "user-list";
    }

    /**
     * http://localhost:8080/users/3
     *
     * @param userId
     * @param model
     * @return
     */
    @RequestMapping(value = "/{userId}", method = RequestMethod.GET)
    public String getUser(@PathVariable("userId") Long userId, Model model) {
        Optional<User> user = userService.getUser(userId);

        if (!user.isPresent()) {
            throw new ResourceNotFound(String.format("User %d not found", userId));
        }

        model.addAttribute("user", user.get());
        return "user-form";
    }

    /**
     * http://localhost:8080/users/save
     *
     * @param user
     * @param result
     * @return
     */
    @RequestMapping(value = "/save", method = RequestMethod.POST)
    public String saveUser(@ModelAttribute("user") User user, BindingResult result) {

        // in controller method
        if (result.hasErrors()) {
            return "user-form";
        }

        userService.saveUser(user);
        return "redirect:/users";
    }
}
