package com.spring.boot.test;

import com.spring.boot.example.Application;
import com.spring.boot.example.ben.Department;
import com.spring.boot.example.ben.Employee;
import com.spring.boot.example.ben.Role;
import com.spring.boot.example.dao.mysql.DepartmentRepository;
import com.spring.boot.example.dao.mysql.EmployeeRepository;
import com.spring.boot.example.dao.mysql.RoleRepository;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;


import org.springframework.data.domain.Sort;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Date;
import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
@ContextConfiguration(classes = Application.class)
public class MysqlTest {

  private static Logger Log = LoggerFactory.getLogger(MysqlTest.class);
  @Autowired
  private DepartmentRepository departmentRepository;
  @Autowired
  private EmployeeRepository employeeRepository;
  @Autowired
  private RoleRepository roleRepository;

  @Test
  public void findPage() {
    Sort sort = Sort.by("id");

    Pageable pageable = PageRequest.of(0, 10, sort);

    Page<Employee> page = employeeRepository.findAll(pageable);
    Assert.assertNotNull(page);
    page.getContent().forEach(employee -> Log.info("employee name:{},department name:{},role name:{}",
        employee.getName(), employee.getDepartment().getName(), employee.getRoles().get(1).getName()));
  }

  @Before
  public void initData() {
    employeeRepository.deleteAll();
    departmentRepository.deleteAll();
    roleRepository.deleteAll();

    Department department = new Department();
    department.setName("开发部");
    departmentRepository.save(department);
    Assert.assertNotNull(department.getId());

    Role role1 = new Role();
    role1.setName("admin");
    Role role2 = new Role();
    role2.setName("normal");
    roleRepository.save(role1);
    Assert.assertNotNull(role1.getId());
    roleRepository.save(role2);
    Assert.assertNotNull(role2.getId());

    List<Role> roles = roleRepository.findAll();

    Employee employee = new Employee();
    employee.setCreateTime(new Date());
    employee.setDepartment(department);
    employee.setName("张三");
    employee.setRoles(roles);
    employeeRepository.save(employee);
    Assert.assertNotNull(employee.getId());
  }

}
