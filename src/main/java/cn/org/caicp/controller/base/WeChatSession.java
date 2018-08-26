package cn.org.caicp.controller.base;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

/**
 *
 * @author liman <liman1@catr.cn>
 */
public class WeChatSession {

    private String openid;
    private String session_key;
    private String unionid;

    public String getOpenid() {
        return openid;
    }

    public void setOpenid(String openid) {
        this.openid = openid;
    }

    public String getSession_key() {
        return session_key;
    }

    public void setSession_key(String session_key) {
        this.session_key = session_key;
    }

    public String getUnionid() {
        return unionid;
    }

    public void setUnionid(String unionid) {
        this.unionid = unionid;
    }

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this, ToStringStyle.SHORT_PREFIX_STYLE);
    }
}
