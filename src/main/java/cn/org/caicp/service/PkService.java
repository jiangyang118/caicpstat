package cn.org.caicp.service;

import cn.org.caicp.vo.PkResultVo;
import cn.org.caicp.vo.PkVo;

/**
 *
 * @author liman <liman1@catr.cn>
 * @since 2014-4-21 15:57:02
 * @version 1.0.0
 */
public interface PkService {

    /**
     * 获取匹配对手，生成一局
     *
     * @param openId
     * @return
     */
    PkVo matchPk(String openId, Long pkId);

    /**
     * 检查是否被匹配到，如果被匹配到则返回生效的pkVo
     *
     * @param pkId
     * @return
     */
    PkVo checkMatchPk(Long pkId);

    /**
     * 更新自己的当前pk状态（包括得分等情况），同时返回对手状态信息
     *
     * @param pkResultVo
     * @return
     */
    PkResultVo updatePkResult(PkResultVo pkResultVo);

    /**
     * 获取最终pk结果信息（对手）
     *
     * @param pkId
     * @return
     */
    public PkResultVo getOpponentFinalResult(Long pkId, String openId);

}
