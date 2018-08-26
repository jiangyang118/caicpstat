package cn.org.caicp.service;

import cn.org.caicp.vo.UserWechatVo;

/**
 *
 * @author liman <liman1@catr.cn>
 * @since 2014-4-21 15:57:02
 * @version 1.0.0
 */
public interface UserWechatService {

    /**
     * 保存用户信息
     *
     * @return
     */
    String createOrUpdate(UserWechatVo user);

    /**
     * 通过openId获取用户信息
     *
     * @param openId
     * @return
     */
    UserWechatVo getUserWechat(String openId);

    /**
     * 通过openId获取用户信息
     *
     * @param openId
     * @return
     */
    UserWechatVo getUserWechatById(Long id);

}
