package cn.yunhe.util;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.Arrays;
import java.util.Random;

import javax.imageio.ImageIO;

/**
 * 1>生成随机数
 * 2>绘制图片
 * 	2.1>设置边框色、背景色
 * 	2.2>绘制干扰线
 * 	2.3>添加噪点（干扰点）
 * 	2.4>图片扭曲
 * 3>将图片以IO流的形式输出
 * 
 * @author Administrator
 *
 */
public class VerifyCodeUtilByYock {
	
	//使用到Algerian字体，系统里没有的话需要安装字体，字体只显示大写，去掉了1,0,i,o几个容易混淆的字符
	public static final String VERIFY_CODES = "23456789ABCDEFGHJKLMNPQRSTUVWXYZ";
	private static Random random = new Random();

	
	/**
	 * 生成验证码
	 * 
	 * @param verifySize
	 *            验证码长度
	 * @return
	 */
	public static String generateVerifyCode(int verifySize) {
		int codesLen = VERIFY_CODES.length();
		Random rand = new Random(System.currentTimeMillis());
		StringBuilder verifyCode = new StringBuilder(verifySize);
		for (int i = 0; i < verifySize; i++) {
			verifyCode.append(VERIFY_CODES.charAt(rand.nextInt(codesLen - 1)));
		}
		return verifyCode.toString();
	}
    
    /** 
     * 生成指定验证码图片到输出流
     * @param w 			图片的width
     * @param h  			图片的height
     * @param os 			输出目的地的流
     * @param code 			验证码
     * @throws IOException 
     */  
    public static void outputImageStream(int w, int h, OutputStream os, String code) throws IOException{  
        int verifySize = code.length(); 
        //image对象就是最终画出来的图片
        BufferedImage image = new BufferedImage(w, h, BufferedImage.TYPE_INT_RGB);  
        Random rand = new Random();  
        //通过缓冲区创建画布
        Graphics2D g2 = image.createGraphics();  
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING,RenderingHints.VALUE_ANTIALIAS_ON);  
        Color[] colors = new Color[5];  
        Color[] colorSpaces = new Color[] { Color.WHITE, Color.CYAN,  
                Color.GRAY, Color.LIGHT_GRAY, Color.MAGENTA, Color.ORANGE,  
                Color.PINK, Color.YELLOW };  
        float[] fractions = new float[colors.length];  
        for(int i = 0; i < colors.length; i++){  
            colors[i] = colorSpaces[rand.nextInt(colorSpaces.length)];  
            fractions[i] = rand.nextFloat();  
        }
        Arrays.sort(fractions);
          
        g2.setColor(Color.GRAY);// 设置边框色  
        g2.fillRect(0, 0, w, h);//填充指定的矩形框
        
        
        Color c = getRandColor(200, 250);
        g2.setColor(c);// 设置背景色
        g2.fillRect(0, 2, w, h-4);
          
        //绘制干扰线
        Random random = new Random();  
        g2.setColor(getRandColor(160, 200));// 设置线条的颜色  
        for (int i = 0; i < 20; i++) {  
            int x = random.nextInt(w - 1);
            int y = random.nextInt(h - 1);  
            int xl = random.nextInt(6) + 1;  
            int yl = random.nextInt(12) + 1;  
            g2.drawLine(x, y, x + xl + 40, y + yl + 20);  
        }
          
        // 添加噪点  
        float yawpRate = 0.05f;// 噪声率，值越高噪点越多  
        int area = (int) (yawpRate * w * h);  
        for (int i = 0; i < area; i++) {  
            int x = random.nextInt(w);  
            int y = random.nextInt(h);  
            int rgb = getRandomIntColor();  
            image.setRGB(x, y, rgb);  
        }
        
        shear(g2, w, h, c);// 使图片扭曲  
        
