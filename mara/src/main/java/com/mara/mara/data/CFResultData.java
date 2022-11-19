package com.mara.mara.data;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Setter
@Getter
@AllArgsConstructor
public class CFResultData {
    Long userId;
    List<Integer> resultList;
}
