package org.launchcode.recipeapp.controllers;


import org.launchcode.recipeapp.models.*;
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

    //list of recipes from searching by CATEGORY and  by KEYWORD
    List<Recipe> foundRecipes = new ArrayList<>();

    //list of recipes from "FILTER BY"
    List<Recipe> filteredRecipes = new ArrayList<>();

    //list of recipes from "SORT BY"
    List<Recipe> sortedRecipes = new ArrayList<>();



    //initialize
    @ModelAttribute
    public void initValues(Model model) {
        model.addAttribute("filterTypes", FilterParameter.values());
        model.addAttribute("categories", Category.values());
        model.addAttribute("sort", SortParameter.values());

    }

    @GetMapping("")
    public String renderSearch(Model model) {
        model.addAttribute("categories", Category.values());
        return "search";
    }

    // SEARCH BY KEYWORD
    @PostMapping(value = "/keywordResults")
    public String searchRecipeByKeyword(Model model, @RequestParam String keyword) {
        foundRecipes.clear();
        filteredRecipes.clear();
        String lower_val = keyword.toLowerCase();

        List<Recipe> recipeList = new ArrayList<>();
        Iterable<Recipe> allRecipes = recipeRepository.findAll();
        allRecipes.forEach(recipeList::add);

        // get recipes if KEYWORD is found in name, ingredients, directions, category and tag.
        for (Recipe recipe : recipeList) {
            if (recipe.getName().toLowerCase().contains(lower_val)) {
                foundRecipes.add(recipe);
            } else if (recipe.getIngredients().toLowerCase().contains(lower_val)) {
                foundRecipes.add(recipe);
            } else if (recipe.getDirections().toLowerCase().contains(lower_val)) {
                foundRecipes.add(recipe);
            } else if (recipe.getCategory().toString().toLowerCase().contains(lower_val)) {
                foundRecipes.add(recipe);
            } else if (recipe.getTag().toString().toLowerCase().contains(lower_val)) {
                foundRecipes.add(recipe);
            }
        }
        // Render recipes with searched KEYWORD
        model.addAttribute("recipes", foundRecipes);
        model.addAttribute("categories", Category.values());
        model.addAttribute("sort", SortParameter.values());
        return "search";
    }


    //SEARCH BY CATEGORY
    @PostMapping(value = "/categoryResults")
    public String searchRecipeByCategory(@RequestParam Category category, Model model) {
        foundRecipes.clear();
        filteredRecipes.clear();
        Iterable<Recipe> recipes = recipeRepository.findAll();
        //ALL CATEGORIES and KEYWORD is blank
        if (category == null) {
            for (Recipe recipe : recipes) {
                foundRecipes.add(recipe);

                // render all recipes if ALL CATEGORIES and KEYWORD is blank
                model.addAttribute("recipes", foundRecipes);
                model.addAttribute("categories", Category.values());
                model.addAttribute("sort", SortParameter.values());
            }
        //get all recipes in selected CATEGORY search
        } else {
            for (Recipe recipe : recipes) {
                if (recipe.getCategory().name().toLowerCase().equals(category.name().toLowerCase())) {
                    foundRecipes.add(recipe);
                    model.addAttribute("recipes", foundRecipes);
                    model.addAttribute("categories", Category.values());
                    model.addAttribute("sort", SortParameter.values());
                }
            }
        }
        return "search";
    }


    // FILTER SEARCH RESULTS
    @PostMapping(value = "/filter")
    public String filterResults(@RequestParam FilterParameter[] filterParameter, Model model) {
        filteredRecipes.clear();

        //get all recipes if "ALL" is selected in "FILTER BY"
        for (FilterParameter filterValue : filterParameter) {
            //filter is set to "All"
            if (filterValue.getName().equals("All")) {
                List<Recipe> allRecipes = (List<Recipe>) recipeRepository.findAll();

                //render all recipes
                model.addAttribute("recipes", allRecipes);
                model.addAttribute("categories", Category.values());
                model.addAttribute("sort", SortParameter.values());

                // filter recipes from CATEGORY search OR KEYWORD search
            } else if (!foundRecipes.isEmpty()) {
                sortedRecipes.clear();
                Iterable<Recipe> recipes = foundRecipes;
                for (Recipe recipe : recipes) {
                    if (recipe.getTag().getName().toLowerCase().equals(filterValue.getName().toLowerCase())) {
                        filteredRecipes.add(recipe);

                        //render filtered recipes from category search OR keyword search
                        model.addAttribute("recipes", filteredRecipes);
                        model.addAttribute("categories", Category.values());
                        model.addAttribute("sort", SortParameter.values());
                    }
                }
            } else if (!sortedRecipes.isEmpty()) {
                //filter recipes from "SORT BY"
                Iterable<Recipe> recipes = sortedRecipes;
                List<Recipe> filteredRecipes = new ArrayList<>();
                for (Recipe recipe : recipes) {
                    if (recipe.getTag().getName().toLowerCase().equals(filterValue.getName().toLowerCase())) {
                        filteredRecipes.add(recipe);

                        //render filtered recipes from "SORT BY"
                        model.addAttribute("recipes", filteredRecipes);
                        model.addAttribute("categories", Category.values());
                        model.addAttribute("sort", SortParameter.values());
                    }
                }
            } else {
                //
                if (filteredRecipes.isEmpty()) {
                Iterable<Recipe> recipes = recipeRepository.findAll();
                List<Recipe> filteredRecipes = new ArrayList<>();
                    for (Recipe recipe : recipes) {
                    if (recipe.getTag().getName().toLowerCase().equals(filterValue.getName().toLowerCase())) {
                        filteredRecipes.add(recipe);

                        //render filtered
                        model.addAttribute("recipes", filteredRecipes);
                        model.addAttribute("categories", Category.values());
                        model.addAttribute("sort", SortParameter.values());
                         }
                    }
                }
            }
        }
        return "search";
    }


    //SORT SEARCH RESULTS
    @PostMapping(value = "/sort")
    public String sortSearchResults(@RequestParam SortParameter sortParameter, Model model) {
      sortedRecipes.clear();
        //sort recipes from CATEGORY search OR KEYWORD search by ASCENDING NAME
        if (filteredRecipes.isEmpty()) {
            Iterable<Recipe> recipes = foundRecipes;
            if ((sortParameter.getName().equals(SortParameter.NAME_ASCENDING.getName()))) {
                List<Recipe> sortedRecipes = new ArrayList<>();

                for (Recipe recipe : recipes) {
                    sortedRecipes.add(recipe);
                    Collections.sort(sortedRecipes, new Recipe.SortByNameAsc());

                    //render filtered recipes from CATEGORY search OR KEYWORD search by ASCENDING NAME
                    model.addAttribute("recipes", sortedRecipes);
                    model.addAttribute("categories", Category.values());
                    model.addAttribute("sort", SortParameter.values());
                }
            //sort recipes from CATEGORY search OR KEYWORD search by DESCENDING NAME
            } else if ((sortParameter.getName().equals(SortParameter.NAME_DESCENDING.getName()))) {
                List<Recipe> sortedRecipes = new ArrayList<>();

                for (Recipe recipe : recipes) {
                    sortedRecipes.add(recipe);
                    Collections.sort(sortedRecipes, new Recipe.SortByNameDesc());

                    //render filtered recipes from CATEGORY search OR KEYWORD search by DESCENDING NAME
                    model.addAttribute("recipes", sortedRecipes);
                    model.addAttribute("categories", Category.values());
                    model.addAttribute("sort", SortParameter.values());

                }
            //sort recipes from CATEGORY search OR KEYWORD search by ASCENDING RATING
            } else if ((sortParameter.getName().equals(SortParameter.RATING_ASCENDING.getName()))) {
                List<Recipe> sortedRecipes = new ArrayList<>();

                for (Recipe recipe : recipes) {
                    sortedRecipes.add(recipe);
                    Collections.sort(sortedRecipes, new Recipe.SortByRatingAsc());

                    //render filtered recipes from CATEGORY search OR KEYWORD search by ASCENDING RATING
                    model.addAttribute("recipes", sortedRecipes);
                    model.addAttribute("categories", Category.values());
                    model.addAttribute("sort", SortParameter.values());
                }
            //sort recipes from CATEGORY search OR KEYWORD search by DESCENDING RATING
            } else if ((sortParameter.getName().equals(SortParameter.RATING_DESCENDING.getName()))) {
                List<Recipe> sortedRecipes = new ArrayList<>();

                for (Recipe recipe : recipes) {
                    sortedRecipes.add(recipe);
                    Collections.sort(sortedRecipes, new Recipe.SortByRatingDsc());
                    //render filtered recipes from CATEGORY search OR KEYWORD search by DESCENDING RATING
                    model.addAttribute("recipes", sortedRecipes);
                    model.addAttribute("categories", Category.values());
                    model.addAttribute("sort", SortParameter.values());
                }
            }
        //sort recipes from "FILTER BY"
        } else {
            Iterable<Recipe> recipes = filteredRecipes;
            //sort recipes from "FILTER BY"  by ASCENDING NAME
            if ((sortParameter.getName().equals(SortParameter.NAME_ASCENDING.getName()))) {
                List<Recipe> sortedRecipes = new ArrayList<>();
                for (Recipe recipe : recipes) {
                    sortedRecipes.add(recipe);
                    Collections.sort(sortedRecipes, new Recipe.SortByNameAsc());

                    //render recipes from "FILTER BY"  by ASCENDING NAME
                    model.addAttribute("recipes", sortedRecipes);
                    model.addAttribute("categories", Category.values());
                    model.addAttribute("sort", SortParameter.values());
                }
            //sort recipes from "FILTER BY"  by DESCENDING NAME
            }  else if ((sortParameter.getName().equals(SortParameter.NAME_DESCENDING.getName()))) {
                List<Recipe> sortedRecipes = new ArrayList<>();
                for (Recipe recipe : recipes) {
                    sortedRecipes.add(recipe);
                    Collections.sort(sortedRecipes, new Recipe.SortByNameDesc());

                    //render recipes from "FILTER BY"  by DESCENDING NAME
                    model.addAttribute("recipes", sortedRecipes);
                    model.addAttribute("categories", Category.values());
                    model.addAttribute("sort", SortParameter.values());
                }
            //sort recipes from "FILTER BY"  by ASCENDING RATING
            } else if ((sortParameter.getName().equals(SortParameter.RATING_ASCENDING.getName()))) {
                List<Recipe> sortedRecipes = new ArrayList<>();

                for (Recipe recipe : recipes) {
                    sortedRecipes.add(recipe);
                    Collections.sort(sortedRecipes, new Recipe.SortByRatingAsc());

                    //render recipes from "FILTER BY"  by ASCENDING RATING
                    model.addAttribute("recipes", sortedRecipes);
                    model.addAttribute("categories", Category.values());
                    model.addAttribute("sort", SortParameter.values());
                }
            //sort recipes from "FILTER BY"  by DESCENDING RATING
            } else if ((sortParameter.getName().equals(SortParameter.RATING_DESCENDING.getName()))) {
                List<Recipe> sortedRecipes = new ArrayList<>();

                for (Recipe recipe : recipes) {
                    sortedRecipes.add(recipe);
                    Collections.sort(sortedRecipes, new Recipe.SortByRatingDsc());

                    //render recipes from "FILTER BY"  by DESCENDING RATING
                    model.addAttribute("recipes", sortedRecipes);
                    model.addAttribute("categories", Category.values());
                    model.addAttribute("sort", SortParameter.values());
                }
            }
        }
        return "search";
    }
}

