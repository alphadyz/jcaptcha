package com.hunng.jcaptcha.custom;

import com.hunng.jcaptcha.random.RandUtils;
import com.hunng.jcaptcha.word.WordBean;

public class ChineseIdiomGuessFactory extends ChineseIdiomFactory {

    public WordBean getNextWord() {
        WordBean wordBean = super.getNextWord();
        String word = wordBean.getWord();
        int pos = RandUtils.randInt(word.length());

        return new WordBean(word.substring(0, pos) + "?" + word.substring(pos + 1),
                word.substring(pos, pos + 1), "请输入图片中?的代表的汉字");

    }

}
