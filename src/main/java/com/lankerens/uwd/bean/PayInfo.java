package com.lankerens.uwd.bean;

import lombok.Data;
import lombok.ToString;
import lombok.experimental.Accessors;

/**
 * @author lankerens
 * @version 1.0
 * @description: TODO
 * @date 2023/2/26 8:42 PM
 */
@Data
@ToString
@Accessors(chain = true)
public class PayInfo {
    private Double price;

    private Long uid;

}
