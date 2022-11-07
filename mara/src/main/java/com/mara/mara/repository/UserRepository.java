package com.mara.mara.repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.util.Optional;

@Repository
public class UserRepository {
    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public UserRepository(DataSource dataSource){
        this.jdbcTemplate = new JdbcTemplate((dataSource));
    }

    public Optional<Long> findById(Long userId){
        return jdbcTemplate.query(
                "select id from user where id = ?"
                ,(rs,num)->{
                    return rs.getLong("id");
                }
                ,userId).stream().findFirst();
    }

    public boolean existsByIdentifyId(String identifyId){
        return !jdbcTemplate.query("select identify_id from user where identify_id=?"
                ,(rs,num)->{
                    return rs.getString("identify_id");
                }
                ,identifyId).isEmpty();
    }

    public boolean existsByNickname(String nickname){
        return !jdbcTemplate.query("select nickname from user where nickname=?"
                ,(rs,num)->{
                    return rs.getString("nickname");
                }
                ,nickname).isEmpty();
    }
}
