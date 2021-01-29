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


    @GetMapping("all")
    public String getAllRecipes (Model model){

        List<Recipe> all = ((List<Recipe>) recipeRepository.findAll());

        model.addAttribute("recipes", all);

        return "recipes/all";

    }



    @PostMapping("recipes/edit{recipesId})")
    public String processEditForm(HttpServletRequest request, Integer recipeId, @ModelAttribute Recipe newRecipe,
                                  Errors errors, Model model, RedirectAttributes redirectAttrs) {
        if (errors.hasErrors()) {
            model.addAttribute("title", "Edit Recipe");
            return "recipes/edit";
        }

        String[] ingredients = request.getParameterValues("ingredient");
        String[] instructions = request.getParameterValues("instruction");
        String[] measurements = request.getParameterValues("measurement");
        String[] quantity = request.getParameterValues("quantity");

        List<Ingredient> ingredientsList = new ArrayList<Ingredient>();
        List<Instruction> instructionsList = new ArrayList<Instruction>();


        Optional<Recipe> recipeOpt = recipeRepository.findById(recipeId);
        if (recipeOpt.isPresent()) {
            Recipe recipe = recipeOpt.get();

            List<Ingredient> ingredientsToDelete = ingredientRepository.findByRecipeId(recipe.getId());
            for (int i = 0; i < ingredientsToDelete.size(); i++) {
                ingredientRepository.delete(ingredientsToDelete.get(i));
            }
            List<Instruction> instructionsToDelete = instructionRepository.findByRecipeId(recipe.getId());
            for (int i = 0; i < instructionsToDelete.size(); i++) {
                instructionRepository.delete(instructionsToDelete.get(i));
            }

            recipe.setCategory(newRecipe.getCategory());
            recipe.setImg(newRecipe.getImg());
            recipe.setName(newRecipe.getName());
            recipe.setTags(newRecipe.getTags());

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


            Recipe savedRecipe = recipeRepository.save(recipe);
            Iterable<Recipe> recipes = recipeRepository.findAll();

            redirectAttrs.addAttribute("recipes", recipes);


        }
        return "redirect:/recipes";

    }

    @RequestMapping("/delete/{recipeId}")
    public String handleDeleteUser(@PathVariable Integer recipeId) {
        Optional<Recipe> recipeOpt = recipeRepository.findById(recipeId);
        List<Review> reviews = recipeOpt.get().getReviews();
        if (recipeOpt.isPresent()) {
            for (int i = 0; i < reviews.size(); i++){
                Review review = reviews.get(i);
                review.setUser(null);
                reviewRepository.deleteById(review.getId());
            }
            recipeRepository.deleteById(recipeId);
        }

        return "redirect:/recipes";
    }

    @RequestMapping("/save/{recipeId}")
    public String saveRecipeToUser(@PathVariable Integer recipeId) {
        return "index";
    }

}

