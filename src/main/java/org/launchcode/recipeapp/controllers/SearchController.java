package org.launchcode.recipeapp.controllers;


import org.launchcode.recipeapp.models.Category;
import org.launchcode.recipeapp.models.Recipe;
import org.launchcode.recipeapp.models.SortParameter;
import org.launchcode.recipeapp.models.data.RecipeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@Controller
@RequestMapping("search")
public class SearchController {

    @Autowired
    private RecipeRepository recipeRepository;

    List<Recipe> foundRecipes = new ArrayList<>();

    @GetMapping("")
    public String renderSearch(Model model) {
        model.addAttribute("categories", Category.values());
        model.addAttribute("sort", SortParameter.values());

        return "search";
    }


    @PostMapping(value = "/results")
    public String searchRecipeByKeyword(Model model, @RequestParam String keyword) {
        String lower_val = keyword.toLowerCase();

        List<Recipe> recipeList = new ArrayList<>();
        Iterable<Recipe> recipesIter = recipeRepository.findAll();
        recipesIter.forEach(recipeList::add);
//        List<Recipe> foundRecipes = new ArrayList<>();
        for (Recipe recipe : recipeList) {
            if (recipe.getName().toLowerCase().contains(lower_val)) {
                foundRecipes.add(recipe);
            } else if (recipe.getIngredients().toLowerCase().contains(lower_val)) {
                foundRecipes.add(recipe);
            } else if (recipe.getDirections().toLowerCase().contains(lower_val)) {
                foundRecipes.add(recipe);
            } else if (recipe.getCategory().toString().toLowerCase().contains(lower_val)) {
                foundRecipes.add(recipe);


            }
        }
        model.addAttribute("recipes", foundRecipes);
        model.addAttribute("categories", Category.values());
        model.addAttribute("sort", SortParameter.values());
        return "search";
    }


    @PostMapping(value = "/selectedCategory")
    public String searchRecipeByCategory(@RequestParam Category category, Model model) {
        Iterable<Recipe> recipes = recipeRepository.findAll();

        if (category == null) {
            List<Recipe> allRecipe = new ArrayList<>();
            for (Recipe recipe : recipes) {
                allRecipe.add(recipe);
                model.addAttribute("recipes", allRecipe);
                model.addAttribute("categories", Category.values());
                model.addAttribute("sort", SortParameter.values());
            }
        } else {
            List<Recipe> recipeByCategory = new ArrayList<>();
            for (Recipe recipe : recipes) {
                if (recipe.getCategory().name().toLowerCase().equals(category.name().toLowerCase())) {
                    recipeByCategory.add(recipe);
                }
            }
            model.addAttribute("recipes", recipeByCategory);
            model.addAttribute("categories", Category.values());
            model.addAttribute("sort", SortParameter.values());

        }
        return "search";
    }

    @PostMapping(value = "/sortKeyword")
    public String sortKeywordSearchResults(@RequestParam SortParameter sortParameter, Model model) {
        Iterable<Recipe> recipes = foundRecipes;

        if (sortParameter == null) {
            List<Recipe> unsortedRecipe = new ArrayList<>();
            for (Recipe recipe : recipes) {
                unsortedRecipe.add(recipe);
                model.addAttribute("recipes", unsortedRecipe);
                model.addAttribute("categories", Category.values());
                model.addAttribute("sort", SortParameter.values());
            }

        } else if ((sortParameter.getName().equals("Ascending Recipe Name"))) {
            List<Recipe> sortedRecipes = new ArrayList<>();
            for (Recipe recipe : recipes) {
                sortedRecipes.add(recipe);
                Collections.sort(sortedRecipes, new Recipe.SortByNameAsc());

            }
            model.addAttribute("recipes", sortedRecipes);
            model.addAttribute("categories", Category.values());
            model.addAttribute("sort", SortParameter.values());

        } else if ((sortParameter.getName().equals("Descending Recipe Name"))) {
            List<Recipe> sortedRecipes = new ArrayList<>();
            for (Recipe recipe : recipes) {
                sortedRecipes.add(recipe);
                Collections.sort(sortedRecipes, new Recipe.SortByNameDesc());

            }
            model.addAttribute("recipes", sortedRecipes);
            model.addAttribute("categories", Category.values());
            model.addAttribute("sort", SortParameter.values());
//                foundRecipes.clear();

        }
        return "search";
    }

}