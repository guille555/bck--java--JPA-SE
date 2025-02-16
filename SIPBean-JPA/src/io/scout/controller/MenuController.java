package io.scout.controller;

import io.scout.model.GroupRol;
import io.scout.model.Menu;
import io.scout.service.MenuService;
import java.util.List;

/**
 * @author DEV Scout
 */
public class MenuController {

  public MenuController() {}

  private SystemResponse<Menu> getSuccessfulResponse(List<Menu> list) {
    SystemResponse<Menu> response = new SystemResponse<Menu>((short) 200, true, "ok");
    response.setContent(list);
    return response;
  }

  private SystemResponse<Menu> getUnsuccessfulResponse(String message) {
    SystemResponse<Menu> response = new SystemResponse<Menu>((short) 500, false, message);
    response.setContent(null);
    return response;
  }

  private Menu prepareData(Integer id, String name, Integer idGroupRol) {
    GroupRol groupRol = new GroupRol();
    Menu menu = new Menu();
    groupRol.setId(idGroupRol);
    menu.setId(id);
    menu.setName(name);
    menu.setGroupRol(groupRol);
    return menu;
  }

  public SystemResponse<Menu> save(String name, Integer idGroupRol) {
    Menu menu = null;
    MenuService service = null;
    SystemResponse<Menu> response = null;
    try {
      service = new MenuService();
      menu = this.prepareData(null, name, idGroupRol);
      service.save(menu);
      response = this.getSuccessfulResponse(null);
      return response;
    } catch (Exception exc) {
      response = this.getUnsuccessfulResponse("bad operation");
      return response;
    }
  }

  public SystemResponse<Menu> update(Integer id, String name, Integer idGroupRol) {
    Menu menu = null;
    MenuService service = null;
    SystemResponse<Menu> response = null;
    try {
      service = new MenuService();
      menu = this.prepareData(id, name, idGroupRol);
      service.update(menu);
      response = this.getSuccessfulResponse(null);
      return response;
    } catch (Exception exc) {
      response = this.getUnsuccessfulResponse("bad operation");
      return response;
    }
  }

  public SystemResponse<Menu> delete(Integer id) {
    Menu menu = null;
    MenuService service = null;
    SystemResponse<Menu> response = null;
    try {
      service = new MenuService();
      menu = this.prepareData(id, null, null);
      service.delete(menu);
      response = this.getSuccessfulResponse(null);
      return response;
    } catch (Exception exc) {
      response = this.getUnsuccessfulResponse("bad operation");
      return response;
    }
  }

  public Menu findById(Integer id) {
    MenuService service = new MenuService();
    Menu menu = service.findById(id);
    return menu;
  }

  public SystemResponse<Menu> findAll() {
    MenuService service = new MenuService();
    List<Menu> list = service.findAll();
    SystemResponse<Menu> response = this.getSuccessfulResponse(list);
    return response;
  }

  public SystemResponse<Menu> findAllByFlagState(boolean flagState) {
    MenuService service = new MenuService();
    List<Menu> list = service.findAllByFlagState(flagState);
    SystemResponse<Menu> response = this.getSuccessfulResponse(list);
    return response;
  }
}
