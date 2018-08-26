/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cn.org.caicp.controller;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.Date;
import java.util.logging.Level;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.google.gson.Gson;

import cn.org.caicp.controller.base.WeChatSession;
import cn.org.caicp.controller.base.WebResponse;
import cn.org.caicp.service.PkService;
import cn.org.caicp.service.QuestionService;
import cn.org.caicp.service.ScoreService;
import cn.org.caicp.service.UserWechatService;
import cn.org.caicp.vo.DangConstant;
import cn.org.caicp.vo.PkResultVo;
import cn.org.caicp.vo.PkVo;
import cn.org.caicp.vo.UserWechatVo;

/**
 *
 * @author liman
 */
@RestController
public class DangController {

    private static final String APPID = "wx0c239562ba105af3";
    private static final String SECRET = "ada5905322ed4fe44b5bb652d10f8813";
    static Logger logger = Logger.getLogger(DangController.class);
    @Autowired
    QuestionService questionService;
    @Autowired
    UserWechatService userWechatService;
    @Autowired
    PkService pkService;
    @Autowired
    ScoreService scoreService;

    /**
     * 接受小程序提交的用户信息，保存到数据库
     *
     * @param user
     * @return
     */
    @RequestMapping(value = "/dang/login", method = RequestMethod.POST)
    @ResponseBody
    public WebResponse login(@RequestBody UserWechatVo user) {
        logger.info("wechat login - code: " + user.getCode());
        String url = "https://api.weixin.qq.com/sns/jscode2session?appid=" + APPID + "&secret=" + SECRET + "&js_code=" + user.getCode() + "&grant_type=authorization_code";
        RestTemplate restTemplate = new RestTemplate();
//        //进行网络请求,访问url接口  
        ResponseEntity<String> responseEntity = restTemplate.exchange(url, HttpMethod.GET, null, String.class);
        //根据返回值进行后续操作   
        if (responseEntity != null && responseEntity.getStatusCode() == HttpStatus.OK) {
            String sessionData = responseEntity.getBody();
            Gson gson = new Gson();
            WeChatSession weChatSession = gson.fromJson(sessionData, WeChatSession.class);
            logger.info("login info: " + weChatSession);
            user.setOpenId(weChatSession.getOpenid());
            logger.info(user);
            try {
                user.setNickNameUTF8(URLEncoder.encode(user.getNickNameOrigin(), "utf-8"));
            } catch (UnsupportedEncodingException ex) {
                logger.info("encode nickname error." + user.getNickNameOrigin());
            }
            logger.info("after encode: " + user);
            userWechatService.createOrUpdate(user);
            return new WebResponse<>(WebResponse.WEB_STATUS_OK, weChatSession);
        }
        return new WebResponse<>(WebResponse.WEB_STATUS_SYSTEM_ERROR, "request wechat error");
    }

    @RequestMapping(value = "/dang/getQuestions")
    @ResponseBody
    public WebResponse getQuestions() {
        logger.info("get questions.");
        return new WebResponse<>(WebResponse.WEB_STATUS_OK, questionService.getRandomQuestions(DangConstant.QUESTION_COUNT));
    }

    /**
     *
     * @param openId
     * @return
     */
    @RequestMapping(value = "/dang/startGame")
    @ResponseBody
    public WebResponse startGame(String openId) {
        logger.info("start game" + openId);
        PkVo result = pkService.matchPk(openId, null);
        int count = 0;
        while (result.getOpponent().getOpenId() == null) {
            if (count > 1.0 * DangConstant.TIMEOUT_WAIT_MATCHED / DangConstant.TIMEOUT_WAIT_MATCHED_INTERVAL) {
                //超时返回
                return new WebResponse<>(WebResponse.WEB_STATUS_BUSSINESS_ERROR, "没有为您找到比赛对手，您可以邀请其他人进入小程序参与答题");
            }
            logger.info("not match----" + openId);
            try {
                Thread.sleep(DangConstant.TIMEOUT_WAIT_MATCHED_INTERVAL * 1000);
            } catch (InterruptedException ex) {
                java.util.logging.Logger.getLogger(DangController.class.getName()).log(Level.SEVERE, null, ex);
            }
            result = pkService.matchPk(openId, result.getId());
            count++;
        }
        logger.info("get match----" + openId);
        return new WebResponse<>(WebResponse.WEB_STATUS_OK, result);
    }

    /**
     * 更新pk状态信息
     *
     * @param vo
     * @return
     */
    @RequestMapping(value = "/dang/updatePkResult", method = RequestMethod.POST)
    @ResponseBody
    public WebResponse updatePkResult(@RequestBody PkResultVo vo) {
        logger.info(vo);
        return new WebResponse<>(WebResponse.WEB_STATUS_OK, pkService.updatePkResult(vo));
    }

    /**
     * 获取对手最终的pk结果
     *
     * @param pkId
     * @param openId
     * @return
     */
    @RequestMapping(value = "/dang/getFinalResult")
    @ResponseBody
    public WebResponse getFinalResult(Long pkId, String openId) {

        logger.info(pkId + "---" + openId);
        PkResultVo result = pkService.getOpponentFinalResult(pkId, openId);
        if (result.getFinished()) {
            logger.info("get final----" + pkId + ":" + openId + "-vs-" + result.getOpenId());

        } else {
            logger.info("not final----" + pkId + ":" + openId + "-vs-" + result.getOpenId());
            result = checkFinal(pkId, openId);
        }
        return new WebResponse<>(WebResponse.WEB_STATUS_OK, result);
    }

    /**
     * 获取积分排行榜
     *
     * @return
     */
    @RequestMapping(value = "/dang/getRankTop")
    @ResponseBody
    public WebResponse getRankTop() {
        logger.info("get Rank top");
        return new WebResponse<>(WebResponse.WEB_STATUS_OK, scoreService.getScoreTop(10));
    }

    /**
     * 获取本人积分
     *
     * @param openId
     * @return
     */
    @RequestMapping(value = "/dang/getMyRank")
    @ResponseBody
    public WebResponse getMyRank(String openId) {
        logger.info("get myRank ");
        return new WebResponse<>(WebResponse.WEB_STATUS_OK, scoreService.getScoreByOpenId(openId));
    }

    private PkResultVo checkFinal(Long pkId, String openId) {
        logger.info("check---" + pkId);
        PkResultVo result = pkService.getOpponentFinalResult(pkId, openId);
        if (!result.getFinished()) {
            long interval = (new Date().getTime() - result.getUpdateTime().getTime()) / 1000;
            //如果对方一直在更新，则继续等待更新到最终结果
            if (interval < DangConstant.MAX_TIME_PER_QUESTION) {
                try {
                    Thread.sleep(1000);
                    checkFinal(pkId, openId);
                } catch (InterruptedException ex) {
                    logger.error(ex);
                }
            }

        }
        logger.info("checked---" + pkId);
        return result;
    }
}
