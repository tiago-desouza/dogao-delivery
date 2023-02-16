INSERT INTO dogao_db.ingredient
(id, name, price)
VALUES(1, 'alface', 0.40);
INSERT INTO dogao_db.ingredient
(id, name, price)
VALUES(2, 'bacon', 2.00);
INSERT INTO dogao_db.ingredient
(id, name, price)
VALUES(3, 'hamburguer', 3.00);
INSERT INTO dogao_db.ingredient
(id, name, price)
VALUES(4, 'ovo', 0.80);
INSERT INTO dogao_db.ingredient
(id, name, price)
VALUES(5, 'queijo', 1.50);

INSERT INTO dogao_db.product
(id, name)
VALUES(1, 'x-egg bacon');
INSERT INTO dogao_db.product
(id, name)
VALUES(2, 'x-egg');
INSERT INTO dogao_db.product
(id, name)
VALUES(3, 'x-burger');
INSERT INTO dogao_db.product
(id, name)
VALUES(4, 'x-bacon');


INSERT INTO dogao_db.product_ingredients
(product_id, ingredients_id)
VALUES(1, 4);
INSERT INTO dogao_db.product_ingredients
(product_id, ingredients_id)
VALUES(1, 3);
INSERT INTO dogao_db.product_ingredients
(product_id, ingredients_id)
VALUES(1, 2);
INSERT INTO dogao_db.product_ingredients
(product_id, ingredients_id)
VALUES(1, 5);
INSERT INTO dogao_db.product_ingredients
(product_id, ingredients_id)
VALUES(2, 4);
INSERT INTO dogao_db.product_ingredients
(product_id, ingredients_id)
VALUES(2, 3);
INSERT INTO dogao_db.product_ingredients
(product_id, ingredients_id)
VALUES(2, 5);
INSERT INTO dogao_db.product_ingredients
(product_id, ingredients_id)
VALUES(3, 3);
INSERT INTO dogao_db.product_ingredients
(product_id, ingredients_id)
VALUES(3, 5);
INSERT INTO dogao_db.product_ingredients
(product_id, ingredients_id)
VALUES(4, 2);
INSERT INTO dogao_db.product_ingredients
(product_id, ingredients_id)
VALUES(4, 3);
INSERT INTO dogao_db.product_ingredients
(product_id, ingredients_id)
VALUES(4, 5);

INSERT INTO dogao_db.promotion
(id, description, discount_percentage, quantity, status_promotion, title, type_promotion, exclude_ingredient_id, include_ingredient_id)
VALUES(1, 'Compre 3 queijos e pague 2', 0, 3, 'ACTIVE', 'muito queijo', 'QUANTITY_ABOVE', NULL, 5);
INSERT INTO dogao_db.promotion
(id, description, discount_percentage, quantity, status_promotion, title, type_promotion, exclude_ingredient_id, include_ingredient_id)
VALUES(2, 'Compre 3 hamburguers e pague 2', 0, 3, 'ACTIVE', 'muita carne', 'QUANTITY_ABOVE', NULL, 3);
INSERT INTO dogao_db.promotion
(id, description, discount_percentage, quantity, status_promotion, title, type_promotion, exclude_ingredient_id, include_ingredient_id)
VALUES(3, 'Se o lanche tem alface e não tem bacon, ganha 10% de desconto', 10, 0, 'ACTIVE', 'light', 'LIGHT', 2, 1);


INSERT INTO dogao_db.order_tb
(id, amount, discount, total_order_amount)
VALUES(1, 28.30, 0.00, 28.30);
INSERT INTO dogao_db.order_tb
(id, amount, discount, total_order_amount)
VALUES(2, 10.30, 0.00, 10.30);
INSERT INTO dogao_db.order_tb
(id, amount, discount, total_order_amount)
VALUES(3, 13.30, 0.00, 13.30);
INSERT INTO dogao_db.order_tb
(id, amount, discount, total_order_amount)
VALUES(4, 13.30, 3.00, 10.30);
INSERT INTO dogao_db.order_tb
(id, amount, discount, total_order_amount)
VALUES(5, 22.30, 6.00, 16.30);
INSERT INTO dogao_db.order_tb
(id, amount, discount, total_order_amount)
VALUES(6, 7.30, 0.00, 7.30);
INSERT INTO dogao_db.order_tb
(id, amount, discount, total_order_amount)
VALUES(7, 5.70, 0.57, 5.13);


INSERT INTO dogao_db.order_product
(id, product_id, order_id)
VALUES(1, 1, 1);
INSERT INTO dogao_db.order_product
(id, product_id, order_id)
VALUES(2, 1, 2);
INSERT INTO dogao_db.order_product
(id, product_id, order_id)
VALUES(3, 1, 3);
INSERT INTO dogao_db.order_product
(id, product_id, order_id)
VALUES(4, 1, 4);
INSERT INTO dogao_db.order_product
(id, product_id, order_id)
VALUES(5, 1, 5);
INSERT INTO dogao_db.order_product
(id, product_id, order_id)
VALUES(6, 1, 6);
INSERT INTO dogao_db.order_product
(id, product_id, order_id)
VALUES(7, 2, 7);


INSERT INTO dogao_db.additional_ingredient
(id, quantity, ingredient_id, order_product_id)
VALUES(1, 7, 3, 1);
INSERT INTO dogao_db.additional_ingredient
(id, quantity, ingredient_id, order_product_id)
VALUES(2, 1, 3, 2);
INSERT INTO dogao_db.additional_ingredient
(id, quantity, ingredient_id, order_product_id)
VALUES(3, 2, 3, 3);
INSERT INTO dogao_db.additional_ingredient
(id, quantity, ingredient_id, order_product_id)
VALUES(4, 2, 3, 4);
INSERT INTO dogao_db.additional_ingredient
(id, quantity, ingredient_id, order_product_id)
VALUES(5, 5, 3, 5);
INSERT INTO dogao_db.additional_ingredient
(id, quantity, ingredient_id, order_product_id)
VALUES(6, 1, 1, 7);
