package cn.org.caicp.service.impl;

import java.util.Date;

import org.apache.log4j.Logger;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cn.org.caicp.dao.ScoreDao;
import cn.org.caicp.dao.UserWechatDao;
import cn.org.caicp.po.Score;
import cn.org.caicp.po.UserWechat;
import cn.org.caicp.service.UserWechatService;
import cn.org.caicp.vo.UserWechatVo;

/**
 *
 * @author liman <liman1@catr.cn>
 * @since 2014-4-21 15:59:59
 * @version 1.0.0
 */
@Service("userWechatService")
@Transactional
public class UserWechatServiceImpl implements UserWechatService {

    @Autowired
    UserWechatDao userWechatDao;
    @Autowired
    ScoreDao scoreDao;
    static Logger logger = Logger.getLogger(UserWechatServiceImpl.class);

    @Override
    public String createOrUpdate(UserWechatVo user) {
        String result = null;
        UserWechat po = userWechatDao.findByOpenId(user.getOpenId());
        if (po == null) {
            po = new UserWechat();
            BeanUtils.copyProperties(user, po);
            po.setCreateTime(new Date());
            po.setUpdateTime(po.getCreateTime());
            po = userWechatDao.save(po);
            Score score = new Score();
            score.setUser(po);
            score.setCreateTime(new Date());
            score.setUpdateTime(score.getCreateTime());
            score.setScore(0);
            scoreDao.save(score);
        } else {
            BeanUtils.copyProperties(user, po, "createTime");
            po.setUpdateTime(new Date());
            userWechatDao.save(po);

        }
        return result;
    }

    @Override
    public UserWechatVo getUserWechat(String openId) {
        UserWechatVo result = new UserWechatVo();
        UserWechat po = userWechatDao.findByOpenId(openId);
        BeanUtils.copyProperties(po, result);
        return result;
    }

    @Override
    public UserWechatVo getUserWechatById(Long id) {
        UserWechatVo result = new UserWechatVo();
        UserWechat po = userWechatDao.findOne(id);
        BeanUtils.copyProperties(po, result);
        return result;
    }

}
