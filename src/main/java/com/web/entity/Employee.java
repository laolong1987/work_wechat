package com.web.entity;

import javax.persistence.*;
import java.util.Date;

/**
 * Created by gaoyang on 17/8/30.
 */
@Entity
@Table(name = "employee")
public class Employee {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name ="Ygbh")
    private String Ygbh;

    @Column(name ="Ygxm")
    private String Ygxm;

    @Column(name ="Ksrq")
    private String Ksrq;
    @Column(name ="Jsrq")
    private String Jsrq;
    @Column(name ="Rsfw")
    private String Rsfw;
    @Column(name ="Ygz")
    private String Ygz;
    @Column(name ="Ygzz")
    private String Ygzz;
    @Column(name ="Rszfw")
    private String Rszfw;
    @Column(name ="Gzfw")
    private String Gzfw;
    @Column(name ="Zzdwbm")
    private String Zzdwbm;
    @Column(name ="Zzdwmc")
    private String Zzdwmc;
    @Column(name ="createtime")
    private Date createtime;
    @Column(name ="updatetime")
    private Date updatetime;
    @Column(name ="status")
    private Integer status;

    @Column(name ="type")
    private Integer type;

    @Column(name ="Sjhm")
    private String Sjhm;

    @Column(name ="Yx")
    private String Yx;

    @Column(name ="IsLockedOut")
    private String IsLockedOut;

    @Column(name ="PositionName")
    private String PositionName;

    @Column(name ="SortIndex")
    private String SortIndex;

    public String getPositionName() {
        return PositionName;
    }

    public void setPositionName(String positionName) {
        PositionName = positionName;
    }

    public String getSortIndex() {
        return SortIndex;
    }

    public void setSortIndex(String sortIndex) {
        SortIndex = sortIndex;
    }

    public String getIsLockedOut() {
        return IsLockedOut;
    }

    public void setIsLockedOut(String isLockedOut) {
        IsLockedOut = isLockedOut;
    }

    public String getSjhm() {
        return Sjhm;
    }

    public void setSjhm(String sjhm) {
        Sjhm = sjhm;
    }

    public String getYx() {
        return Yx;
    }

    public void setYx(String yx) {
        Yx = yx;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getYgbh() {
        return Ygbh;
    }

    public void setYgbh(String ygbh) {
        Ygbh = ygbh;
    }

    public String getYgxm() {
        return Ygxm;
    }

    public void setYgxm(String ygxm) {
        Ygxm = ygxm;
    }

    public String getKsrq() {
        return Ksrq;
    }

    public void setKsrq(String ksrq) {
        Ksrq = ksrq;
    }

    public String getJsrq() {
        return Jsrq;
    }

    public void setJsrq(String jsrq) {
        Jsrq = jsrq;
    }

    public String getRsfw() {
        return Rsfw;
    }

    public void setRsfw(String rsfw) {
        Rsfw = rsfw;
    }

    public String getYgz() {
        return Ygz;
    }

    public void setYgz(String ygz) {
        Ygz = ygz;
    }

    public String getYgzz() {
        return Ygzz;
    }

    public void setYgzz(String ygzz) {
        Ygzz = ygzz;
    }

    public String getRszfw() {
        return Rszfw;
    }

    public void setRszfw(String rszfw) {
        Rszfw = rszfw;
    }

    public String getGzfw() {
        return Gzfw;
    }

    public void setGzfw(String gzfw) {
        Gzfw = gzfw;
    }

    public String getZzdwbm() {
        return Zzdwbm;
    }

    public void setZzdwbm(String zzdwbm) {
        Zzdwbm = zzdwbm;
    }

    public String getZzdwmc() {
        return Zzdwmc;
    }

    public void setZzdwmc(String zzdwmc) {
        Zzdwmc = zzdwmc;
    }

    public Date getCreatetime() {
        return createtime;
    }

    public void setCreatetime(Date createtime) {
        this.createtime = createtime;
    }

    public Date getUpdatetime() {
        return updatetime;
    }

    public void setUpdatetime(Date updatetime) {
        this.updatetime = updatetime;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }
}

