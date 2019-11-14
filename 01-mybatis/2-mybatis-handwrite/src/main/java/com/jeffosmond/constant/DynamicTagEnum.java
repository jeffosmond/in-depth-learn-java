package com.jeffosmond.constant;

import lombok.Getter;

/**
 * 动态标签枚举
 */
@Getter
public enum DynamicTagEnum {

    //不特殊处理标签
    NO_DEAL_TAG("${"),
    //特殊处理标签
    NEED_DEAL_TAG("#{")
    ;


    private String tag;

    DynamicTagEnum(String tag) {
        this.tag = tag;
    }
}
