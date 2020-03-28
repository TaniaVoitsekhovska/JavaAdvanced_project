package ua.lviv.home.services;

import ua.lviv.home.daos.BucketDao;
import ua.lviv.home.enteties.Bucket;

import java.util.List;

public class BucketService {

    private static BucketService bucketService;

    private BucketDao bucketDao;

    public static BucketService getInstance() {
        if (bucketService == null) {
            bucketService = new BucketService();
        }
        return bucketService;
    }

    public BucketService() {
        bucketDao = new BucketDao();
    }

    public Bucket create(Bucket t) {
        return bucketDao.insert(t);
    }

    public Bucket read(int id) {
        return bucketDao.read(id);
    }

    public void update(Bucket t, int id) {
        bucketDao.update(t, id);
    }

    public void delete(Integer id) {
        bucketDao.delete(id);
    }

    public List<Bucket> readAll() {
        return bucketDao.readAll();
    }
}
