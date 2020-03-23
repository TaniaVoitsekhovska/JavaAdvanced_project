package ua.lviv.home.services;

import ua.lviv.home.daos.ProductDao;
import ua.lviv.home.enteties.Product;

import java.util.List;

public class ProductService {
    private static ProductService productService;
    private ProductDao productDao;

    public ProductService() {
        productDao = new ProductDao();
    }
    public static ProductService getInstance() {
        if (productService == null) {
            productService = new ProductService();
        }
        return productService;
    }

    public Product create(Product t) {
        return productDao.insert(t);
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
