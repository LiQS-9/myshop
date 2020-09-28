package entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @program: test02
 * @description:
 * @author: lqs
 * @create: 2020-09-24 09:59
 **/
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Weixin {

        private Result result;
        private String type;

}
