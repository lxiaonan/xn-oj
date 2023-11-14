package com.xiaonan.xnoj.judge.codesandbox;

import com.xiaonan.xnoj.judge.codesandbox.model.CodeExecuteRequest;
import com.xiaonan.xnoj.judge.codesandbox.model.CodeExecuteResponse;
import com.xiaonan.xnoj.model.enums.QuestionSubmitLanguageEnum;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Arrays;
import java.util.Scanner;


@SpringBootTest
class CodeSandBoxTest {
    @Value("${codeSandBox.type}")
    String type;

    @Test
    public void test() {
        CodeSandBox codeSandBox = CodeSandBoxFactory.newInstance(type);
        CodeExecuteRequest codeExecuteRequest = CodeExecuteRequest
                .builder()
                .code("public static void main(String[] args) {\n" +
                        "        \n" +
                        "    }")
                .inputList(Arrays.asList("1 2", "3 4"))
                .language(QuestionSubmitLanguageEnum.JAVA.getValue())
                .build();
        codeSandBox.codeExecute(codeExecuteRequest);
    }
    @Test
    public void testProxy(){
        CodeSandBox codeSandBox = CodeSandBoxFactory.newInstance(type);
        CodeSandBoxProxy codeSandBoxProxy = new CodeSandBoxProxy(codeSandBox);
        CodeExecuteRequest codeExecuteRequest = CodeExecuteRequest
                .builder()
                .code("public static void main(String[] args) {\n" +
                        "        \n" +
                        "    }")
                .inputList(Arrays.asList("1 2", "3 4"))
                .language(QuestionSubmitLanguageEnum.JAVA.getValue())
                .build();
        CodeExecuteResponse codeExecuteResponse = codeSandBoxProxy.codeExecute(codeExecuteRequest);
        System.out.println(codeExecuteResponse);
    }


}
