package com.hunng.jcaptcha.custom;


import com.hunng.jcaptcha.random.RandUtils;
import com.hunng.jcaptcha.random.SpellUtils;
import com.hunng.jcaptcha.word.WordBean;
import com.hunng.jcaptcha.word.WordFactory;

public class RandomChineseQuanpinFactory implements WordFactory {
    @Override
    public WordBean getNextWord() {
        String randChinese = RandUtils.randChinese(2);

        return new WordBean("请输入“" + randChinese + "”的全拼", SpellUtils.getFull(randChinese), "请输入全拼");
    }

    @Override
    public String[] getSupportedFontFamilies() {
        return null;
    }
}
