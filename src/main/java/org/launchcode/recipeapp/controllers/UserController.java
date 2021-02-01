package org.launchcode.recipeapp.controllers;

import org.launchcode.recipeapp.models.*;
import org.launchcode.recipeapp.models.data.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

/**
 * @author Oksana
 */
@Controller
@RequestMapping("users")
public class UserController {

   @Autowired
   private UserRepository userRepository;

   @Autowired
   private UserRecipeRepository userRecipeRepository;

   @Autowired
   private RecipeRepository recipeRepository;

   @Autowired
   private ShoppingListRepository shoppingListRepository;

   @Autowired
   private RecipeShoppingListRepository recipeShoppingListRepository;

   @Autowired
   private TagRepository tagRepository;
   List<Recipe> recipes = new ArrayList<>();
   List<Tag> selectedTags = new ArrayList<>();


   @GetMapping()
   public String getAllUsers(Model model) {
      List<User> users = new ArrayList<>();

      Iterable<User> usersIter = userRepository.findAll();


      usersIter.forEach(users::add);


      model.addAttribute("users", users);
      return "users/index";
   }

   @GetMapping("/profile")
   public String getUserProfile(HttpServletRequest request, Model model, RedirectAttributes redirectAttributes) {
      String sessionUser = (String) request.getSession().getAttribute("user");
      selectedTags.clear();

      if (sessionUser == null) {
         model.addAttribute("title", "No user found");
      } else {

         recipes.clear();
         User user = userRepository.findByUsername(sessionUser);
         List<UserRecipe> userRecipes = userRecipeRepository.findAllByUser(user);

         for (UserRecipe userRecipe : userRecipes) {
            Recipe recipe = userRecipe.getRecipe();
            recipes.add(recipe);
         }

         model.addAttribute("title", sessionUser);
         model.addAttribute("user", sessionUser);
         model.addAttribute("recipes", recipes);
         model.addAttribute("title1", redirectAttributes.getAttribute("title1"));
         model.addAttribute("categories", Category.values());
         model.addAttribute("sort", SortParameter.values());


         List<Tag> tags = tagRepository.findAll();
         List<Tag> filterTags = new ArrayList<>();
         for (Tag tag : tags) {
            if(tag.getIsFilterable() == null){}
            else if (tag.getIsFilterable()) {
               filterTags.add(tag);
            }
         }
         model.addAttribute("tag", filterTags);


      }
      return "users/profile";
   }


   @PostMapping("/profile/sort")
   public String sortMyRecipes(@RequestParam SortParameter sortParameter, HttpServletRequest request,Category category, Model model) {
      String sessionUser = (String) request.getSession().getAttribute("user");
      User user = userRepository.findByUsername(sessionUser);

      if(selectedTags.isEmpty()) {
         selectedTags = tagRepository.findAll();
      }

      recipes.clear();
      for (UserRecipe userRecipe : user.getRecipes()) {
         Optional<Recipe> singleRecipe = recipeRepository.findById(userRecipe.getRecipe().getId());
         if(singleRecipe.isPresent()) {
            recipes.add(singleRecipe.get());
         }
      }

      List<Tag> filterTags = new ArrayList<>();

      for (Tag tag : tagRepository.findAll()) {
         if(tag.getIsFilterable() == null){}
         else if (tag.getIsFilterable()) {
            filterTags.add(tag);
         }
      }

      model.addAttribute("tag", filterTags);

      List<Recipe> filteredRecipes = new ArrayList<>();

      for (Recipe recipe : recipes) {
         for (Tag recipeTag : recipe.getTags()) {
            for (Tag tag : selectedTags) {
               if (recipeTag.getId() == tag.getId()) {
                  if (!filteredRecipes.contains(recipe)) {
                     filteredRecipes.add(recipe);
                  }
               }
            }
         }
      }


      //If selected sort is NAME ASCENDING
      if ((sortParameter.getName().equals(SortParameter.NAME_ASCENDING.getName()))) {

         Collections.sort(filteredRecipes, new Recipe.SortByNameAsc());

         //If selected sort is NAME DESCENDING
      } else if ((sortParameter.getName().equals(SortParameter.NAME_DESCENDING.getName()))) {
         Collections.sort(filteredRecipes, new Recipe.SortByNameDesc());

         //if selected sort is ASCENDING RATING
      } else if ((sortParameter.getName().equals(SortParameter.RATING_ASCENDING.getName()))) {
         Collections.sort(filteredRecipes, new Recipe.SortByRatingAsc());


         //if selected sort is DESCENDING RATING
      } else if ((sortParameter.getName().equals(SortParameter.RATING_DESCENDING.getName()))) {
         Collections.sort(filteredRecipes, new Recipe.SortByRatingDsc());

      }
      //render  sorted recipes
      model.addAttribute("recipes", filteredRecipes);
      model.addAttribute("categories", Category.values());
      model.addAttribute("category", category);
      model.addAttribute("sort", SortParameter.values());

      return "users/profile";
   }



