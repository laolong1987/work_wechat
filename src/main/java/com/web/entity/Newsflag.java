package com.web.entity;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "newsflag")
public class Newsflag {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name ="readname")
    private String readname;

    @Column(name ="readid")
    private String readid;

    @Column(name ="newsid")
    private int newsid;

    @Column(name ="createtime")
    private Date createtime;

    @Column(name ="updatetime")
    private Date updatetime;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getReadname() {
        return readname;
    }

    public void setReadname(String readname) {
        this.readname = readname;
    }

    public String getReadid() {
        return readid;
    }

    public void setReadid(String readid) {
        this.readid = readid;
    }

    public int getNewsid() {
        return newsid;
    }

    public void setNewsid(int newsid) {
        this.newsid = newsid;
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
}
