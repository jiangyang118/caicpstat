package cn.org.caicp.vo;

import java.io.Serializable;
import java.util.Date;

import org.apache.commons.lang3.builder.ReflectionToStringBuilder;

/**
 * 区域
 *
 * @author liman <liman1@catr.cn>
 * @version 1.0.0
 * @since 2014-4-30 14:22:16
 */
public class ScoreVo implements Serializable {

    private Long id;
    private UserWechatVo user = new UserWechatVo();
    private Integer score;
    private int rank;
    private Date createTime;
    private Date updateTime;

    public int getRank() {
        return rank;
    }

    public void setRank(int rank) {
        this.rank = rank;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public UserWechatVo getUser() {
        return user;
    }

    public void setUser(UserWechatVo user) {
        this.user = user;
    }

    public Integer getScore() {
        return score;
    }

    public void setScore(Integer score) {
        this.score = score;
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

    @Override
    public String toString() {
        return ReflectionToStringBuilder.toString(this);
    }
}
