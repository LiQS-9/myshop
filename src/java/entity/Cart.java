package entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @program: test02
 * @description:
 * @author: lqs
 * @create: 2020-09-22 19:49
 **/
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Cart {

   private Integer cid;// 购物车id
   private Integer uid;// 用户id
   private Product product;// 商品
//   private Integer pid;// 商品id
   private  Double ccount;// 商品总价
   private Integer cnum;// 商品数量

}
