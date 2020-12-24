package com.mobile.utils;


import com.alibaba.fastjson.JSONArray;
import com.mobile.model.Point2D;

import javax.sql.DataSource;
import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 判断坐标是否在边界内
 */
public class IsPtInPoly {
    /*static String File_path = "F:\\home\\hadoop\\data_points1\\";
    static FileWriter fw = null;
    static File fnew = null;

    static {
        fnew = new File(File_path + "txt_000.txt");
        try {
            fw = new FileWriter(fnew, true);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }*/
//
//    static JdbcTemplate jdbc = getJdbc();
//
//    /**
//     * 数据库连接
//     */
//    private static JdbcTemplate getJdbc() {
//        DataSource dsspe = new DataSourceSpe().getDataSourceSpe();
//        JdbcTemplate jdbc = new JdbcTemplate(dsspe);
//        return jdbc;
//    }

    /**
     * 判断点是否在多边形内
     *
     * @param point 检测点
     * @param pts   多边形的顶点
     * @return 点在多边形内返回true, 否则返回false
     */
    public static boolean isPtInPoly(Point2D point, List<Point2D> pts) {

        int N = pts.size();
        boolean boundOrVertex = true; //如果点位于多边形的顶点或边上，也算做点在多边形内，直接返回true
        int intersectCount = 0;//cross points count of x
        double precision = 2e-10; //浮点类型计算时候与0比较时候的容差
        Point2D p1, p2;//neighbour bound vertices
        Point2D p = point; //当前点
        p1 = pts.get(0);//left vertex
        for (int i = 1; i <= N; ++i) {//check all rays
            if (p.equals(p1)) {
                return boundOrVertex;//p is an vertex
            }
            p2 = pts.get(i % N);//right vertex
            if (p.x < Math.min(p1.x, p2.x) || p.x > Math.max(p1.x, p2.x)) {//ray is outside of our interests
                p1 = p2;
                continue;//next ray left point
            }
            if (p.x > Math.min(p1.x, p2.x) && p.x < Math.max(p1.x, p2.x)) {//ray is crossing over by the algorithm (common part of)
                if (p.y <= Math.max(p1.y, p2.y)) {//x is before of ray
                    if (p1.x == p2.x && p.y >= Math.min(p1.y, p2.y)) {//overlies on a horizontal ray
                        return boundOrVertex;
                    }

                    if (p1.y == p2.y) {//ray is vertical
                        if (p1.y == p.y) {//overlies on a vertical ray
                            return boundOrVertex;
                        } else {//before ray
                            ++intersectCount;
                        }
                    } else {//cross point on the left side
                        double xinters = (p.x - p1.x) * (p2.y - p1.y) / (p2.x - p1.x) + p1.y;//cross point of y
                        if (Math.abs(p.y - xinters) < precision) {//overlies on a ray
                            return boundOrVertex;
                        }

                        if (p.y < xinters) {//before ray
                            ++intersectCount;
                        }
                    }
                }
            } else {//special case when ray is crossing through the vertex
                if (p.x == p2.x && p.y <= p2.y) {//p crossing over p2
                    Point2D p3 = pts.get((i + 1) % N); //next vertex
                    if (p.x >= Math.min(p1.x, p3.x) && p.x <= Math.max(p1.x, p3.x)) {//p.x lies between p1.x & p3.x
                        ++intersectCount;
                    } else {
                        intersectCount += 2;
                    }
                }
            }
            p1 = p2;//next ray left point
        }

        if (intersectCount % 2 == 0) {//偶数在多边形外
            return false;
        } else { //奇数在多边形内
            return true;
        }
    }

    /**
     * 判断是否在圆形内
     *
     * @param p
     * @param c
     * @return
     */
//    public static String distencePC(Point2D p, Circle c) {//判断点与圆心之间的距离和圆半径的关系
//        String s;
//        double d2 = Math.hypot((p.getX() - c.getCC().getX()), (p.getY() - c.getCC().getY()));
//        System.out.println("d2==" + d2);
//        double r = c.getR();
//        if (d2 > r) {
//            s = "圆外";
//        } else if (d2 < r) {
//            s = "圆内";
//        } else {
//            s = "圆上";
//        }
//        return s;
//    }

    /**
     * 获取边界内坐标信息
     *
     * @param points
     * @param areas
     * @return
     */
//    public static List<Row> getPoints(List<Point2D> points, List<Point2D> areas, String types) {
//        List<Row> rows = new ArrayList<>();
//        if (points == null || areas == null)
//            return null;
//        for (Point2D point2D : points) {
//            if (isPtInPoly(point2D, areas))
//                rows.add(RowFactory.create(types, point2D.getX() + "", point2D.getY() + ""));
//        }
//        return rows;
//    }



    public static List<Point2D> getArea(String file_path_areas) {
        List<Point2D> point2DS = new ArrayList<>();
        //String file_path_areas = "F:\\home\\hadoop\\data_points1\\VI5_Area.txt";
        //      待读文件
        File file_points = new File(file_path_areas);
        //      创建字符输出流对象
        BufferedReader reader = null;
        String result = null;
        try {
            //          创建file文件关联的字符输入缓冲流
            reader = new BufferedReader(new FileReader(file_points));
            String tempString = null;
            while ((tempString = reader.readLine()) != null) {
                String str_ = tempString;
                str_ = str_.split("\t")[1];
                result = str_;

            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        if (result != null && !"".equals(result)) {
            JSONArray jsa = JSONArray.parseArray(result);
            for (Object json : jsa) {
                JSONArray jsa1 = JSONArray.parseArray(json.toString());
                point2DS.add(new Point2D(Double.parseDouble(jsa1.get(0).toString()), Double.parseDouble(jsa1.get(1).toString())));
            }
        }
        return point2DS;
    }

    public static List<Point2D> getPoint(String file_path_points) {
        List<Point2D> point2DS = new ArrayList<>();
        //String file_path_points = "F:\\home\\hadoop\\data_points1\\VI5_Points.txt\\part-00000";
        //      待读文件
        File file_points = new File(file_path_points);
        //      创建字符输出流对象
        BufferedReader reader = null;
        try {
            //          创建file文件关联的字符输入缓冲流
            reader = new BufferedReader(new FileReader(file_points));
            String tempString = null;
            while ((tempString = reader.readLine()) != null) {
                String str_ = tempString;
                String strs_[] = str_.split(",");
                point2DS.add(new Point2D(Double.parseDouble(strs_[0].toString()), Double.parseDouble(strs_[1].toString())));
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
//        if(result!=null&&!"".equals(result)){
//            System.out.println(result.toString());
//            JSONArray jsa = JSONArray.parseArray(result);
//            for (Object json:jsa){
//                JSONArray jsa1 = JSONArray.parseArray(json.toString());
//                System.out.println(jsa1.get(0));
//                point2DS.add( new Point2D(Double.parseDouble(jsa1.get(0).toString()), Double.parseDouble(jsa1.get(1).toString())));
//            }
//        }
        return point2DS;
    }


    public static void main(String[] args) {

    }


}
