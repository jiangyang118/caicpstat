package cn.org.caicp.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cn.org.caicp.dao.ScoreDao;
import cn.org.caicp.po.Score;
import cn.org.caicp.service.ScoreService;
import cn.org.caicp.vo.ScoreVo;

/**
 *
 * @author liman <liman1@catr.cn>
 * @since 2014-4-21 15:59:59
 * @version 1.0.0
 */
@Service("scoreService")
@Transactional
public class ScoreServiceImpl implements ScoreService {

    @Autowired
    ScoreDao scoreDao;
    static Logger logger = Logger.getLogger(ScoreServiceImpl.class);

    @Override
    public List<ScoreVo> getScoreTop(int topCount) {
        List<ScoreVo> result = new ArrayList<ScoreVo>();
        List<Score> pos = scoreDao.findTop();
        if (pos.size() > 10) {
            pos = pos.subList(0, topCount);
        }
        for (Score po : pos) {
            ScoreVo vo = new ScoreVo();
            BeanUtils.copyProperties(po, vo);
            BeanUtils.copyProperties(po.getUser(), vo.getUser());
            result.add(vo);
        }
        return result;
    }

    @Override
    public ScoreVo getScoreByOpenId(String openId) {
        Score po = scoreDao.findByOpenId(openId);
        ScoreVo vo = new ScoreVo();
        BeanUtils.copyProperties(po, vo);
        BeanUtils.copyProperties(po.getUser(), vo.getUser());
        List<Score> list = scoreDao.findMoreScore(po.getScore());
        int rank = 0;
        if (list != null && !list.isEmpty()) {
            rank = list.size();
        }
        vo.setRank(rank + 1);
        return vo;
    }
}
