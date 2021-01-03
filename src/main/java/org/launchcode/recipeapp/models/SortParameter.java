package org.launchcode.recipeapp.models;

public enum SortParameter {
     NAME_ASCENDING ("Ascending Recipe Name"),
    NAME_DESCENDING ("Descending Recipe Name");

     private String name;

    SortParameter(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}

