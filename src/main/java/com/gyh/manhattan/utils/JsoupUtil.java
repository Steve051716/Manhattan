package com.gyh.manhattan.utils;

import org.apache.commons.lang3.StringUtils;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.safety.Whitelist;

/**
 * xss非法标签过滤工具类
 * @author gaoyuhang
 */
public class JsoupUtil {
    /**
     * 使用基础白名单
     */
    private static final Whitelist whitelist = Whitelist.basicWithImages();
    /** 配置过滤化参数,不对代码进行格式化 */
    private static final Document.OutputSettings outputSettings = new Document.OutputSettings().prettyPrint(false);
    static {
        // 过滤富文本中的样式
        whitelist.addAttributes(":all", "style");
    }

    public static String clean(String content) {
        if(StringUtils.isNotBlank(content)){
            content = content.trim();
        }
        return Jsoup.clean(content, "", whitelist, outputSettings);
    }
}
