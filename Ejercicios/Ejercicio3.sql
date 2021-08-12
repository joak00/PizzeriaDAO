START TRANSACTION;
INSERT INTO ingredient (id, name, price)
VALUES (100, "Tomate", 1);
INSERT INTO ingredient(id, name, price)
VALUES (200, "Queso", 3);
INSERT INTO pizza (id, name, url)
VALUES (300, "Carbonara", "urlPhoto");
INSERT INTO pizza_ingredient(id, id_pizza, id_ingredient)
VALUES (400, 300, 200);
INSERT INTO pizza_ingredient(id, id_pizza, id_ingredient)
VALUES(500, 300, 100);
COMMIT;
