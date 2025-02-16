package io.scout.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
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
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 * @author DEV Scout
 */
@Entity
@Table(name = "group_rol")
@NamedQueries({
  @NamedQuery(name = "GroupRol.findAll", query = "SELECT groupRoles FROM GroupRol AS groupRoles ORDER BY groupRoles.name"),
  @NamedQuery(name = "GroupRol.findAllByFlagState", query = "SELECT groupRoles FROM GroupRol AS groupRoles WHERE (groupRoles.flagState = :flagState) ORDER BY groupRoles.name")
})
public class GroupRol implements Serializable {

  private static final long serialVersionUID = 852963L;
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "group_rol_id")
  private Integer id;
  @Column(name = "name", length = 64)
  private String name;
  @Column(name = "flag_state", nullable = false)
  private boolean flagState = false;
  @Column(name = "register_date", nullable = false)
  @Temporal(TemporalType.TIMESTAMP)
  private Date registerDate = new Date();
  @JoinColumn(
    name = "system_tag_id",
    referencedColumnName = "system_tag_id"
  )
  @ManyToOne(fetch = FetchType.LAZY)
  private SystemTag systemTag;
  @OneToMany(mappedBy = "groupRol", fetch = FetchType.LAZY)
  private List<CompanyUser> listCompanyUsers;
  @OneToMany(mappedBy = "groupRol", fetch = FetchType.LAZY)
  private List<Menu> listMenus;

  public GroupRol() {
    this.listMenus = new ArrayList<Menu>();
    this.listCompanyUsers = new ArrayList<CompanyUser>();
  }

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

  public SystemTag getSystemTag() {
    return systemTag;
  }

  public void setSystemTag(SystemTag systemTag) {
    this.systemTag = systemTag;
  }

  public List<CompanyUser> getListCompanyUsers() {
    return listCompanyUsers;
  }

  public void setListCompanyUsers(List<CompanyUser> listCompanyUsers) {
    this.listCompanyUsers = listCompanyUsers;
  }

  public List<Menu> getListMenus() {
    return listMenus;
  }

  public void setListMenus(List<Menu> listMenus) {
    this.listMenus = listMenus;
  }

  @Override
  public int hashCode() {
    int hash = 5;
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
    final GroupRol other = (GroupRol) obj;
    return Objects.equals(this.id, other.id);
  }

  @Override
  public String toString() {
    return "GroupRol {}";
  }
}
