package cn.org.caicp.service;

import java.util.List;

import cn.org.caicp.vo.ScoreVo;

/**
 *
 * @author liman <liman1@catr.cn>
 * @since 2014-4-21 15:57:02
 * @version 1.0.0
 */
public interface ScoreService {

    /**
     * 获取排名TOPN
     *
     * @param topCount
     * @return
     */
    List<ScoreVo> getScoreTop(int topCount);

    /**
     * 根据openId获取积分信息
     *
     * @param openId
     * @return
     */
    ScoreVo getScoreByOpenId(String openId);

}
