/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cn.org.caicp.vo;

/**
 *
 * @author liman
 */
public class DangConstant {

    //每局比赛的题目数量，小程序端需要同步设置
    public static int QUESTION_COUNT = 5;

    //每次等待时间（单位：s），超过这个时间就返回没有匹配到选手
    public static int TIMEOUT_WAIT_MATCHED = 10;
    //每次等待时间（单位：s），中间间隔时间
    public static int TIMEOUT_WAIT_MATCHED_INTERVAL = 1;

    public static int MAX_TIME_PER_QUESTION = 15;//每道题最长答题时间，如果当前距离最新一次更新的时间超过这个时间值，则认为对方掉线
//    public static int TIMEOUT_WAIT_MATCHED=10;

//    public static int TIMEOUT_WAIT
}
