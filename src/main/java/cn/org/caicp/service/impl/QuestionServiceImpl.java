package cn.org.caicp.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cn.org.caicp.dao.QuestionDao;
import cn.org.caicp.po.Question;
import cn.org.caicp.service.QuestionService;
import cn.org.caicp.utils.RandomUtil;
import cn.org.caicp.vo.QuestionVo;

/**
 *
 * @author liman <liman1@catr.cn>
 * @since 2014-4-21 15:59:59
 * @version 1.0.0
 */
@Service("questionService")
@Transactional
public class QuestionServiceImpl implements QuestionService {
    
    static List<QuestionVo> QUESTIONS = null;
    @Autowired
    QuestionDao questionDao;
    static Logger logger = Logger.getLogger(QuestionServiceImpl.class);
    
    @Override
    public List<QuestionVo> getAllQuestions() {
        List<QuestionVo> result = new ArrayList<QuestionVo>();
        List<Question> pos = questionDao.findAll();
        for (Question po : pos) {
            QuestionVo vo = new QuestionVo();
            BeanUtils.copyProperties(po, vo);
            result.add(vo);
        }
        return result;
    }
    
    @Override
    public List<QuestionVo> getRandomQuestions(int count) {
        List<QuestionVo> result = new ArrayList<QuestionVo>();
        if (QUESTIONS == null) {
            QUESTIONS = getAllQuestions();
        }
        int[] indexs = RandomUtil.randomArray(0, QUESTIONS.size() - 1, count);
        logger.info(indexs);
        for (int index : indexs) {
            result.add(QUESTIONS.get(index));
        }
        return result;
    }
    
}
