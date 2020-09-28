package entity;

import lombok.Data;

/**
 * @program: test02
 * @description:
 * @author: lqs
 * @create: 2020-09-18 19:34
 **/
@Data
public class ResultData {
    private String code;
    private String desc;
    private Object data;

    public static ResultData successful(Object o){
        ResultData resultData = new ResultData();
        resultData.data = o;
        resultData.code = "0";// 成功返回0
        return resultData;
    }

    public static ResultData failure(String code,String desc){
        ResultData resultData = new ResultData();
        resultData.code = code;
        resultData.desc = desc;
        return resultData;
    }

    // 成功后封装 msg
    public static ResultData successMsg(String code,String msg){
        ResultData resultData = new ResultData();
        resultData.code = code;
        resultData.desc = msg;
        return resultData;
    }

}
