package com.xiaonan.xnoj.model.enums;

public enum JudgeInfoMessageEnum {
    SUCCESS("操作成功"),
    FAILURE("操作失败"),
    INVALID_INPUT("输入无效"),
    UNAUTHORIZED("未授权的操作"),
    NOT_FOUND("未找到对应数据"),
    SERVER_ERROR("服务器内部错误"),
    ACCEPTED("已接受");

    private final String message;

    JudgeInfoMessageEnum(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }
}
