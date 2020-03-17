package ua.lviv.services;

import ua.lviv.daos.ProductDao;
import ua.lviv.enteties.Product;
import java.util.List;

public class ProductService {
    private ProductDao productDao;

    public ProductService() {
        productDao = new ProductDao();
    }

    public Product create(Product t) {
        return productDao.create(t);
    }

    public Product read(int id) {
        return productDao.read(id);
    }

    public void update(Product t) {
        productDao.update(t);
    }

    public void delete(Integer id) {
        productDao.delete(id);
    }

    public List<Product> readAll() {
        return productDao.readAll();
    }
}
