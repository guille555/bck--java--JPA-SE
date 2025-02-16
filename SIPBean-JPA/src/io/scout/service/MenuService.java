package io.scout.service;

import io.scout.dao.BasicDAOImp;
import io.scout.dao.IBasicDAO;
import io.scout.model.GroupRol;
import io.scout.model.Menu;
import java.util.ArrayList;
import java.util.Date;
import java.util.EmptyStackException;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

/**
 * @author DEV Scout
 */
public class MenuService {

  public MenuService() {}

  private GroupRol findGroupRolById(Integer id) {
    GroupRolService service = new GroupRolService();
    GroupRol groupRol = service.findById(id);
    return groupRol;
  }

  private Menu getDefaultObject() {
    Menu menu = new Menu();
    menu.setId(0);
    menu.setFlagState(false);
    menu.setGroupRol(null);
    menu.setRegisterDate(null);
    return menu;
  }

  private Menu getMenuLoaded(Menu data) {
    Integer id = ((data.getId() != null) && (data.getId() > 0)) ? (data.getId()) : (null);
    Date date = new Date();
    Menu menu = new Menu();
    menu.setId(id);
    menu.setName(data.getName().toUpperCase());
    menu.setFlagState(true);
    menu.setRegisterDate(date);
    return menu;
  }

  private Menu prepareMenu(Menu data) {
    GroupRol groupRol = this.findGroupRolById(data.getGroupRol().getId());
    Menu menu = this.getMenuLoaded(data);
    menu.setGroupRol(groupRol);
    return menu;
  }

  public void save(Menu data) {
    Menu menu = null;
    IBasicDAO dao = null;
    try {
      dao = new BasicDAOImp();
      menu = this.prepareMenu(data);
      dao.save(menu);
    } catch (Exception exc) {
      System.err.println("ERROR SERVICE SAVE: " + exc.getMessage());
      throw exc;
    }
  }

  public void update(Menu data) {
    Menu menu = null;
    Menu menuUpdated = null;
    IBasicDAO dao = null;
    try {
      dao = new BasicDAOImp();
      menu = this.findById(data.getId());
      if ((menu.getId() > 0) && (menu.isFlagState())) {
        menuUpdated = this.prepareMenu(data);
        menuUpdated.setId(menu.getId());
        dao.update(menuUpdated);
      } else  {
        throw new EmptyStackException();
      }
    } catch (Exception exc) {
      System.err.println("ERROR SERVICE UPDATE: " + exc.getMessage());
      throw exc;
    }
  }

  public void delete(Menu data) {
    Menu menu = null;
    IBasicDAO dao = null;
    try {
      dao = new BasicDAOImp();
      menu = this.findById(data.getId());
      if (menu.getId() > 0) {
        dao.delete(menu);
      } else  {
        throw new EmptyStackException();
      }
    } catch (Exception exc) {
      System.err.println("ERROR SERVICE DELETE: " + exc.getMessage());
      throw exc;
    }
  }

  public Menu findById(Integer id) {
    Menu menu = null;
    IBasicDAO dao = null;
    EntityManager manager = null;
    try {
      dao = new BasicDAOImp();
      manager = dao.getManager();
      menu = manager.find(Menu.class, id);
      return menu;
    } catch (Exception exc) {
      System.err.println("ERROR SERVICE FINDING: " + exc.getMessage());
      menu = this.getDefaultObject();
      return menu;
    } finally {
      if ((manager != null) && (manager.isOpen())) {
        manager.close();
      }
    }
  }

  //*** TYPED QUERY
  /*
  public List<Menu> findAll() {
    IBasicDAO dao = null;
    EntityManager manager = null;
    TypedQuery<Menu> query = null;
    List<Menu> list = null;
    try {
      dao = new BasicDAOImp();
      manager = dao.getManager();
      query = manager.createNamedQuery("Menu.findAll", Menu.class);
      list = query.getResultList();
      return list;
    } catch (Exception exc) {
      System.err.println("ERROR SERVICE FINDING: " + exc.getMessage());
      list = new ArrayList<Menu>();
      return list;
    } finally {
      if ((manager != null) && (manager.isOpen())) {
        manager.close();
      }
    }
  }
  */

