Проект Интернет магазина тканей "Metry" (Name: metryfabricshopnew)

technologies:
-Java
-Spring boot
-Spring web
-Spring security
-thymeleaf
-Spring data jpa + postgresql version (4.30 (datasource.url=jdbc:postgresql://localhost:5432/shop))
additionally:
-flyway
-lombok

Базовый функционал:
1.Аутентификация и регистрация пользователей
2.Защита Web-приложения
3.Просмотр таваров
4. Добавление в корзину
5. Формирование заказов
6. Оплата
7. Сортировка товаров
8. Управление корзиной
9. Валидация вводимых данных
10. Оповещение по e-mail
11. Навигация

Entities:

1.Product
    -id
    -title
    -price
    -categories
    -sales(optional)

2.User
    -id
    -username
    -email
    -password
    -roles    
    -archive
    -numberPhone(optional)
    -address (optional)
    -phone

3.Role
    -CLIENT 
    -MANAGER 
    -ADMIN

4.Order
    -id
    -create date
    -last change date
    -adress
    -user
    -status (NEW, CANCELED, PAID, CLOSED)
    -details(product, price, amount, comment)

5.Category
  - id
  - title

6.Basket
-id
-user
-details(product, product List)
