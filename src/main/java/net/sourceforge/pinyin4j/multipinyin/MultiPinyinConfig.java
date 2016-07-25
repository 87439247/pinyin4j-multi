package net.sourceforge.pinyin4j.multipinyin;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 多音字配置,可外挂
 * Created by 刘一波 on 16/3/4.
 * E-Mail:yibo.liu@tqmall.com
 */
public final class MultiPinyinConfig {
    public static Logger logger = LoggerFactory.getLogger("pinyin4j-multi");
    /**
     * 外挂多音字路径
     */
    public static String multiPinyinPath;
    /**
     * 外挂多音字HTTP路径,每分钟检查一次,自动更新
     */
    public static String multiPinyinHttpPath;
}
