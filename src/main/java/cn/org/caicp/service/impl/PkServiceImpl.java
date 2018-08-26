package cn.org.caicp.service.impl;

import java.util.Date;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cn.org.caicp.dao.PkDao;
import cn.org.caicp.dao.PkResultDao;
import cn.org.caicp.dao.ScoreDao;
import cn.org.caicp.dao.UserWechatDao;
import cn.org.caicp.po.Pk;
import cn.org.caicp.po.PkResult;
import cn.org.caicp.po.Score;
import cn.org.caicp.po.UserWechat;
import cn.org.caicp.service.PkService;
import cn.org.caicp.vo.DangConstant;
import cn.org.caicp.vo.PkResultVo;
import cn.org.caicp.vo.PkVo;

/**
 *
 * @author liman <liman1@catr.cn>
 * @since 2014-4-21 15:59:59
 * @version 1.0.0
 */
@Service("pkService")
@Transactional
public class PkServiceImpl implements PkService {
    
    @Autowired
    PkDao pkDao;
    @Autowired	
    PkResultDao pkResultDao;
    @Autowired
    UserWechatDao userWechatDao;
    @Autowired
    ScoreDao scoreDao;
    static Logger logger = Logger.getLogger(PkServiceImpl.class);
    
    @Override
    public PkVo matchPk(String openId, Long pkId) {
        logger.info(openId + ":" + pkId);
        PkVo result = new PkVo();
        //如果不是第一次访问这个方法
        if (pkId != null) {
            Pk po = pkDao.findOne(pkId) ;
            if (po != null && openId.equals(po.getUser1().getOpenId())) { //被匹配到，则返回pk的详细信息
                BeanUtils.copyProperties(po, result);
                if (po.getUser2() != null) {
                    BeanUtils.copyProperties(po.getUser2(), result.getOpponent());
                }
            }
            return result;
        }
        boolean findMatch = false;
        UserWechat user = userWechatDao.findByOpenId(openId);
        List<Pk> pos = pkDao.findWaitMatched();
        for (Pk po : pos) {
            long interval = (new Date().getTime() - po.getCreateTime().getTime()) / 1000;
            //如果超时，直接设为无效，并继续查找
            if (interval > DangConstant.TIMEOUT_WAIT_MATCHED) {
                po.setValid(Boolean.FALSE);
                po.setUpdateTime(new Date());
                pkDao.save(po);
                continue;
            }
            //如果已添加过了，则直接返回
            if (openId.equals(po.getUser1().getOpenId())) {
                BeanUtils.copyProperties(po, result);
                if (po.getUser2() != null) {
                    BeanUtils.copyProperties(po.getUser2(), result.getOpponent());
                }
                return result;
            }
            //找到正在等待的对手
            po.setUser2(user);
            pkDao.save(po);
            
            PkResult pkResult1 = new PkResult();
            pkResult1.setUser(po.getUser1());
            pkResult1.setCreateTime(new Date());
            pkResult1.setUpdateTime(pkResult1.getCreateTime());
            pkResult1.setFinished(false);
            pkResult1.setPk(po);
            pkResult1.setScore(0);
            pkResult1.setRightNum(0);
            pkResultDao.save(pkResult1);
            
            PkResult pkResult2 = new PkResult();
            pkResult2.setUser(po.getUser2());
            pkResult2.setCreateTime(new Date());
            pkResult2.setUpdateTime(pkResult1.getCreateTime());
            pkResult2.setFinished(false);
            pkResult2.setPk(po);
            pkResult2.setScore(0);
            pkResult2.setRightNum(0);
            pkResultDao.save(pkResult2);
            
            BeanUtils.copyProperties(po, result);
            BeanUtils.copyProperties(po.getUser1(), result.getOpponent());
            return result;
        }
        //如果没有匹配到已有的用户，则新建一个Pk，并循环等待被别人选中匹配
        if (!findMatch) {
            Pk po = new Pk();
            po.setUser1(user);
            po.setValid(true);
            po.setCreateTime(new Date());
            po.setUpdateTime(po.getCreateTime());
            pkDao.save(po);
            BeanUtils.copyProperties(po, result);
        }
        return result;
    }
    
    @Override
    public PkResultVo updatePkResult(PkResultVo pkResultVo) {
        PkResultVo result = new PkResultVo();
        List<PkResult> pos = pkResultDao.findByPkIdAndOpenId(pkResultVo.getPkId(), pkResultVo.getOpenId());
        PkResult po;
        if (pos == null || pos.isEmpty()) {
            po = new PkResult();
            po.setPk(pkDao.findOne(pkResultVo.getPkId()) );
            po.setUser(userWechatDao.findByOpenId(pkResultVo.getOpenId()));
            po.setCreateTime(new Date());
            po.setFinished(Boolean.FALSE);
        } else {
            po = pos.get(0);
        }
        if (pkResultVo.getFinished() && !po.getFinished()) {
            Score score = scoreDao.findByOpenId(pkResultVo.getOpenId());
            score.setScore(score.getScore() + po.getScore());
            scoreDao.save(score);
        }
        if (!po.getFinished()) {
            po.setFinished(pkResultVo.getFinished());
            po.setRightNum(pkResultVo.getRightNum());
            po.setScore(pkResultVo.getScore());
            po.setUpdateTime(new Date());
            pkResultDao.save(po);
        }
        
        List<PkResult> list = pkResultDao.findByPkIdAndNotOpenId(pkResultVo.getPkId(), pkResultVo.getOpenId());
        if (list == null || list.isEmpty()) {
            result.setScore(0);
            return result;
        }
        BeanUtils.copyProperties(list.get(0), result);
        return result;
    }
    
    @Override
    public PkResultVo getOpponentFinalResult(Long pkId, String openId) {
        PkResultVo result = new PkResultVo();
        List<PkResult> list = pkResultDao.findByPkIdAndNotOpenId(pkId, openId);
        if (list != null && !list.isEmpty()) {
            PkResult opponent = list.get(0);
            BeanUtils.copyProperties(opponent, result);
            return result;
        }
        return result;
    }
    
    @Override
    public PkVo checkMatchPk(Long pkId) {
        //返回null则认为匹配异常，如果user2！=null则认为匹配正常，如果返回值user2=null则认为要继续等待
        PkVo result = null;
        Pk po = pkDao.findOne(pkId) ;
        if (po == null) {
            return result;
        }
        //被匹配到，则返回pk的详细信息
        if (po.getUser2() != null) {
            result = new PkVo();
            BeanUtils.copyProperties(po, result);
            BeanUtils.copyProperties(po.getUser2(), result.getOpponent());
            return result;
        }
        long interval = (new Date().getTime() - po.getCreateTime().getTime()) / 1000;
        //等待超时，无需继续等待
        if (interval > DangConstant.TIMEOUT_WAIT_MATCHED) {
            po.setValid(Boolean.FALSE);
            po.setUpdateTime(new Date());
            pkDao.save(po);
            return result;
        }
        //未被匹配到，需要继续等待
        result = new PkVo();
        BeanUtils.copyProperties(po, result);
        return result;
    }
    
}
