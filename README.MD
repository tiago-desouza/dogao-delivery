
# Dogão-Delivery

This is a backend system for ordering food.



## Documentation

Under construction

## Tech Stack

* **Language:** Java 8
* **Framework:** Spring Boot
* **Database:** MySQL 8.0
* **CI/CD:** Docker, Docker-Compose


## Run locally

Clone the project

```bash
  git clone https://github.com/tiago-desouza/dogao-delivery.git
```

Enter the project directory

```bash
  cd dogao-delivery
```

Start Database
```bash
  docker-compose up
```


Start the server

```bash
  mvn spring-boot:run
```



## For Test

To test the system just import the **curl-import.json** file for insomnia or Postman which is inside the project directory

or via Curl:

### Ingredient
```bash
curl --request POST \
--url http://localhost:8080/v1/ingredient \
--header 'Content-Type: application/json' \
--data '	{
"name": "Queijo",
"price": 1.5
}'
```

```bash
curl --request PUT \
  --url http://localhost:8080/v1/ingredient/1 \
  --header 'Content-Type: application/json' \
  --data '	{
		"name": "Queijo provolone 2.0",
		"price": 3
	}'
```

```bash
curl --request GET \
  --url http://localhost:8080/v1/ingredient
```

```bash
curl --request GET \
  --url http://localhost:8080/v1/ingredient/1
```

```bash
curl --request DELETE \
  --url http://localhost:8080/v1/ingredient/4
```
### Product
```bash
curl --request POST \
  --url http://localhost:8080/v1/product \
  --header 'Content-Type: application/json' \
  --data '{
	"name": "X-Bacon",
	"ingredientNames": [
		"Bacon",
		"hamburguer",
		"queijo"
	]
}'
```

```bash
curl --request PUT \
  --url http://localhost:8080/v1/product/1 \
  --header 'Content-Type: application/json' \
  --data '{
	"name": "X-Egg Bacon Salada 4.1",
	"ingredientNames": [
		"Ovo",
		"Queijo",
		"hambúrguer",
		"alface"
	]
}'
```

```bash
curl --request GET \
  --url http://localhost:8080/v1/product
```

```bash
curl --request GET \
  --url http://localhost:8080/v1/product/2
```

```bash
curl --request DELETE \
  --url http://localhost:8080/v1/product/2
```
### Order
```bash
curl --request POST \
  --url http://localhost:8080/v1/order \
  --header 'Content-Type: application/json' \
  --data '{
	"order_products": [
		{
			"product_name": "X-Egg",
			"additional_ingredients": [
				{
					"ingredient_name": "alface",
					"ingredient_quantity": 1
				}
			]
		}
	]
}'
```

```bash
curl --request GET \
  --url http://localhost:8080/v1/order/1
```

```bash
curl --request GET \
  --url http://localhost:8080/v1/order
```

```bash
curl --request DELETE \
  --url http://localhost:8080/v1/order/1
```
### Promotion
```bash
curl --request POST \
  --url http://localhost:8080/v1/promotion \
  --header 'Content-Type: application/json' \
  --data '{
	"promotion_title": "Light",
	"promotion_description": "Se o lanche tem alface e não tem bacon, ganha 10% de desconto",
	"promotion_include_ingredient": "alface",
	"promotion_exclude_ingredient": "bacon",
	"promotion_type": "LIGHT",
	"promotion_discount_percentage": 10,
	"promotion_quantity": 0
}'
```

```bash
curl --request GET \
  --url http://localhost:8080/v1/promotion/1
```

```bash
curl --request GET \
  --url http://localhost:8080/v1/promotion
```

```bash
curl --request PUT \
  --url http://localhost:8080/v1/promotion/3/enable
```

```bash
curl --request PUT \
  --url http://localhost:8080/v1/promotion/10/disable
```

