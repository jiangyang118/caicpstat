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
public class PkResultVo implements Serializable {

    private Long id;
    private Long pkId;
    private String openId;
    private Integer score;
    private Integer rightNum;
    private Boolean finished;
    private Date createTime;
    private Date updateTime;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getPkId() {
        return pkId;
    }

    public void setPkId(Long pkId) {
        this.pkId = pkId;
    }

    public String getOpenId() {
        return openId;
    }

    public void setOpenId(String openId) {
        this.openId = openId;
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

    public Boolean getFinished() {
        return finished;
    }

    public void setFinished(Boolean finished) {
        this.finished = finished;
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
