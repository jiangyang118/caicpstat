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

@Entity
@Table(name = "pk_result")
public class PkResult implements Serializable {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @JoinColumn(name = "pk_id")
    @ManyToOne
    private Pk pk;
    @JoinColumn(name = "user")
    @ManyToOne
    private UserWechat user;
    @Column(name = "score")
    private Integer score;
    @Column(name = "rightNum")
    private Integer rightNum;
    @Column(name = "finished")
    private Boolean finished;
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

    public Boolean getFinished() {
        return finished;
    }

    public void setFinished(Boolean finished) {
        this.finished = finished;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Pk getPk() {
        return pk;
    }

    public void setPk(Pk pk) {
        this.pk = pk;
    }

    public UserWechat getUser() {
        return user;
    }

    public void setUser(UserWechat user) {
        this.user = user;
    }

    public Integer getScore() {
        return score;
    }

    public void setScore(Integer score) {
        this.score = score;
    }

    public Integer getRightNum() {
        return rightNum;
    }

    public void setRightNum(Integer rightNum) {
        this.rightNum = rightNum;
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
