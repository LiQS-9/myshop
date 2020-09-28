package entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

/**
 * @program: test02
 * @description:
 * @author: lqs
 * @create: 2020-09-21 19:15
 **/
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Goods {
    private Integer pid;
    private Type type;
    private String pname;
    private Date ptime;
    private String pimage;
    private Double pprice;
    private Integer pstate;//评分
    private String  pinfo;

}
