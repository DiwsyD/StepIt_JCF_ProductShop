## Тема JCF (Java Collection Framework)

# Завдання "ProductShop"

- Реалізувати клас [ProductShop](src/main/java/org/itstep/jcf/shop/impl/ProductShop.java) відповідно до документації інтерфейсу [Shop](src/main/java/org/itstep/jcf/shop/Shop.java). 

### Опис:
#### - Клас [ProductShop](src/main/java/org/itstep/jcf/shop/impl/ProductShop.java) (продуктовий магазин) повинен зберігати:
     
     - Продукти та їх ціни
     - Кошик користувача
###
####  - Мати наступний функціонал:

     - Перегляд наявних товарів
     - Додавання продукту у кошик та додавання певної кількості продуктів
     - Видалення продукту із кошику та видалення певної кількості продуктів
     - Перегляду кошика
     - Перегляду вартості кошика із товарами
     - Можливістю оплатити товари
     - Можливістю очистити кошик
###
#### - Кошик повинен зберігати:
     - Обрані користувачем продукти та кількість кожного обраного продукту

#### Для заповнення магазину продуктами, скористайтесь статичним методом #fillShopWithProducts(Map<String, Double> shopProductStorage) із класу [ShopFiller](src/main/java/org/itstep/jcf/shop/util/ShopFiller.java)
#
### [Для того щоб протестувати свою реалізацію, запустіть [ProductShopTest](src/test/java/shop/impl/ProductShopTest.java) файл.]