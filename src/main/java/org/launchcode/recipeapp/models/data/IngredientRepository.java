package org.launchcode.recipeapp.models.data;

import org.launchcode.recipeapp.models.Ingredient;
import org.springframework.data.repository.CrudRepository;

public interface IngredientRepository extends CrudRepository<Ingredient, Integer> {
}
