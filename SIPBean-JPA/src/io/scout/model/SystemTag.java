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
@Table(name = "system_tag")
@NamedQueries({
  @NamedQuery(name = "SystemTag.findAll", query = "SELECT systemTags FROM SystemTag AS systemTags ORDER BY systemTags.name"),
  @NamedQuery(name = "SystemTag.findAllByFlagState", query = "SELECT systemTags FROM SystemTag AS systemTags WHERE (systemTags.flagState = :flagSate) ORDER BY systemTags.name")
})
public class SystemTag implements Serializable {

  private static final long serialVersionUID = 147852L;
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "system_tag_id")
  private Integer id;
  @Column(name = "name", length = 32)
  private String name;
  @Column(name = "flag_state", nullable = false)
  private boolean flagState = false;
  @Column(name = "register_date", nullable = false)
  @Temporal(TemporalType.TIMESTAMP)
  private Date registerDate = new Date();
  @OneToMany(mappedBy = "systemTag", fetch = FetchType.LAZY)
  private List<GroupRol> listGroupRoles;
  @OneToMany(mappedBy = "systemTag", fetch = FetchType.LAZY)
  private List<CompanyUser> listCompanyUsers;
  
  public SystemTag() {
    this.listGroupRoles = new ArrayList<GroupRol>();
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

  public List<GroupRol> getListGroupRoles() {
    return listGroupRoles;
  }

  public void setListGroupRoles(List<GroupRol> listGroupRoles) {
    this.listGroupRoles = listGroupRoles;
  }

  public List<CompanyUser> getListCompanyUsers() {
    return listCompanyUsers;
  }

  public void setListCompanyUsers(List<CompanyUser> listCompanyUsers) {
    this.listCompanyUsers = listCompanyUsers;
  }

  @Override
  public int hashCode() {
    int hash = 5;
    hash = 97 * hash + Objects.hashCode(this.id);
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
    final SystemTag other = (SystemTag) obj;
    return Objects.equals(this.id, other.id);
  }

  @Override
  public String toString() {
    return "SystemTag {}";
  }
}
