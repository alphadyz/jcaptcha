package com.hunng.jcaptcha.custom;

import com.hunng.jcaptcha.random.RandUtils;
import com.hunng.jcaptcha.random.StrUtils;
import com.hunng.jcaptcha.word.WordBean;
import com.hunng.jcaptcha.word.WordFactory;

import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class EnglishWordFactory implements WordFactory {
    private static String[] idioms;

    static {
        String str = StrUtils.loadClasspathResourceToString("/com/hunng/captcha/englishwords.txt");
        Pattern pattern = Pattern.compile("\\b\\w{5,8}\\b");
        Matcher matcher = pattern.matcher(str);
        ArrayList<String> words = new ArrayList<String>();
        while (matcher.find()) {
            words.add(matcher.group());
        }

        idioms = words.toArray(new String[0]);
    }

    @Override
    public WordBean getNextWord() {
        int nextInt = RandUtils.randInt(idioms.length);
        String answer = idioms[nextInt];
        return new WordBean(answer, answer, "请输入图片中的英文单词");
    }

    @Override
    public String[] getSupportedFontFamilies() {
        return null;
    }

}
