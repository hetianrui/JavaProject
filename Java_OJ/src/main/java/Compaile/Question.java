package Compaile;

import lombok.Getter;
import lombok.Setter;

//要编译的内容
@Setter
@Getter
public class Question {
    //要编译的代码
    private String code;
    //要写入的文件
    private String stdin;
}
