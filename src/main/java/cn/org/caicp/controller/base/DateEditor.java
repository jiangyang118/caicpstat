package cn.org.caicp.controller.base;

import java.beans.PropertyEditorSupport;
import java.text.ParseException;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.time.DateFormatUtils;
import org.apache.commons.lang3.time.DateUtils;

/**
 * Editor - 日期
 *
 * @author yigou Team
 * @version 1.0
 */
public class DateEditor extends PropertyEditorSupport {

    /**
     * 日期格式配比
     */
    private static final String[] DATE_PATTERNS = new String[]{"yyyy", "yyyy-MM", "yyyyMM", "yyyy/MM", "yyyy-MM-dd",
        "yyyyMMdd", "yyyy/MM/dd", "yyyy-MM-dd HH:mm:ss", "yyyyMMddHHmmss", "yyyy/MM/dd HH:mm:ss"};
    /**
     * 默认日期格式
     */
    private static final String DEFAULT_DATE_FORMAT = "yyyy-MM-dd HH:mm:ss";

    /**
     * 是否将空转换为null
     */
    private boolean emptyAsNull;

    /**
     * 日期格式
     */
    private String dateFormat = DEFAULT_DATE_FORMAT;

    /**
     * 构造方法
     *
     * @param emptyAsNull 是否将空转换为null
     */
    public DateEditor(boolean emptyAsNull) {
        this.emptyAsNull = emptyAsNull;
    }

    /**
     * 构造方法
     *
     * @param emptyAsNull 是否将空转换为null
     * @param dateFormat 日期格式
     */
    public DateEditor(boolean emptyAsNull, String dateFormat) {
        this.emptyAsNull = emptyAsNull;
        this.dateFormat = dateFormat;
    }

    /**
     * 获取日期
     *
     * @return 日期
     */
    @Override
    public String getAsText() {
        Date value = (Date) getValue();
        return value != null ? DateFormatUtils.format(value, dateFormat) : StringUtils.EMPTY;
    }

    /**
     * 设置日期
     *
     * @param text 字符串
     */
    @Override
    public void setAsText(String text) {
        if (text != null) {
            String value = text.trim();
            if (emptyAsNull && StringUtils.isEmpty(text)) {
                setValue(null);
            } else {
                try {
                    setValue(DateUtils.parseDate(value, DATE_PATTERNS));
                } catch (ParseException ex) {
                    Logger.getLogger(DateEditor.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        } else {
            setValue(null);
        }
    }

}
