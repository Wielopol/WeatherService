package pl.sda.weather.repository.impl;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import pl.sda.weather.connection.HibernateUtil;
import pl.sda.weather.model.entity.LocationModelEntity;
import pl.sda.weather.repository.ILocationRepository;

import java.util.Collections;
import java.util.List;

public class LocationRepositoryDbImpl implements ILocationRepository {

    private final Logger logger = LogManager.getLogger(LocationRepositoryDbImpl.class);

    @Override
    public void saveLocation(LocationModelEntity locationModelEntity) {

        Transaction transaction = null;

        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            session.save(locationModelEntity);
            transaction.commit();
        } catch (HibernateException e) {
            if (transaction != null)
                transaction.rollback();
            logger.error(e.getMessage(), e);
        }
    }

    @Override
    public void editLocation(LocationModelEntity locationModelEntity) {

        Transaction transaction = null;

        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            session.saveOrUpdate(locationModelEntity);
            transaction.commit();
        } catch (HibernateException e) {
            if (transaction != null)
                transaction.rollback();

            logger.error(e.getMessage(), e);
        }

    }

    @Override
    public List<LocationModelEntity> getAllLocationModelData() {

        Transaction transaction = null;

        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();

            List<LocationModelEntity> list = session.createQuery("SELECT n FROM LocationModelEntity AS n", LocationModelEntity.class)
                    .getResultList();

            transaction.commit();

            return list;
        } catch (HibernateException e) {
            if (transaction != null)
                transaction.rollback();

            logger.error(e.getMessage(), e);
        }

        return Collections.emptyList();

    }

    @Override
    public LocationModelEntity getAllLocationModelDataByCityNameOrId(String pattern) {

        Transaction transaction = null;

        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();

            LocationModelEntity model = (LocationModelEntity) session.createQuery("FROM LocationModelEntity l WHERE l.cityName = :cityName or l.id =:id")
                    .setParameter("cityName", pattern)
                    .setParameter("id", pattern)
                    .getSingleResult();

            transaction.commit();
            return model;

        } catch (HibernateException e) {
            if (transaction != null)
                transaction.rollback();

            logger.error(e.getMessage(), e);
        }
        return null;
    }



    @Override
    public void delateRecord(LocationModelEntity locationModelEntity) {
        Transaction transaction = null;

        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            session.delete(locationModelEntity);
            transaction.commit();
        } catch (HibernateException e) {
            if (transaction != null)
                transaction.rollback();

            logger.error(e.getMessage(), e);


        }
    }
}
