package org.launchcode.recipeapp.models;

/**
 * @author Oksana
 */
public enum Category {

  BEVERAGES ("Beverage"),
  APPETIZER ("Appetizer"),
  ENTREE ("Entree"),
  SOUP ("Soup"),
  SIDES ("Side"),
  DESSERT ("Dessert");

  private  String name;

  Category(String name) {
    this.name = name;
  }

  public String getName() {
    return name;
  }
}


