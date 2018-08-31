package com.cnaidun.user.api.policeUser.bean;

import lombok.Data;

import java.util.Date;

/**
 * 项目名称：PoliceCloud
 * 类名称：Dictionar
 * 类描述：
 * 创建人：JackJun
 * 创建时间：2018/7/25
 * 修改人：JackJun
 * 修改时间：2018/7/25
 * 修改备注：
 * 版权所有权：江苏艾盾网络科技有限公司
 *
 * @version V1.0
 */
@Data
public class Dictionar {

    /**
     * id
     */
    private Integer id;

    /**
     * 字典-名称
     */
    private String zdName;

    /**
     * 码值
     */
    private String code;

    /**
     * 名称
     */
    private String name;

    /**
     * 类型 3-户政
     */
    private Integer type;

    /**
     * 级别 行政区域 1.省 2.市 3.区县
     */
    private Integer rank;

    /**
     * 创建时间
     */
    private Date createTime;


}

