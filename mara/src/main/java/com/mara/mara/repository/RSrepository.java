package com.mara.mara.repository;

import com.mara.mara.data.UserLikeTagData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.util.List;

@Repository
public class RSrepository {
    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public RSrepository(DataSource dataSource){
        this.jdbcTemplate = new JdbcTemplate((dataSource));
    }

    public int saveCocktailId(Long userId,Long cocktailId){
        return jdbcTemplate.update("insert into user_like_cocktails(user_id,cocktail_id) values(?,?)",userId,cocktailId);
    }

    public int saveTagId(Long userId,Long tagId){
        return jdbcTemplate.update("insert into user_prefer_tags(user_id,tag_id) values(?,?)",userId,tagId);
    }

    public List<UserLikeTagData> getTop3TagByCocktail(Long cocktailId){
        return jdbcTemplate.query(
                "select * " +
                "from ( select cocktail_id,tag_id, count(tag_id) as n " +
                "from user_like_cocktails left join user_like_cocktails_tags on user_like_cocktails.user_like_id = user_like_cocktails_tags.user_like_id " +
                "where tag_id is not null " +
                "group by cocktail_id,tag_id " +
                ") as rs " +
                "where cocktail_id=? " +
                "order by n desc " +
                "limit 3 "
                ,(rs,num)->{
                    return new UserLikeTagData(
                            rs.getLong("cocktail_id"),
                            rs.getLong("tag_id"),
                            rs.getInt("n")
                    );
                }
                ,cocktailId);
    }


}
