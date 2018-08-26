/**
 * Copyright (c) 2018,sunnybs. 
 * All Rights Reserved.
 * 
 * Project Name:account
 * Package Name:com.sunego.commerce.account.controller.base
 * File Name:BaseController.java
 * Date:2018年4月4日 下午1:58:57
 * 
 */
package cn.org.caicp.controller.base;

import java.util.Date;

import org.springframework.beans.propertyeditors.StringTrimmerEditor;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;


/**
 * ClassName: BaseController <br/>
 * Description: 参数类型的初始化 <br/>
 * Date: 2018年4月4日 下午1:58:57 <br/>
 * <br/>
 * 
 * @author m1339(邮箱)
 * 
 *         修改记录
 * @version 产品版本信息 yyyy-mm-dd 姓名(邮箱) 修改信息<br/>
 * 
 */

public class BaseController {

    /**
     * 数据绑定
     * 
     * @param binder
     *            WebDataBinder
     */
    @InitBinder
    protected void initBinder(WebDataBinder binder) {
        binder.registerCustomEditor(String.class, new StringTrimmerEditor(true));
        binder.registerCustomEditor(Date.class, new DateEditor(true));
        binder.registerCustomEditor(String.class, "password", new StringEditor(true));
    }

}
