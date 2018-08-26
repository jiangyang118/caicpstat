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
public class PkVo implements Serializable {

    private Long id;
    private UserWechatVo user1 = new UserWechatVo();
    private UserWechatVo user2 = new UserWechatVo();
    private UserWechatVo opponent = new UserWechatVo();
    private Boolean valid;
    private Date createTime;
    private Date updateTime;

    public UserWechatVo getOpponent() {
        return opponent;
    }

    public void setOpponent(UserWechatVo opponent) {
        this.opponent = opponent;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public UserWechatVo getUser1() {
        return user1;
    }

    public void setUser1(UserWechatVo user1) {
        this.user1 = user1;
    }

    public UserWechatVo getUser2() {
        return user2;
    }

    public void setUser2(UserWechatVo user2) {
        this.user2 = user2;
    }

    public Boolean getValid() {
        return valid;
    }

    public void setValid(Boolean valid) {
        this.valid = valid;
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
