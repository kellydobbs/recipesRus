package org.launchcode.recipeapp.controllers;

import org.launchcode.recipeapp.models.data.UserRepository;
import org.launchcode.recipeapp.models.dto.LoginFormDTO;
import org.launchcode.recipeapp.models.User;
import org.launchcode.recipeapp.models.dto.RegistrationFormDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.util.Optional;

@Controller
@RequestMapping("register")
public class AuthenticationController extends AbstractController {

    @Autowired
    UserRepository userRepository;

    private  static final String userSessionKey = "user";

    public User getUserFromSession(HttpSession session) {
        return (User) session.getAttribute(userSessionKey);
    }

    private static void setUserInSession(HttpSession session, User user) {
        session.setAttribute(userSessionKey, user);
    }

    @GetMapping("/user")
    public String displayRegistrationUserForm(Model model) {
        model.addAttribute(new RegistrationFormDTO());
        model.addAttribute("title", "Register");
        return "/register/user";
    }

    @PostMapping("/user")
    public String processRegistrationUserForm(@ModelAttribute @Valid RegistrationFormDTO registrationFormDTO,
                                          Errors errors, HttpServletRequest request,
                                          Model model) {

        if (errors.hasErrors()) {
            model.addAttribute("title", "Register");
            return "register/user";
        }

        User existingUser = userRepository.findByUsername(registrationFormDTO.getUsername());
        String access = registrationFormDTO.getAccess();

        if (existingUser != null) {
            errors.rejectValue("username", "username.already exists", "A user with that username already exists");
            return "register/user";
        }

        String email = registrationFormDTO.getEmail();
        String verifyEmail = registrationFormDTO.getVerifyEmail();
        if (!email.equals(verifyEmail)) {
            errors.rejectValue("email", "email.mismatch", "Email entered does not match");
            model.addAttribute("title", "Register");
            return "register/user";
        }

        String password = registrationFormDTO.getPassword();
        String verifyPassword = registrationFormDTO.getVerifyPassword();
        if (!password.equals(verifyPassword)) {
            errors.rejectValue("password", "passwords.mismatch", "Passwords do not match");
            model.addAttribute("title", "Register");
            return "register/user";        }

        registrationFormDTO.setAccess("2");
        User newUser = new User(registrationFormDTO.getUsername(), registrationFormDTO.getPassword(), registrationFormDTO.getAccess(), registrationFormDTO.getEmail());
        userRepository.save(newUser);
        setUserInSession(request.getSession(), newUser);

        if (registrationFormDTO.getAccess().equals("2")){
            return"redirect:/users/profile";
        }

        return "redirect:/home";
    }

    @GetMapping("admin")
    public String displayRegisterAdminForm(Model model) {
        model.addAttribute(new RegistrationFormDTO());
        model.addAttribute("title", "Register");
        return "/register/admin";
    }

    @PostMapping("admin")
    public String processRegisterAdminForm(@ModelAttribute @Valid RegistrationFormDTO registrationFormDTO,
                                          Errors errors, HttpServletRequest request,
                                          Model model) {

        if (errors.hasErrors()) {
            model.addAttribute("title", "Register");
            return "/register/admin";
        }

        User existingUser = userRepository.findByUsername(registrationFormDTO.getUsername());

        if (existingUser != null) {
            errors.rejectValue("username", "username.already exists", "A user with that username already exists");
            model.addAttribute("title", "Register");
            return "/register/admin";

        }
        registrationFormDTO.setAccess("1");
        User newUser = new User(registrationFormDTO.getUsername(), registrationFormDTO.getPassword(), registrationFormDTO.getAccess(), registrationFormDTO.getEmail());
        userRepository.save(newUser);
        setUserInSession(request.getSession(), newUser);

        if (registrationFormDTO.getAccess().equals("1")){
            return"redirect:/admin";
        }

        return "redirect:/register/admin";
    }

    @GetMapping("/login")
    public String displayLoginForm(Model model) {
        model.addAttribute(new LoginFormDTO());
        model.addAttribute("title", "Log In");
        return "register/login";
    }

    @PostMapping("/login")
    public String processLoginForm(@ModelAttribute @Valid LoginFormDTO loginFormDTO,
                                   Errors errors, HttpServletRequest request,
                                   Model model) {

        if (errors.hasErrors()) {
            model.addAttribute("title", "Log In");
            return "register/login";
        }

        User theUser = userRepository.findByUsername(loginFormDTO.getUsername());


        if (theUser == null) {
            errors.rejectValue("username", "user.invalid", "The given username does not exist");
            model.addAttribute("title", "Log In");
            return "register/login";
        }

        String email = loginFormDTO.getEmail();

        if (theUser == null) {
            errors.rejectValue("email", "email.invalid", "Please enter valid email");
            model.addAttribute("title", "Log In");
            return "register/login";
        }

        String password = loginFormDTO.getPassword();

        if (!theUser.isMatchingPassword(password)) {
            errors.rejectValue("password", "password.invalid", "Invalid password");
            model.addAttribute("title", "Log In");
            return "register/login";
        }

        if (theUser.getAccess().equals("1")) {
            return "redirect:admin/index";
        }

        if (theUser.getAccess().equals("2")) {
            return "redirect:user/profile";


    } else {

            setUserInSession(request.getSession(), theUser);

            return "redirect:/register/login";


        }
    }

        @GetMapping("/logout")
        public String logout(HttpServletRequest request){
            request.getSession().invalidate();
            return "redirect:/home";
        }

    }
