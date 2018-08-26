package cn.org.caicp.dao;

import java.util.List;

import javax.persistence.QueryHint;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.QueryHints;
import org.springframework.stereotype.Repository;

import cn.org.caicp.po.Pk;

/**
 *
 * @author liman <liman1@catr.cn>
 * @version 1.0.0
 * @since 2014-4-30 11:21:03
 */
@Repository("pkDao")
public interface PkDao extends BaseDao<Pk, Long> {

    /**
     * 获取没有等待匹配的列表
     *
     * @return
     */
    @QueryHints({ @QueryHint(name = "org.hibernate.cacheable", value = "true") })
    @Query("from Pk t where t.user2 is null and t.valid = 1 order by t.createTime")
    List<Pk> findWaitMatched();

}
