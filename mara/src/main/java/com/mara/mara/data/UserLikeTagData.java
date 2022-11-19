package com.mara.mara.data;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class UserLikeTagData {
    private Long cocktailId;
    private Long tagId;
    private Integer n;

}
