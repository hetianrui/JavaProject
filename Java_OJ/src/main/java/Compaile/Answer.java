package Compaile;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class Answer {
    //通过error表示当前的错误类型
    //约定error为0表示没错误，error为1表示编译出错，error为2表示运行出错
    private int error;
    //表示具体的出错原因，可能是编译错误，也可能是运行错误（异常信息）
    private String reason;
    //执行时标准输出对应的内容
    private String stdout;
    //执行时标准错误对应的内容
    private String stderr;

    @Override
    public String toString() {
        return "Answer{" +
                "error=" + error +
                ", reason='" + reason + '\'' +
                ", stdout='" + stdout + '\'' +
                ", stderr='" + stderr + '\'' +
                '}';
    }
}
