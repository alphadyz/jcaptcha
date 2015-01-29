# jcaptcha
Simple yet powerful CAPTCHA library written in Java. 
patchca base on patchca : http://code.google.com/p/patchca/

jcaptcha基于patchca做了扩展实现，并整合了spring mvc，生成五颜六色的验证码。

	/**
	 * @author hunng
	 */
	@Controller
	public class CaptchaController {
	    private static ConfigurableCaptchaService ccs    = new ConfigurableCaptchaService();
	    private static Random                     random = new Random();
	    static {
	        ccs.setColorFactory(new ColorFactory() {
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
	        RandomWordFactory wf = new RandomWordFactory();
	        wf.setCharacters("23456789abcdefghigkmnpqrstuvwxyzABCDEFGHIGKLMNPQRSTUVWXYZ");
	        wf.setMaxLength(4);
	        wf.setMinLength(4);
	        ccs.setWordFactory(wf);
	    }
	
	    private void setResponseHeaders(HttpServletResponse response) {
	        response.setContentType("image/png");
	        response.setHeader("Cache-Control", "no-cache, no-store");
	        response.setHeader("Pragma", "no-cache");
	        long time = System.currentTimeMillis();
	        response.setDateHeader("Last-Modified", time);
	        response.setDateHeader("Date", time);
	        response.setDateHeader("Expires", time);
	    }
	
	    /**
	     * 获取图片验证码
	     * 
	     * @param response
	     * @param mobile
	     * @throws IOException
	     */
	    @RequestMapping("/get-captcha")
	    public void getCaptcha(HttpServletRequest request,HttpServletResponse response) throws IOException {
	        switch (random.nextInt(5)) {
	            case 0:
	                ccs.setFilterFactory(new CurvesRippleFilterFactory(ccs.getColorFactory()));
	                break;
	            case 1:
	                ccs.setFilterFactory(new MarbleRippleFilterFactory());
	                break;
	            case 2:
	                ccs.setFilterFactory(new DoubleRippleFilterFactory());
	                break;
	            case 3:
	                ccs.setFilterFactory(new WobbleRippleFilterFactory());
	                break;
	            case 4:
	                ccs.setFilterFactory(new DiffuseRippleFilterFactory());
	                break;
	        }
	        HttpSession session = request.getSession(false);
            if (session == null) {
            	session = request.getSession();
            }
			setResponseHeaders(response);
			String token = EncoderHelper.getChallangeAndWriteImage(cs, "png", response.getOutputStream());
			session.setAttribute("captchaToken", token);
			System.out.println("当前SessionID=" + session.getId() + "，验证码=" + token);
	    }
	｝




