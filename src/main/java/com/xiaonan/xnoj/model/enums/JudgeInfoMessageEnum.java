package com.xiaonan.xnoj.model.enums;

public enum JudgeInfoMessageEnum {
    SUCCESS("答案正确"),
    WRONG_ANSWER("Wrong Answer"),
    PARTIALLY_CORRECT("部分正确"),
    INVALID_INPUT("输入无效"),
    MEMORY_LIMIT_EXCEEDED("MEMORY_LIMIT_EXCEEDED"),
    TIME_LIMIT_EXCEEDED("TIME_LIMIT_EXCEEDED"),
    UNAUTHORIZED("未授权的操作"),
    NOT_FOUND("未找到对应数据"),
    SERVER_ERROR("服务器内部错误"),
    COMPILE_ERROR("Compile Error"),
    ACCEPTED("Accepted");


    private final String message;

    JudgeInfoMessageEnum(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }
}
