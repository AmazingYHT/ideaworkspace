package com.cnaidun.dataservice.dboperation.mysql.model;

import java.io.Serializable;
import java.util.Date;

public class WorkSheet implements Serializable {
    private String workno;

    private String worktypecode;

    private String unitcode;

    private String uid;

    private String reqcontent;

    private Date createtime;

    private String status;

    private static final long serialVersionUID = 1L;

    public String getWorkno() {
        return workno;
    }

    public void setWorkno(String workno) {
        this.workno = workno;
    }

    public String getWorktypecode() {
        return worktypecode;
    }

    public void setWorktypecode(String worktypecode) {
        this.worktypecode = worktypecode;
    }

    public String getUnitcode() {
        return unitcode;
    }

    public void setUnitcode(String unitcode) {
        this.unitcode = unitcode;
    }

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public String getReqcontent() {
        return reqcontent;
    }

    public void setReqcontent(String reqcontent) {
        this.reqcontent = reqcontent;
    }

    public Date getCreatetime() {
        return createtime;
    }

    public void setCreatetime(Date createtime) {
        this.createtime = createtime;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", workno=").append(workno);
        sb.append(", worktypecode=").append(worktypecode);
        sb.append(", unitcode=").append(unitcode);
        sb.append(", uid=").append(uid);
        sb.append(", reqcontent=").append(reqcontent);
        sb.append(", createtime=").append(createtime);
        sb.append(", status=").append(status);
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
        WorkSheet other = (WorkSheet) that;
        return (this.getWorkno() == null ? other.getWorkno() == null : this.getWorkno().equals(other.getWorkno()))
            && (this.getWorktypecode() == null ? other.getWorktypecode() == null : this.getWorktypecode().equals(other.getWorktypecode()))
            && (this.getUnitcode() == null ? other.getUnitcode() == null : this.getUnitcode().equals(other.getUnitcode()))
            && (this.getUid() == null ? other.getUid() == null : this.getUid().equals(other.getUid()))
            && (this.getReqcontent() == null ? other.getReqcontent() == null : this.getReqcontent().equals(other.getReqcontent()))
            && (this.getCreatetime() == null ? other.getCreatetime() == null : this.getCreatetime().equals(other.getCreatetime()))
            && (this.getStatus() == null ? other.getStatus() == null : this.getStatus().equals(other.getStatus()));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getWorkno() == null) ? 0 : getWorkno().hashCode());
        result = prime * result + ((getWorktypecode() == null) ? 0 : getWorktypecode().hashCode());
        result = prime * result + ((getUnitcode() == null) ? 0 : getUnitcode().hashCode());
        result = prime * result + ((getUid() == null) ? 0 : getUid().hashCode());
        result = prime * result + ((getReqcontent() == null) ? 0 : getReqcontent().hashCode());
        result = prime * result + ((getCreatetime() == null) ? 0 : getCreatetime().hashCode());
        result = prime * result + ((getStatus() == null) ? 0 : getStatus().hashCode());
        return result;
    }
}