package cn.org.caicp.dao;

import java.util.List;

import javax.persistence.QueryHint;

import org.springframework.data.jpa.repository.QueryHints;
import org.springframework.stereotype.Repository;

import cn.org.caicp.po.UserWechat;

/**
 *
 * @author liman <liman1@catr.cn>
 * @version 1.0.0
 * @since 2014-4-30 11:21:03
 */
@Repository("userWechatDao")
public interface UserWechatDao extends BaseDao<UserWechat, Long> {

    @QueryHints({ @QueryHint(name = "org.hibernate.cacheable", value = "true") })
    @Override
    List<UserWechat> findAll();

    @QueryHints({ @QueryHint(name = "org.hibernate.cacheable", value = "true") })
    UserWechat findByOpenId(String openid);

}
