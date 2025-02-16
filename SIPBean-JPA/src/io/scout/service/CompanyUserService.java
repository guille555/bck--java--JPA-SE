package io.scout.service;

import io.scout.dao.BasicDAOImp;
import io.scout.dao.IBasicDAO;
import io.scout.model.CompanyUser;
import io.scout.model.GroupRol;
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
public class CompanyUserService {

  public CompanyUserService() {}

  private SystemTag findSystemTagById(Integer id) {
    SystemTagService service = new SystemTagService();
    SystemTag systemTag = service.findById(id);
    return systemTag;
  }

  private GroupRol findGroupRolById(Integer id) {
    GroupRolService service = new GroupRolService();
    GroupRol groupRol = service.findById(id);
    return groupRol;
  }

  private CompanyUser getDefaultObject() {
    CompanyUser companyUser = new CompanyUser();
    companyUser.setId(0L);
    companyUser.setFlagState(false);
    companyUser.setRegisterDate(null);
    companyUser.setSystemTag(null);
    companyUser.setGroupRol(null);
    return companyUser;
  }

  private CompanyUser getCompanyUserLoaded(CompanyUser data) {
    Long id = ((data.getId() != null) && (data.getId() > 0)) ? (data.getId()) : (null);
    Date date = new Date();
    CompanyUser companyUser = new CompanyUser();
    companyUser.setId(id);
    companyUser.setUsername(data.getUsername());
    companyUser.setPassword(data.getPassword());
    companyUser.setFlagState(true);
    companyUser.setRegisterDate(date);
    return companyUser;
  }

  private CompanyUser prepareCompanyUser(CompanyUser data) {
    SystemTag systemTag = this.findSystemTagById(data.getSystemTag().getId());
    GroupRol groupRol = this.findGroupRolById(data.getGroupRol().getId());
    CompanyUser companyUser = this.getCompanyUserLoaded(data);
    companyUser.setSystemTag(systemTag);
    companyUser.setGroupRol(groupRol);
    return companyUser;
  }

  public void save(CompanyUser data) {
    CompanyUser companyUser = null;
    IBasicDAO dao = null;
    try {
      dao = new BasicDAOImp();
      companyUser = this.prepareCompanyUser(data);
      dao.save(companyUser);
    } catch (Exception exc) {
      System.err.println("ERROR SERVICE SAVE: " + exc.getMessage());
      throw exc;
    }
  }

  public void update(CompanyUser data) {
    CompanyUser companyUser = null;
    IBasicDAO dao = null;
    try {
      dao = new BasicDAOImp();
      companyUser = this.findById(data.getId());
      if ((companyUser.getId() > 0) && (companyUser.isFlagState())) {
        companyUser = this.prepareCompanyUser(data);
        dao.update(companyUser);
      } else {
        throw new EmptyStackException();
      }
    } catch (Exception exc) {
      System.err.println("ERROR SERVICE UPDATE: " + exc.getMessage());
      throw exc;
    }
  }

  public void delete(CompanyUser data) {
    CompanyUser companyUser = null;
    IBasicDAO dao = null;
    try {
      dao = new BasicDAOImp();
      companyUser = this.findById(data.getId());
      if (companyUser.getId() > 0) {
        dao.delete(companyUser);
      } else {
        throw new EmptyStackException();
      }
    } catch (Exception exc) {
      System.err.println("ERROR SERVICE DELETE: " + exc.getMessage());
      throw exc;
    }
  }

  public CompanyUser findById(Long id) {
    CompanyUser companyUser = null;
    IBasicDAO dao = null;
    EntityManager manager = null;
    try {
      dao = new BasicDAOImp();
      manager = dao.getManager();
      companyUser = manager.find(CompanyUser.class, id);
      return companyUser;
    } catch (Exception exc) {
      System.err.println("ERROR SERVICE FINDING: " + exc.getMessage());
      companyUser = this.getDefaultObject();
      return companyUser;
    } finally {
      if ((manager != null) && (manager.isOpen())) {
        manager.close();
      }
    }
  }

  //*** TYPED QUERY
  /*
  public List<CompanyUser> findAll() {
    IBasicDAO dao = null;
    EntityManager manager = null;
    TypedQuery<CompanyUser> query = null;
    List<CompanyUser> list = null;
    try {
      dao = new BasicDAOImp();
      manager = dao.getManager();
      query = manager.createNamedQuery("CompanyUser.findAll", CompanyUser.class);
      list = query.getResultList();
      return list;
    } catch (Exception exc) {
      System.err.println("ERROR SERVICE FINDING: " + exc.getMessage());
      list = new ArrayList<CompanyUser>();
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
  public List<CompanyUser> findAllByFlagState(boolean flagState) {
    IBasicDAO dao = null;
    EntityManager manager = null;
    TypedQuery<CompanyUser> query = null;
    List<CompanyUser> list = null;
    try {
      dao = new BasicDAOImp();
      manager = dao.getManager();
      query = manager.createNamedQuery("CompanyUser.findAllByFlagState", CompanyUser.class);
      query.setParameter("flagState", flagState);
      list = query.getResultList();
      return list;
    } catch (Exception exc) {
      System.err.println("ERROR SERVICE FINDING: " + exc.getMessage());
      list = new ArrayList<CompanyUser>();
      return list;
    } finally {
      if ((manager != null) && (manager.isOpen())) {
        manager.close();
      }
    }
  }
  */

