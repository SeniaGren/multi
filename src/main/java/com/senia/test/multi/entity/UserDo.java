package com.senia.test.multi.entity;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.HashSet;
import java.util.Set;

@Data
@NoArgsConstructor
public class UserDo {
    private Integer id;
    private String name;
    private String type;
    private String password;
    private Set<RoleDo> roles = new HashSet<>();
}
