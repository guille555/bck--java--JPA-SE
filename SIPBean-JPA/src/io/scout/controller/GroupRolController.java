package io.scout.controller;

import io.scout.model.GroupRol;
import io.scout.model.SystemTag;
import io.scout.service.GroupRolService;
import java.util.List;

/**
 * @author DEV Scout
 */
public class GroupRolController {

  public GroupRolController() {}

  private SystemResponse<GroupRol> getSuccessfulResponse(List<GroupRol> list) {
    SystemResponse<GroupRol> response = new SystemResponse<GroupRol>((short) 200, true, "ok");
    response.setContent(list);
    return response;
  }

  private SystemResponse<GroupRol> getUnsuccessfulResponse(String message) {
    SystemResponse<GroupRol> response = new SystemResponse<GroupRol>((short) 500, false, message);
    response.setContent(null);
    return response;
  }

  private GroupRol prepareData(Integer id, String name, Integer idSystemTag) {
    SystemTag systemTag = new SystemTag();
    GroupRol groupRol = new GroupRol();
    systemTag.setId(idSystemTag);
    groupRol.setId(id);
    groupRol.setName(name);
    groupRol.setSystemTag(systemTag);
    return groupRol;
  }

  public SystemResponse<GroupRol> save(String name, Integer idSystemTag) {
    GroupRol groupRol = null;
    GroupRolService service = null;
    SystemResponse<GroupRol> response = null;
    try {
      service = new GroupRolService();
      groupRol = this.prepareData(null, name, idSystemTag);
      service.save(groupRol);
      response = this.getSuccessfulResponse(null);
      return response;
    } catch (Exception exc) {
      response = this.getUnsuccessfulResponse("bad operation");
      return response;
    }
  }

  public SystemResponse<GroupRol> update(Integer id, String name, Integer idSystemTag) {
    GroupRol groupRol = null;
    GroupRolService service = null;
    SystemResponse<GroupRol> response = null;
    try {
      service = new GroupRolService();
      groupRol = this.prepareData(id, name, idSystemTag);
      service.update(groupRol);
      response = this.getSuccessfulResponse(null);
      return response;
    } catch (Exception exc) {
      response = this.getUnsuccessfulResponse("bad operation");
      return response;
    }
  }

  public SystemResponse<GroupRol> delete(Integer id) {
    GroupRol groupRol = null;
    GroupRolService service = null;
    SystemResponse<GroupRol> response = null;
    try {
      service = new GroupRolService();
      groupRol = this.prepareData(id, null, null);
      service.delete(groupRol);
      response = this.getSuccessfulResponse(null);
      return response;
    } catch (Exception exc) {
      response = this.getUnsuccessfulResponse("bad operation");
      return response;
    }
  }

  public GroupRol findById(Integer id) {
    GroupRolService service = new GroupRolService();
    GroupRol groupRol = service.findById(id);
    return groupRol;
  }

  public SystemResponse<GroupRol> findAll() {
    GroupRolService service = new GroupRolService();
    List<GroupRol> list = service.findAll();
    SystemResponse<GroupRol> response = this.getSuccessfulResponse(list);
    return response;
  }

  public SystemResponse<GroupRol> findAllByFlagState(boolean flagState) {
    GroupRolService service = new GroupRolService();
    List<GroupRol> list = service.findAllByFlagState(flagState);
    SystemResponse<GroupRol> response = this.getSuccessfulResponse(list);
    return response;
  }
}
