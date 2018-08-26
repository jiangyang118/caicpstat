package cn.org.caicp.po;

import java.io.Serializable;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.apache.commons.lang3.builder.ReflectionToStringBuilder;

/**
 * 用户表
 *
 * @author liman <liman1@catr.cn>
 * @since 2014-4-22 15:18:25
 * @version 1.0.0
 */
@Entity
@Table(name = "user_wechat")
public class UserWechat implements Serializable {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "openId")
    private String openId;
    @Column(name = "nickName")
    private String nickNameUTF8;
    @Column(name = "avatarUrl")
    private String avatarUrl;
    @Column(name = "city")
    private String city;
    @Column(name = "country")
    private String country;
    @Column(name = "gender")
    private Integer gender;
    @Column(name = "language")
    private String language;
    @Column(name = "province")
    private String province;

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

    public String getNickName() {
        try {
            return URLDecoder.decode(nickNameUTF8, "utf-8");
        } catch (UnsupportedEncodingException ex) {
            Logger.getLogger(UserWechat.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getOpenId() {
        return openId;
    }

    public void setOpenId(String openId) {
        this.openId = openId;
    }

    public String getNickNameUTF8() {
        return nickNameUTF8;
    }

    public void setNickNameUTF8(String nickNameUTF8) {
        this.nickNameUTF8 = nickNameUTF8;
    }

    public String getAvatarUrl() {
        return avatarUrl;
    }

    public void setAvatarUrl(String avatarUrl) {
        this.avatarUrl = avatarUrl;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public Integer getGender() {
        return gender;
    }

    public void setGender(Integer gender) {
        this.gender = gender;
    }

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }

    public String getProvince() {
        return province;
    }

    public void setProvince(String province) {
        this.province = province;
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
