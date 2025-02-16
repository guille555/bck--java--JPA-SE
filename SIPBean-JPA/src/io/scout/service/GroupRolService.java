package io.scout.service;

import io.scout.dao.BasicDAOImp;
import io.scout.dao.IBasicDAO;
import io.scout.model.CompanyUser;
import io.scout.model.GroupRol;
import io.scout.model.Menu;
import io.scout.model.SystemTag;
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
public class GroupRolService {

  public GroupRolService() {}

  private SystemTag findSystemTagById(Integer id) {
    SystemTagService service = new SystemTagService();
    SystemTag systemTag = service.findById(id);
    return systemTag;
  }

  private GroupRol getDefaultObject() {
    GroupRol groupRol = new GroupRol();
    groupRol.setId(0);
    groupRol.setFlagState(false);
    groupRol.setRegisterDate(null);
    groupRol.setSystemTag(null);
    return groupRol;
  }

  private GroupRol getGroupRolLoaded(GroupRol data) {
    Integer id = ((data.getId() != null) && (data.getId() > 0)) ? (data.getId()) : (null);
    Date date = new Date();
    GroupRol groupRol = new GroupRol();
    groupRol.setId(id);
    groupRol.setName(data.getName().toUpperCase());
    groupRol.setFlagState(true);
    groupRol.setRegisterDate(date);
    return groupRol;
  }

  private GroupRol prepareGroupRol(GroupRol data) {
    SystemTag systemTag = this.findSystemTagById(data.getSystemTag().getId());
    GroupRol groupRol = this.getGroupRolLoaded(data);
    groupRol.setSystemTag(systemTag);
    return groupRol;
  }

  private List<Menu> findMenusByGroupRol(Integer idGroupRol) {
    MenuService service = new MenuService();
    List<Menu> list = service.findAllByGroupRol(idGroupRol);
    return list;
  }

  private List<CompanyUser> findCompanyUsersByGroupRol(Integer id) {
    CompanyUserService service = new CompanyUserService();
    List<CompanyUser> list = service.findAllByGroupRol(id);
    return list;
  }

  public void save(GroupRol data) {
    GroupRol groupRol = null;
    IBasicDAO dao = null;
    try {
      dao = new BasicDAOImp();
      groupRol = this.prepareGroupRol(data);
      dao.save(groupRol);
    } catch (Exception exc) {
      System.err.println("ERROR SERVICE SAVE: " + exc.getMessage());
      throw exc;
    }
  }

  public void update(GroupRol data) {
    GroupRol groupRol = null;
    GroupRol groupRolUpdated = null;
    IBasicDAO dao = null;
    try {
      dao = new BasicDAOImp();
      groupRol = this.findById(data.getId());
      if ((groupRol.getId() > 0) && (groupRol.isFlagState())) {
        groupRolUpdated = this.prepareGroupRol(data);
        dao.update(groupRolUpdated);
      } else {
        throw new EmptyStackException();
      }
    } catch (Exception exc) {
      System.err.println("ERROR SERVICE UPDATE: " + exc.getMessage());
      throw exc;
    }
  }

  public void delete(GroupRol data) {
    GroupRol groupRol = null;
    IBasicDAO dao = null;
    try {
      dao = new BasicDAOImp();
      groupRol = this.findById(data.getId());
      if (groupRol.getId() > 0) {
        dao.delete(groupRol);
      } else {
        throw new EmptyStackException();
      }
    } catch (Exception exc) {
      System.err.println("ERROR SERVICE DELETE: " + exc.getMessage());
      throw exc;
    }
  }

  public GroupRol findById(Integer id) {
    GroupRol groupRol = null;
    IBasicDAO dao = null;
    EntityManager manager = null;
    List<Menu> listMenus = null;
    List<CompanyUser> listCompanyUsers = null;
    try {
      dao = new BasicDAOImp();
      manager = dao.getManager();
      groupRol = manager.find(GroupRol.class, id);
      listMenus = this.findMenusByGroupRol(groupRol.getId());
      listCompanyUsers = this.findCompanyUsersByGroupRol(groupRol.getId());
      groupRol.setListMenus(listMenus);
      groupRol.setListCompanyUsers(listCompanyUsers);
      return groupRol;
    } catch (Exception exc) {
      System.err.println("ERROR SERVICE FINDING: " + exc.getMessage());
      groupRol = this.getDefaultObject();
      return groupRol;
    } finally {
      if ((manager != null) && (manager.isOpen())) {
        manager.close();
      }
    }
  }

