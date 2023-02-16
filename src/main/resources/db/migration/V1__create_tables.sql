-- dogao_db.ingredient definition

CREATE TABLE IF NOT EXISTS `ingredient` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `name` varchar(255) NOT NULL,
  `price` decimal(19,2) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- dogao_db.product definition

CREATE TABLE IF NOT EXISTS `product` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `name` varchar(255) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- dogao_db.product_ingredients definition

CREATE TABLE IF NOT EXISTS `product_ingredients` (
  `product_id` bigint NOT NULL,
  `ingredients_id` bigint NOT NULL,
  KEY `ingredient_product_ingredients_fk` (`ingredients_id`),
  KEY `product_product_ingredients_fk` (`product_id`),
  CONSTRAINT `product_product_ingredients_fk` FOREIGN KEY (`product_id`) REFERENCES `product` (`id`),
  CONSTRAINT `ingredient_product_ingredients_fk` FOREIGN KEY (`ingredients_id`) REFERENCES `ingredient` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- dogao_db.order_tb definition

CREATE TABLE IF NOT EXISTS `order_tb` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `amount` decimal(19,2) NOT NULL,
  `discount` decimal(19,2) NOT NULL,
  `total_order_amount` decimal(19,2) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- dogao_db.order_product definition

CREATE TABLE IF NOT EXISTS `order_product` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `product_id` bigint DEFAULT NULL,
  `order_id` bigint DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `product_order_fk` (`product_id`),
  KEY `order_order_product_fk` (`order_id`),
  CONSTRAINT `product_order_fk` FOREIGN KEY (`product_id`) REFERENCES `product` (`id`),
  CONSTRAINT `order_order_product_fk` FOREIGN KEY (`order_id`) REFERENCES `order_tb` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- dogao_db.additional_ingredient definition

CREATE TABLE IF NOT EXISTS `additional_ingredient` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `quantity` int DEFAULT NULL,
  `ingredient_id` bigint DEFAULT NULL,
  `order_product_id` bigint DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `ingredient_additional_ingredient_fk` (`ingredient_id`),
  KEY `order_product_addtional_ingredient_fk` (`order_product_id`),
  CONSTRAINT `order_product_addtional_ingredient_fk` FOREIGN KEY (`order_product_id`) REFERENCES `order_product` (`id`),
  CONSTRAINT `ingredient_additional_ingredient_fk` FOREIGN KEY (`ingredient_id`) REFERENCES `ingredient` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- dogao_db.promotion definition

CREATE TABLE IF NOT EXISTS `promotion` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `description` varchar(255) NOT NULL,
  `discount_percentage` int DEFAULT NULL,
  `quantity` int DEFAULT NULL,
  `status_promotion` varchar(255) NOT NULL,
  `title` varchar(255) NOT NULL,
  `type_promotion` varchar(255) NOT NULL,
  `exclude_ingredient_id` bigint DEFAULT NULL,
  `include_ingredient_id` bigint DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `exclude_ingredient_promotion_fk` (`exclude_ingredient_id`),
  KEY `include_ingredient_promotion_fk` (`include_ingredient_id`),
  CONSTRAINT `include_ingredient_promotion_fk` FOREIGN KEY (`include_ingredient_id`) REFERENCES `ingredient` (`id`),
  CONSTRAINT `exclude_ingredient_promotion_fk` FOREIGN KEY (`exclude_ingredient_id`) REFERENCES `ingredient` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;