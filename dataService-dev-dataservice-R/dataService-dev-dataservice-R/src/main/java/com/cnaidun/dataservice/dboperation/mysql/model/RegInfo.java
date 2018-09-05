package com.cnaidun.dataservice.dboperation.mysql.model;

import java.io.Serializable;
import java.util.Date;

public class RegInfo implements Serializable {
    private String uid;

    private String unitcode;

    private String openid;

    private Date createtime;

    private static final long serialVersionUID = 1L;

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public String getUnitcode() {
        return unitcode;
    }

    public void setUnitcode(String unitcode) {
        this.unitcode = unitcode;
    }

    public String getOpenid() {
        return openid;
    }

    public void setOpenid(String openid) {
        this.openid = openid;
    }

    public Date getCreatetime() {
        return createtime;
    }

    public void setCreatetime(Date createtime) {
        this.createtime = createtime;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", uid=").append(uid);
        sb.append(", unitcode=").append(unitcode);
        sb.append(", openid=").append(openid);
        sb.append(", createtime=").append(createtime);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }

    @Override
    public boolean equals(Object that) {
        if (this == that) {
            return true;
        }
        if (that == null) {
            return false;
        }
        if (getClass() != that.getClass()) {
            return false;
        }
        RegInfo other = (RegInfo) that;
        return (this.getUid() == null ? other.getUid() == null : this.getUid().equals(other.getUid()))
            && (this.getUnitcode() == null ? other.getUnitcode() == null : this.getUnitcode().equals(other.getUnitcode()))
            && (this.getOpenid() == null ? other.getOpenid() == null : this.getOpenid().equals(other.getOpenid()))
            && (this.getCreatetime() == null ? other.getCreatetime() == null : this.getCreatetime().equals(other.getCreatetime()));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getUid() == null) ? 0 : getUid().hashCode());
        result = prime * result + ((getUnitcode() == null) ? 0 : getUnitcode().hashCode());
        result = prime * result + ((getOpenid() == null) ? 0 : getOpenid().hashCode());
        result = prime * result + ((getCreatetime() == null) ? 0 : getCreatetime().hashCode());
        return result;
    }
}