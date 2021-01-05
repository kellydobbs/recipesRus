package org.launchcode.recipeapp.controllers;


import org.launchcode.recipeapp.models.Category;
import org.launchcode.recipeapp.models.Recipe;
import org.launchcode.recipeapp.models.SortParameter;
import org.launchcode.recipeapp.models.Tag;
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

//
//    @ModelAttribute
//    public void initValues(Model model) {
//        model.addAttribute("filterTypes", Arrays.asList("Chicken", "Fish"));
//    }

    @GetMapping("")
    public String renderSearch(Model model) {
        model.addAttribute("categories", Category.values());
        model.addAttribute("sort", SortParameter.values());
        model.addAttribute("filterTypes", Tag.values());

        return "search";
    }

    // Search by keyword
    @PostMapping(value = "/keywordResults")
    public String searchRecipeByKeyword(Model model, @RequestParam String keyword) {
        foundRecipes.clear();
        String lower_val = keyword.toLowerCase();

        List<Recipe> recipeList = new ArrayList<>();
        Iterable<Recipe> recipesIter = recipeRepository.findAll();
        recipesIter.forEach(recipeList::add);

        for (Recipe recipe : recipeList) {
            if (recipe.getName().toLowerCase().contains(lower_val)) {
                foundRecipes.add(recipe);
            } else if (recipe.getIngredients().toLowerCase().contains(lower_val)) {
                foundRecipes.add(recipe);
            } else if (recipe.getDirections().toLowerCase().contains(lower_val)) {
                foundRecipes.add(recipe);
            } else if (recipe.getCategory().toString().toLowerCase().contains(lower_val)) {
                foundRecipes.add(recipe);
            }else if (recipe.getTag().toString().toLowerCase().contains(lower_val)) {
                foundRecipes.add(recipe);
            }
        }
        model.addAttribute("recipes", foundRecipes);
        model.addAttribute("categories", Category.values());
        model.addAttribute("sort", SortParameter.values());
        return "search";
    }


    //filter by category
    @PostMapping(value = "/categoryResults")
    public String searchRecipeByCategory(@RequestParam Category category, Model model) {
        foundRecipes.clear();
        Iterable<Recipe> recipes = recipeRepository.findAll();

        if (category == null) {
            for (Recipe recipe : recipes) {
                foundRecipes.add(recipe);
                model.addAttribute("recipes", foundRecipes);
                model.addAttribute("categories", Category.values());
                model.addAttribute("sort", SortParameter.values());
            }
        } else {
            for (Recipe recipe : recipes) {
                if (recipe.getCategory().name().toLowerCase().equals(category.name().toLowerCase())) {
                    foundRecipes.add(recipe);
                }
            }
            model.addAttribute("recipes", foundRecipes);
            model.addAttribute("categories", Category.values());
            model.addAttribute("sort", SortParameter.values());

        }
        return "search";
    }


    //sort search results
    @PostMapping(value = "/sort")
    public String sortSearchResults(@RequestParam SortParameter sortParameter, Model model) {
        Iterable<Recipe> recipes = foundRecipes;

        // sort is black
        if (sortParameter == null) {
            List<Recipe> unsortedRecipe = new ArrayList<>();
            for (Recipe recipe : recipes) {
                unsortedRecipe.add(recipe);
                model.addAttribute("recipes", unsortedRecipe);
                model.addAttribute("categories", Category.values());
                model.addAttribute("sort", SortParameter.values());
            }

        //sort is ascending name
        } else if ((sortParameter.getName().equals("Recipe Name: A-Z"))) {
            List<Recipe> sortedRecipes = new ArrayList<>();
            for (Recipe recipe : recipes) {
                sortedRecipes.add(recipe);
                Collections.sort(sortedRecipes, new Recipe.SortByNameAsc());

            }
            model.addAttribute("recipes", sortedRecipes);
            model.addAttribute("categories", Category.values());
            model.addAttribute("sort", SortParameter.values());

        //sort is descending name
        } else if ((sortParameter.getName().equals("Recipe Name: Z-A"))) {
            List<Recipe> sortedRecipes = new ArrayList<>();
            for (Recipe recipe : recipes) {
                sortedRecipes.add(recipe);
                Collections.sort(sortedRecipes, new Recipe.SortByNameDesc());

            }
            model.addAttribute("recipes", sortedRecipes);
            model.addAttribute("categories", Category.values());
            model.addAttribute("sort", SortParameter.values());

        //sort is ascending averageRating
        } else if ((sortParameter.getName().equals("Average Rating: High-Low"))) {
            List<Recipe> sortedRecipes = new ArrayList<>();
            for (Recipe recipe : recipes) {
                sortedRecipes.add(recipe);
                Collections.sort(sortedRecipes, new Recipe.SortByRatingAsc());

            }
            model.addAttribute("recipes", sortedRecipes);
            model.addAttribute("categories", Category.values());
            model.addAttribute("sort", SortParameter.values());

        //sort is descending averageRating
        } else if ((sortParameter.getName().equals("Average Rating: Low-High"))) {
            List<Recipe> sortedRecipes = new ArrayList<>();
            for (Recipe recipe : recipes) {
                sortedRecipes.add(recipe);
                Collections.sort(sortedRecipes, new Recipe.SortByRatingDsc());

            }
            model.addAttribute("recipes", sortedRecipes);
            model.addAttribute("categories", Category.values());
            model.addAttribute("sort", SortParameter.values());

        }
        return "search";
    }


    // filter search results
    @RequestMapping(value = "/filter")
    public String filterSearchResults(@RequestParam Tag tag, Model model) {
        model.addAttribute("filterTypes", Tag.values());

        Iterable<Recipe> recipes = foundRecipes;
        return "search";
    }




}