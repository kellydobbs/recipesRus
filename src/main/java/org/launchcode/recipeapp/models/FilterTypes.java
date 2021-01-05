package org.launchcode.recipeapp.models;

public enum FilterTypes {

        BEVERAGES ("Beverage"),
        APPETIZER ("Appetizer"),
        ENTREE ("Entree"),
        SOUP ("Soup"),
        SIDES ("Side"),
        DESSERT ("Dessert");

        private  String name;

        FilterTypes (String name) {
            this.name = name;
        }

        public String getName() {
            return name;
        }
    }
