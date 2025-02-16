package io.scout.controller;

import io.scout.model.CompanyUser;
import io.scout.model.GroupRol;
import io.scout.model.SystemTag;
import io.scout.service.CompanyUserService;
import java.util.List;

/**
 * @author DEV Scout
 */
public class CompanyUserController {

  public CompanyUserController() {}

  private SystemResponse<CompanyUser> getSuccessfulResponse(List<CompanyUser> list) {
    SystemResponse<CompanyUser> response = new SystemResponse<CompanyUser>((short) 200, true, "ok");
    response.setContent(list);
    return response;
  }

  private SystemResponse<CompanyUser> getUnsuccessfulResponse(String message) {
    SystemResponse<CompanyUser> response = new SystemResponse<CompanyUser>((short) 500, false, message);
    response.setContent(null);
    return response;
  }

  private CompanyUser prepareData(Long id, String username, String password, Integer idSystemTag, Integer idGroupRol) {
    SystemTag systemTag = new SystemTag();
    GroupRol groupRol = new GroupRol();
    CompanyUser companyUser = new CompanyUser();
    systemTag.setId(idSystemTag);
    groupRol.setId(idGroupRol);
    companyUser.setId(id);
    companyUser.setUsername(username);
    companyUser.setPassword(password);
    companyUser.setSystemTag(systemTag);
    companyUser.setGroupRol(groupRol);
    return companyUser;
  }

  public SystemResponse<CompanyUser> save(String username, String password, Integer idSystemTag, Integer idGroupRol) {
    CompanyUser data = null;
    CompanyUserService service = null;
    SystemResponse<CompanyUser> response = null;
    try {
      service = new CompanyUserService();
      data = this.prepareData(null, username, password, idSystemTag, idGroupRol);
      service.save(data);
      response = this.getSuccessfulResponse(null);
      return response;
    } catch (Exception exc) {
      response = this.getUnsuccessfulResponse("bad operation");
      return response;
    }
  }

  public SystemResponse<CompanyUser> update(Long id, String username, String password, Integer idSystemTag, Integer idGroupRol) {
    CompanyUser data = null;
    CompanyUserService service = null;
    SystemResponse<CompanyUser> response = null;
    try {
      service = new CompanyUserService();
      data = this.prepareData(id, username, password, idSystemTag, idGroupRol);
      service.update(data);
      response = this.getSuccessfulResponse(null);
      return response;
    } catch (Exception exc) {
      response = this.getUnsuccessfulResponse("bad operation");
      return response;
    }
  }

  public SystemResponse<CompanyUser> deelete(Long id) {
    CompanyUser data = null;
    CompanyUserService service = null;
    SystemResponse<CompanyUser> response = null;
    try {
      service = new CompanyUserService();
      data = this.prepareData(id, null, null, null, null);
      service.delete(data);
      response = this.getSuccessfulResponse(null);
      return response;
    } catch (Exception exc) {
      response = this.getUnsuccessfulResponse("bad operation");
      return response;
    }
  }

  public CompanyUser findById(Long id) {
    CompanyUserService service = new CompanyUserService();
    CompanyUser data = service.findById(id);
    return data;
  }

  public SystemResponse<CompanyUser> findAll() {
    CompanyUserService service = new CompanyUserService();
    List<CompanyUser> list = service.findAll();
    SystemResponse<CompanyUser> response = this.getSuccessfulResponse(list);
    return response;
  }

  public SystemResponse<CompanyUser> findAllByFlagState(boolean flagState) {
    CompanyUserService service = new CompanyUserService();
    List<CompanyUser> list = service.findAllByFlagState(flagState);
    SystemResponse<CompanyUser> response = this.getSuccessfulResponse(list);
    return response;
  }
}
