package org.launchcode.recipeapp.models;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

/**
 * @author Oksana
 */
@Entity
public class Recipe extends AbstractEntity {

   private String name;

   @NotNull(message = "Ingredients required")
   private String ingredients;

   private String directions;

   @NotNull(message = "Category required")
   private Category category;

   private SortParameter sortParameter;

   private Tag tag;

   private String img;

   @OneToMany(mappedBy = "recipe", cascade = {CascadeType.MERGE, CascadeType.REMOVE})
   @NotNull(message = "User is required")
   private List<UserRecipe> users = new ArrayList<>();

   public Recipe() {
   }

   //// Used for sorting in ascending order of name
   public static class SortByName implements Comparator<Recipe> {
      public int compare(Recipe a, Recipe b)
      {
         return a.name.compareTo(b.name);
      }

   }


   public String getName() {
      return name;
   }

   public void setName(String name) {
      this.name = name;
   }


   public Category getCategory() {
      return category;
   }

   public void setCategory(Category category) {
      this.category = category;
   }


   public SortParameter getSortParameter() {
      return sortParameter;
   }

   public void setSortParameter(SortParameter sortParameter) {
      this.sortParameter = sortParameter;
   }

   public List<UserRecipe> getUsers() {
      return users;
   }

   public void setUsers(List<UserRecipe> users) {
      this.users = users;
   }

   public String getImg() {
      return img;
   }

   public void setImg(String img) {
      this.img = img;
   }

   public String getIngredients() {
      return ingredients;
   }

   public void setIngredients(String ingredients) {
      this.ingredients = ingredients;
   }

   public String getDirections() {
      return directions;
   }

   public void setDirections(String directions) {
      this.directions = directions;
   }

   public Tag getTag() {
      return tag;
   }

   public void setTag(Tag tag) {
      this.tag = tag;
   }
}
