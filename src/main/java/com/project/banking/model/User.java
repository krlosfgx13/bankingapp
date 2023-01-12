package com.project.banking.model;

import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.sql.Timestamp;

@Entity
@Table(name = "user_account", schema = "bankingapp")
public class User {

    @Id
    @Column(name = "user_account_id")
    private Integer userAccountId;

    @Column(name = "user_name")
    private String userName;

    @Column(name = "password")
    private String password;

    @Column(name = "created_date")
    @CreationTimestamp
    private Timestamp createdDate;


    @Column(name = "person_id")
    private Integer personId;

    public Integer getUserAccountId() {
        return userAccountId;
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

    public void setUserAccountId(Integer userAccountId) {
        this.userAccountId = userAccountId;
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
