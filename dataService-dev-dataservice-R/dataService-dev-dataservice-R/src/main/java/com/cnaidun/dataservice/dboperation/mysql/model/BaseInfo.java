package com.cnaidun.dataservice.dboperation.mysql.model;

import java.io.Serializable;
import java.util.Date;

public class BaseInfo implements Serializable {
    private String code;

    private String name;

    private String nickset;

    private String infotype;

    private String datatype;

    private String status;

    private Date createtime;

    private static final long serialVersionUID = 1L;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getNickset() {
        return nickset;
    }

    public void setNickset(String nickset) {
        this.nickset = nickset;
    }

    public String getInfotype() {
        return infotype;
    }

    public void setInfotype(String infotype) {
        this.infotype = infotype;
    }

    public String getDatatype() {
        return datatype;
    }

    public void setDatatype(String datatype) {
        this.datatype = datatype;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
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
        sb.append(", code=").append(code);
        sb.append(", name=").append(name);
        sb.append(", nickset=").append(nickset);
        sb.append(", infotype=").append(infotype);
        sb.append(", datatype=").append(datatype);
        sb.append(", status=").append(status);
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
        BaseInfo other = (BaseInfo) that;
        return (this.getCode() == null ? other.getCode() == null : this.getCode().equals(other.getCode()))
            && (this.getName() == null ? other.getName() == null : this.getName().equals(other.getName()))
            && (this.getNickset() == null ? other.getNickset() == null : this.getNickset().equals(other.getNickset()))
            && (this.getInfotype() == null ? other.getInfotype() == null : this.getInfotype().equals(other.getInfotype()))
            && (this.getDatatype() == null ? other.getDatatype() == null : this.getDatatype().equals(other.getDatatype()))
            && (this.getStatus() == null ? other.getStatus() == null : this.getStatus().equals(other.getStatus()))
            && (this.getCreatetime() == null ? other.getCreatetime() == null : this.getCreatetime().equals(other.getCreatetime()));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getCode() == null) ? 0 : getCode().hashCode());
        result = prime * result + ((getName() == null) ? 0 : getName().hashCode());
        result = prime * result + ((getNickset() == null) ? 0 : getNickset().hashCode());
        result = prime * result + ((getInfotype() == null) ? 0 : getInfotype().hashCode());
        result = prime * result + ((getDatatype() == null) ? 0 : getDatatype().hashCode());
        result = prime * result + ((getStatus() == null) ? 0 : getStatus().hashCode());
        result = prime * result + ((getCreatetime() == null) ? 0 : getCreatetime().hashCode());
        return result;
    }
}