let ingredientsContainer = document.getElementById("ingredientsContainer");
let addIngredientButton = document.getElementById("addIngredientButton");
if(addIngredientButton) {
    addIngredientButton.addEventListener('click', (e) => {
        e.preventDefault();
        console.log("hello world");
        let newIngredient = document.createElement("input");
        newIngredient.setAttribute("name", "ingredient");
        ingredientsContainer.appendChild(newIngredient);
    })
}