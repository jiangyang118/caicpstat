package cn.org.caicp.dao;

import java.util.List;

import javax.persistence.QueryHint;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.QueryHints;
import org.springframework.stereotype.Repository;

import cn.org.caicp.po.Score;

/**
 *
 * @author liman <liman1@catr.cn>
 * @version 1.0.0
 * @since 2014-4-30 11:21:03
 */
@Repository("scoreDao")
public interface ScoreDao extends BaseDao<Score, Long> {

    @QueryHints({ @QueryHint(name = "org.hibernate.cacheable", value = "true") })
    @Override
    List<Score> findAll();

    @QueryHints({ @QueryHint(name = "org.hibernate.cacheable", value = "true") })
    @Query("from Score t where t.user.openId = ?1")
    Score findByOpenId(String openId);

    /**
     * 获取积分最高的top
     *
     * @param topCount
     * @return
     */
    @QueryHints({ @QueryHint(name = "org.hibernate.cacheable", value = "true") })
    @Query("from Score t order by t.score desc ")
    List<Score> findTop();

    @QueryHints({ @QueryHint(name = "org.hibernate.cacheable", value = "true") })
    @Query("from Score t where t.score>?1")
    List<Score> findMoreScore(int score);
}
