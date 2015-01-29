package com.hunng.jcaptcha;

import com.hunng.jcaptcha.color.ColorFactory;
import com.hunng.jcaptcha.custom.ConfigurableCaptchaService;
import com.hunng.jcaptcha.filter.predefined.CurvesRippleFilterFactory;
import com.hunng.jcaptcha.filter.predefined.DoubleRippleFilterFactory;
import com.hunng.jcaptcha.filter.predefined.RandomFilterFactory;
import com.hunng.jcaptcha.filter.predefined.WobbleRippleFilterFactory;
import com.hunng.jcaptcha.font.RandomFontFactory;
import com.hunng.jcaptcha.service.Captcha;
import com.hunng.jcaptcha.word.RandomWordFactory;

import java.awt.*;
import java.util.Random;

public class Patchca {
    private static ConfigurableCaptchaService cs = new ConfigurableCaptchaService();
    private static Random random = new Random();

    static {
//        cs.setColorFactory(new SingleColorFactory(new Color(25, 60, 170)));
        cs.setColorFactory(new ColorFactory() {
            @Override
            public Color getColor(int x) {
                int[] c = new int[3];
                int i = random.nextInt(c.length);
                for (int fi = 0; fi < c.length; fi++) {
                    if (fi == i) {
                        c[fi] = random.nextInt(71);
                    } else {
                        c[fi] = random.nextInt(256);
                    }
                }
                return new Color(c[0], c[1], c[2]);
            }
        });

        // 随机字体生成器
        RandomFontFactory fontFactory = new RandomFontFactory();
        fontFactory.setMaxSize(30);
        fontFactory.setMinSize(25);
        cs.setFontFactory(fontFactory);

        RandomWordFactory wf = new RandomWordFactory();
        wf.setCharacters("23456789abcdefghgkmnpqrstuvwxyzABCDEFGHGKMNPQRSTUVWXYZ");
        wf.setMaxLength(4);
        wf.setMinLength(4);
        cs.setWordFactory(wf);

        RandomFilterFactory filterFactory = new RandomFilterFactory();
        filterFactory.addFilterFactory(new CurvesRippleFilterFactory(cs.getColorFactory()));
//        filterFactory.addFilterFactory(new MarbleRippleFilterFactory());// 糊
        filterFactory.addFilterFactory(new DoubleRippleFilterFactory());
        filterFactory.addFilterFactory(new WobbleRippleFilterFactory());
//        filterFactory.addFilterFactory(new DiffuseRippleFilterFactory());// 糊
        cs.setFilterFactory(filterFactory);

        // 验证码图片的大小
        cs.setWidth(90);
        cs.setHeight(40);
    }

    public static Captcha next() {
        return cs.getCaptcha();
    }
}
