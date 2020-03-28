package ua.lviv.home.daos;

import org.apache.log4j.Logger;
import ua.lviv.home.ConnectionUtil;
import ua.lviv.home.enteties.Product;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ProductDao implements CRUD<Product> {

    private static final Logger LOG = Logger.getLogger(ProductDao.class);

    private static String READ_ALL = "select * from products";
    private static String CREATE = "insert into products(`name`, `description`, `price`) values (?,?,?)";
    private static String READ_BY_ID = "select * from products where id =?";
    private static String UPDATE_BY_ID = "update products set name=?, description = ?, price = ? where id = ?";
    private static String DELETE_BY_ID = "delete from products where id=?";

    private Connection connection;
    private PreparedStatement preparedStatement;

    public ProductDao() {
        connection = ConnectionUtil.getConnection();
    }

    @Override
    public Product insert(Product product) {
        String message = String.format("Will create a product for  productId=%d",
                product.getId());
        LOG.debug(message);
        try {
            preparedStatement = connection.prepareStatement(CREATE, Statement.RETURN_GENERATED_KEYS);
            preparedStatement.setString(1, product.getName());
            preparedStatement.setString(2, product.getDescription());
            preparedStatement.setDouble(3, product.getPrice());
            preparedStatement.executeUpdate();

            ResultSet rs = preparedStatement.getGeneratedKeys();
            rs.next();
            product.setId(rs.getInt(1));
            return product;
        } catch (SQLException e) {
            String errorMessage = String.format("Fail to create a product for productId=%d",
                    product.getId());
            LOG.error(errorMessage, e);
            throw new RuntimeException(e);
        }
    }

    @Override
    public Product read(int id) {

        try {
            Product product = null;
            preparedStatement = connection.prepareStatement(READ_BY_ID);
            preparedStatement.setInt(1, id);

            ResultSet result = preparedStatement.executeQuery();
            result.next();
            product = Product.of(result);
            return product;
        } catch (SQLException e) {
            String errorMessage = String.format("Fail to get a product with id=%d", id);
            LOG.error(errorMessage, e);
            throw new RuntimeException(e);
        }
    }

    @Override
    public void update(Product product, int id) {

        try {
            product.setId(id);
            preparedStatement = connection.prepareStatement(UPDATE_BY_ID);
            preparedStatement.setString(1, product.getName());
            preparedStatement.setString(2, product.getDescription());
            preparedStatement.setDouble(3, product.getPrice());
            preparedStatement.setInt(4, product.getId());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            String errorMessage = String.format("Fail to update a product with id=%d", product.getId());
            LOG.error(errorMessage, e);
            throw new RuntimeException(e);
        }
    }

    @Override
    public void delete(int id) {
        try {
            preparedStatement = connection.prepareStatement(DELETE_BY_ID);
            preparedStatement.setInt(1, id);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            LOG.error("Failed to delete product by id " + id, e);
            throw new RuntimeException(e);
        }
    }

    @Override
    public List<Product> readAll() {
        List<Product> productRecords = new ArrayList<>();
        try {
            preparedStatement = connection.prepareStatement(READ_ALL);
            ResultSet result = preparedStatement.executeQuery();
            while (result.next()) {
                productRecords.add(Product.of(result));
            }
        } catch (SQLException e) {
            LOG.error("Failed to get list of products", e);
            throw new RuntimeException(e);
        }

        return productRecords;
    }
}