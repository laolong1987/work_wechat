package com.web.entity;

import javax.persistence.*;

@Entity
@Table(name = "Dept")
public class Dept {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name ="DeptID")
    private String DeptID;

    @Column(name ="DeptName")
    private String DeptName;

    @Column(name ="SortIndex")
    private String SortIndex;

    @Column(name ="UpDeptID")
    private String UpDeptID;

    @Column(name ="status")
    private Integer status;

    @Column(name ="type")
    private Integer type;

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
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

    public String getDeptID() {
        return DeptID;
    }

    public void setDeptID(String deptID) {
        DeptID = deptID;
    }

    public String getDeptName() {
        return DeptName;
    }

    public void setDeptName(String deptName) {
        DeptName = deptName;
    }

    public String getSortIndex() {
        return SortIndex;
    }

    public void setSortIndex(String sortIndex) {
        SortIndex = sortIndex;
    }

    public String getUpDeptID() {
        return UpDeptID;
    }

    public void setUpDeptID(String upDeptID) {
        UpDeptID = upDeptID;
    }
}
