package pl.sda.weather.repository.impl;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import pl.sda.weather.connection.HibernateUtil;
import pl.sda.weather.model.entity.LocationModelEntity;
import pl.sda.weather.model.entity.WeatherModelEntity;
import pl.sda.weather.repository.IWeatherRepository;

import java.util.*;

public class WeatherRepositoryImpl implements IWeatherRepository {

    private final Logger logger = LogManager.getLogger(WeatherRepositoryImpl.class);

    @Override
    public void saveWeather(WeatherModelEntity weatherModelEntity) {
        Transaction transaction = null;

        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            session.save(weatherModelEntity);
            transaction.commit();
        } catch (HibernateException e) {
            if (transaction != null)
                transaction.rollback();

            logger.error(e.getMessage(), e);
        }
    }

    @Override
    public List<WeatherModelEntity> getAllWeatherModelData() {

        Transaction transaction = null;

        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();

            List<WeatherModelEntity> list = session.createQuery("SELECT n FROM WeatherModelEntity AS n", WeatherModelEntity.class)
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
    public WeatherModelEntity getWeatherModelDataByLocation(LocationModelEntity location) {
        Transaction transaction = null;

        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();

            WeatherModelEntity model = (WeatherModelEntity) session.createQuery("FROM WeatherModelEntity w WHERE w.location =:location")
                    .setParameter("location", location)
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
    public void deleteRecord(WeatherModelEntity weatherModelEntity) {
        Transaction transaction = null;

        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            session.delete(weatherModelEntity);
            transaction.commit();
        } catch (HibernateException e) {
            if (transaction != null)
                transaction.rollback();

            logger.error(e.getMessage(), e);
        }
    }
}
