package com.senia.test.multi.entity;

import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 权限类
 * @author senia
 */
@Data
@NoArgsConstructor
public class PermissionDo {
    private String id;
    private String name;
    private String url;
}
