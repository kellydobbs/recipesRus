package org.launchcode.recipeapp.controllers;


import org.dom4j.rule.Mode;
import org.launchcode.recipeapp.models.Category;
import org.launchcode.recipeapp.models.Recipe;
import org.launchcode.recipeapp.models.Tag;
import org.launchcode.recipeapp.models.data.RecipeRepository;
import org.launchcode.recipeapp.models.data.ReviewRepository;
import org.launchcode.recipeapp.models.data.UserRecipeRepository;
import org.launchcode.recipeapp.models.data.UserRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;
import java.util.Optional;

@Controller
@RequestMapping("admin/index")
public class AdminController extends AbstractController {

    @Autowired
    private RecipeRepository recipeRepository;

    @Autowired
    private ReviewRepository reviewRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UserRecipeRepository userRecipeRepository;

    @GetMapping("admin")
    public String index(Model model) {
        model.addAttribute("title", "Admin Links");
        return "admin/index";


    }


    @RequestMapping("recipe")
    public String recipesIndex(Model model) {

        model.addAttribute("title", "Admin: Recipes List");
        model.addAttribute("recipes", recipeRepository.findByName(""));

        return "admin/recipe";
    }

    @RequestMapping("review")
    public String review(Model model) {

        model.addAttribute("title", "Admin: Reviews");
        model.addAttribute("review", reviewRepository.findAll());

        return "admin/review";
    }

    @RequestMapping("userrecipe")
    public String userrecipe(Model model) {

        model.addAttribute("title", "Admin: User Recipes");
        model.addAttribute("review", userRecipeRepository.findAll());

        return "admin/userrecipes";

    }


    @GetMapping("admin/edit/{recipeId}")
    public String displayEditForm(Model model, @PathVariable int recipeId) {

        Category[] categories = Category.values();
        Tag[] tags = Tag.values();
        Optional<Recipe> recipeOpt = recipeRepository.findById(recipeId);
        if (recipeOpt.isPresent()) {
            Recipe recipe = recipeOpt.get();
            model.addAttribute("recipe", recipe);
            model.addAttribute("title", "Edit recipe " + recipe.getName());
            model.addAttribute("recipeId", recipe.getId());
        } else {
            model.addAttribute("recipe", new Recipe());
        }
        model.addAttribute("categories", categories);
        model.addAttribute("tags", tags);

        return "admin/edit";

    }

    @PostMapping("edit")
    public String processEditForm(Integer recipeId, @ModelAttribute @Valid Recipe newRecipe,
                                  Errors errors, Model model, RedirectAttributes redirectAttrs) {
        if (errors.hasErrors()) {
            model.addAttribute("title", "Edit Recipe");
            return "admin/edit";
        }
        Optional<Recipe> recipeOpt = recipeRepository.findById(recipeId);
        if (recipeOpt.isPresent()) {
            Recipe recipe = recipeOpt.get();
            recipe.setCategory(newRecipe.getCategory());
            recipe.setDirections(newRecipe.getDirections());
            recipe.setImg(newRecipe.getImg());
            recipe.setIngredients(newRecipe.getIngredients());
            recipe.setName(newRecipe.getName());
            recipe.setTag(newRecipe.getTag());


            Recipe savedRecipe = recipeRepository.save(recipe);
            Iterable<Recipe> recipes = recipeRepository.findAll();

            redirectAttrs.addAttribute("recipes", recipes);

        }
        return "redirect:";

    }

    @RequestMapping("/delete/{recipeId}")
    public String handleDeleteUser(@PathVariable Integer recipeId) {
        Optional<Recipe> recipeOpt = recipeRepository.findById(recipeId);
        if (recipeOpt.isPresent()) {
            recipeRepository.deleteById(recipeId);
        }

        return "redirect:/recipes";
    }

    @RequestMapping("/save/{recipeId}")
    public String saveRecipeToUser(@PathVariable Integer recipeId) {
        return "index";
    }
    @RequestMapping("user")
    public String userIndex(Model model) {

        model.addAttribute("title", "Admin: Users List");
        model.addAttribute("user", userRepository.findAll());

        return "admin/user";
    }


}
