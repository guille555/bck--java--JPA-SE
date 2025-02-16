package io.scout.controller;

import io.scout.model.SystemTag;
import io.scout.service.SystemTagService;
import java.util.List;

/**
 * @author DEV Scout
 */
public class SystemTagController {

  public SystemTagController() {}

  private SystemResponse<SystemTag> getSuccessfulResponse(List<SystemTag> list) {
    SystemResponse<SystemTag> response = new SystemResponse<SystemTag>((short) 200, true, "ok");
    response.setContent(list);
    return response;
  }

  private SystemResponse<SystemTag> getUnsuccessfulResponse(String message) {
    SystemResponse<SystemTag> response = new SystemResponse<SystemTag>((short) 500, false, message);
    response.setContent(null);
    return response;
  }

  private SystemTag prepareData(Integer id, String name) {
    SystemTag data = new SystemTag();
    data.setId(id);
    data.setName(name);
    return data;
  }

  public SystemResponse save(String name) {
    SystemTag data = null;
    SystemTagService service = null;
    SystemResponse<SystemTag> response = null;
    try {
      data = this.prepareData(null, name);
      service = new SystemTagService();
      service.save(data);
      response = this.getSuccessfulResponse(null);
      return response;
    } catch (Exception exc) {
      System.err.println("ERROR CONTROLLER SAVE: " + exc.getMessage());
      response = this.getUnsuccessfulResponse("bad operation");
      return response;
    }
  }

  public SystemResponse update(Integer id, String name) {
    SystemTag data = null;
    SystemTagService service = null;
    SystemResponse<SystemTag> response = null;
    try {
      data = this.prepareData(id, name);
      service = new SystemTagService();
      service.update(data);
      response = this.getSuccessfulResponse(null);
      return response;
    } catch (Exception exc) {
      System.err.println("ERROR CONTROLLER UPDATE: " + exc.getMessage());
      response = this.getUnsuccessfulResponse("bad operation");
      return response;
    }
  }

  public SystemResponse delete(Integer id) {
    SystemTag data = null;
    SystemTagService service = null;
    SystemResponse<SystemTag> response = null;
    try {
      data = this.prepareData(id, null);
      service = new SystemTagService();
      service.delete(data);
      response = this.getSuccessfulResponse(null);
      return response;
    } catch (Exception exc) {
      System.err.println("ERROR CONTROLLER UPDATE: " + exc.getMessage());
      response = this.getUnsuccessfulResponse("bad operation");
      return response;
    }
  }

  public SystemTag findById(Integer id) {
    SystemTagService service = new SystemTagService();
    SystemTag systemTag = service.findById(id);
    return systemTag;
  }

  public SystemResponse<SystemTag> findAll() {
    SystemTagService service = new SystemTagService();
    List<SystemTag> list = service.findAll();
    SystemResponse<SystemTag> response = this.getSuccessfulResponse(list);
    return response;
  }

  public SystemResponse<SystemTag> findAllByFlagState(boolean flagState) {
    SystemTagService service = new SystemTagService();
    List<SystemTag> list = service.findAllByFlagState(flagState);
    SystemResponse<SystemTag> response = this.getSuccessfulResponse(list);
    return response;
  }
}
