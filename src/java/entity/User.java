package entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @program: test02
 * @description:
 * @author: lqs
 * @create: 2020-09-21 16:45
 **/
@Data
@AllArgsConstructor
@NoArgsConstructor
public class User {
    private Integer uid;
    private String  uname;
    private String upassword;
    private String uemail;
    private String  usex;
    private Integer ustatus;// 状态
    private String  ucode;
    private Integer urole;// 角色

}
