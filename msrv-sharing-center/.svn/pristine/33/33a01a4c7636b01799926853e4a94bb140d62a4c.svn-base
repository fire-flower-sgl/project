//package com.mhtech.platform.msrv.sharing.utils;
//
//import java.io.IOException;
//import java.io.InputStreamReader;
//import java.util.Properties;
//import java.util.Set;
//
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//
//public class PropertyUtils {
//	
//	/**
//	 * 文件路径
//	 */
//    private static String sysFile = "application.properties";
//    /**
//     * Properties实体
//     */
//    private static Properties Sysproperties ;
//    
//    static {
//         Sysproperties = PropertyUtils.getProperties(sysFile);
//     }
//     
//    /**
//     * 获取指定路径下的配置文件信息
//     *
//     * @param configPath
//     * @return
//     */
//    public static Properties getProperties(String configPath) {        
//    	//
//    	Properties pop = new Properties();
//        Properties pros = new Properties();
//        try {
//            ClassLoader cl = ClassLoader.getSystemClassLoader();
//            ClassLoader.getSystemClassLoader().getResource(sysFile);
//            pop.load(new InputStreamReader(cl.getResourceAsStream(sysFile), "UTF-8"));
//            String type = pop.getProperty("spring.profiles.active");
//            String devOrPodPath = "";
//            if(type.equals("dev")) {
//            	devOrPodPath = "application-dev.properties";
//            }else if(type.equals("prd")){
//            	devOrPodPath = "application-prd.properties";
//            }
//            if(!devOrPodPath.equals("")) {
//            	ClassLoader c2 = ClassLoader.getSystemClassLoader();
//                ClassLoader.getSystemClassLoader().getResource(devOrPodPath);
//                pros.load(new InputStreamReader(c2.getResourceAsStream(devOrPodPath), "UTF-8"));
//                Set<Object> keySet = pros.keySet();
//                keySet.forEach(key -> {
//                	pop.put(key, pros.get(key));
//                });
//            } 
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//        return pop;
//    }
//     
//    public static String getSysConfigSet(String key){
//        return Sysproperties.getProperty(key);
//    }
//    
//    public static void main(String[] args) {
//    	System.err.println(getSysConfigSet("spring.profiles.active"));
//    	System.err.println(getSysConfigSet("SmsSend.url"));
//    	
//	}
//     
//}
