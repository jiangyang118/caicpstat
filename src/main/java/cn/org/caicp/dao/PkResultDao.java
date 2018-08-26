package cn.org.caicp.dao;

import java.util.List;

import javax.persistence.QueryHint;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.QueryHints;
import org.springframework.stereotype.Repository;

import cn.org.caicp.po.PkResult;

/**
 *
 * @author liman <liman1@catr.cn>
 * @version 1.0.0
 * @since 2014-4-30 11:21:03
 */
@Repository("pkResultDao")
public interface PkResultDao extends BaseDao<PkResult, Long> {

    /**
     * 获取pk结果
     *
     * @param pkId
     * @return
     */
    @QueryHints({ @QueryHint(name = "org.hibernate.cacheable", value = "true") })
    @Query("from PkResult t where t.pk.id = ?1")
    List<PkResult> findByPkId(Long pkId);

    @QueryHints({ @QueryHint(name = "org.hibernate.cacheable", value = "true") })
    @Query("from PkResult t where t.pk.id = ?1 and t.user.openId = ?2")
    List<PkResult> findByPkIdAndOpenId(Long pkId, String openId);

    @QueryHints({ @QueryHint(name = "org.hibernate.cacheable", value = "true") })
    @Query("from PkResult t where t.pk.id = ?1 and t.user.openId != ?2")
    List<PkResult> findByPkIdAndNotOpenId(Long pkId, String openId);
}
