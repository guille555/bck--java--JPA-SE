package io.scout.dao;

import io.scout.persistence.DBPersistence;
import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;

/**
 * @author DEV Scout
 */
public class BasicDAOImp implements IBasicDAO {

  public BasicDAOImp() {}

  @Override
  public void save(Object object) {
    EntityManager manager = null;
    EntityTransaction transaction = null;
    try {
      manager = DBPersistence.getFactory().createEntityManager();
      transaction = manager.getTransaction();
      transaction.begin();
      manager.persist(object);
      transaction.commit();
    } catch (Exception exc) {
      System.err.println("ERROR DAO SAVE: " + exc.getMessage());
      throw exc;
    } finally {
      if ((manager != null) && (manager.isOpen())) {
        manager.close();
      }
    }
  }

  @Override
  public void update(Object object) {
    EntityManager manager = null;
    EntityTransaction transaction = null;
    try {
      manager = DBPersistence.getFactory().createEntityManager();
      transaction = manager.getTransaction();
      transaction.begin();
      manager.merge(object);
      transaction.commit();
    } catch (Exception exc) {
      System.err.println("ERROR DAO MERGE: " + exc.getMessage());
      throw exc;
    } finally {
      if ((manager != null) && (manager.isOpen())) {
        manager.close();
      }
    }
  }

  @Override
  public void delete(Object object) {
    EntityManager manager = null;
    EntityTransaction transaction = null;
    try {
      manager = DBPersistence.getFactory().createEntityManager();
      transaction = manager.getTransaction();
      transaction.begin();
      manager.remove(manager.merge(object));
      transaction.commit();
    } catch (Exception exc) {
      System.err.println("ERROR DAO MERGE: " + exc.getMessage());
      throw exc;
    } finally {
      if ((manager != null) && (manager.isOpen())) {
        manager.close();
      }
    }
  }

  @Override
  public EntityManager getManager() {
    EntityManager manager = null;
    try {
      manager = DBPersistence.getFactory().createEntityManager();
      return manager;
    } catch (Exception exc) {
      System.err.println("ERROR DAO SAVE: " + exc.getMessage());
      throw exc;
    }
  }
}
