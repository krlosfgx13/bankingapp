package com.project.banking.model;

import javax.persistence.*;

@Entity
@Table(name = "role", schema = "bankingapp")
public class Role {

    public Role(){

    }

    public Role(Integer roleId, RoleEnum roleName) {
        this.roleId = roleId;
        this.roleName = roleName;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "role_id")
    private Integer roleId;

//    @Column(name = "role_name")
//    private String roleName;

    @Enumerated(EnumType.STRING)
    @Column(name = "role_name")
    private RoleEnum roleName;

    public Integer getRoleId() {
        return roleId;
    }

    public void setRoleId(Integer roleId) {
        this.roleId = roleId;
    }

    public RoleEnum getRoleName() {
        return roleName;
    }

    public void setRoleName(RoleEnum roleName) {
        this.roleName = roleName;
    }
}