  //*** CRITERIA
  public List<CompanyUser> findAll() {
    IBasicDAO dao = null;
    EntityManager manager = null;
    CriteriaBuilder criteriaBuilder = null;
    CriteriaQuery<CompanyUser> criteriaQuery = null;
    Root<CompanyUser> root = null;
    List<CompanyUser> list  = null;
    try {
      dao = new BasicDAOImp();
      manager = dao.getManager();
      criteriaBuilder = manager.getCriteriaBuilder();
      criteriaQuery = criteriaBuilder.createQuery(CompanyUser.class);
      root = criteriaQuery.from(CompanyUser.class);
      criteriaQuery.select(root);
      list = manager.createQuery(criteriaQuery).getResultList();
      return list;
    } catch (Exception exc) {
      System.err.println("ERROR SERVICE FINDING: " + exc.getMessage());
      list = new ArrayList<CompanyUser>();
      return list;
    } finally {
      if ((manager != null) && (manager.isOpen())) {
        manager.close();
      }
    }
  }

  //*** CRITERIA
  public List<CompanyUser> findAllByFlagState(boolean flagState) {
    IBasicDAO dao = null;
    EntityManager manager = null;
    CriteriaBuilder criteriaBuilder = null;
    CriteriaQuery<CompanyUser> criteriaQuery = null;
    Root<CompanyUser> root = null;
    Predicate paramFlagState = null;
    List<CompanyUser> list  = null;
    try {
      dao = new BasicDAOImp();
      manager = dao.getManager();
      criteriaBuilder = manager.getCriteriaBuilder();
      criteriaQuery = criteriaBuilder.createQuery(CompanyUser.class);
      root = criteriaQuery.from(CompanyUser.class);
      paramFlagState = criteriaBuilder.equal(root.get("flagState"), flagState);
      criteriaQuery.select(root);
      criteriaQuery.where(paramFlagState);
      list = manager.createQuery(criteriaQuery).getResultList();
      return list;
    } catch (Exception exc) {
      System.err.println("ERROR SERVICE FINDING: " + exc.getMessage());
      list = new ArrayList<CompanyUser>();
      return list;
    } finally {
      if ((manager != null) && (manager.isOpen())) {
        manager.close();
      }
    }
  }

  //*** CRITERIA
  public List<CompanyUser> findAllBySystemTag(Integer idSystemTag) {
    IBasicDAO dao = null;
    EntityManager manager = null;
    CriteriaBuilder criteriaBuilder = null;
    CriteriaQuery<CompanyUser> criteriaQuery = null;
    Predicate paramIdSystemTag = null;
    Root<CompanyUser> root = null;
    List<CompanyUser> list  = null;
    try {
      dao = new BasicDAOImp();
      manager = dao.getManager();
      criteriaBuilder = manager.getCriteriaBuilder();
      criteriaQuery = criteriaBuilder.createQuery(CompanyUser.class);
      root = criteriaQuery.from(CompanyUser.class);
      paramIdSystemTag = criteriaBuilder.equal(root.get("systemTag").get("id"), idSystemTag);
      criteriaQuery.select(root);
      criteriaQuery.where(paramIdSystemTag);
      list = manager.createQuery(criteriaQuery).getResultList();
      return list;
    } catch (Exception exc) {
      System.err.println("ERROR SERVICE FINDING: " + exc.getMessage());
      list = new ArrayList<CompanyUser>();
      return list;
    } finally {
      if ((manager != null) && (manager.isOpen())) {
        manager.close();
      }
    }
  }

  //*** CRITERIA
  public List<CompanyUser> findAllByGroupRol(Integer idGroupRol) {
    IBasicDAO dao = null;
    EntityManager manager = null;
    CriteriaBuilder criteriaBuilder = null;
    CriteriaQuery<CompanyUser> criteriaQuery = null;
    Predicate paramIdGroupRol = null;
    Root<CompanyUser> root = null;
    List<CompanyUser> list  = null;
    try {
      dao = new BasicDAOImp();
      manager = dao.getManager();
      criteriaBuilder = manager.getCriteriaBuilder();
      criteriaQuery = criteriaBuilder.createQuery(CompanyUser.class);
      root = criteriaQuery.from(CompanyUser.class);
      paramIdGroupRol = criteriaBuilder.equal(root.get("groupRol").get("id"), idGroupRol);
      criteriaQuery.select(root);
      criteriaQuery.where(paramIdGroupRol);
      list = manager.createQuery(criteriaQuery).getResultList();
      return list;
    } catch (Exception exc) {
      System.err.println("ERROR SERVICE FINDING: " + exc.getMessage());
      list = new ArrayList<CompanyUser>();
      return list;
    } finally {
      if ((manager != null) && (manager.isOpen())) {
        manager.close();
      }
    }
  }
}
