package com.mara.mara.repository;

import com.mara.mara.data.ErrorCode;
import com.mara.mara.dto.req.UserSignUpSubmitRequestDTO;
import com.mara.mara.exception.CustomException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.util.Optional;

@Repository
public class UserSignUpRepository {
    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public UserSignUpRepository(DataSource dataSource){
        this.jdbcTemplate = new JdbcTemplate((dataSource));
    }

    public int saveJoin(UserSignUpSubmitRequestDTO submitDTO){
        return jdbcTemplate.update(
                "insert into user(identify_id, nickname, pw) values(?,?,?)"
                ,submitDTO.getIdentifyId(),submitDTO.getNickname(),submitDTO.getPassword()
        );
    }

    public Optional<String> checkDuplicateIdentifyId(String identifyId){
        return jdbcTemplate.query("select identify_id from user where identify_id=?"
                ,(rs,num)->{
                    return rs.getString("identify_id");
                }
                ,identifyId).stream().findFirst();
    }

    public Optional<String> checkDuplicateNickname(String nickname){
        return jdbcTemplate.query("select nickname from user where nickname=?"
                ,(rs,num)->{
                    return rs.getString("nickname");
                }
                ,nickname).stream().findFirst();
    }

}
