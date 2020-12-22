package org.launchcode.recipeapp.controllers;


import org.launchcode.recipeapp.models.Category;
import org.launchcode.recipeapp.models.Recipe;
import org.launchcode.recipeapp.models.data.RecipeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("search")
public class SearchController {

    @Autowired
    private RecipeRepository recipeRepository;




    @PostMapping(value="/results")
    public String searchByKeyword(Model model, @RequestParam String keyword) {

        List<Recipe> recipeList = new ArrayList<>();
        Iterable<Recipe> recipesIter = recipeRepository.findAll();
        recipesIter.forEach(recipeList::add);
        List<Recipe> foundRecipes = new ArrayList<>();
        for (Recipe recipe : recipeList) {
            if (recipe.getName().toLowerCase().contains(keyword.toLowerCase())) {
                foundRecipes.add(recipe);
            }
        }
        model.addAttribute("recipes", foundRecipes);
        return "search";
    }

    @PostMapping(value= "/selectedCategory")
    public String getRecipeByCategory(@ModelAttribute Category category, Model model){
        Iterable<Recipe> recipes = recipeRepository.findAll();
        List<Recipe> recipeByCategory = new ArrayList<>();
        for (Recipe recipe:recipes) {
        if(recipe.getCategory().name().equals(category.name())){
            recipeByCategory.add(recipe);
        }
        model.addAttribute("recipes", recipeByCategory);
    }
        return "search";
    }

}
