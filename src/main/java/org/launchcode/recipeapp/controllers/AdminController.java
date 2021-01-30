package org.launchcode.recipeapp.controllers;

import org.dom4j.rule.Mode;
import org.launchcode.recipeapp.models.*;
import org.launchcode.recipeapp.models.data.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("admin")
public class AdminController {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UserRecipeRepository userRecipeRepository;

    @Autowired
    private RecipeRepository recipeRepository;

    @Autowired
    private ReviewRepository reviewRepository;

    @Autowired
    private TagRepository tagRepository;

    @Autowired
    private IngredientRepository ingredientRepository;

    @Autowired
    private InstructionRepository instructionRepository;

    private static final String userSessionKey = "user";

    public User getUserFromSession(HttpSession session) {
        return (User) session.getAttribute(userSessionKey);
    }

    private static void setUserInSession(HttpSession session, User user) {
        session.setAttribute(userSessionKey, user);
    }

    @GetMapping()
    public String index(Model model) {
        model.addAttribute("title", "Admin Portal");
        return "admin/index";

    }
    @GetMapping("/profile")
    public String getUserProfile(HttpServletRequest request, Model model, RedirectAttributes redirectAttributes) {
        User sessionUser = (User) request.getSession().getAttribute("user");
        if (sessionUser == null) {
            model.addAttribute("title", "No user found");
        } else {
            List<Recipe> recipes = new ArrayList<>();
            List<UserRecipe> userRecipes = userRecipeRepository.getAllByUser(sessionUser);

            for (UserRecipe userRecipe : userRecipes) {
                Recipe recipe = userRecipe.getRecipe();
                recipes.add(recipe);
            }

            model.addAttribute("title", sessionUser.getUsername());
            model.addAttribute("user", sessionUser);
            model.addAttribute("recipes", recipes);
            model.addAttribute("title1", redirectAttributes.getAttribute("title1"));

        }
        return "admin/profile";
    }


    @GetMapping("all")
    public String getAllRecipes (Model model){

        List<Recipe> all = ((List<Recipe>) recipeRepository.findAll());

        model.addAttribute("recipes", all);

        return "recipes/all";

    }



    @PostMapping("edit-users")
    public String processEditUser(HttpServletRequest request, Integer userId, @ModelAttribute User newUser,
                                  Errors errors, Model model,  RedirectAttributes redirectAttributes) {
        if (errors.hasErrors()) {
            model.addAttribute("title", "Edit User");
            return "admin/edit-user";
        }
        return "redirect:/admin";
    }
}

