package com.mara.mara.repository;

import com.mara.mara.constant.ErrorCode;
import com.mara.mara.data.CocktailData;
import com.mara.mara.data.TagData;
import com.mara.mara.dto.req.UserCreateAccountDTO;
import com.mara.mara.exception.CustomException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.util.List;
import java.util.Optional;

@Repository
public class UserSignUpRepository {
    private final JdbcTemplate jdbcTemplate;

    @Value("${img.path}")
    private String IMG_PATH;

    @Autowired
    public UserSignUpRepository(DataSource dataSource){
        this.jdbcTemplate = new JdbcTemplate((dataSource));
    }

    public int saveJoin(UserCreateAccountDTO submitDTO) throws Exception {
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

    public List<CocktailData> getCocktailDataForSignUp(){
        return jdbcTemplate.query("select cocktail_id, cocktail_name, cocktail_img_url from cocktails where init=1"
                ,(rs,num)-> {
                    return new CocktailData(
                            rs.getLong("cocktail_id"),
                            rs.getString("cocktail_name"),
                            IMG_PATH+rs.getString("cocktail_img_url")
                    );
                });
    }

    public List<TagData> getTagDataForSignUp(){
        return jdbcTemplate.query("select tag_id, tag_content from tags "
                ,(rs,num)-> {
                    return new TagData(
                            rs.getLong("tag_id"),
                            rs.getString("tag_content")
                    );
                });
    }
}
