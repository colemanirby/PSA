package com.psa.domain.users.dao;


import com.psa.domain.users.UserInfo;
import javax.sql.DataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

/**
 * Created by ckirb on 11/11/2016.
 */

@Repository
public class UserDao {

    private JdbcTemplate jdbcTemplate;

    @Autowired
    public void setDataSource(DataSource dataSource) {
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }


    public UserInfo getUserInfo(String username){
        final String GETUSERQUERY = "SELECT USERNAME, PASSWORD, ROLE FROM PSA_USERS WHERE USERNAME = ?";



        UserInfo userInfo = jdbcTemplate.queryForObject(GETUSERQUERY, new Object[]{username},
                (rs, rowNum) -> {
                    UserInfo user = new UserInfo();
                    user.setUsername(rs.getString("username"));
                    user.setPassword(rs.getString("password"));
                    user.setRole(rs.getString("role"));
                    return user;
                });
        return userInfo;
    }

    public void addUser(UserInfo userInfo)
    {
        final String INSERTUSERQUERY = "INSERT INTO PSA_USERS" +
                "USERNAME = ? PASSWORD = ? ROLE = 'ROLE_USER'";
        jdbcTemplate.update( INSERTUSERQUERY, new Object[] { userInfo.getUsername(), userInfo.getPassword()});

    }
}
