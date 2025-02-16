package io.scout.model;

import java.io.Serializable;
import java.util.Date;
import java.util.Objects;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import org.eclipse.persistence.annotations.JoinFetch;

/**
 * @author DEV Scout
 */
@Entity
@Table(name = "menu")
@NamedQueries({
  @NamedQuery(name = "Menu.findAll", query = "SELECT menus FROM Menu AS menus ORDER BY menus.name"),
  @NamedQuery(name = "Menu.findAllByFlagState", query = "SELECT menus FROM Menu AS menus WHERE (menus.flagState = :flagState) ORDER BY menus.name")
})
public class Menu implements Serializable {

  private static final long serialVersionUID = 159863L;
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "menu_id")
  private Integer id;
  @Column(name = "name", length = 64)
  private String name;
  @Column(name = "flag_state")
  private boolean flagState = false;
  @Temporal(TemporalType.TIMESTAMP)
  @Column(name = "register_date")
  private Date registerDate = new Date();
  @JoinColumn(
    name = "group_rol_id",
    referencedColumnName = "group_rol_id"
  )
  @ManyToOne(fetch = FetchType.LAZY)
  @JoinFetch
  private GroupRol groupRol = null;

  public Menu() {}

  public Integer getId() {
    return id;
  }

  public void setId(Integer id) {
    this.id = id;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public boolean isFlagState() {
    return flagState;
  }

  public void setFlagState(boolean flagState) {
    this.flagState = flagState;
  }

  public Date getRegisterDate() {
    return registerDate;
  }

  public void setRegisterDate(Date registerDate) {
    this.registerDate = registerDate;
  }

  public GroupRol getGroupRol() {
    return groupRol;
  }

  public void setGroupRol(GroupRol groupRol) {
    this.groupRol = groupRol;
  }

  @Override
  public int hashCode() {
    int hash = 7;
    hash = 37 * hash + Objects.hashCode(this.id);
    return hash;
  }

  @Override
  public boolean equals(Object obj) {
    if (this == obj) {
      return true;
    }
    if (obj == null) {
      return false;
    }
    if (getClass() != obj.getClass()) {
      return false;
    }
    final Menu other = (Menu) obj;
    return Objects.equals(this.id, other.id);
  }

  @Override
  public String toString() {
    return "Menu {}";
  }
}