  //*** TYPED QUERY
  /*
  public List<Menu> findAllByFlagState(boolean flagState) {
    IBasicDAO dao = null;
    EntityManager manager = null;
    TypedQuery<Menu> query = null;
    List<Menu> list = null;
    try {
      dao = new BasicDAOImp();
      manager = dao.getManager();
      query = manager.createNamedQuery("Menu.findAllByFlagState", Menu.class);
      query.setParameter("flagState", flagState);
      list = query.getResultList();
      return list;
    } catch (Exception exc) {
      System.err.println("ERROR SERVICE FINDING: " + exc.getMessage());
      list = new ArrayList<Menu>();
      return list;
    } finally {
      if ((manager != null) && (manager.isOpen())) {
        manager.close();
      }
    }
  }
  */

  //*** CRITERIA
  public List<Menu> findAll() {
    IBasicDAO dao = null;
    EntityManager manager = null;
    CriteriaBuilder criteriaBuilder = null;
    CriteriaQuery<Menu> criteriaQuery = null;
    Root<Menu> root = null;
    List<Menu> list  = null;
    try {
      dao = new BasicDAOImp();
      manager = dao.getManager();
      criteriaBuilder = manager.getCriteriaBuilder();
      criteriaQuery = criteriaBuilder.createQuery(Menu.class);
      root = criteriaQuery.from(Menu.class);
      criteriaQuery.select(root);
      list = manager.createQuery(criteriaQuery).getResultList();
      return list;
    } catch (Exception exc) {
      System.err.println("ERROR SERVICE FINDING: " + exc.getMessage());
      list = new ArrayList<Menu>();
      return list;
    } finally {
      if ((manager != null) && (manager.isOpen())) {
        manager.close();
      }
    }
  }

  //*** CRITERIA
  public List<Menu> findAllByFlagState(boolean flagState) {
    IBasicDAO dao = null;
    EntityManager manager = null;
    CriteriaBuilder criteriaBuilder = null;
    CriteriaQuery<Menu> criteriaQuery = null;
    Root<Menu> root = null;
    Predicate paramFlagState = null;
    List<Menu> list  = null;
    try {
      dao = new BasicDAOImp();
      manager = dao.getManager();
      criteriaBuilder = manager.getCriteriaBuilder();
      criteriaQuery = criteriaBuilder.createQuery(Menu.class);
      root = criteriaQuery.from(Menu.class);
      criteriaQuery.select(root);
      paramFlagState = criteriaBuilder.equal(root.get("flagState"), flagState);
      criteriaQuery.where(paramFlagState);
      list = manager.createQuery(criteriaQuery).getResultList();
      return list;
    } catch (Exception exc) {
      System.err.println("ERROR SERVICE FINDING: " + exc.getMessage());
      list = new ArrayList<Menu>();
      return list;
    } finally {
      if ((manager != null) && (manager.isOpen())) {
        manager.close();
      }
    }
  }

  //*** CRITERIA
  public List<Menu> findAllByGroupRol(Integer idGroupRol) {
    IBasicDAO dao = null;
    EntityManager manager = null;
    CriteriaBuilder criteriaBuilder = null;
    CriteriaQuery<Menu> criteriaQuery = null;
    Predicate paramIdGroupRol = null;
    Root<Menu> root = null;
    List<Menu> list  = null;
    try {
      dao = new BasicDAOImp();
      manager = dao.getManager();
      criteriaBuilder = manager.getCriteriaBuilder();
      criteriaQuery = criteriaBuilder.createQuery(Menu.class);
      root = criteriaQuery.from(Menu.class);
      paramIdGroupRol = criteriaBuilder.equal(root.get("groupRol").get("id"), idGroupRol);
      criteriaQuery.select(root);
      criteriaQuery.where(paramIdGroupRol);
      list = manager.createQuery(criteriaQuery).getResultList();
      return list;
    } catch (Exception exc) {
      System.err.println("ERROR SERVICE FINDING: " + exc.getMessage());
      list = new ArrayList<Menu>();
      return list;
    } finally {
      if ((manager != null) && (manager.isOpen())) {
        manager.close();
      }
    }
  }
}
