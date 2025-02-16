package io.scout.dao;

import javax.persistence.EntityManager;

/**
 * @author DEV Scout
 */
public interface IBasicDAO {

  public abstract void save(Object object);

  public abstract void update(Object object);

  public abstract void delete(Object object);

  public abstract EntityManager getManager();
}
