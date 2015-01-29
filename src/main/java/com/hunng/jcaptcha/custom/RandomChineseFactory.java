package com.hunng.jcaptcha.custom;


import com.hunng.jcaptcha.random.RandUtils;
import com.hunng.jcaptcha.word.WordBean;
import com.hunng.jcaptcha.word.WordFactory;

public class RandomChineseFactory implements WordFactory {
    @Override
    public WordBean getNextWord() {
        String randChinese = RandUtils.randChinese((3 + RandUtils.randInt(2)));

        return new WordBean(randChinese, randChinese, "请输入图片中的文字");
    }

    @Override
    public String[] getSupportedFontFamilies() {
        return null;
    }
}
