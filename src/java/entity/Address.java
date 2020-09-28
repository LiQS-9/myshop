package entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @program: test02
 * @description:
 * @author: lqs
 * @create: 2020-09-23 09:46
 **/
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Address {
    private Integer aid;
    private Integer uid;//用户id
    private String aname;
    private String aphone;
    private String adetail;
    private Integer astate;

}
