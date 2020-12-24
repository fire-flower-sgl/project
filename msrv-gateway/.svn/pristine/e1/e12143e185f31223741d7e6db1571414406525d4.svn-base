package com.mhtech.platform.msrv.gateway.test;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.UUID;

import javax.imageio.ImageIO;

import org.junit.Test;

import com.mhtech.platform.msrv.gateway.utils.HttpClientPoolUtils;
import com.mhtech.platform.msrv.gateway.utils.HttpClientUtils;
import com.mhtech.platform.msrv.pojo.AlertNotifyLogCounterBO;
import com.mhtech.platform.msrv.pojo.DailyAlertsVO;
import com.mhtech.platform.msrv.pojo.DetailAlertVO;

public class TestUtils {


//	@Test
//	public void httpClient() {
//		for (int i = 0; i < 100; i++) {
//			long start = System.currentTimeMillis();
//			HttpClientUtils.get("https://www.baidu.com/8000");
//			System.out.println("Spend : " + (System.currentTimeMillis() - start));
//		}
//	}
//	
//	@Test
//	public void httpClientPool() {
//		for (int i = 0; i < 100; i++) {
//			long start = System.currentTimeMillis();
//			HttpClientPoolUtils.doGet("https://www.baidu.com/8000");
//			System.out.println("Spend : " + (System.currentTimeMillis() - start));
//		}
//	}

//	@Test
//	public void mapAdd() {
//		
//	 //add(xx) 与 add(xx.put(x,x))的区别	
//		
//	  List<Object> list1=new ArrayList<Object>();
//	  List<Object> list2=new ArrayList<Object>();
//	  Map<String,Object> map1=new HashMap<String, Object>();
//	  Map<String,Object> map2=new HashMap<String, Object>();
//	  
//	  
//	  map1.put("mjl", "测试");
//	  list1.add(map1);  
//	  System.err.println(list1);
//	  
//	  //执行顺序---先添加list(所以添加了一个null)-在执行map2的添加
//	  list2.add(map2.put("mjl", "测试"));
//	  System.err.println(list2);
//	  System.err.println(map2);
//	  
//	  
//	}
	@Test
	public void list() throws FileNotFoundException, IOException {
//		
//			String a=new String("mjl");
//			String b=new String("mjl");
//			String a2="mjl"; 
//			String a1="mjl"; 
//		    
//		
//          System.err.println(a==a2); //2个都存放在堆中，但位置不同，a2在堆中的常量池里面，a在堆中单独开辟一处空间存放
//          System.err.println(a1==a2);//2个都是引用了堆的常量池  所以为true
//          System.err.println(a==b);//2个都放在了堆中，，但是位置不同（所有new出来的，均要运行后，才能确实是什么东西，所以会单独开辟一处空间存放。所以不断new会很容易出现堆溢出）
//          
//          //常量池在堆区，线程共享
	  
	   	  int w = 70;
		  int h = 35;
		  Random r = new Random();
		  BufferedImage image = new BufferedImage(w, h,
		  BufferedImage.TYPE_INT_RGB);
		  Graphics2D g2 = (Graphics2D) image.getGraphics();
		  g2.fillRect(0, 0, w, h);
		  
		  // 向图片中画4个字符
		  StringBuffer sb = new StringBuffer(6);
		  for (int i = 0; i < 4; i++) {// 循环四次，每次生成一个字符
		   String s = r.nextInt(10) + "";// 随机生成一个字母
		   sb.append(s);
		   float x = i * 1.0F * w / 4; // 设置当前字符的x轴坐标
		   g2.setColor(Color.BLUE);
		   g2.drawString(s, x, h - 5); // 画图
		   g2.setColor(Color.YELLOW);
		   g2.drawLine(5, 5, 35, 35);
		  }
		  System.err.println(UUID.randomUUID().toString());
		  ImageIO.write(image, "JPEG", new FileOutputStream("E:\\test1.jpg"));
		  System.out.println(sb);
		  System.err.println("---------"+genRandomNum());
	}
	
	
	/**
	 * 生成随机数
	 * @return
	 */
	public String genRandomNum(){
		  int  maxNum = 36;
		  int i;
		  int count = 0;
		  char[] str = { 'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J', 'K',
		    'L', 'M', 'N', 'O', 'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W',
		    'X', 'Y', 'Z', '0', '1', '2', '3', '4', '5', '6', '7', '8', '9' };	  
		  StringBuffer pwd = new StringBuffer("");
		  Random r = new Random();
		  while(count < 8){
		   i = Math.abs(r.nextInt(maxNum));   
		   if (i >= 0 && i < str.length) {
		    pwd.append(str[i]);
		    count ++;
		   }
		  }
		  return pwd.toString();
		}

	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}



