package com.dogpro.common.tool;

import java.awt.Shape;
import java.awt.geom.Point2D;
import java.awt.geom.Point2D.Double;
import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

import org.junit.Assert;
import org.junit.Test;

import com.alibaba.fastjson.JSONObject;
import com.vividsolutions.jts.awt.PointShapeFactory;
import com.vividsolutions.jts.awt.PointShapeFactory.Circle;
import com.vividsolutions.jts.geom.Coordinate;
import com.vividsolutions.jts.geom.GeometryFactory;
import com.vividsolutions.jts.geom.LinearRing;
import com.vividsolutions.jts.geom.Point;
import com.vividsolutions.jts.geom.Polygon;
import com.vividsolutions.jts.io.ParseException;
import com.vividsolutions.jts.io.WKTReader;
/**
 * 判断当前位置是否在围栏 的工具类
 * @author 
 *
 */
public class MapTools {
	private static GeometryFactory gf;//几何工厂
	static{
		gf = new GeometryFactory();
	}
	/**
	 * 判断当前位置是否在围栏内
	 * 返回一个点是否在一个多边形区域内
	 * @param point
	 *            点
	 * @param pointList
	 *            相当于是面，因为是多个点的集合
	 * @return
	 */
	public static boolean isInPolygon(Point2D.Double point,
			List<Point2D.Double> pointList) {
		java.awt.geom.GeneralPath p = new java.awt.geom.GeneralPath();
		Point2D.Double first = pointList.get(0);
		p.moveTo(first.getX(), first.getY());
		for (int i = 1; i < pointList.size(); i++) {
			Point2D.Double d = pointList.get(i);
			p.lineTo(d.getX(),d.getY());
		}
		p.closePath();
		return p.contains(point);
	}
	/**
	 * 返回的是一个Polygon 的对象
	 * @param str  所有坐标的字符串(开始点和结尾点一样) 格式：x,y|x,y|……
	 * @return
	 */
	public static Polygon returnPolygon(String str){
		try {
			String[] strs = str.split("\\|", -1);//将字符串分割成 "x,y","x,y"…… 
			int coordinateLength = strs.length;
			if ("".equals(strs[strs.length - 1])) {
				coordinateLength--;
			}
			Coordinate[] coordinatess = new Coordinate[coordinateLength];
			int i = 0;
			Coordinate coordinate =null;
			for (String point : strs) {
				String[] pointXY = point.split(",", -1);
				java.lang.Double x = java.lang.Double.parseDouble(pointXY[0]);
				java.lang.Double y = java.lang.Double.parseDouble(pointXY[1]);
				coordinate = new Coordinate(x, y);
				coordinatess[i] = coordinate;
				i++;
			}
			LinearRing shell = gf.createLinearRing(coordinatess);
			LinearRing[] holes = new LinearRing[0];
			Polygon polygon = gf.createPolygon(shell, holes);
			return polygon;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	/**
	 * 返回的是一个String 字符串
	 * @param polygon  一个面的对象
	 * @return
	 */
	public static String returnStrByPolygon(Polygon polygon){
		try {
			Coordinate[] coordinates = polygon.getCoordinates();
			StringBuilder sb = new StringBuilder();
			Point pt = null;
			for (int i = 0; i < coordinates.length; i++) {
				pt = gf.createPoint(coordinates[i]);
				sb.append(pt.getX()+","+pt.getY()+"|");
			}
			return sb.toString();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	/**
	 * 通过 Polygon对象返回一个  List<Point2D.Double> 集合 --> 面
	 * @param polygon    Polygon对象
	 * @return
	 */
	public static List<Point2D.Double> getPointListByPolygon(Polygon polygon){
		Coordinate[] coordinates = polygon.getCoordinates();
		List<Point2D.Double> list=new ArrayList<Point2D.Double>();
		Point pt = null;
		Point2D.Double e = null;
		for (int i = 0; i < coordinates.length; i++) {
			pt = gf.createPoint(coordinates[i]);
			e = new Point2D.Double(pt.getX(), pt.getY());
			list.add(e);
		}
		return list;
	}
	/**
	 * 通过字符串返回  Polygon
	 * @param str -->Polygon((11 12,12 12,11 12))
	 * @return
	 * @throws ParseException
	 */
	public static  Polygon createPolygonByWKT(String str) throws ParseException{
        WKTReader reader = new WKTReader(gf);
        Polygon polygon = (Polygon)reader.read(str);
        return polygon;
    }
	public static List<java.lang.Double[]> returnListStrByPolygon(Polygon polygon){
		Coordinate[] coordinates = polygon.getCoordinates();
		Point pt = null;
		List<java.lang.Double[]> ls = new ArrayList<java.lang.Double[]>();
		java.lang.Double[] temp = null;
		for (int i = 0; i < coordinates.length; i++) {
			pt = gf.createPoint(coordinates[i]);
			temp = new java.lang.Double[2];
			temp[0] = pt.getX();
			temp[1] = pt.getY();
			ls.add(temp);
		}
		return ls;
	}
	/**
	 * 获取当前经纬度对应的范围，单位是公里    ---->   半径
	 * @param point  当前经纬度
	 * @param km  公里
	 * @return  返回一个圆面 <所有的点>
	 */
	public static List<Point2D.Double> getCurrentCircle(Point2D.Double point,java.lang.Double km){
		java.lang.Double MY_R = km/111;//半径
		java.lang.Double x = point.getX();//经度  -->113.116439
		java.lang.Double y = point.getY();//纬度  -->22.97107 + 2000  = 22.99107
		DecimalFormat format = new DecimalFormat("0.000000");//六位小数点
		y =java.lang.Double.valueOf(format.format(((y* 100000)+km*1000)/100000));
		java.lang.Double firstX = (java.lang.Double) (x - MY_R * Math.sin(Math.PI * (0 - 90) / 180));  
    	java.lang.Double firstY = (java.lang.Double) (y - MY_R + MY_R * Math.cos(Math.PI * (0 - 90) / 180));
        List<Double> points=new ArrayList<Point2D.Double>();
		for (int i = 0; i < 360; i += 1) {  
        	java.lang.Double xx = (java.lang.Double) (x - MY_R * Math.sin(Math.PI * (i - 90) / 180));
        	java.lang.Double yy = (java.lang.Double) (y - MY_R + MY_R * Math.cos(Math.PI * (i - 90) / 180));
    		points .add(new Point2D.Double(xx, yy));
        }
        points.add(new Point2D.Double(firstX, firstY));//把开始的点又放回来
		return points;
	}
	@Test
	public void testKM(){
		DecimalFormat format = new DecimalFormat("0.000000");
//		System.out.println(format.format(22.971076));
		System.out.println( format.format(((22.971072 * 100000)+2000)/100000));
		java.lang.Double y = 22.971072;
		y =java.lang.Double.valueOf(format.format(((y* 100000)+2000)/100000));
		System.out.println(y);
	}
	/**
	 * 通过一个点的集合返回一个 面Polygon
	 * @param listPoint 点的集合
	 * @return  面
	 */
	public static Polygon getPolygonByListPoint(List<Point2D.Double> listPoint){
		Coordinate[] coordinatess = new Coordinate[listPoint.size()];
		Coordinate coordinate = null;
		int i = 0;
		for (Double d: listPoint) {
			coordinate = new Coordinate(d.getX(), d.getY());
			coordinatess[i] = coordinate;
			i++;
		}
		LinearRing shell = gf
				.createLinearRing(coordinatess);
		LinearRing[] holes = new LinearRing[0];
		Polygon polygon = null;
		try {
			polygon = gf.createPolygon(shell, holes);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return polygon;
	}
//	
//	
//	private final static double PI = 3.14159265358979323; // 圆周率
//    private final static double R = 6371229; // 地球的半径
//
//    public static double getDistance(double longt1, double lat1, double longt2,double lat2) {
//        double x, y, distance;
//        x = (longt2 - longt1) * PI * R
//                * Math.cos(((lat1 + lat2) / 2) * PI / 180) / 180;
//        y = (lat2 - lat1) * PI * R / 180;
//        distance = Math.hypot(x, y);
//        return distance;
//    }
//    public static double Distance(double long1, double lat1, double long2,  
//            double lat2) {  
//        double a, b, R;  
//        R = 6378137; // 地球半径  
//        lat1 = lat1 * Math.PI / 180.0;  
//        lat2 = lat2 * Math.PI / 180.0;  
//        a = lat1 - lat2;  
//        b = (long1 - long2) * Math.PI / 180.0;  
//        double d;  
//        double sa2, sb2;  
//        sa2 = Math.sin(a / 2.0);  
//        sb2 = Math.sin(b / 2.0);  
//        d = 2  
//                * R  
//                * Math.asin(Math.sqrt(sa2 * sa2 + Math.cos(lat1)  
//                        * Math.cos(lat2) * sb2 * sb2));  
//        return d;  
//    } 
	@Test
	public void test(){
//		String[] strs = "a|a|".split("\\|", -1);
//		System.out.println(strs[2]);
		Point2D.Double point2D = new Point2D.Double(113.147978,23.037803);
		Polygon polygon = null;//这里为一个面的对象-->从数据库拿
		List<Point2D.Double> pointList = MapTools.getPointListByPolygon(polygon);
		boolean withinGeo = MapTools.isInPolygon(point2D,pointList);
		System.out.println(withinGeo);
		Assert.assertTrue(withinGeo);
	}
//    @Test
//    public void testDistance(){
//    	double d = MapTools.Distance(113.147978,23.037803, 116.147978,10.037803);
//    	//公里
//    	System.out.println(d);
//    	d = d/1000.0;
//    	System.out.println(d);
//    }
    
    @Test
    public void test1(){
    	List<Point2D.Double> points = new ArrayList<Point2D.Double>();
    	//1 --> 111 公里    1/111 = ?/2   111*? = 2
    	java.lang.Double gongli = 2.0;//公里
    	java.lang.Double MY_R = 1/gongli/111;//113.153285,23.053982
    	java.lang.Double firstX = (java.lang.Double) (113.153285 - MY_R * Math.sin(Math.PI * (0 - 90) / 180));  
    	java.lang.Double firstY = (java.lang.Double) (23.053982 - MY_R + MY_R * Math.cos(Math.PI * (0 - 90) / 180));
    	StringBuilder str = new StringBuilder();
        for (int i = 0; i < 360; i += 1) {  
        	java.lang.Double x = (java.lang.Double) (113.153285 - MY_R * Math.sin(Math.PI * (i - 90) / 180));  
        	java.lang.Double y = (java.lang.Double) (23.053982 - MY_R -  
                    + MY_R * Math.cos(Math.PI * (i - 90) / 180));
        	str.append(x+","+y+"|");
    		points .add(new Point2D.Double(x, y));
        }
        str.append(firstX+","+firstY);
        
        points.add(new Point2D.Double(firstX, firstY));
        Polygon polygon = MapTools.returnPolygon(str.toString());
        System.out.println(str);
//		List<java.lang.Double[]> list = MapTools.returnListStrByPolygon(polygon);
        //System.out.println(JSONObject.toJSONString(list));
    }
    public void testLL(){
    	//[ (A点经度 - B点经度)^2 + (A点纬度 - B点纬度)^2 ] ^ (1/2) = [ (11695400 - 11695300)^2
    	// + (3995400 - 3995300)^2 ] ^(1/2) =(10000+10000) ^ (1/2) =141米
    	double ax = 113.0;
    	double ay = 23.0;
    	double bx = 112.0;
    	double by = 22.0;
//    	double a = [(ax - bx)^2 + (ay - by)^2]^(1/2);
    }
}
