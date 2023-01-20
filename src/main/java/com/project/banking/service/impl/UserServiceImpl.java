
package com.project.banking.service.impl;

import com.project.banking.model.Person;
import com.project.banking.model.Role;
import com.project.banking.model.RoleEnum;
import com.project.banking.model.User;
import com.project.banking.response.UsersData;
import com.project.banking.repository.UserRepository;
import com.project.banking.response.ApiResponse;
import com.project.banking.response.UsersResponse;
import com.project.banking.service.PersonService;
import com.project.banking.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.security.crypto.password.Pbkdf2PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import java.sql.*;

import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    UserRepository repository;
    ApiResponse response = new ApiResponse();

    @Autowired
    PersonService personService;

    @Autowired
    Pbkdf2PasswordEncoder pbkdf2PasswordEncoder;

    @Autowired
    EntityManager entityManager;

    @Autowired
    private  LocalContainerEntityManagerFactoryBean entityManagerBean;

    @Override
    public ApiResponse createUser(User user) throws NoSuchAlgorithmException, InvalidKeySpecException {
        try {
            if(user.getUserName() == "" || user.getPassword() == ""){
                return new ApiResponse(500, "Fill out mandatory fields.", "error");
            }
            Person person = personService.getPersonById(user.getPersonId());
            if (person != null) {
                user.setPassword(pbkdf2PasswordEncoder.encode(user.getPassword()));
                repository.save(user);
                response.setMessage("Operation performed successfully.");
                response.setCode(200);
                response.setStatus("success");
            } else {
                return new ApiResponse(500, "Invalid DPI. Check that there is a person with this DPI number.", "error");
            }
            return response;
        } catch (Exception ex) {
            throw ex;
        }
    }

    @Override
    public ApiResponse updateUserPassword(Integer id, User user) throws NoSuchAlgorithmException, InvalidKeySpecException {
        try {
            User userObj = repository.findByUserId(id);
            String newPassword = pbkdf2PasswordEncoder.encode(user.getPassword());
            if (userObj != null) {
                userObj.setPassword(newPassword);
                repository.save(userObj);

            } else {
                return new ApiResponse(500, "Something went wrong.", "error");
            }
        } catch (Exception ex) {
            throw ex;
        }
        return new ApiResponse(200, "Operation performed successfully.", "success");
    }

    @Override
    public ApiResponse deleteUser(Integer id) {
        try {
            User user = repository.findByUserId(id);

            if (user != null) {
                repository.delete(user);
            } else {
                return new ApiResponse(500, "Something went wrong.", "error");
            }
        } catch (Exception ex) {
            throw ex;
        }
        return new ApiResponse(200, "Operation performed successfully.", "success");
    }

    @Override
    public User getUserById(Integer id) {
        try {
            return repository.findByUserId(id);
        } catch (Exception ex) {
            throw ex;
        }
    }

    @Override
    public User getUserByUserName(String userName) {
        try {
            return repository.findByUserName(userName);
        } catch (Exception ex) {
            throw ex;
        }
    }

    @Override
    public List<User> getAllUsers() {
        List<User> list = new ArrayList<>();
        try {
            list = repository.findAll();
            if (!list.isEmpty()) {
                return list;
            }
        } catch (Exception ex) {
            throw ex;
        }
        return list;
    }

    @Override
    public UsersResponse getUsersData() throws SQLException, ClassNotFoundException {
        List<UsersData> listOfUsers = new ArrayList<>();
        UsersResponse response = new UsersResponse();
        Connection conn = null;
        try{
            Class.forName("com.mysql.cj.jdbc.Driver");
            conn = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/bankingapp","root","12345678");
            String selectStatement = "select p.person_id, u.user_name, p.first_name, p.last_name, p.address, \n" +
                    "p.phone_number, p.home_phone_number,\n" +
                    "p.email_address, u.created_date \n" +
                    "from person p \n" +
                    "join user_account u on p.person_id = u.person_id";
            Statement st = conn.createStatement();
            ResultSet rs = st.executeQuery(selectStatement);
           while(rs.next()){
                UsersData users = new UsersData();
                users.setPersonId(rs.getInt(1));
                users.setUserName(rs.getString(2));
                users.setFirstName(rs.getString(3));
                users.setLastName(rs.getString(4));
                users.setAddress(rs.getString(5));
                users.setPhoneNumber(rs.getInt(6));
                users.setHomePhoneNumber(rs.getInt(7));
                users.setEmailAddress(rs.getString(8));
                users.setCreationDate(rs.getTimestamp(9));
                listOfUsers.add(users);
            }

        } catch(Exception ex){
            System.out.println("Something went wrong: " + ex.getMessage());
            throw ex;
        } finally{
            conn.close();
        }
        response.setCode(200);
        response.setMessage("Operation performed successfully.");
        response.setStatus("success");
        response.setListOfUsers(listOfUsers);
        return response;
    }

    @Override
    public Boolean authenticateUser(String username, String password) throws NoSuchAlgorithmException, InvalidKeySpecException {
        try {
            User user = repository.findByUserName(username);
            if (user != null) {
                return pbkdf2PasswordEncoder.matches(password, user.getPassword());
            } else {
                return false;
            }
        } catch (Exception ex) {
            throw ex;
        }
    }

//    @Override
//    public List<Role> getRolesFromUser(Integer userId) {
//        String query = "select r.role_id, r.role_name \n" +
//                "from user_role ur\n" +
//                "join role r on ur.role_id = r.role_id\n" +
//                "where user_account_id = ?";
//        Query q = entityManager.createNativeQuery(query);
//        q.setParameter(1, userId);
//        return q.getResultList();
//    }

    @Override
    public List<Role> getRolesFromUser(Integer userId) {
        String query = "select r.role_id, r.role_name \n" +
                "from user_role ur\n" +
                "join role r on ur.role_id = r.role_id\n" +
                "where user_account_id = :userId";
        NamedParameterJdbcTemplate jdbcTemplate = new NamedParameterJdbcTemplate(entityManagerBean.getDataSource());
        SqlParameterSource params = new MapSqlParameterSource("userId", userId);
        return jdbcTemplate.query(query, params, new BeanPropertyRowMapper<>(Role.class));
    }
}