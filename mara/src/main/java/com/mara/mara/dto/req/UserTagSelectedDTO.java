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
public class UserTagSelectedDTO {
    private Long id;
    private List<Long> tagIdSelectedList;
}
