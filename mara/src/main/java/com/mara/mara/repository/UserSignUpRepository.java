package com.mara.mara.repository;

import com.mara.mara.dto.req.UserSignUpSubmitRequestDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;

@Repository
public class UserSignUpRepository {
    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public UserSignUpRepository(DataSource dataSource){
        this.jdbcTemplate = new JdbcTemplate((dataSource));
    }

    public int saveJoin(UserSignUpSubmitRequestDTO submitDTO){
        return jdbcTemplate.update(
                "insert into user(identiy_id, nickname, password) values(?,?,?)"
                ,submitDTO.getIdentifyId(),submitDTO.getNickname(),submitDTO.getPassword()
        );

    }


}
