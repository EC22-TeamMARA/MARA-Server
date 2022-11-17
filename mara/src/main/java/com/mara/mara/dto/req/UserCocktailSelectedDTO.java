package com.mara.mara.dto.req;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class UserCocktailSelectedDTO {
    private Long id;
    private List<Long> cocktailIdSelectedList;


}
