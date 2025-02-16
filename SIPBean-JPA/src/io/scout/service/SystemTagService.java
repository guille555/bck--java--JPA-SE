package io.scout.service;

import io.scout.dao.BasicDAOImp;
import io.scout.dao.IBasicDAO;
import io.scout.model.CompanyUser;
import io.scout.model.GroupRol;
import io.scout.model.SystemTag;
import java.util.ArrayList;
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
public class SystemTagService {

  public SystemTagService() {}

  private SystemTag getDefaultObject() {
    SystemTag systemTag = new SystemTag();
    systemTag.setId(0);
    systemTag.setFlagState(false);
    systemTag.setRegisterDate(null);
    return systemTag;
  }

  private SystemTag getSystemTagLoaded(SystemTag data) {
    Integer id = ((data.getId() != null) && (data.getId() > 0)) ? (data.getId()) : (null);
    SystemTag systemTag = new SystemTag();
    systemTag.setId(id);
    systemTag.setName(data.getName().toUpperCase());
    systemTag.setFlagState(true);
    return systemTag;
  }

  private List<GroupRol> findGroupRolesBySystemTag(Integer id) {
    GroupRolService service = new GroupRolService();
    List<GroupRol> list = service.findAllBySystemTag(id);
    return list;
  }

  private List<CompanyUser> findCompanyUsersBySystemTag(Integer id) {
    CompanyUserService service = new CompanyUserService();
    List<CompanyUser> list = service.findAllBySystemTag(id);
    return list;
  }

  public void save(SystemTag data) {
    SystemTag systemTag = null;
    IBasicDAO dao = null;
    try {
      dao = new BasicDAOImp();
      systemTag = this.getSystemTagLoaded(data);
      dao.save(systemTag);
    } catch (Exception exc) {
      System.err.println("ERROR SERVICE SAVE: " + exc.getMessage());
      throw exc;
    }
  }

  public void update(SystemTag data) {
    SystemTag systemTag = null;
    SystemTag systemTagUpdated = null;
    IBasicDAO dao = null;
    try {
      dao = new BasicDAOImp();
      systemTag = this.findById(data.getId());
      if ((systemTag.getId() > 0) && (systemTag.isFlagState())) {
        systemTagUpdated = this.getSystemTagLoaded(data);
        dao.update(systemTagUpdated);
      } else {
        throw new EmptyStackException();
      }
    } catch (Exception exc) {
      System.err.println("ERROR SERVICE UPDATE: " + exc.getMessage());
      throw exc;
    }
  }

  public void delete(SystemTag data) {
    SystemTag systemTag = null;
    IBasicDAO dao = null;
    try {
      dao = new BasicDAOImp();
      systemTag = this.findById(data.getId());
      if (systemTag.getId() > 0) {
        dao.delete(systemTag);
      } else {
        throw new EmptyStackException();
      }
    } catch (Exception exc) {
      System.err.println("ERROR SERVICE DELETE: " + exc.getMessage());
      throw exc;
    }
  }

  public SystemTag findById(Integer id) {
    SystemTag systemTag = null;
    IBasicDAO dao = null;
    EntityManager manager = null;
    List<GroupRol> listGroupRol = null;
    List<CompanyUser> listCompanyUser = null;
    try {
      dao = new BasicDAOImp();
      manager = dao.getManager();
      systemTag = manager.find(SystemTag.class, id);
      listGroupRol = this.findGroupRolesBySystemTag(systemTag.getId());
      listCompanyUser = this.findCompanyUsersBySystemTag(systemTag.getId());
      systemTag.setListGroupRoles(listGroupRol);
      systemTag.setListCompanyUsers(listCompanyUser);
      return systemTag;
    } catch (Exception exc) {
      System.err.println("ERROR SERVICE FINDING: " + exc.getMessage());
      systemTag = this.getDefaultObject();
      return systemTag;
    } finally {
      if ((manager != null) && (manager.isOpen())) {
        manager.close();
      }
    }
  }

  //*** TYPED QUERY
  /*
  public List<SystemTag> findAll() {
    IBasicDAO dao = null;
    EntityManager manager = null;
    TypedQuery<SystemTag> query = null;
    List<SystemTag> list = null;
    List<GroupRol> listGroupRoles = null;
    List<CompanyUser> listCompanyUsers = null;
    try {
      dao = new BasicDAOImp();
      manager = dao.getManager();
      query = manager.createNamedQuery("SystemTag.findAll", SystemTag.class);
      list = query.getResultList();
      for (SystemTag item : list) {
        listGroupRoles = this.findGroupRolesBySystemTag(item.getId());
        listCompanyUsers = this.findCompanyUsersBySystemTag(item.getId());
        item.setListGroupRoles(listGroupRoles);
        item.setListCompanyUsers(listCompanyUsers);
      }
      return list;
    } catch (Exception exc) {
      System.err.println("ERROR SERVICE FINDING: " + exc.getMessage());
      return new ArrayList<SystemTag>();
    } finally {
      if ((manager != null) && (manager.isOpen())) {
        manager.close();
      }
    }
  }
  */

