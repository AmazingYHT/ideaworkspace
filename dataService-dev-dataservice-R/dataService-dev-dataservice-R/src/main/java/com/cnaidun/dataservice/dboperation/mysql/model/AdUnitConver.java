package com.cnaidun.dataservice.dboperation.mysql.model;

import java.io.Serializable;

public class AdUnitConver implements Serializable {
    private String tenCode;

    private String code;

    private String name;

    private String tenName;

    private Integer deleted;

    private static final long serialVersionUID = 1L;

    public String getTenCode() {
        return tenCode;
    }

    public void setTenCode(String tenCode) {
        this.tenCode = tenCode;
    }

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

    public String getTenName() {
        return tenName;
    }

    public void setTenName(String tenName) {
        this.tenName = tenName;
    }

    public Integer getDeleted() {
        return deleted;
    }

    public void setDeleted(Integer deleted) {
        this.deleted = deleted;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", tenCode=").append(tenCode);
        sb.append(", code=").append(code);
        sb.append(", name=").append(name);
        sb.append(", tenName=").append(tenName);
        sb.append(", deleted=").append(deleted);
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
        AdUnitConver other = (AdUnitConver) that;
        return (this.getTenCode() == null ? other.getTenCode() == null : this.getTenCode().equals(other.getTenCode()))
            && (this.getCode() == null ? other.getCode() == null : this.getCode().equals(other.getCode()))
            && (this.getName() == null ? other.getName() == null : this.getName().equals(other.getName()))
            && (this.getTenName() == null ? other.getTenName() == null : this.getTenName().equals(other.getTenName()))
            && (this.getDeleted() == null ? other.getDeleted() == null : this.getDeleted().equals(other.getDeleted()));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getTenCode() == null) ? 0 : getTenCode().hashCode());
        result = prime * result + ((getCode() == null) ? 0 : getCode().hashCode());
        result = prime * result + ((getName() == null) ? 0 : getName().hashCode());
        result = prime * result + ((getTenName() == null) ? 0 : getTenName().hashCode());
        result = prime * result + ((getDeleted() == null) ? 0 : getDeleted().hashCode());
        return result;
    }
}