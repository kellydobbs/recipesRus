package org.launchcode.recipeapp.models;

import lombok.Data;
import lombok.ToString;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.PreRemove;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Data
@ToString(exclude = "users")
@DynamicInsert
@DynamicUpdate
@Entity
public class Recipe extends AbstractEntity {

   private String name;

   @NotNull(message = "Ingredients required")
   private String ingredients;

   private String directions;

   @NotNull(message = "Category required")
   private Category category;

   private Tag tag;

   private String img;

   private Double averageRating;
   private Integer numComments = 0;

   @OneToMany(mappedBy = "recipe")
   private final List<Review> reviews = new ArrayList<>();

   @OneToMany(mappedBy = "recipe", cascade = {CascadeType.MERGE, CascadeType.REMOVE})
   @NotNull(message = "User is required")
   private List<UserRecipe> users = new ArrayList<>();

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
   public boolean equals(Object o) {
      if (this == o) return true;
      if (!(o instanceof Recipe)) return false;
      if (!super.equals(o)) return false;
      Recipe recipe = (Recipe) o;
      return Objects.equals(getName(), recipe.getName()) && Objects.equals(getIngredients(), recipe.getIngredients()) && Objects.equals(getDirections(), recipe.getDirections()) && getCategory() == recipe.getCategory() && getTag() == recipe.getTag() && Objects.equals(getImg(), recipe.getImg()) && Objects.equals(getAverageRating(), recipe.getAverageRating()) && Objects.equals(getNumComments(), recipe.getNumComments()) && Objects.equals(getReviews(), recipe.getReviews()) && Objects.equals(getUsers(), recipe.getUsers());
   }

   @Override
   public int hashCode() {
      return Objects.hash(super.hashCode(), getName(), getIngredients(), getDirections(), getCategory(), getTag(), getImg(), getAverageRating(), getNumComments(), getReviews(), getUsers());
   }


//   @PreRemove
//   private void removeGroupsFromUsers() {
//      for (UserRecipe u : users) {
//         u.getGroups().remove(this);
//      }
//   }
}