  //*** TYPED QUERY
  /*
  public List<SystemTag> findAllByFlagState(boolean flagState) {
    IBasicDAO dao = null;
    EntityManager manager = null;
    TypedQuery<SystemTag> query = null;
    List<SystemTag> list = null;
    List<GroupRol> listGroupRoles = null;
    List<CompanyUser> listCompanyUsers = null;
    try {
      dao = new BasicDAOImp();
      manager = dao.getManager();
      query = manager.createNamedQuery("SystemTag.findAllByFlagState", SystemTag.class);
      query.setParameter("flagState", flagState);
      list = query.getResultList();
      for (SystemTag item : list) {
        listGroupRoles = this.findGroupRolesBySystemTag(item.getId());
        listCompanyUsers = this.findCompanyUsersBySystemTag(item.getId());
        item.setListGroupRoles(listGroupRoles);
        item.setListCompanyUsers(listCompanyUsers);
      }
      return list;
    } catch (Exception exc) {
      System.err.println("ERROR SERVICE FINDING: " + exc.getMessage());
      return new ArrayList<SystemTag>();
    } finally {
      if ((manager != null) && (manager.isOpen())) {
        manager.close();
      }
    }
  }
  */

  //*** CRITERIA
  public List<SystemTag> findAll() {
    IBasicDAO dao = null;
    EntityManager manager = null;
    CriteriaBuilder criteriaBuilder = null;
    CriteriaQuery<SystemTag> criteriaQuery = null;
    List<SystemTag> list = null;
    List<GroupRol> listGroupRoles = null;
    List<CompanyUser> listCompanyUsers = null;
    try {
      dao = new BasicDAOImp();
      manager = dao.getManager();
      criteriaBuilder = manager.getCriteriaBuilder();
      criteriaQuery = criteriaBuilder.createQuery(SystemTag.class);
      Root<SystemTag> root = criteriaQuery.from(SystemTag.class);
      criteriaQuery.select(root);
      list = manager.createQuery(criteriaQuery).getResultList();
      for (SystemTag item : list) {
        listGroupRoles = this.findGroupRolesBySystemTag(item.getId());
        listCompanyUsers = this.findCompanyUsersBySystemTag(item.getId());
        item.setListGroupRoles(listGroupRoles);
        item.setListCompanyUsers(listCompanyUsers);
      }
      return list;
    } catch (Exception exc) {
      System.err.println("ERROR SERVICE FINDING: " + exc.getMessage());
      return new ArrayList<SystemTag>();
    } finally {
      if ((manager != null) && (manager.isOpen())) {
        manager.close();
      }
    }
  }

  //*** CRITERIA
  public List<SystemTag> findAllByFlagState(boolean flagState) {
    IBasicDAO dao = null;
    EntityManager manager = null;
    CriteriaBuilder criteriaBuilder = null;
    CriteriaQuery<SystemTag> criteriaQuery = null;
    Predicate paramFlagState = null;
    List<SystemTag> list = null;
    List<GroupRol> listGroupRoles = null;
    List<CompanyUser> listCompanyUsers = null;
    try {
      dao = new BasicDAOImp();
      manager = dao.getManager();
      criteriaBuilder = manager.getCriteriaBuilder();
      criteriaQuery = criteriaBuilder.createQuery(SystemTag.class);
      Root<SystemTag> root = criteriaQuery.from(SystemTag.class);
      criteriaQuery.select(root);
      paramFlagState = criteriaBuilder.equal(root.get("flagState"), flagState);
      criteriaQuery.where(paramFlagState);
      list = manager.createQuery(criteriaQuery).getResultList();
      for (SystemTag item : list) {
        listGroupRoles = this.findGroupRolesBySystemTag(item.getId());
        listCompanyUsers = this.findCompanyUsersBySystemTag(item.getId());
        item.setListGroupRoles(listGroupRoles);
        item.setListCompanyUsers(listCompanyUsers);
      }
      return list;
    } catch (Exception exc) {
      System.err.println("ERROR SERVICE FINDING: " + exc.getMessage());
      return new ArrayList<SystemTag>();
    } finally {
      if ((manager != null) && (manager.isOpen())) {
        manager.close();
      }
    }
  }
}
