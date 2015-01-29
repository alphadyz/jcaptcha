package com.hunng.jcaptcha;

import com.hunng.jcaptcha.background.MyCustomBackgroundFactory;
import com.hunng.jcaptcha.color.RandomColorFactory;
import com.hunng.jcaptcha.custom.ConfigurableCaptchaService;
import com.hunng.jcaptcha.filter.ConfigurableFilterFactory;
import com.hunng.jcaptcha.filter.library.AbstractImageOp;
import com.hunng.jcaptcha.filter.library.WobbleImageOp;
import com.hunng.jcaptcha.font.RandomFontFactory;
import com.hunng.jcaptcha.service.Captcha;
import com.hunng.jcaptcha.text.renderer.BestFitTextRenderer;
import com.hunng.jcaptcha.word.RandomWordFactory;

import java.awt.image.BufferedImageOp;
import java.util.ArrayList;

public class Patchca1 {
    public static ConfigurableCaptchaService createCaptchaService() {
        ConfigurableCaptchaService configurableCaptchaService = new ConfigurableCaptchaService();

        // 颜色创建工厂,使用一定范围内的随机色
        RandomColorFactory colorFactory = new RandomColorFactory();
        configurableCaptchaService.setColorFactory(colorFactory);

        // 随机字体生成器
        RandomFontFactory fontFactory = new RandomFontFactory();
        fontFactory.setMaxSize(32);
        fontFactory.setMinSize(28);
        configurableCaptchaService.setFontFactory(fontFactory);

        // 随机字符生成器,去除掉容易混淆的字母和数字,如o和0等
        RandomWordFactory wordFactory = new RandomWordFactory();
        wordFactory.setCharacters("abcdefghkmnpqstwxyz23456789");
        wordFactory.setMaxLength(5);
        wordFactory.setMinLength(4);
        configurableCaptchaService.setWordFactory(wordFactory);
        fontFactory.setWordFactory(wordFactory);


        // 自定义验证码图片背景
        MyCustomBackgroundFactory backgroundFactory = new MyCustomBackgroundFactory();
        configurableCaptchaService.setBackgroundFactory(backgroundFactory);

        // 图片滤镜设置
        ConfigurableFilterFactory filterFactory = new ConfigurableFilterFactory();

        java.util.List<BufferedImageOp> filters = new ArrayList<BufferedImageOp>();
        WobbleImageOp wobbleImageOp = new WobbleImageOp();
        wobbleImageOp.setEdgeMode(AbstractImageOp.EDGE_MIRROR);
        wobbleImageOp.setxAmplitude(2.0);
        wobbleImageOp.setyAmplitude(1.0);
        filters.add(wobbleImageOp);
        filterFactory.setFilters(filters);
        configurableCaptchaService.setFilterFactory(filterFactory);

        // 文字渲染器设置
        BestFitTextRenderer textRenderer = new BestFitTextRenderer();
        textRenderer.setBottomMargin(3);
        textRenderer.setTopMargin(3);
        configurableCaptchaService.setTextRenderer(textRenderer);

        // 验证码图片的大小
        configurableCaptchaService.setWidth(90);
        configurableCaptchaService.setHeight(40);
        return configurableCaptchaService;
    }

    static ConfigurableCaptchaService cs = createCaptchaService();

    public static Captcha next() {
        return cs.getCaptcha();
    }

}
