package cn.org.caicp.service;

import java.util.List;

import cn.org.caicp.vo.QuestionVo;

/**
 *
 * @author liman <liman1@catr.cn>
 * @since 2014-4-21 15:57:02
 * @version 1.0.0
 */
public interface QuestionService {

    /**
     * 获取题库所有问题
     *
     * @return
     */
    List<QuestionVo> getAllQuestions();

    /**
     * 随机获取问题
     *
     * @param count 数量
     * @return
     */
    List<QuestionVo> getRandomQuestions(int count);

}
