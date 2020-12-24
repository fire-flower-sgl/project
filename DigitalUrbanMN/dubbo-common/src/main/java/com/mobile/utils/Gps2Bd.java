package com.mobile.utils;

import java.awt.geom.Point2D;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;

/**
 * Created by Administrator on 2019/6/25.
 */
public class Gps2Bd {
	
	@Autowired
//	private static JdbcTemplate jdbc;
	
    static double pi = 3.141592653589793238;
    static double a = 6378245.0;
    static double ee = 0.00669342162296594323;
    public final static double x_pi =3.141592653589793238* 3000.0 / 180.0;
    //gps-bd
    public static double[] wgs2bd(double lat, double lon) {
        double[] wgs2gcj = wgs2gcj(lat, lon);     //GPS坐标转火星坐标
        double[] gcj2bd = gcj2bd(wgs2gcj[0], wgs2gcj[1]); //火星坐标转百度坐标
        return gcj2bd;
    }
    //gd-bd
    public static double[] gcj2bd(double lat, double lon) {
        double x = lon, y = lat;
        double z = Math.sqrt(x * x + y * y) + 0.00002 * Math.sin(y * x_pi);
        double theta = Math.atan2(y, x) + 0.000003 * Math.cos(x * x_pi);
        double bd_lon = z * Math.cos(theta) + 0.0065;
        double bd_lat = z * Math.sin(theta) + 0.006;
        return new double[] { bd_lat, bd_lon };
    }
    //gps-gd
    public static double[] wgs2gcj(double lat, double lon) {
        double dLat = transformLat(lon - 105.0, lat - 35.0);
        double dLon = transformLon(lon - 105.0, lat - 35.0);
        double radLat = lat / 180.0 * pi;
        double magic = Math.sin(radLat);
        magic = 1 - ee * magic * magic;
        double sqrtMagic = Math.sqrt(magic);
        dLat = (dLat * 180.0) / ((a * (1 - ee)) / (magic * sqrtMagic) * pi);
        dLon = (dLon * 180.0) / (a / sqrtMagic * Math.cos(radLat) * pi);
        double mgLat = lat + dLat;
        double mgLon = lon + dLon;
        double[] loc = { mgLat, mgLon };
        return loc;
    }
    private static double transformLat(double lat, double lon) {
        double ret = -100.0 + 2.0 * lat + 3.0 * lon + 0.2 * lon * lon + 0.1 * lat * lon + 0.2 * Math.sqrt(Math.abs(lat));
        ret += (20.0 * Math.sin(6.0 * lat * pi) + 20.0 * Math.sin(2.0 * lat * pi)) * 2.0 / 3.0;
        ret += (20.0 * Math.sin(lon * pi) + 40.0 * Math.sin(lon / 3.0 * pi)) * 2.0 / 3.0;
        ret += (160.0 * Math.sin(lon / 12.0 * pi) + 320 * Math.sin(lon * pi  / 30.0)) * 2.0 / 3.0;
        return ret;
    }
    private static double transformLon(double lat, double lon) {
        double ret = 300.0 + lat + 2.0 * lon + 0.1 * lat * lat + 0.1 * lat * lon + 0.1 * Math.sqrt(Math.abs(lat));
        ret += (20.0 * Math.sin(6.0 * lat * pi) + 20.0 * Math.sin(2.0 * lat * pi)) * 2.0 / 3.0;
        ret += (20.0 * Math.sin(lat * pi) + 40.0 * Math.sin(lat / 3.0 * pi)) * 2.0 / 3.0;
        ret += (150.0 * Math.sin(lat / 12.0 * pi) + 300.0 * Math.sin(lat / 30.0 * pi)) * 2.0 / 3.0;
        return ret;
    }

    public static String[] wgs2gcj(String lng,String lat){
        double[] local = wgs2bd(Double.parseDouble(lat),Double.parseDouble(lng));
        String str_format="#.########";
//        new Point2D(DoubleUtils.double_format(local[1],str_format),DoubleUtils.double_format(local[0],str_format))
        return new String[]{local[1]+"",local[0]+""};
    }

    private static final double EARTH_RADIUS = 6371393; // 平均半径,单位：m
    
    /**
     * 通过AB点经纬度获取距离
     * @param pointA A点(经，纬)
     * @param pointB B点(经，纬)
     * @return 距离(单位：米)
     */
    public static double getDistance(Point2D pointA, Point2D pointB) {
        // 经纬度（角度）转弧度。弧度用作参数，以调用Math.cos和Math.sin
        double radiansAX = Math.toRadians(pointA.getX()); // A经弧度
        double radiansAY = Math.toRadians(pointA.getY()); // A纬弧度
        double radiansBX = Math.toRadians(pointB.getX()); // B经弧度
        double radiansBY = Math.toRadians(pointB.getY()); // B纬弧度
        if (radiansAX==radiansBX&&radiansAY==radiansBY) {
        	return 0;
		}
        // 公式中“cosβ1cosβ2cos（α1-α2）+sinβ1sinβ2”的部分，得到∠AOB的cos值
        double cos = Math.cos(radiansAY) * Math.cos(radiansBY) * Math.cos(radiansAX - radiansBX)
                + Math.sin(radiansAY) * Math.sin(radiansBY);
//        System.out.println("cos = " + cos); // 值域[-1,1]
        double acos = Math.acos(cos); // 反余弦值
//        System.out.println("acos = " + acos); // 值域[0,π]
//        System.out.println("∠AOB = " + Math.toDegrees(acos)); // 球心角 值域[0,180]
        return EARTH_RADIUS * acos; // 最终结果
    }
    
    
    public static void main(String[] args) throws Exception {
    	/*Point2D pointA=new Point2D.Double(119.324895,26.09654252);
    	Point2D pointB=new Point2D.Double(119.3205269,26.09921657);
    	System.out.println("=============="+getDistance(pointA,pointB));*/
    	String sql="select id,lat,lng from sp_android_pos1";
//    	List<Map<String, Object>> list=jdbc.queryForList(sql);
//    	String sqls[]=new String[list.size()];
//    	for(int i=0;i<list.size();i++) {
//    		double lat=Double.parseDouble(list.get(i).get("lat").toString());
//    		double lng=Double.parseDouble(list.get(i).get("lng").toString());
//    		String id=list.get(i).get("id").toString();
//    		double[] point=wgs2gcj(lat,lng);
//    		sqls[i]="update sp_android_pos1 set gd_lat='"+point[0]+"' and gd_lng='"+point[1]+"'";
//    	}
//    	jdbc.batchUpdate(sqls);
//    	
    }


}
//{x=104.8891751, y=28.31275191}
//{x=104.8891751, y=28.3127519}
//{x=104.889175, y=28.312751}
//{x=104.8891751013948, y=28.3127519194533}
