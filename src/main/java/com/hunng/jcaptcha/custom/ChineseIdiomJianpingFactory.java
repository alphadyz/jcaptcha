package com.hunng.jcaptcha.custom;

import com.google.common.base.Splitter;
import com.google.common.collect.Iterables;
import com.hunng.jcaptcha.random.RandUtils;
import com.hunng.jcaptcha.random.SpellUtils;
import com.hunng.jcaptcha.random.StrUtils;
import com.hunng.jcaptcha.word.WordBean;
import com.hunng.jcaptcha.word.WordFactory;

public class ChineseIdiomJianpingFactory implements WordFactory {
    protected static String[] idioms;

    static {
        String str = StrUtils.loadClasspathResourceToString("/com/hunng/captcha/chineseidioms.txt");
        Iterable<String> split = Splitter.onPattern("\\s+").omitEmptyStrings().split(str);
        idioms = Iterables.toArray(split, String.class);

    }

    public WordBean getNextWord() {
        int nextInt = RandUtils.randInt(idioms.length);
        String answer = idioms[nextInt];

        return new WordBean(answer, SpellUtils.getFirst(answer), "请输入简拼");
    }

    @Override
    public String[] getSupportedFontFamilies() {
        return null;
    }

}
