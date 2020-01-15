package com.senia.test.multi.entity;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.HashSet;
import java.util.Set;

/**
 * 角色类
 * @author senia
 */

@Data
@NoArgsConstructor
public class RoleDo {
    private String id;
    private String name;
    private Set<PermissionDo> permissions = new HashSet<PermissionDo>();
}
