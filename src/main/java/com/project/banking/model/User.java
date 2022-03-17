package com.project.banking.model;

import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.sql.Timestamp;

@Entity
@Table(name = "Usuario", schema = "bankingapp")
public class User {

    @Id
    @Column(name = "Dpi")
    private Long dpi;

    @Column(name = "NombreUsuario")
    private String userName;

    @Column(name = "Password")
    private String password;

    @Column(name = "FechaCreacionUsuario")
    @CreationTimestamp
    private Timestamp createdDate;

    public Long getDpi() {
        return dpi;
    }

    public String getUserName() {
        return userName;
    }

    public String getPassword() {
        return password;
    }

    public Timestamp getCreatedDate() {
        return createdDate;
    }

    public void setDpi(Long dpi) {
        this.dpi = dpi;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setCreatedDate(Timestamp createdDate) {
        this.createdDate = createdDate;
    }
}
