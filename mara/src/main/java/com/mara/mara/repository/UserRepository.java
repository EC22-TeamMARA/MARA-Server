package com.mara.mara.repository;

import com.mara.mara.data.ErrorCode;
import com.mara.mara.exception.CustomException;
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

    public Long findById(Long userId){
        return jdbcTemplate.query(
                "select id from user where id = ?"
                ,(rs,num)->{
                    return rs.getLong("id");
                }
                ,userId).stream().findFirst().orElseThrow(()-> new CustomException(ErrorCode.NO_EXIST_USER));
    }

    public String findByIdentifyId(String identifyId){
        return jdbcTemplate.query("select identify_id from user where identify_id=?"
                ,(rs,num)->{
                    return rs.getString("identify_id");
                }
                ,identifyId).stream().findFirst().orElseThrow(()-> new CustomException(ErrorCode.NO_EXIST_ID));
    }

    public String findByNickname(String nickname){
        return jdbcTemplate.query("select nickname from user where nickname=?"
                ,(rs,num)->{
                    return rs.getString("nickname");
                }
                ,nickname).stream().findFirst().orElseThrow(()-> new CustomException(ErrorCode.NO_EXIST_NICKNAME));
    }
}
