SELECT pizza.id AS IdPizzaSinIngredientes, pizza.name AS PizzaNameSinIngredientes
FROM pizza LEFT JOIN pizza_ingredient on Pizza.ID = pizza_ingredient.id_pizza 
WHERE pizza_ingredient.id_ingredient IS NULL;