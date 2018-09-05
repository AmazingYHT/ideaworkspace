package com.cnaidun.dataservice.dboperation.mysql.model;

import java.io.Serializable;
import java.util.Date;

public class WeixinSend implements Serializable {
    private String wid;

    private String idno;

    private String mobile;

    private Date startday;

    private Date endday;

    private Integer wsend;

    private Integer dsend;

    private Date createtime;

    private static final long serialVersionUID = 1L;

    public String getWid() {
        return wid;
    }

    public void setWid(String wid) {
        this.wid = wid;
    }

    public String getIdno() {
        return idno;
    }

    public void setIdno(String idno) {
        this.idno = idno;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public Date getStartday() {
        return startday;
    }

    public void setStartday(Date startday) {
        this.startday = startday;
    }

    public Date getEndday() {
        return endday;
    }

    public void setEndday(Date endday) {
        this.endday = endday;
    }

    public Integer getWsend() {
        return wsend;
    }

    public void setWsend(Integer wsend) {
        this.wsend = wsend;
    }

    public Integer getDsend() {
        return dsend;
    }

    public void setDsend(Integer dsend) {
        this.dsend = dsend;
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
        sb.append(", wid=").append(wid);
        sb.append(", idno=").append(idno);
        sb.append(", mobile=").append(mobile);
        sb.append(", startday=").append(startday);
        sb.append(", endday=").append(endday);
        sb.append(", wsend=").append(wsend);
        sb.append(", dsend=").append(dsend);
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
        WeixinSend other = (WeixinSend) that;
        return (this.getWid() == null ? other.getWid() == null : this.getWid().equals(other.getWid()))
            && (this.getIdno() == null ? other.getIdno() == null : this.getIdno().equals(other.getIdno()))
            && (this.getMobile() == null ? other.getMobile() == null : this.getMobile().equals(other.getMobile()))
            && (this.getStartday() == null ? other.getStartday() == null : this.getStartday().equals(other.getStartday()))
            && (this.getEndday() == null ? other.getEndday() == null : this.getEndday().equals(other.getEndday()))
            && (this.getWsend() == null ? other.getWsend() == null : this.getWsend().equals(other.getWsend()))
            && (this.getDsend() == null ? other.getDsend() == null : this.getDsend().equals(other.getDsend()))
            && (this.getCreatetime() == null ? other.getCreatetime() == null : this.getCreatetime().equals(other.getCreatetime()));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getWid() == null) ? 0 : getWid().hashCode());
        result = prime * result + ((getIdno() == null) ? 0 : getIdno().hashCode());
        result = prime * result + ((getMobile() == null) ? 0 : getMobile().hashCode());
        result = prime * result + ((getStartday() == null) ? 0 : getStartday().hashCode());
        result = prime * result + ((getEndday() == null) ? 0 : getEndday().hashCode());
        result = prime * result + ((getWsend() == null) ? 0 : getWsend().hashCode());
        result = prime * result + ((getDsend() == null) ? 0 : getDsend().hashCode());
        result = prime * result + ((getCreatetime() == null) ? 0 : getCreatetime().hashCode());
        return result;
    }
}