package org.itstep.jcf.shop.util;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

public class ShopFiller {

    private static final Random random = new Random();

    /**
     * Метод для заповнення колекції продуктів (заповнення Вашого магазину)
     * @return колекцію із продуктами.
     */
    public static Map<String, Double> fillShopWithProducts() {
        List<String> productsNameList = getProductsFromFile("products");

        double minPrice = 1.0;
        double maxPrice = 55.5;

        Map<String, Double> shopProductStorage = new HashMap<>();

        for (String productName : productsNameList) {
            shopProductStorage.put(productName, getRandomPrice(minPrice, maxPrice));
        }
        return shopProductStorage;
    }

    public static List<String> getProductsFromFile(String fileName) {
        List<String> productsNameList = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(fileName))) {
            String line;
            while ((line = br.readLine()) != null) {
                productsNameList.add(line.trim());
            }
        } catch (IOException e) {
            System.err.println("Error reading the file: " + e.getMessage());
        }
        return productsNameList;
    }

    private static double getRandomPrice(double min, double max) {
        return Math.round((min + (max - min) * random.nextDouble()) * 100.0) / 100.0;
    }

}