        g2.setColor(getRandColor(100, 160));  
        int fontSize = h-4;
        Font font = new Font("Algerian", Font.ITALIC, fontSize);  
        g2.setFont(font);  
        char[] chars = code.toCharArray();  
        for(int i = 0; i < verifySize; i++){  
            AffineTransform affine = new AffineTransform();  
            affine.setToRotation(Math.PI / 4 * rand.nextDouble() * (rand.nextBoolean() ? 1 : -1), (w / verifySize) * i + fontSize/2, h/2);  
            g2.setTransform(affine);  
            g2.drawChars(chars, i, 1, ((w-10) / verifySize) * i + 5, h/2 + fontSize/2 - 10);  
        }
          
        g2.dispose();
        
        //ImageIO这个类，提供了write方法来将指定的BufferedImage对象（image）输出到指定的流（os）的目的地
        ImageIO.write(image, "jpg", os);
        
    }
    
    private static Color getRandColor(int fc, int bc) {  
        if (fc > 255)  
            fc = 255;  
        if (bc > 255)  
            bc = 255;  
        int r = fc + random.nextInt(bc - fc);  
        int g = fc + random.nextInt(bc - fc);  
        int b = fc + random.nextInt(bc - fc);  
        return new Color(r, g, b);  
    }  
      
    private static int getRandomIntColor() {  
        int[] rgb = getRandomRgb();  
        int color = 0;  
        for (int c : rgb) {  
            color = color << 8;  
            color = color | c;  
        }  
        return color;  
    }
    
    private static int[] getRandomRgb() {  
        int[] rgb = new int[3];  
        for (int i = 0; i < 3; i++) {  
            rgb[i] = random.nextInt(255);  
        }  
        return rgb;  
    }  
  
    private static void shear(Graphics g, int w1, int h1, Color color) {  
        shearX(g, w1, h1, color);  
        shearY(g, w1, h1, color);  
    }
    
    private static void shearX(Graphics g, int w1, int h1, Color color) {  
    	  
        int period = random.nextInt(2);  
  
        boolean borderGap = true;  
        int frames = 1;  
        int phase = random.nextInt(2);  
  
        for (int i = 0; i < h1; i++) {  
            double d = (double) (period >> 1)  
                    * Math.sin((double) i / (double) period  
                            + (6.2831853071795862D * (double) phase)  
                            / (double) frames);  
            g.copyArea(0, i, w1, 1, (int) d, 0);  
            if (borderGap) {  
                g.setColor(color);  
                g.drawLine((int) d, i, 0, i);  
                g.drawLine((int) d + w1, i, w1, i);  
            }  
        }  
  
    }
    
    private static void shearY(Graphics g, int w1, int h1, Color color) {  
    	  
        int period = random.nextInt(40) + 10; // 50;  
  
        boolean borderGap = true;  
        int frames = 20;  
        int phase = 7;  
        for (int i = 0; i < w1; i++) {  
            double d = (double) (period >> 1)  
                    * Math.sin((double) i / (double) period  
                            + (6.2831853071795862D * (double) phase)  
                            / (double) frames);  
            g.copyArea(i, 0, 1, h1, 0, (int) d);  
            if (borderGap) {  
                g.setColor(color);  
                g.drawLine(i, (int) d, i, 0);  
                g.drawLine(i, (int) d + h1, i, h1);  
            }  
  
        } 
  
    }
    
    
    
    public static void main(String[] args) throws IOException {
    	
    	//生成验证码
    	String verifyCode = generateVerifyCode(4);
    	
    	//生成图片，输出到指定的输出流
    	OutputStream oos=new FileOutputStream("E:/verify/"+verifyCode+".jpg");
    	outputImageStream(200, 80, oos, verifyCode);
    	oos.close();
    	
    	
    	
    	
//    	File dir = new File("/Users/yock/Desktop/verifies");  
//        int w = 200, h = 80;  
//        for(int i = 0; i < 50; i++){
//            String verifyCode = generateVerifyCode(4);  
//            File file = new File(dir, verifyCode + ".jpg");  
//            try {
//            	outputImageFile(w, h, file, verifyCode);
//			} catch (IOException e) {
//				// TODO Auto-generated catch block
//				e.printStackTrace();
//			}
//        }
	}
    
    
    
    
	
}
