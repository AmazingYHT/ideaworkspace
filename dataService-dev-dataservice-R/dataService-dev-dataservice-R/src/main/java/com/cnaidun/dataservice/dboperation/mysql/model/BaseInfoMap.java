package com.cnaidun.dataservice.dboperation.mysql.model;

import java.io.Serializable;
import java.util.Date;

public class BaseInfoMap implements Serializable {
    private String infocode;

    private String infotype;

    private String parentcode;

    private String parenttype;

    private Date createtime;

    private String infoname;

    private String parentname;

    private static final long serialVersionUID = 1L;

    public String getInfocode() {
        return infocode;
    }

    public void setInfocode(String infocode) {
        this.infocode = infocode;
    }

    public String getInfotype() {
        return infotype;
    }

    public void setInfotype(String infotype) {
        this.infotype = infotype;
    }

    public String getParentcode() {
        return parentcode;
    }

    public void setParentcode(String parentcode) {
        this.parentcode = parentcode;
    }

    public String getParenttype() {
        return parenttype;
    }

    public void setParenttype(String parenttype) {
        this.parenttype = parenttype;
    }

    public Date getCreatetime() {
        return createtime;
    }

    public void setCreatetime(Date createtime) {
        this.createtime = createtime;
    }

    public String getInfoname() {
        return infoname;
    }

    public void setInfoname(String infoname) {
        this.infoname = infoname;
    }

    public String getParentname() {
        return parentname;
    }

    public void setParentname(String parentname) {
        this.parentname = parentname;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", infocode=").append(infocode);
        sb.append(", infotype=").append(infotype);
        sb.append(", parentcode=").append(parentcode);
        sb.append(", parenttype=").append(parenttype);
        sb.append(", createtime=").append(createtime);
        sb.append(", infoname=").append(infoname);
        sb.append(", parentname=").append(parentname);
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
        BaseInfoMap other = (BaseInfoMap) that;
        return (this.getInfocode() == null ? other.getInfocode() == null : this.getInfocode().equals(other.getInfocode()))
            && (this.getInfotype() == null ? other.getInfotype() == null : this.getInfotype().equals(other.getInfotype()))
            && (this.getParentcode() == null ? other.getParentcode() == null : this.getParentcode().equals(other.getParentcode()))
            && (this.getParenttype() == null ? other.getParenttype() == null : this.getParenttype().equals(other.getParenttype()))
            && (this.getParentname() == null ? other.getParentname() == null : this.getParentname().equals(other.getParentname()))
            && (this.getCreatetime() == null ? other.getCreatetime() == null : this.getCreatetime().equals(other.getCreatetime()))
            && (this.getInfoname() == null ? other.getInfoname() == null : this.getInfoname().equals(other.getInfoname()));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getInfocode() == null) ? 0 : getInfocode().hashCode());
        result = prime * result + ((getInfotype() == null) ? 0 : getInfotype().hashCode());
        result = prime * result + ((getParentcode() == null) ? 0 : getParentcode().hashCode());
        result = prime * result + ((getParenttype() == null) ? 0 : getParenttype().hashCode());
        result = prime * result + ((getCreatetime() == null) ? 0 : getCreatetime().hashCode());
        result = prime * result + ((getInfoname() == null) ? 0 : getInfoname().hashCode());
        result = prime * result + ((getParentname() == null) ? 0 : getParentname().hashCode());
        return result;
    }
}