  //*** TYPED QUUERY
  /*
  public List<GroupRol> findAll() {
    IBasicDAO dao = null;
    EntityManager manager = null;
    TypedQuery<GroupRol> query = null;
    List<GroupRol> list = null;
    List<Menu> listMenus = null;
    List<CompanyUser> listCompanyUsers = null;
    try {
      dao = new BasicDAOImp();
      manager = dao.getManager();
      query = manager.createNamedQuery("GroupRol.findAll", GroupRol.class);
      list = query.getResultList();
      for (GroupRol item : list) {
        listMenus = this.findMenusByGroupRol(item.getId());
        listCompanyUsers = this.findCompanyUsersByGroupRol(item.getId());
        item.setListMenus(listMenus);
        item.setListCompanyUsers(listCompanyUsers);
      }
      return list;
    } catch (Exception exc) {
      System.err.println("ERROR SERVICE FINDING: " + exc.getMessage());
      list = new ArrayList<GroupRol>();
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
  public List<GroupRol> findAllByFlagState(boolean flagState) {
    IBasicDAO dao = null;
    EntityManager manager = null;
    TypedQuery<GroupRol> query = null;
    List<GroupRol> list = null;
    List<Menu> listMenus = null;
    List<CompanyUser> listCompanyUsers = null;
    try {
      dao = new BasicDAOImp();
      manager = dao.getManager();
      query = manager.createNamedQuery("GroupRol.findAllByFlagState", GroupRol.class);
      query.setParameter("flagState", flagState);
      list = query.getResultList();
      for (GroupRol item : list) {
        listMenus = this.findMenusByGroupRol(item.getId());
        listCompanyUsers = this.findCompanyUsersByGroupRol(item.getId());
        item.setListMenus(listMenus);
        item.setListCompanyUsers(listCompanyUsers);
      }
      return list;
    } catch (Exception exc) {
      System.err.println("ERROR SERVICE FINDING: " + exc.getMessage());
      list = new ArrayList<GroupRol>();
      return list;
    } finally {
      if ((manager != null) && (manager.isOpen())) {
        manager.close();
      }
    }
  }
  */

  //*** CRITERIA
  public List<GroupRol> findAll() {
    IBasicDAO dao = null;
    EntityManager manager = null;
    CriteriaBuilder criteriaBuilder = null;
    CriteriaQuery<GroupRol> criteriaQuery = null;
    Root<GroupRol> root = null;
    List<GroupRol> list  = null;
    List<Menu> listMenus  = null;
    List<CompanyUser> listCompanyUsers = null;
    try {
      dao = new BasicDAOImp();
      manager = dao.getManager();
      criteriaBuilder = manager.getCriteriaBuilder();
      criteriaQuery = criteriaBuilder.createQuery(GroupRol.class);
      root = criteriaQuery.from(GroupRol.class);
      criteriaQuery.select(root);
      list = manager.createQuery(criteriaQuery).getResultList();
      for (GroupRol item : list) {
        listMenus = this.findMenusByGroupRol(item.getId());
        listCompanyUsers = this.findCompanyUsersByGroupRol(item.getId());
        item.setListMenus(listMenus);
        item.setListCompanyUsers(listCompanyUsers);
      }
      return list;
    } catch (Exception exc) {
      System.err.println("ERROR SERVICE FINDING: " + exc.getMessage());
      list = new ArrayList<GroupRol>();
      return list;
    } finally {
      if ((manager != null) && (manager.isOpen())) {
        manager.close();
      }
    }
  }

  public List<GroupRol> findAllByFlagState(boolean flagState) {
    IBasicDAO dao = null;
    EntityManager manager = null;
    CriteriaBuilder criteriaBuilder = null;
    CriteriaQuery<GroupRol> criteriaQuery = null;
    Root<GroupRol> root = null;
    Predicate paramFlagState = null;
    List<GroupRol> list  = null;
    List<Menu> listMenus  = null;
    List<CompanyUser> listCompanyUsers = null;
    try {
      dao = new BasicDAOImp();
      manager = dao.getManager();
      criteriaBuilder = manager.getCriteriaBuilder();
      criteriaQuery = criteriaBuilder.createQuery(GroupRol.class);
      root = criteriaQuery.from(GroupRol.class);
      criteriaQuery.select(root);
      paramFlagState = criteriaBuilder.equal(root.get("flagState"), flagState);
      criteriaQuery.where(paramFlagState);
      list = manager.createQuery(criteriaQuery).getResultList();
      for (GroupRol item : list) {
        listMenus = this.findMenusByGroupRol(item.getId());
        listCompanyUsers = this.findCompanyUsersByGroupRol(item.getId());
        item.setListMenus(listMenus);
        item.setListCompanyUsers(listCompanyUsers);
      }
      return list;
    } catch (Exception exc) {
      System.err.println("ERROR SERVICE FINDING: " + exc.getMessage());
      list = new ArrayList<GroupRol>();
      return list;
    } finally {
      if ((manager != null) && (manager.isOpen())) {
        manager.close();
      }
    }
  }

  public List<GroupRol> findAllBySystemTag(Integer idSystemTag) {
    IBasicDAO dao = null;
    EntityManager manager = null;
    CriteriaBuilder criteriaBuilder = null;
    CriteriaQuery<GroupRol> criteriaQuery = null;
    Predicate paramIdSystemTag = null;
    Root<GroupRol> root = null;
    List<GroupRol> list  = null;
    List<Menu> listMenus = null;
    try {
      dao = new BasicDAOImp();
      manager = dao.getManager();
      criteriaBuilder = manager.getCriteriaBuilder();
      criteriaQuery = criteriaBuilder.createQuery(GroupRol.class);
      root = criteriaQuery.from(GroupRol.class);
      //Join<SystemTag, GroupRol> join = root.join("listGroupRoles");
      paramIdSystemTag = criteriaBuilder.equal(root.get("systemTag").get("id"), idSystemTag);
      criteriaQuery.select(root);
      criteriaQuery.where(paramIdSystemTag);
      list = manager.createQuery(criteriaQuery).getResultList();
      for (GroupRol item : list) {
        listMenus = this.findMenusByGroupRol(item.getId());
        item.setListMenus(listMenus);
      }
      return list;
    } catch (Exception exc) {
      System.err.println("ERROR SERVICE FINDING: " + exc.getMessage());
      list = new ArrayList<GroupRol>();
      return list;
    } finally {
      if ((manager != null) && (manager.isOpen())) {
        manager.close();
      }
    }
  }
}
