package com.cnaidun.user.api.policeUser.bean;
import lombok.Data;

/**
 * 项目名称：PoliceCloud
 * 类名称：Dictionary
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
public class Dictionary {

    private int id;

    /**
     * 字典名
     */
    private String zd_name;

    /**
     * 代码
     */
    private String code;

    /**
     * 国家/地区名称
     */
    private String name;

    /**
     * 类型 1.出入境 2.交管 3.户政
     */
    private int type;

    /**
     * 层级 1.省 2.市 3.区县
     */
    private String rank;

    /**
     * 状态
     */
    private int status;
}
