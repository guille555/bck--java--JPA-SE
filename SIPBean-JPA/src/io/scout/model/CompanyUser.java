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

/**
 * @author DEV Scout
 */
@Entity
@Table(name = "company_user")
@NamedQueries({
  @NamedQuery(name = "CompanyUser.findAll", query = "SELECT companyUsers FROM CompanyUser AS companyUsers ORDER BY companyUsers.username"),
  @NamedQuery(name = "CompanyUser.findAllByFlagState", query = "SELECT companyUsers FROM CompanyUser AS companyUsers WHERE (companyUsers.flagState = :flagState) ORDER BY companyUsers.username")
})
public class CompanyUser implements Serializable {

  private static final long serialVersionUID = 854967L;
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;
  @Column(name = "username", length = 32)
  private String username;
  @Column(name = "password", length = 32)
  private String password;
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
  @JoinColumn(
    name = "group_rol_id",
    referencedColumnName = "group_rol_id"
  )
  @ManyToOne(fetch = FetchType.LAZY)
  private GroupRol groupRol;

  public CompanyUser() {}

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public String getUsername() {
    return username;
  }

  public void setUsername(String username) {
    this.username = username;
  }

  public String getPassword() {
    return password;
  }

  public void setPassword(String password) {
    this.password = password;
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

  public GroupRol getGroupRol() {
    return groupRol;
  }

  public void setGroupRol(GroupRol groupRol) {
    this.groupRol = groupRol;
  }

  @Override
  public int hashCode() {
    int hash = 7;
    hash = 79 * hash + Objects.hashCode(this.id);
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
    final CompanyUser other = (CompanyUser) obj;
    return Objects.equals(this.id, other.id);
  }

  @Override
  public String toString() {
    return "CompanyUser {}";
  }
}
