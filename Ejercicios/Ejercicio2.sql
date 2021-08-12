SELECT pizza.name, SUM(ingredient.price) AS precio FROM pizza
INNER JOIN pizza_ingredient ON pizza.id = pizza_ingredient.id_pizza
INNER JOIN ingredient ON pizza_ingredient.id_ingredient = ingredient.id
GROUP BY pizza.name;