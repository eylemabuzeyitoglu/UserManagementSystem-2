package com.eylemabz.UserManagementSystem2;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Repository
public class UserRepository {
    private final JdbcTemplate jdbc;

    public UserRepository(JdbcTemplate jdbc){
        this.jdbc = jdbc;
    }

    public List<User> findAll(){
        String sql = "SELECT * FROM users";
        return jdbc.query(sql,new UserRowMapper());
    }

    public User findById(long id){
        String sql = "SELECT * FROM users";
        return jdbc.queryForObject(sql,new Object[]{id}, new UserRowMapper());
    }

    public int save(User user){
        String sql = "INSERT INTO users (id,name,email) VALUES (?,?,?)";
        return jdbc.update(sql,user.getId(),user.getName(),user.getEmail());
    }

    public int update(User user){
        String sql = "UPDATE users SET name = ?, email = ?, WHERE id = ?";
        return jdbc.update(sql,user.getName(),user.getEmail(),user.getId());
    }

    public int delete(long id){
        String sql = "DELETE FROM users WHERE id = ?";
        return jdbc.update(sql,id);
    }

    private static class UserRowMapper implements RowMapper<User>{
        @Override
        public User mapRow(ResultSet rs, int rowNum) throws SQLException{
            User user = new User();
            user.setId(rs.getLong("id"));
            user.setName(rs.getString("name"));
            user.setEmail(rs.getString("email"));
            return user;
        }
    }


}
