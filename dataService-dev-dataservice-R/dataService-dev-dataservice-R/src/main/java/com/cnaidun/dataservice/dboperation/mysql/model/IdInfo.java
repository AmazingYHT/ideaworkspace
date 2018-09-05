package com.cnaidun.dataservice.dboperation.mysql.model;

import java.io.Serializable;
import java.util.Date;

public class IdInfo implements Serializable {
    private String uid;

    private String idno;

    private String name;

    private Date birthday;

    private String sex;

    private String nation;

    private String citycode;

    private String address;

    private Date createtime;

    private String validdate;

    private String authority;

    private String phone;

    private String idpositive;

    private String idopposite;

    private static final long serialVersionUID = 1L;

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public String getIdno() {
        return idno;
    }

    public void setIdno(String idno) {
        this.idno = idno;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Date getBirthday() {
        return birthday;
    }

    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public String getNation() {
        return nation;
    }

    public void setNation(String nation) {
        this.nation = nation;
    }

    public String getCitycode() {
        return citycode;
    }

    public void setCitycode(String citycode) {
        this.citycode = citycode;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Date getCreatetime() {
        return createtime;
    }

    public void setCreatetime(Date createtime) {
        this.createtime = createtime;
    }

    public String getValiddate() {
        return validdate;
    }

    public void setValiddate(String validdate) {
        this.validdate = validdate;
    }

    public String getAuthority() {
        return authority;
    }

    public void setAuthority(String authority) {
        this.authority = authority;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getIdpositive() {
        return idpositive;
    }

    public void setIdpositive(String idpositive) {
        this.idpositive = idpositive;
    }

    public String getIdopposite() {
        return idopposite;
    }

    public void setIdopposite(String idopposite) {
        this.idopposite = idopposite;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", uid=").append(uid);
        sb.append(", idno=").append(idno);
        sb.append(", name=").append(name);
        sb.append(", birthday=").append(birthday);
        sb.append(", sex=").append(sex);
        sb.append(", nation=").append(nation);
        sb.append(", citycode=").append(citycode);
        sb.append(", address=").append(address);
        sb.append(", createtime=").append(createtime);
        sb.append(", validdate=").append(validdate);
        sb.append(", authority=").append(authority);
        sb.append(", phone=").append(phone);
        sb.append(", idpositive=").append(idpositive);
        sb.append(", idopposite=").append(idopposite);
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
        IdInfo other = (IdInfo) that;
        return (this.getUid() == null ? other.getUid() == null : this.getUid().equals(other.getUid()))
            && (this.getIdno() == null ? other.getIdno() == null : this.getIdno().equals(other.getIdno()))
            && (this.getName() == null ? other.getName() == null : this.getName().equals(other.getName()))
            && (this.getBirthday() == null ? other.getBirthday() == null : this.getBirthday().equals(other.getBirthday()))
            && (this.getSex() == null ? other.getSex() == null : this.getSex().equals(other.getSex()))
            && (this.getNation() == null ? other.getNation() == null : this.getNation().equals(other.getNation()))
            && (this.getCitycode() == null ? other.getCitycode() == null : this.getCitycode().equals(other.getCitycode()))
            && (this.getAddress() == null ? other.getAddress() == null : this.getAddress().equals(other.getAddress()))
            && (this.getCreatetime() == null ? other.getCreatetime() == null : this.getCreatetime().equals(other.getCreatetime()))
            && (this.getValiddate() == null ? other.getValiddate() == null : this.getValiddate().equals(other.getValiddate()))
            && (this.getAuthority() == null ? other.getAuthority() == null : this.getAuthority().equals(other.getAuthority()))
            && (this.getPhone() == null ? other.getPhone() == null : this.getPhone().equals(other.getPhone()))
            && (this.getIdpositive() == null ? other.getIdpositive() == null : this.getIdpositive().equals(other.getIdpositive()))
            && (this.getIdopposite() == null ? other.getIdopposite() == null : this.getIdopposite().equals(other.getIdopposite()));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getUid() == null) ? 0 : getUid().hashCode());
        result = prime * result + ((getIdno() == null) ? 0 : getIdno().hashCode());
        result = prime * result + ((getName() == null) ? 0 : getName().hashCode());
        result = prime * result + ((getBirthday() == null) ? 0 : getBirthday().hashCode());
        result = prime * result + ((getSex() == null) ? 0 : getSex().hashCode());
        result = prime * result + ((getNation() == null) ? 0 : getNation().hashCode());
        result = prime * result + ((getCitycode() == null) ? 0 : getCitycode().hashCode());
        result = prime * result + ((getAddress() == null) ? 0 : getAddress().hashCode());
        result = prime * result + ((getCreatetime() == null) ? 0 : getCreatetime().hashCode());
        result = prime * result + ((getValiddate() == null) ? 0 : getValiddate().hashCode());
        result = prime * result + ((getAuthority() == null) ? 0 : getAuthority().hashCode());
        result = prime * result + ((getPhone() == null) ? 0 : getPhone().hashCode());
        result = prime * result + ((getIdpositive() == null) ? 0 : getIdpositive().hashCode());
        result = prime * result + ((getIdopposite() == null) ? 0 : getIdopposite().hashCode());
        return result;
    }
}