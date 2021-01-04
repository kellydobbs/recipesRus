package org.launchcode.recipeapp.models;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.validation.constraints.NotNull;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Oksana
 */
@Entity
public class Recipe extends AbstractEntity {

   private String name;

   @OneToMany(mappedBy = "recipe", cascade = CascadeType.ALL)
   private List<Ingredient> ingredients = new ArrayList<Ingredient>();

   @OneToMany(mappedBy = "recipe", cascade = CascadeType.ALL)
   private List<Instruction> instructions = new ArrayList<Instruction>();

   @NotNull(message = "Category required")
   private Category category;

   private Tag tag;

   private String img;

   @OneToMany(mappedBy = "recipe", cascade = {CascadeType.MERGE, CascadeType.REMOVE})
   @NotNull(message = "User is required")
   private List<UserRecipe> users = new ArrayList<>();

   public Recipe() {
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


   public List<Ingredient> getIngredients() {
      return ingredients;
   }

   public void setIngredients(List<Ingredient> ingredients) {
      this.ingredients = ingredients;
   }


   public Tag getTag() {
      return tag;
   }

   public void setTag(Tag tag) {
      this.tag = tag;
   }

   public List<Instruction> getInstructions() {
      return instructions;
   }

   public void setInstructions(List<Instruction> instructions) {
      this.instructions = instructions;
   }
}
