package entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

/**
 * @program: test02
 * @description:
 * @author: lqs
 * @create: 2020-09-23 09:42
 **/
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Order {
    private String oid;

//    private Integer uid;
    private User user;// 用户
//    private Integer aid;
    private Address address;// 地址
    private Double  ocount; // 订单总价
    private Date otime; // 订单创建时间
    private Integer ostate; // 订单状态

}
