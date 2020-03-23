package ua.lviv.home.services;

import ua.lviv.home.daos.ProductDao;
import ua.lviv.home.enteties.Product;

import java.util.List;

public class ProductService {
    private ProductDao productDao;

    public ProductService() {
        productDao = new ProductDao();
    }

    public void insert(String name, String description, double price){
         productDao.insert(
                Product.builder()
                        .setName(name)
                        .setDescription(description)
                        .setPrice(price)
                        .build()
        );
    }

    public Product read(int id) {
        return productDao.read(id);
    }

    public void update(Product t, int id) {
        productDao.update(t, id);
    }

    public void delete(Integer id) {
        productDao.delete(id);
    }

    public List<Product> readAll() {
        return productDao.readAll();
    }
}
