package org.itstep.jcf.shop;

import org.itstep.jcf.shop.exception.CheckoutException;

import java.util.List;

public interface Shop {

    /**
     * Допомагає побачити усі товари
     *
     * @return Список товарів у вигляді: *Товар* - *Ціна*
     */
    List<String> products();

    /**
     * Додає товар до корзини
     *
     * @param product - товар для додавання у кошик
     * @return True - Якщо товар було додано, False - якщо такого товару нема у магазині.
     */
    boolean addToCart(String product);

    /**
     * Додає товар до корзини
     *
     * @param product - товар для додавання у кошик
     * @param amount  - кількість товару для додавання у кошик
     * @return True - Якщо товар було додано, False - якщо такого товару нема у магазині.
     */
    boolean addToCart(String product, int amount);

    /**
     * Видаляє товар із корзини
     *
     * @param product - товар для видалення із корзини
     * @return True - Якщо товар було видалено, False - якщо такого товару у кошику не було.
     */
    boolean removeFromCart(String product);

    /**
     * Видаляє товар із корзини
     *
     * @param product - товар для видалення із корзини
     * @param amount  - кількість товару для видалення з кошика
     * @return True - Якщо товар було видалено, False - якщо такого товару у кошику не було.
     */
    boolean removeFromCart(String product, int amount);

    /**
     * Показує усі товари у кошику.
     *
     * @return Список товарів у вигляді: *Товар* - *Кількість*
     */
    String showCart();

    /**
     * Повертає суму чека
     *
     * @return числове значення яке дорівнює сумі вартості усіх товарів
     */
    double checkout();

    /**
     * Повертає результат операції та очищає кошик.
     *
     * @param amount - сума для оплати товарів
     * @return True - Якщо сума покриває (дорівнює, або більше) вартість усіх товарів
     * False - якщо сума менше вартості
     * @throws CheckoutException Якщо сума має значення менше нуля.
     */
    boolean pay(double amount) throws CheckoutException;

    /**
     * Видаляє усі продукти із кошика
     */
    void clearCart();

}
