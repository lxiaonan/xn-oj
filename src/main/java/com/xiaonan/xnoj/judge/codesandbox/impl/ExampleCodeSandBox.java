package com.xiaonan.xnoj.judge.codesandbox.impl;

import com.xiaonan.xnoj.judge.codesandbox.CodeSandBox;
import com.xiaonan.xnoj.judge.codesandbox.model.CodeExecuteRequest;
import com.xiaonan.xnoj.judge.codesandbox.model.CodeExecuteResponse;
import com.xiaonan.xnoj.judge.codesandbox.model.JudgeInfo;
import com.xiaonan.xnoj.model.enums.JudgeInfoMessageEnum;
import com.xiaonan.xnoj.model.enums.QuestionSubmitStatusEnum;
import lombok.extern.slf4j.Slf4j;

import java.util.List;

@Slf4j
public class ExampleCodeSandBox implements CodeSandBox {
    @Override
    public CodeExecuteResponse codeExecute(CodeExecuteRequest executeCodeRequest) {
        List<String> inputList = executeCodeRequest.getInputList();
        CodeExecuteResponse executeCodeResponse = new CodeExecuteResponse();
        executeCodeResponse.setOutputList(inputList);
        executeCodeResponse.setMessage("测试执行成功");
        executeCodeResponse.setStatus(QuestionSubmitStatusEnum.SUCCESS.getValue());
        JudgeInfo judgeInfo = new JudgeInfo();
        judgeInfo.setMessage(JudgeInfoMessageEnum.ACCEPTED.getMessage());
        judgeInfo.setMemory(1024L);
        judgeInfo.setTime(1024L);
        executeCodeResponse.setJudgeInfo(judgeInfo);
        return executeCodeResponse;
    }
}
//public class ExampleCodeSandBox implements CodeSandBox {
//
//    @Override
//    public CodeExecuteResponse codeExecute(CodeExecuteRequest codeExecuteRequest) {
//        System.out.println("样例代码沙箱环境");
//            String userCode = "" +
//                    "import java.util.Scanner;" +
//                    "public class Main {" +
//                    "    public static void main(String[] args) {" +
//                    "        Scanner scanner = new Scanner(System.in);" +
//                    "        int a = scanner.nextInt();" +
//                    "        int b = scanner.nextInt();" +
//                    "        System.out.println(a + b);" +
//                    "    }" +
//                    "}";
//
//            // 将用户提供的代码写入到一个临时文件中
//            String filePath = "Main.java";
//            try (PrintWriter out = new PrintWriter(filePath)) {
//                out.println(userCode);
//            } catch (FileNotFoundException e) {
//                e.printStackTrace();
//            }
//
//            // 编译用户提供的代码
//            JavaCompiler compiler = ToolProvider.getSystemJavaCompiler();
//            compiler.run(null, null, null, filePath);
//
//            // 加载并执行用户提供的代码
//            try {
//                // 加载 UserCode 类
//                Class<?> userCodeClass = Class.forName("Main");
//
//                // 实例化 UserCode 对象
//                Object userCodeObj = userCodeClass.getDeclaredConstructor().newInstance();
//
//                // 获取 execute 方法
//                Method executeMethod = userCodeClass.getMethod("main");
//
//                // 设置输入参数
//
//                String input = "5\n10"; // 替换为你想要的输入
//                InputStream inputStream = new ByteArrayInputStream(input.getBytes());
//                System.setIn(inputStream);
//
//                // 执行用户提供的代码
//                executeMethod.invoke(userCodeObj);
//            } catch (Exception e) {
//                e.printStackTrace();
//            }
//        System.out.println("样例代码沙箱环境");
//        return null;
//    }
//}
