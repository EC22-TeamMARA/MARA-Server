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
        Integer n = jdbcTemplate.query("select count(*) as n from user_like_cocktails where user_id=? and cocktail_id=?"
                ,(rs,num)->{
                    return rs.getInt("n");
                }
                ,userId,cocktailId).stream().findFirst().get();
        if(n==0)
            return jdbcTemplate.update("insert into user_like_cocktails(user_id,cocktail_id) values(?,?)",userId,cocktailId);
        return 0;
    }

    public int saveTagId(Long userId,Long tagId){
        Integer n = jdbcTemplate.query("select count(*) as n from user_prefer_tags where user_id=?"
                ,(rs,num)->{
                    return rs.getInt("n");
                }
                ,userId).stream().findFirst().get();
        if(n==0)
            return jdbcTemplate.update("insert into user_prefer_tags(user_id,tag_id) values(?,?)",userId,tagId);
        return 0;
    }

    public Boolean okForSaveTagId(Long userId){
        Integer n = jdbcTemplate.query("select count(*) as n from user_prefer_tags where user_id=?"
                ,(rs,num)->{
                    return rs.getInt("n");
                }
                ,userId).stream().findFirst().get();
        if(n==0)
            return true;
        return false;
    }


    public List<Integer> getLikeCocktailsAllByUserId(Long userId){
        return jdbcTemplate.query(
                "select cocktails.cocktail_id, if(user_id is null , 0 , 1) as u " +
                        "from cocktails left join (select user_id,cocktail_id " +
                        "from user_like_cocktails " +
                        "where user_id=? ) as ut on cocktails.cocktail_id = ut.cocktail_id "
                ,(rs,num)->{
                    return rs.getInt("u");
                }
                ,userId);
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
