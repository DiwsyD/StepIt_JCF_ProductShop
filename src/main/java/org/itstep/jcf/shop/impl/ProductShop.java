package org.itstep.jcf.shop.impl;

import org.itstep.jcf.shop.Shop;
import org.itstep.jcf.shop.exception.CheckoutException;

import java.util.List;

public class ProductShop implements Shop {

    @Override
    public List<String> products() {
        return null;
    }

    @Override
    public boolean addToCart(String product) {
        return false;
    }

    @Override
    public boolean addToCart(String product, int amount) {
        return false;
    }

    @Override
    public boolean removeFromCart(String product) {
        return false;
    }

    @Override
    public boolean removeFromCart(String product, int amount) {
        return false;
    }

    @Override
    public String showCart() {
        return null;
    }

    @Override
    public double checkout() {
        return 0;
    }

    @Override
    public boolean pay(double amount) throws CheckoutException {
        return false;
    }

    @Override
    public void clearCart() {

    }
}
