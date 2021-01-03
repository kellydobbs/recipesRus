package org.launchcode.recipeapp.models;

public enum SortParameter {
    NAME_ASCENDING ("Ascending Recipe Name"),
    NAME_DESCENDING ("Descending Recipe Name"),
    TIME_ASCENDING ("Ascending Total Time"),
    TIME_DESCENDING ("Descending Total Time"),
    RATING_ASCENDING ("Rating: Highest - Lowest"),
    RATING_DESCENDING ("Rating: Lowest - Highest");


     private String name;

    SortParameter(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}

