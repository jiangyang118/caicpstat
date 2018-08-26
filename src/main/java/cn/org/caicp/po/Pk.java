package cn.org.caicp.po;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.apache.commons.lang3.builder.ReflectionToStringBuilder;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

	
@Entity
@Table(name = "pk")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class Pk implements Serializable {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @JoinColumn(name = "user1")
    @ManyToOne
    private UserWechat user1;
    @JoinColumn(name = "user2")
    @ManyToOne
    private UserWechat user2;
    @Column(name = "valid")
    private Boolean valid;
    @Column(name = "create_time")
    @Temporal(TemporalType.TIMESTAMP)
    private Date createTime;
    @Column(name = "update_time")
    @Temporal(TemporalType.TIMESTAMP)
    private Date updateTime;

    @Override
    public String toString() {
        return ReflectionToStringBuilder.toString(this);
    }

    public Boolean getValid() {
        return valid;
    }

    public void setValid(Boolean valid) {
        this.valid = valid;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public UserWechat getUser1() {
        return user1;
    }

    public void setUser1(UserWechat user1) {
        this.user1 = user1;
    }

    public UserWechat getUser2() {
        return user2;
    }

    public void setUser2(UserWechat user2) {
        this.user2 = user2;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

}
