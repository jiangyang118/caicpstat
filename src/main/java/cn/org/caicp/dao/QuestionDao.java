package cn.org.caicp.dao;

import java.util.List;

import javax.persistence.QueryHint;

import org.springframework.data.jpa.repository.QueryHints;
import org.springframework.stereotype.Repository;

import cn.org.caicp.po.Question;

/**
 *
 * @author liman <liman1@catr.cn>
 * @version 1.0.0
 * @since 2014-4-30 11:21:03
 */
@Repository("questionDao")
public interface QuestionDao extends BaseDao<Question, Long> {

    @QueryHints({
        @QueryHint(name = "org.hibernate.cacheable", value = "true")})
    @Override
    List<Question> findAll();

}
