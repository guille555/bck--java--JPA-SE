package io.scout.persistence;

import java.sql.DriverManager;
import java.sql.SQLException;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

/**
 * @author DEV Scout
 */
public class DBPersistence {

  private static EntityManagerFactory factory = null;

  private DBPersistence() {}

  public static EntityManagerFactory getFactory() {
    if (DBPersistence.factory == null) {
      DBPersistence.factory = Persistence.createEntityManagerFactory("BeanPU");
    }
    return DBPersistence.factory;
  }

  public static void closeFactory() {
    if ((DBPersistence.factory != null) && (DBPersistence.factory.isOpen())) {
      DBPersistence.factory.close();
      DBPersistence.factory = null;
      System.err.println("DB ERROR: factory closed");
    }
  }

  public static void closeDB() {
    try {
      //Class.forName("org.apache.derby.jdbc.EmbeddedDriver");
      DriverManager.getConnection("jdbc:derby:memory:app/test_db;user=dev;password=sprout;shutdown=true");
    } catch (SQLException exc) {
      System.err.println("DB ERROR: Data Base closed");
    }
  }
}
