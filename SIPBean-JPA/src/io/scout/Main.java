package io.scout;

import io.scout.controller.CompanyUserController;
import io.scout.controller.GroupRolController;
import io.scout.controller.MenuController;
import io.scout.controller.SystemTagController;
import io.scout.persistence.DBPersistence;

/**
 * @author DEV Scout
 */
public class Main {

  public static void main(String[] args) {
    SystemTagController systemTagController = new SystemTagController();
    GroupRolController groupRolController = new GroupRolController();
    MenuController menuController = new MenuController();
    CompanyUserController companyUserController = new CompanyUserController();

    systemTagController.save("sys_01");
    systemTagController.save("sys_02");
    systemTagController.save("sys_03");
    //systemTagController.update(2, "sys_02_upd");
    //systemTagController.delete(2);
    /*
    List<SystemTag> list = systemTagController.findAllByFlagState(true).getContent();
    for (SystemTag item : list) {
      System.out.println("ELEMENT: " + item.getId() + " NAME: " + item.getName());
    }
    */

    groupRolController.save("grp_01", 1);
    groupRolController.save("grp_02", 1);
    groupRolController.save("grp_03", 1);
    //groupRolController.update(2, "grp_02_upd", 1);
    //groupRolController.delete(2);
    /*
    List<GroupRol> list = groupRolController.findAllByFlagState(true).getContent();
    for (GroupRol item : list) {
      System.out.println("ELEMENT: " + item.getId() + " NAME: " + item.getName());
    }
    */

    menuController.save("menu_01", 1);
    menuController.save("menu_02", 1);
    menuController.save("menu_03", 1);
    //menuController.update(2, "menu_02_upd", 1);
    //menuController.delete(2);
    /*
    List<Menu> list = menuController.findAllByFlagState(true).getContent();
    for (Menu item : list) {
      System.out.println("ELEMENT: " + item.getId() + " NAME: " + item.getName());
    }
    */

    companyUserController.save("user_01", "pass", 1, 1);
    companyUserController.save("user_02", "pass", 1, 1);
    companyUserController.save("user_03", "pass", 1, 1);
    //companyUserController.update(2L, "user_02_upd", "pass", 2, 2);
    //companyUserController.deelete(3L);
    /*
    List<CompanyUser> list = companyUserController.findAllByFlagState(true).getContent();
    for (CompanyUser item : list) {
      System.out.println("ELEMENT: " + item.getId() + " " + item.getUsername() + " " + item.getSystemTag().getId() + " " + item.getGroupRol().getId());
    }
    */

    DBPersistence.closeFactory();
    DBPersistence.closeDB();
  }
}
