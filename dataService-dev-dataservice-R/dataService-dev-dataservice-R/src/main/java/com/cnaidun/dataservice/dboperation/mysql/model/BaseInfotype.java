package com.cnaidun.dataservice.dboperation.mysql.model;

import java.io.Serializable;
import java.util.Date;

public class BaseInfotype implements Serializable {
    private String infotype;

    private String infoname;

    private String description;

    private String status;

    private Date createtime;

    private static final long serialVersionUID = 1L;

    public String getInfotype() {
        return infotype;
    }

    public void setInfotype(String infotype) {
        this.infotype = infotype;
    }

    public String getInfoname() {
        return infoname;
    }

    public void setInfoname(String infoname) {
        this.infoname = infoname;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
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
        sb.append(", infotype=").append(infotype);
        sb.append(", infoname=").append(infoname);
        sb.append(", description=").append(description);
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
        BaseInfotype other = (BaseInfotype) that;
        return (this.getInfotype() == null ? other.getInfotype() == null : this.getInfotype().equals(other.getInfotype()))
            && (this.getInfoname() == null ? other.getInfoname() == null : this.getInfoname().equals(other.getInfoname()))
            && (this.getDescription() == null ? other.getDescription() == null : this.getDescription().equals(other.getDescription()))
            && (this.getStatus() == null ? other.getStatus() == null : this.getStatus().equals(other.getStatus()))
            && (this.getCreatetime() == null ? other.getCreatetime() == null : this.getCreatetime().equals(other.getCreatetime()));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getInfotype() == null) ? 0 : getInfotype().hashCode());
        result = prime * result + ((getInfoname() == null) ? 0 : getInfoname().hashCode());
        result = prime * result + ((getDescription() == null) ? 0 : getDescription().hashCode());
        result = prime * result + ((getStatus() == null) ? 0 : getStatus().hashCode());
        result = prime * result + ((getCreatetime() == null) ? 0 : getCreatetime().hashCode());
        return result;
    }
}