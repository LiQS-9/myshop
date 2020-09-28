package entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

/**
 * @program: test02
 * @description:
 * @author: lqs
 * @create: 2020-09-21 15:09
 **/
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Product {
    private Integer pid;
    private Integer tid;
    private String pname;
    private Date ptime;
    private String pimage;
    private Double pprice;
    private Integer pstate;
    private String  pinfo;

}
