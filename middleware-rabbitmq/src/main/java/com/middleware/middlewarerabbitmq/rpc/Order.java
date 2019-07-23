package com.middleware.middlewarerabbitmq.rpc;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Date;

/**
 * @author wangxia
 * @date 2019/7/23 16:08
 * @Description:
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Order implements Serializable {

    private Integer id;
    private String orderId;
    private Date createTime;

    public String print(){
        return "{id:"+id+",orderId:"+orderId+",createTime:"+createTime+"}";
    }

}
