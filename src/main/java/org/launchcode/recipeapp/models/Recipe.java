package org.launchcode.recipeapp.models;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Locale;

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

   private Double averageRating;
   private Integer numComments = 0;

   @OneToMany(mappedBy = "recipe")
   private final List<Review> reviews = new ArrayList<>();

   @OneToMany(mappedBy = "recipe", cascade = {CascadeType.MERGE, CascadeType.REMOVE})
   @NotNull(message = "User is required")
   private List<UserRecipe> users = new ArrayList<>();




   //// Used for sorting in ascending order of name
   public static class SortByNameAsc implements Comparator<Recipe> {
      public int compare(Recipe a, Recipe b) {
         return a.name.toLowerCase().compareTo(b.name.toLowerCase());
      }

   }

   //// Used for sorting in descending order of name
   public static class SortByNameDesc implements Comparator<Recipe> {
      public int compare(Recipe a, Recipe b) {
         return b.name.toLowerCase().compareTo(a.name.toLowerCase());
      }

   }

   //// Used for sorting in ascending order of averageRating
   public static class SortByRatingAsc implements Comparator<Recipe> {
      public int compare(Recipe a, Recipe b) {
         if (a.averageRating == null && b.averageRating == null)
            return 0;
         if (a.averageRating == null)
            return 1;
         else if (b.averageRating == null)
            return -1;
         return b.averageRating.compareTo(a.averageRating);
      }
   }

   //// Used for sorting in descending order of averageRating
   public static class SortByRatingDsc implements Comparator<Recipe> {
      public int compare(Recipe a, Recipe b) {
         if (a.averageRating == null && b.averageRating == null)
            return 0;
         if (b.averageRating == null)
            return 1;
         else if (a.averageRating == null)
            return -1;
         return a.averageRating.compareTo(b.averageRating);
      }
   }


   public Recipe() {
   }


   // Getters and Setters
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

   public List<Review> getReviews() {
      return reviews;
   }


   public Double getAverageRating() {
      return averageRating;
   }

   public void setAverageRating() {
      List<Review> reviewList = getReviews();
      double numRatings = reviewList.size();
      double sumRatings = 0.0;

      for(int i =0; i < numRatings; i++){
         double reviewRating = reviewList.get(i).getRating();
         sumRatings += reviewRating;
      }
      double average = Double.parseDouble(String.format("%.1f",(double)sumRatings  /  numRatings));
      averageRating = average;
   }

   public Integer getNumComments() {
      return numComments;
   }


   public Integer setNumComments(Review review){
      if(numComments == null){
         numComments = 0;
      }
      if (!review.getComment().isEmpty()){
         numComments ++;
      }
      return numComments;
   }



   @Override
   public String toString() {
      return "Recipe{" +
              "name='" + name + '\'' +
              ", ingredients='" + ingredients + '\'' +
              ", directions='" + directions + '\'' +
              ", category=" + category +
              ", tag=" + tag +
              ", img='" + img + '\'' +
              ", averageRating=" + averageRating +
              ", reviews=" + reviews +
              ", users=" + users +
              '}';
   }


}