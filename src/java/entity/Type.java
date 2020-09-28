package entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @program: test02
 * @description:
 * @author: lqs
 * @create: 2020-09-21 14:17
 **/
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Type {
    private Integer tid;
    private String  tname;
    private String  tinfo;

}