   @PostMapping(value = "/profile/filter")
   public String filterMyRecipes(@RequestParam (required =false) List<Integer> tagId, @RequestParam  Category category, Model model) {
      model.addAttribute("category", category);
      selectedTags.clear();

      // store selected filters in an arrayList
      List<Tag> allTags = tagRepository.findAll();

      if(tagId != null) {
         for (Integer aTagId : tagId) {
            selectedTags.add(tagRepository.findById(aTagId).get());
         }
      } else if( tagId == null) {
         selectedTags = allTags;
      }

      // filter checkboxes

      List<Tag> filters = new ArrayList<>();
      for (Tag aTag : allTags) {
         if (aTag.getIsFilterable() == null) {
            } else if (aTag.getIsFilterable()) {
               filters.add(aTag);
               model.addAttribute("tag", filters);
         }
      }

      // find and store recipes with the selected tag
      List<Recipe> filteredRecipes = new ArrayList<>();
      for (Recipe recipe : recipes) {
         for (Tag recipeTag : recipe.getTags()) {
            for (Tag tag : selectedTags) {
               if (recipeTag.getId() == tag.getId()) {
                  if(!filteredRecipes.contains(recipe)) {
                     filteredRecipes.add(recipe);
                  }
               }
            }
         }
      }

      //render filtered recipes
      model.addAttribute("recipes", filteredRecipes);
      model.addAttribute("categories", Category.values());
      model.addAttribute("sort", SortParameter.values());

      return "users/profile";
   }



   @PostMapping("/addRecipe/{id}")
   public String addRecipe(@PathVariable Integer id, HttpServletRequest request, Model model,
                           RedirectAttributes redirectAttrs) {
      String sessionUser = (String) request.getSession().getAttribute("user");

      Optional<Recipe> recipeOptional = recipeRepository.findById(id);
      if (recipeOptional.isPresent()) {
         User user = userRepository.findByUsername(sessionUser);
         Recipe recipe = recipeOptional.get();


         UserRecipe userRecipe = new UserRecipe();
         userRecipe.setUser(user);
         userRecipe.setRecipe(recipe);

         Optional<UserRecipe> recipeByUserOptional = userRecipeRepository.findByRecipeAndUser(recipe,user);
         if (recipeByUserOptional.isPresent()) {
            return "redirect:/recipes/display?recipeId="+recipe.getId();
         } else {
            userRecipeRepository.save(userRecipe);
         }
      }

      return "redirect:/users/profile";
   }


   @PostMapping("/deleteRecipe/{id}")
   public String deleteRecipeFromFavorite(@PathVariable Integer id, HttpServletRequest request, Model model) {
      String sessionUser = (String) request.getSession().getAttribute("user");
      User user = userRepository.findByUsername(sessionUser);
      List<UserRecipe> userRecipes = userRecipeRepository.getAllByUser(user);

      Optional<Recipe> recipeOptional = recipeRepository.findById(id);
      if (recipeOptional.isPresent()) {

         Recipe recipe = recipeOptional.get();

         for (UserRecipe userRecipe : userRecipes) {
            Optional<Recipe> recipeOpt = recipeRepository.findById(userRecipe.getRecipe().getId());
            if (recipeOpt.isPresent()) {
               Recipe recipe1 = recipeOpt.get();
               if (recipe1.getId() == recipe.getId()) {

                  userRecipeRepository.delete(userRecipe);

               }

            }
         }

      }

      return "redirect:/users/profile";
   }

   @GetMapping("/shoppingList")
   public String getUserShoppingList(Model model, HttpServletRequest request){
      User sessionUser = (User) request.getSession().getAttribute("user");
      if (sessionUser == null) {
         model.addAttribute("title", "No user found");
         return "/login";
      }

      User user = userRepository.getById(sessionUser.getId());
      Optional<ShoppingList> shoppingListOptional = shoppingListRepository.findByUserAndActive(user, true);
      if (shoppingListOptional.isPresent()) {
         ShoppingList shoppingList = shoppingListOptional.get();
         model.addAttribute("shoppingList", shoppingList);
      }

      return "users/shopping-list";
   }

   @PostMapping("/shoppingList/close")
   public String closeShoppingList(HttpServletRequest request) {
      User sessionUser = (User) request.getSession().getAttribute("user");
      if (sessionUser == null) {
         return "/login";
      }

      User user = userRepository.getById(sessionUser.getId());
      Optional<ShoppingList> shoppingListOptional = shoppingListRepository.findByUserAndActive(user, true);

      if (shoppingListOptional.isPresent()) {
         ShoppingList shoppingList = shoppingListOptional.get();
         shoppingList.setActive(false);
         shoppingListRepository.save(shoppingList);
      }

      return "redirect:/users/shoppingList";
   }

   @Transactional
   @PostMapping("/shoppingList/add/{recipeId}")
   public String addToShoppingList(@PathVariable int recipeId, @RequestParam int portions,
                                   Model model, HttpServletRequest request) {
      User sessionUser = (User) request.getSession().getAttribute("user");
      if (sessionUser == null) {
         model.addAttribute("title", "No user found");
         return "/login";
      }

      User user = userRepository.getById(sessionUser.getId());
      Optional<ShoppingList> shoppingListOptional = shoppingListRepository.findByUserAndActive(user, true);

      ShoppingList shoppingList;
      if (shoppingListOptional.isPresent()) {
         shoppingList = shoppingListOptional.get();
      } else {
         shoppingList = new ShoppingList();
         shoppingList.setUser(user);
         shoppingList.setActive(true);

         shoppingList = shoppingListRepository.save(shoppingList);
      }

      saveRecipeToShoppingList(recipeId, portions, shoppingList);

      return "redirect:/users/shoppingList";
   }

   private void saveRecipeToShoppingList(int recipeId, int portions, ShoppingList shoppingList) {
      Recipe recipe = recipeRepository.getById(recipeId);
      RecipeShoppingList recipeShoppingList = new RecipeShoppingList();
      recipeShoppingList.setShoppingList(shoppingList);
      recipeShoppingList.setRecipe(recipe);
      if (portions == 0) {
         portions = 1;
      }
      recipeShoppingList.setPortions(portions);
      recipeShoppingListRepository.save(recipeShoppingList);
   }

}





