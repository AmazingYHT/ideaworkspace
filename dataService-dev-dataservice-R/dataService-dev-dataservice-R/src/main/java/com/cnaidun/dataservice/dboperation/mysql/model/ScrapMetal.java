package com.cnaidun.dataservice.dboperation.mysql.model;

import java.io.Serializable;

public class ScrapMetal implements Serializable {
    private String workno;

    private String worktypecode;

    private String unitcode;

    private String parentcode;

    private Integer type;

    private Integer status;

    private String uid;

    private String chargename;

    private String mobile;

    private String handleoffice;

    private String servicemode;

    private String serviceadress;

    private String unitname;

    private String creditcode;

    private String corporationname;

    private String corporationmobile;

    private String corporationid;

    private String operatorname;

    private String operatormobile;

    private String operatoridno;

    private String businesslicence;

    private String corporationidno;

    private String scrapmetalregistrationtable;

    private String personnelregistrationtable;

    private String shopfacadephotos;

    private String houselease;

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

    public String getParentcode() {
        return parentcode;
    }

    public void setParentcode(String parentcode) {
        this.parentcode = parentcode;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public String getChargename() {
        return chargename;
    }

    public void setChargename(String chargename) {
        this.chargename = chargename;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getHandleoffice() {
        return handleoffice;
    }

    public void setHandleoffice(String handleoffice) {
        this.handleoffice = handleoffice;
    }

    public String getServicemode() {
        return servicemode;
    }

    public void setServicemode(String servicemode) {
        this.servicemode = servicemode;
    }

    public String getServiceadress() {
        return serviceadress;
    }

    public void setServiceadress(String serviceadress) {
        this.serviceadress = serviceadress;
    }

    public String getUnitname() {
        return unitname;
    }

    public void setUnitname(String unitname) {
        this.unitname = unitname;
    }

    public String getCreditcode() {
        return creditcode;
    }

    public void setCreditcode(String creditcode) {
        this.creditcode = creditcode;
    }

    public String getCorporationname() {
        return corporationname;
    }

    public void setCorporationname(String corporationname) {
        this.corporationname = corporationname;
    }

    public String getCorporationmobile() {
        return corporationmobile;
    }

    public void setCorporationmobile(String corporationmobile) {
        this.corporationmobile = corporationmobile;
    }

    public String getCorporationid() {
        return corporationid;
    }

    public void setCorporationid(String corporationid) {
        this.corporationid = corporationid;
    }

    public String getOperatorname() {
        return operatorname;
    }

    public void setOperatorname(String operatorname) {
        this.operatorname = operatorname;
    }

    public String getOperatormobile() {
        return operatormobile;
    }

    public void setOperatormobile(String operatormobile) {
        this.operatormobile = operatormobile;
    }

    public String getOperatoridno() {
        return operatoridno;
    }

    public void setOperatoridno(String operatoridno) {
        this.operatoridno = operatoridno;
    }

    public String getBusinesslicence() {
        return businesslicence;
    }

    public void setBusinesslicence(String businesslicence) {
        this.businesslicence = businesslicence;
    }

    public String getCorporationidno() {
        return corporationidno;
    }

    public void setCorporationidno(String corporationidno) {
        this.corporationidno = corporationidno;
    }

    public String getScrapmetalregistrationtable() {
        return scrapmetalregistrationtable;
    }

    public void setScrapmetalregistrationtable(String scrapmetalregistrationtable) {
        this.scrapmetalregistrationtable = scrapmetalregistrationtable;
    }

    public String getPersonnelregistrationtable() {
        return personnelregistrationtable;
    }

    public void setPersonnelregistrationtable(String personnelregistrationtable) {
        this.personnelregistrationtable = personnelregistrationtable;
    }

    public String getShopfacadephotos() {
        return shopfacadephotos;
    }

    public void setShopfacadephotos(String shopfacadephotos) {
        this.shopfacadephotos = shopfacadephotos;
    }

    public String getHouselease() {
        return houselease;
    }

    public void setHouselease(String houselease) {
        this.houselease = houselease;
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
        sb.append(", parentcode=").append(parentcode);
        sb.append(", type=").append(type);
        sb.append(", status=").append(status);
        sb.append(", uid=").append(uid);
        sb.append(", chargename=").append(chargename);
        sb.append(", mobile=").append(mobile);
        sb.append(", handleoffice=").append(handleoffice);
        sb.append(", servicemode=").append(servicemode);
        sb.append(", serviceadress=").append(serviceadress);
        sb.append(", unitname=").append(unitname);
        sb.append(", creditcode=").append(creditcode);
        sb.append(", corporationname=").append(corporationname);
        sb.append(", corporationmobile=").append(corporationmobile);
        sb.append(", corporationid=").append(corporationid);
        sb.append(", operatorname=").append(operatorname);
        sb.append(", operatormobile=").append(operatormobile);
        sb.append(", operatoridno=").append(operatoridno);
        sb.append(", businesslicence=").append(businesslicence);
        sb.append(", corporationidno=").append(corporationidno);
        sb.append(", scrapmetalregistrationtable=").append(scrapmetalregistrationtable);
        sb.append(", personnelregistrationtable=").append(personnelregistrationtable);
        sb.append(", shopfacadephotos=").append(shopfacadephotos);
        sb.append(", houselease=").append(houselease);
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
        ScrapMetal other = (ScrapMetal) that;
        return (this.getWorkno() == null ? other.getWorkno() == null : this.getWorkno().equals(other.getWorkno()))
            && (this.getWorktypecode() == null ? other.getWorktypecode() == null : this.getWorktypecode().equals(other.getWorktypecode()))
            && (this.getUnitcode() == null ? other.getUnitcode() == null : this.getUnitcode().equals(other.getUnitcode()))
            && (this.getParentcode() == null ? other.getParentcode() == null : this.getParentcode().equals(other.getParentcode()))
            && (this.getType() == null ? other.getType() == null : this.getType().equals(other.getType()))
            && (this.getStatus() == null ? other.getStatus() == null : this.getStatus().equals(other.getStatus()))
            && (this.getUid() == null ? other.getUid() == null : this.getUid().equals(other.getUid()))
            && (this.getChargename() == null ? other.getChargename() == null : this.getChargename().equals(other.getChargename()))
            && (this.getMobile() == null ? other.getMobile() == null : this.getMobile().equals(other.getMobile()))
            && (this.getHandleoffice() == null ? other.getHandleoffice() == null : this.getHandleoffice().equals(other.getHandleoffice()))
            && (this.getServicemode() == null ? other.getServicemode() == null : this.getServicemode().equals(other.getServicemode()))
            && (this.getServiceadress() == null ? other.getServiceadress() == null : this.getServiceadress().equals(other.getServiceadress()))
            && (this.getUnitname() == null ? other.getUnitname() == null : this.getUnitname().equals(other.getUnitname()))
            && (this.getCreditcode() == null ? other.getCreditcode() == null : this.getCreditcode().equals(other.getCreditcode()))
            && (this.getCorporationname() == null ? other.getCorporationname() == null : this.getCorporationname().equals(other.getCorporationname()))
            && (this.getCorporationmobile() == null ? other.getCorporationmobile() == null : this.getCorporationmobile().equals(other.getCorporationmobile()))
            && (this.getCorporationid() == null ? other.getCorporationid() == null : this.getCorporationid().equals(other.getCorporationid()))
            && (this.getOperatorname() == null ? other.getOperatorname() == null : this.getOperatorname().equals(other.getOperatorname()))
            && (this.getOperatormobile() == null ? other.getOperatormobile() == null : this.getOperatormobile().equals(other.getOperatormobile()))
            && (this.getOperatoridno() == null ? other.getOperatoridno() == null : this.getOperatoridno().equals(other.getOperatoridno()))
            && (this.getBusinesslicence() == null ? other.getBusinesslicence() == null : this.getBusinesslicence().equals(other.getBusinesslicence()))
            && (this.getCorporationidno() == null ? other.getCorporationidno() == null : this.getCorporationidno().equals(other.getCorporationidno()))
            && (this.getScrapmetalregistrationtable() == null ? other.getScrapmetalregistrationtable() == null : this.getScrapmetalregistrationtable().equals(other.getScrapmetalregistrationtable()))
            && (this.getPersonnelregistrationtable() == null ? other.getPersonnelregistrationtable() == null : this.getPersonnelregistrationtable().equals(other.getPersonnelregistrationtable()))
            && (this.getShopfacadephotos() == null ? other.getShopfacadephotos() == null : this.getShopfacadephotos().equals(other.getShopfacadephotos()))
            && (this.getHouselease() == null ? other.getHouselease() == null : this.getHouselease().equals(other.getHouselease()));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getWorkno() == null) ? 0 : getWorkno().hashCode());
        result = prime * result + ((getWorktypecode() == null) ? 0 : getWorktypecode().hashCode());
        result = prime * result + ((getUnitcode() == null) ? 0 : getUnitcode().hashCode());
        result = prime * result + ((getParentcode() == null) ? 0 : getParentcode().hashCode());
        result = prime * result + ((getType() == null) ? 0 : getType().hashCode());
        result = prime * result + ((getStatus() == null) ? 0 : getStatus().hashCode());
        result = prime * result + ((getUid() == null) ? 0 : getUid().hashCode());
        result = prime * result + ((getChargename() == null) ? 0 : getChargename().hashCode());
        result = prime * result + ((getMobile() == null) ? 0 : getMobile().hashCode());
        result = prime * result + ((getHandleoffice() == null) ? 0 : getHandleoffice().hashCode());
        result = prime * result + ((getServicemode() == null) ? 0 : getServicemode().hashCode());
        result = prime * result + ((getServiceadress() == null) ? 0 : getServiceadress().hashCode());
        result = prime * result + ((getUnitname() == null) ? 0 : getUnitname().hashCode());
        result = prime * result + ((getCreditcode() == null) ? 0 : getCreditcode().hashCode());
        result = prime * result + ((getCorporationname() == null) ? 0 : getCorporationname().hashCode());
        result = prime * result + ((getCorporationmobile() == null) ? 0 : getCorporationmobile().hashCode());
        result = prime * result + ((getCorporationid() == null) ? 0 : getCorporationid().hashCode());
        result = prime * result + ((getOperatorname() == null) ? 0 : getOperatorname().hashCode());
        result = prime * result + ((getOperatormobile() == null) ? 0 : getOperatormobile().hashCode());
        result = prime * result + ((getOperatoridno() == null) ? 0 : getOperatoridno().hashCode());
        result = prime * result + ((getBusinesslicence() == null) ? 0 : getBusinesslicence().hashCode());
        result = prime * result + ((getCorporationidno() == null) ? 0 : getCorporationidno().hashCode());
        result = prime * result + ((getScrapmetalregistrationtable() == null) ? 0 : getScrapmetalregistrationtable().hashCode());
        result = prime * result + ((getPersonnelregistrationtable() == null) ? 0 : getPersonnelregistrationtable().hashCode());
        result = prime * result + ((getShopfacadephotos() == null) ? 0 : getShopfacadephotos().hashCode());
        result = prime * result + ((getHouselease() == null) ? 0 : getHouselease().hashCode());
        return result;
    }
}