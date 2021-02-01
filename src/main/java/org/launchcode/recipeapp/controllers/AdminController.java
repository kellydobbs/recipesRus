package org.launchcode.recipeapp.controllers;

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


    @RequestMapping("users")
    public String getTableOfAllUsers(Model model) {

        model.addAttribute("title", "All Users");
        model.addAttribute("users", userRepository.findAll());
        return "admin/users";
    }

    @GetMapping("edit-users/{usersid}")
    public String displayUserEditForm(Model model, @PathVariable int userId) {

        model.addAttribute("title", "Edit Users");
        model.addAttribute("users", userRepository.findById(userId));
        return "admin/edit-users";


    }

    @PostMapping("edit-users/{usersid}")
    public String processUserEditForm( Model model, @PathVariable int userId,
                                       @ModelAttribute User newUser){


    return "admin/edit-users";

}
    @GetMapping("all")
    public String getAllRecipes(Model model) {

        List<Recipe> all = ((List<Recipe>) recipeRepository.findAll());

        model.addAttribute("recipes", all);

        return "recipes/all";

    }
    @GetMapping("create")
    public String createAdminRecipe(Model model) {
        Category[] categories = Category.values();
        Measurement[] measurements = Measurement.values();
        Iterable<Tag> tags = tagRepository.findAll();

        model.addAttribute("title", "Create Recipe");
        model.addAttribute("recipe", new Recipe());
        model.addAttribute("categories", categories);
        model.addAttribute("tags", tags);
        model.addAttribute("measurements", measurements);

        return "recipes/create";
    }

    @PostMapping("create")
    public String createAdminRecipe(HttpServletRequest request, @ModelAttribute Recipe newRecipe,
                               @ModelAttribute @Valid String newCategory,
                               Errors errors, Model model, RedirectAttributes redirectAttrs) {

        if (errors.hasErrors()) {
            model.addAttribute("title", "Create Recipe");
            return "admin/create";
        }

        String[] ingredients = request.getParameterValues("ingredient");
        String[] instructions = request.getParameterValues("instruction");
        String[] measurements = request.getParameterValues("measurement");
        String[] quantity = request.getParameterValues("quantity");

        List<Ingredient> ingredientsList = new ArrayList<Ingredient>();
        List<Instruction> instructionsList = new ArrayList<Instruction>();

        Recipe recipe = recipeRepository.save(newRecipe);


        for (int i = 0; i < ingredients.length; i++) {
            Ingredient newIngredient = new Ingredient(ingredients[i], Double.parseDouble(quantity[i]), measurements[i]);
            newIngredient.setRecipe(recipe);
            ingredientsList.add(newIngredient);
            ingredientRepository.save(newIngredient);
        }
        for (int i = 0; i < instructions.length; i++) {
            Instruction newInstruction = new Instruction(instructions[i]);
            newInstruction.setRecipe(recipe);
            instructionsList.add(newInstruction);
            instructionRepository.save(newInstruction);
        }
        redirectAttrs.addAttribute("recipeId", recipe.getId());

        return "redirect:/recipes/display";
    }

}
