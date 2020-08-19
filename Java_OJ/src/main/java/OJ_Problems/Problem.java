package OJ_Problems;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
//对应数据库的实体类
@Getter
@Setter
@ToString
public class Problem {
    private int id;
    private String title;
    private String level;
    private String description;
    private String templateCode;
    private String testCode;
}
