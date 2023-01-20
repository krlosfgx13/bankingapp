package com.project.banking.repository;

import com.project.banking.model.Role;
import com.project.banking.model.User;
import com.project.banking.response.UsersResponse;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {
    //In the query specification, classes and field names are used instead of tables and columns.

    @Query("FROM User WHERE userName = ?1")
    User findByUserName(String username);

    @Query("FROM User WHERE person_id = ?1")
    User findByUserId(Integer id);

    @Query(value = "select r.role_id roleId, r.role_name roleName \n" +
            "from user_role ur\n" +
            "join role r on ur.role_id = r.role_id\n" +
            "where user_account_id = ?1", nativeQuery = true)
    List<Role> getRolesFromUser(Integer userAccountId);

//    @Query("SELECT password FROM User WHERE userName = ?1")
//    String findByPassword(String username);
//
//    @Query("SELECT p.person_id, u.userName, p.firstName, p.firstLastName, p.address, p.phoneNumber, p.homePhoneNumber,\n" +
//            "p.emailAddress, u.createdDate \n" +
//            "from Person p \n" +
//            "join User u on p.person_id = u.person_id")
//    List<UsersResponse> findUsersData();

}
