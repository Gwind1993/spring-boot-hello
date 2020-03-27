package com.spring.boot.example.ben;

import com.fasterxml.jackson.annotation.JsonBackReference;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 * 员工实体类
 *
 * @author
 * @since
 */
@Entity
public class Employee implements Serializable {

  /**
   * 员工编号
   */
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Integer id;
  /**
   * 员工名称
   */
  private String name;

  /**
   *
   */
  @DateTimeFormat(pattern = "YYYY-MM-DD HH:mm:ss")
  private Date createTime;

  /**
   * 用户对应部门
   */
  @ManyToOne
  @JoinColumn(name = "d_id")
  @JsonBackReference
  private Department department;

  /**
   * 用户对应角色
   */
  @ManyToMany(cascade = {},fetch = FetchType.EAGER)
  @JoinTable(name = "user_role",joinColumns = {@JoinColumn(columnDefinition = "user_id")},
      inverseJoinColumns = {@JoinColumn(name = "roles_id")})
  private List<Role> roles;

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

  public Date getCreateTime() {
    return createTime;
  }

  public void setCreateTime(Date createTime) {
    this.createTime = createTime;
  }

  public Department getDepartment() {
    return department;
  }

  public void setDepartment(Department department) {
    this.department = department;
  }

  public List<Role> getRoles() {
    return roles;
  }

  public void setRoles(List<Role> roles) {
    this.roles = roles;
  }

  @Override
  public String toString() {
    return "Employee{" +
        "id=" + id +
        ", name='" + name + '\'' +
        '}';
  }

}
