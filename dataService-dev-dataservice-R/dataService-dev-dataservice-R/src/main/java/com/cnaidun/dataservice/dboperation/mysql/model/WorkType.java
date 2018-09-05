package com.cnaidun.dataservice.dboperation.mysql.model;

import java.io.Serializable;

public class WorkType implements Serializable {
    private String worktypecode;

    private String worklevel;

    private String workname;

    private String parent;

    private static final long serialVersionUID = 1L;

    public String getWorktypecode() {
        return worktypecode;
    }

    public void setWorktypecode(String worktypecode) {
        this.worktypecode = worktypecode;
    }

    public String getWorklevel() {
        return worklevel;
    }

    public void setWorklevel(String worklevel) {
        this.worklevel = worklevel;
    }

    public String getWorkname() {
        return workname;
    }

    public void setWorkname(String workname) {
        this.workname = workname;
    }

    public String getParent() {
        return parent;
    }

    public void setParent(String parent) {
        this.parent = parent;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", worktypecode=").append(worktypecode);
        sb.append(", worklevel=").append(worklevel);
        sb.append(", workname=").append(workname);
        sb.append(", parent=").append(parent);
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
        WorkType other = (WorkType) that;
        return (this.getWorktypecode() == null ? other.getWorktypecode() == null : this.getWorktypecode().equals(other.getWorktypecode()))
            && (this.getWorklevel() == null ? other.getWorklevel() == null : this.getWorklevel().equals(other.getWorklevel()))
            && (this.getWorkname() == null ? other.getWorkname() == null : this.getWorkname().equals(other.getWorkname()))
            && (this.getParent() == null ? other.getParent() == null : this.getParent().equals(other.getParent()));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getWorktypecode() == null) ? 0 : getWorktypecode().hashCode());
        result = prime * result + ((getWorklevel() == null) ? 0 : getWorklevel().hashCode());
        result = prime * result + ((getWorkname() == null) ? 0 : getWorkname().hashCode());
        result = prime * result + ((getParent() == null) ? 0 : getParent().hashCode());
        return result;
    }
}