package ua.lviv.daos;

import org.apache.commons.lang3.NotImplementedException;
import org.apache.log4j.Logger;
import ua.lviv.enteties.Bucket;

import java.sql.*;

import ua.lviv.ConnectionUtil;

import java.util.ArrayList;
import java.sql.Date;
import java.util.List;

public class BucketDao implements CRUD<Bucket> {

    private static final Logger LOG = Logger.getLogger(BucketDao.class);

    private static String READ_ALL = "select * from bucket";
    private static String CREATE = "insert into bucket(`user_id`, `product_id`, `purchase_date`) values (?,?,?)";
    private static String READ_BY_ID = "select * from bucket where id =?";
    private static String DELETE_BY_ID = "delete from bucket where id=?";

    private Connection connection;
    private PreparedStatement preparedStatement;

    public BucketDao() {
        connection = ConnectionUtil.getConnection();
    }

    @Override
    public Bucket create(Bucket bucket) {
        String message = String.format("Will create a bucket for userId=%d and productId=%d",
                bucket.getUserId(), bucket.getProductId());
        LOG.debug(message);

        try {
            preparedStatement = connection.prepareStatement(CREATE, Statement.RETURN_GENERATED_KEYS);
            preparedStatement.setInt(1, bucket.getUserId());
            preparedStatement.setInt(2, bucket.getProductId());
            preparedStatement.setDate(3, new Date(bucket.getPurchaseDate().getTime()));
            preparedStatement.executeUpdate();

            ResultSet rs = preparedStatement.getGeneratedKeys();
            rs.next();
            bucket.setId(rs.getInt(1));
            return bucket;
        } catch (SQLException e) {
            String errorMessage = String.format("Fail to create a bucket for userId=%d and productId=%d",
                    bucket.getUserId(), bucket.getProductId());
            LOG.error(errorMessage, e);
            throw new RuntimeException(e);
        }
    }

    @Override
    public Bucket read(int id) {
        try {
            Bucket bucket = null;
            preparedStatement = connection.prepareStatement(READ_BY_ID);
            preparedStatement.setInt(1, id);
            ResultSet result = preparedStatement.executeQuery();
            result.next();

            bucket = Bucket.of(result);
            return bucket;
        } catch (SQLException e) {
            String errorMessage = String.format("Fail to get a bucket with id=%d", id);
            LOG.error(errorMessage, e);
            throw new RuntimeException(e);
        }
    }

    @Override
    public void update(Bucket t) {
        throw new NotImplementedException("there is no update method for bucket");
    }

    @Override
    public void delete(int id) {
        try {
            preparedStatement = connection.prepareStatement(DELETE_BY_ID);
            preparedStatement.setInt(1, id);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            LOG.error("Failed to delete bucket by id " + id, e);
            throw new RuntimeException(e);
        }
    }

    @Override
    public List<Bucket> readAll() {

        List<Bucket> bucketRecords = new ArrayList<>();
        try {
            preparedStatement = connection.prepareStatement(READ_ALL);
            ResultSet result = preparedStatement.executeQuery();
            while (result.next()) {
                bucketRecords.add(Bucket.of(result));
            }
        } catch (SQLException e) {
            LOG.error("Failed to get list of buckets", e);
            throw new RuntimeException(e);
        }

        return bucketRecords;
    }

}
