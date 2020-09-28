package entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @program: test02
 * @description:
 * @author: lqs
 * @create: 2020-09-23 15:06
 **/
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Item {

    private Integer iid;
    private String oid;
    private Product product;
    private Double icount;// 该商品总计
    private Integer inum;//该商品个数

}
