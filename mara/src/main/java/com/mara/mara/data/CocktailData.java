package com.mara.mara.data;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class CocktailData {
    private Long cocktailId;
    private String cocktailName;
    private String cocktailImgUrl;
}
