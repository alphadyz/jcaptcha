package com.hunng.jcaptcha.custom;


import com.hunng.jcaptcha.random.RandUtils;
import com.hunng.jcaptcha.random.SpellUtils;
import com.hunng.jcaptcha.word.WordBean;
import com.hunng.jcaptcha.word.WordFactory;

public class RandomChineseJianpinFactory implements WordFactory {
    @Override
    public WordBean getNextWord() {
        String randChinese = RandUtils.randChinese(4);

        return new WordBean("请输入“" + randChinese + "”的简拼", SpellUtils.getFirst(randChinese), "请输入简拼");
    }

    @Override
    public String[] getSupportedFontFamilies() {
        return new String[]{"宋体"};
    }
}
