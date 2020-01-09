package com.senia.test.multi.entity;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class UserDo {
    private Integer id;
    private String name;
    private String type;
}
