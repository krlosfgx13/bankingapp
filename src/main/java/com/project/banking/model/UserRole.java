package com.project.banking.model;

import javax.persistence.*;

@Entity
@Table(name = "user_role", schema = "bankingapp")
public class UserRole {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_role_id")
    private Integer userRoleId;

    @Column(name = "user_account_id")
    private Integer userAccountId;

    @Column(name = "role_id")
    private Integer roleId;

    public UserRole(){

    }

    public UserRole(Integer userRoleId, Integer userAccountId, Integer roleId) {
        this.userRoleId = userRoleId;
        this.userAccountId = userAccountId;
        this.roleId = roleId;
    }

    public Integer getUserRoleId() {
        return userRoleId;
    }

    public void setUserRoleId(Integer userRoleId) {
        this.userRoleId = userRoleId;
    }

    public Integer getUserAccountId() {
        return userAccountId;
    }

    public void setUserAccountId(Integer userAccountId) {
        this.userAccountId = userAccountId;
    }

    public Integer getRoleId() {
        return roleId;
    }

    public void setRoleId(Integer roleId) {
        this.roleId = roleId;
    }
}
