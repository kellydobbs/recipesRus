package org.launchcode.recipeapp.models;

import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.validation.constraints.NotBlank;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Oksana
 */
@Data
@Entity
@EqualsAndHashCode(callSuper=false)
public class User extends AbstractEntity {



   private static final BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();



   @NotBlank(message = "Username is required")
   private String username;


   @NotBlank(message = "Password is required")
   private String pwHash;

   @OneToMany(mappedBy = "user", cascade = {CascadeType.MERGE, CascadeType.REMOVE})
   private List<UserRecipe> recipes = new ArrayList<>();

   private String access;

   public User() {
   }

   public User(String username, String password, String access) {
      this.username = username;
      this.pwHash = encoder.encode(password);
      this.access = access;
   }

   public boolean isMatchingPassword(String password) { return encoder.matches(password, pwHash); }

   public String getAccess() { return access; }

   public String getUsername() {
      return username;
   }

   public void setAccess(String access) { this.access = access; }

   public void setUsername(String username) {
      this.username = username;
   }
}
