package cn.org.caicp.vo;

import java.io.Serializable;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.apache.commons.lang3.builder.ReflectionToStringBuilder;

/**
 * 区域
 *
 * @author liman <liman1@catr.cn>
 * @version 1.0.0
 * @since 2014-4-30 14:22:16
 */
public class UserWechatVo implements Serializable {

    private String code;
    private String openId;
    private String nickName;
    private String nickNameUTF8;
    private String avatarUrl;
    private String city;
    private String country;
    private Integer gender;
    private String language;
    private String province;
    private Date createTime;
    private Date updateTime;

    public void setNickName(String nickName) {
        this.nickName = nickName;
    }

    public String getNickNameUTF8() {
        return nickNameUTF8;
    }

    public void setNickNameUTF8(String nickNameUTF8) {
        this.nickNameUTF8 = nickNameUTF8;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getOpenId() {
        return openId;
    }

    public void setOpenId(String openId) {
        this.openId = openId;
    }

    public String getNickNameOrigin() {
        return this.nickName;
    }

    public String getNickName() {
        try {
            if (nickNameUTF8 == null) {
                return null;
            }
            return URLDecoder.decode(nickNameUTF8, "utf-8");
        } catch (UnsupportedEncodingException ex) {
            Logger.getLogger(UserWechatVo.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
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

    @Override
    public String toString() {
        return ReflectionToStringBuilder.toString(this);
    }
}
