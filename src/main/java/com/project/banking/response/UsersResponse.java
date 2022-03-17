package com.project.banking.response;

import java.util.List;

public class UsersResponse extends ApiResponse{

    List<UsersData> listOfUsers;

    public List<UsersData> getListOfUsers() {
        return listOfUsers;
    }

    public void setListOfUsers(List<UsersData> listOfUsers) {
        this.listOfUsers = listOfUsers;
    }
}
