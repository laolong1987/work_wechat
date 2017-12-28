package com.web.entity;

/**
 * 微信通用接口凭证
 *
 * @author zhanglei
 */

import java.util.Date;
import javax.persistence.*;

@Entity
@Table(name = "token")
public class Token {

    @Id
    @Basic(optional = false)
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "agentId")
    private String agentId;

    @Column(name = "name")
    private String name;

    @Column(name = "token")
    private String token;

    @Column(name = "createtime")
    private Date createtime;

    @Column(name = "time")
    private int time;

    @Column(name = "updatetime")
    private Date updatetime;

    public void setId(int id) {
        this.id = id;
    }

    public int getId() {
        return this.id;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public void setCreatetime(Date createtime) {
        this.createtime = createtime;
    }

    public Date getCreatetime() {
        return this.createtime;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getTime() {
        return time;
    }

    public void setTime(int time) {
        this.time = time;
    }

    public Date getUpdatetime() {
        return updatetime;
    }

    public void setUpdatetime(Date updatetime) {
        this.updatetime = updatetime;
    }

}
