package com.mobile.utils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.StringWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.aspectj.weaver.ast.Test;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;
import org.dom4j.io.OutputFormat;
import org.dom4j.io.XMLWriter;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;

public class MobileLocationTool {

	public static void main(String[] args) {
		String phone = "13761096686";// 移动 15267607453 17596566797

//		phone = "13356077805";
//		System.out.println(Utilis.getUUID());
		MobileLocationTool mobile = new MobileLocationTool();

		String s = mobile.Judge_Operator("13860602490");
		System.out.println(s);
		if (true)
			return;
		String xml = "<?xml version=\"1.0\"?>\n" + "<!DOCTYPE REQ SYSTEM \"LOCREQ.DTD\">\n" + "<REQ>\n" + "<CLIENT> \n"
				+ "    <LCSCLIENTID>3434800042</LCSCLIENTID> \n" + "    <PASSWORD>198412</PASSWORD>\n" + "</CLIENT>\n"
				+ "<ORIGINATOR>\n" + " <ORID>17596566797</ORID>\n" + " <ORID_TYPE>11</ORID_TYPE>\n" + "</ORIGINATOR>\n"
				+ "<REQ_PLATFORM>1</REQ_PLATFORM>\n" + "<SERVICEID>3434800042</SERVICEID>\n" + "<LIR>\n" + "\t<MSIDS>\n"
				+ "      <MSID>17596566797</MSID>\n" + "\t <MSID_TYPE>9</MSID_TYPE>\n" + "</MSIDS>\n" + "<GEO_INFO>\n"
				+ "  <COORD_SYS>LL</COORD_SYS>\n" + "      <DATUM>WGS-84</DATUM>\n" + "  <LL_FORMAT>DMS3</LL_FORMAT>\n"
				+ "</GEO_INFO>\n" + "<PQOS>\n" + "  <RESP_REQ>0</RESP_REQ>\n" + "<HOR_ACC>200</HOR_ACC>\n"
				+ "  <ALT_ACC>200</ALT_ACC>\n" + "</PQOS>\n" + "<PRIO>1</PRIO>\n"
				+ "<REQ_RESULTFORMAT>1</REQ_RESULTFORMAT>\n" + "</LIR>\n" + "</REQ>";
		xml = xml.replaceAll("\\r|\\n", "");
		System.out.println(xml);
		String url = "http://220.194.51.17:7001/LcsInterface/";
//        String s = Utilis.requestResult(url);
//        String s = loadJson(url);
//        System.out.println(s);
		/*
		 * String s = mobile.Judge_Operator(phone); System.out.println(s);
		 */
		/*
		 * String s = Utilis.requestResult(
		 * "https://tcc.taobao.com/cc/json/mobile_tel_segment.htm?tel=13356077805"); s =
		 * s.replaceAll("__GetZoneResult_ =", ""); JSONObject object =
		 * JSON.parseObject(s); System.out.println(object.get("catName")); String s1 =
		 * Utilis.requestResult(
		 * "https://cx.shouji.360.cn/phonearea.php?number=15298736167"); String s2 =
		 * Utilis.unicodeToCn(s1); System.out.println(s2);
		 */

		/*
		 * String status = mobile.getStatus(phone); System.out.println(status);
		 */
//        String s = mobile.openPosition(phone);//授权定位
//        System.out.println(s);
//        mobile.getPosition(phone);//获取定位信息
//		mobile.getReal_Time_Positioning(phone);
//		mobile.closePosition(phone);
//		System.out.println(unicodeToCn("用户不存在"));
//		mobile.SendSMS(phone);
//		System.out.println(unicodeToCn(""));

	}

	/**
	 * 移动、电信号 手机定位授权
	 * 
	 * @param mturl
	 * @param key
	 * @param secret
	 * @param phone
	 * @param OperatorCode
	 * @param OperatorName
	 * @return
	 */
	public static String openPosition(String mturl, String key, String secret, String phone, String OperatorCode,
			String OperatorName) {
		String url = mturl + "/authlbsopen/?key=" + key + "&secret=" + secret + "&mobile=" + phone;
		String result = Utilis.requestResult(url);
//        		"{\"host\":\"111\",\"mobile\":\"15267607453\",\"async\":0,\"proc\":1,\"resmsg\": \"授权短信发送成功,请通知用户回复短信大写的Y\",\"seqno\":\"1a0981f6bd2819f036d455195f6547e5\",\"resid\": 0}";//Utilis.requestResult(url);
//        System.out.println(result);
		// 可根据需求存入数据库
		/*
		 * {"host":"111","mobile":"15267607453","async":0,"proc":1,"resmsg":
		 * "授权短信发送成功,请通知用户回复短信大写的Y","seqno":"1a0981f6bd2819f036d455195f6547e5","resid":
		 * 0} {"host":"1","proc":1,"resmsg":"授权短信发送成功,请通知用户回复短信大写的Y","async":0,
		 * "ReturnStatus":"0000","StreamingNo":"201907191051043083","resid":0,"seqno":
		 * "eaa163549c485acc01ec214372e296bd","channel":"1"}
		 * {"host":"111","mobile":"17596566797","async":0,"proc":1,"resmsg":
		 * "授权短信发送成功,请通知用户回复短信大写的Y","seqno":"609427c43205663129011c23fe9b9707","resid":
		 * 0} { "resid": 0, "mobile":"15812345678", "resmsg": "白名单开通成功，请通知用户回复短信小写的y" }
		 * { "resid": 1, "mobile":"15812345678", "resmsg": "当前手机号已在白名单内" }
		 */
		JSONObject jsonObject = JSON.parseObject(result);
		if (jsonObject != null) {
			jsonObject.put("OperatorCode", OperatorCode);
			jsonObject.put("OperatorName", OperatorName);
			jsonObject.put("mobile", phone);
			result = jsonObject.toJSONString();
		}
		return result;
	}

	/**
	 * 移动、电信号 实时定位 返回经纬度相关信息
	 * 
	 * @param mturl
	 * @param key
	 * @param secret
	 * @param phone
	 * @param OperatorCode
	 * @param OperatorName
	 * @param requestNumber
	 * @return
	 */
	public static String getPosition(String mturl, String key, String secret, String phone, String OperatorCode,
			String OperatorName, int requestNumber) {
		String url = mturl + "/authlbsquery/?key=" + key + "&secret=" + secret + "&mobile=" + phone;
		String result = Utilis.requestResult(url);
//        		Utilis.requestResult(url);
//        "{\"host\":\"111\",\"district\":\"南湖区\",\"lng\":\"120.82522\",\"province\":\"浙江省\",\"mobile\":\"15267607453\",\"ctime\":\"2019-07-19T09:31:17.115101\",\"lat\":\"30.73596\",\"proc\":1,\"msec\":579,\"street\":\"广益路\",\"roamcity\":\"\",\"rtime\":\"2019-07-19T09:31:17.1154\", \"resmsg\":\"移动授权经纬度定位成功\",\"areacode\":\"\",\"code\":\"\",\"exinfo\":\"\",\"city\":\"嘉兴市\", \"location\":\"浙江省嘉兴市南湖区大桥镇广益路创新大厦\",\"resid\":0}";//Utilis.requestResult(url);
//        System.out.println(result);
		/*
		 * {"host":"111","district":"南湖区","lng":"120.82522","province":"浙江省","mobile":
		 * "15267607453","ctime":"2019-07-19T09:31:17.115101","lat":"30.73596","proc":1,
		 * "msec":579,"street":"广益路","roamcity":"","rtime":"2019-07-19T09:31:17.1154",
		 * "resmsg":"移动授权经纬度定位成功","areacode":"","code":"","exinfo":"","city":"嘉兴市",
		 * "location":"浙江省嘉兴市南湖区大桥镇广益路创新大厦","resid":0} 移动
		 * {"host":"1","district":"南湖区","lng":"120.821929","channel":"1","province":
		 * "浙江省","mobile":"13356077805","proc":1,"msec":900,"street":"智慧路","city":"嘉兴市",
		 * "lat":"30.743741","rtime":"2019-07-19T10:54:28.607049","ctime":
		 * "2019-07-19T10:54:28.606761","exinfo":"","resmsg":"调用成功","location":
		 * "浙江省嘉兴市南湖区大桥镇智慧路195号绿景名邸","resid":0} 电信
		 * {"host":"1","district":"南湖区","lng":"120.821929","channel":"1","province":
		 * "浙江省","mobile":"13356077805","resid":0,"street":"智慧路","msec":454,"rtime":
		 * "2019-07-19T10:56:07.607658","resmsg":"调用成功","city":"嘉兴市","ctime":
		 * "2019-07-19T10:56:07.153556","exinfo":"","lat":"30.743741","location":
		 * "浙江省嘉兴市南湖区大桥镇智慧路195号绿景名邸","type":"c"}
		 * {"host":"111","district":"南湖区","lng":"120.82522","province":"浙江省","mobile":
		 * "15298736167","ctime":"2019-07-19T11:57:15.600326","lat":"30.73594","proc":1,
		 * "msec":583,"street":"亚太路","roamcity":"","rtime":"2019-07-19T11:57:15.600654",
		 * "resmsg":"移动授权经纬度定位成功","areacode":"","code":"","exinfo":"","city":"嘉兴市",
		 * "location":"浙江省嘉兴市南湖区大桥镇广益路创新大厦","resid":0}
		 *
		 */
		JSONObject jsonObject = JSON.parseObject(result);
		// 记录次数
		int number = 1;
		// 数据请求错误重复去请求requestNumber次
		while ("-998".equals(jsonObject.get("resid").toString()) && number <= requestNumber) {
			result = Utilis.requestResult(url);
			jsonObject = JSON.parseObject(result);
			number++;
		}
		if (jsonObject != null) {
			jsonObject.put("OperatorCode", OperatorCode);
			jsonObject.put("OperatorName", OperatorName);
			jsonObject.put("original_lng", "");
			jsonObject.put("original_lat", "");
		}
		if (jsonObject != null && jsonObject.get("lng") != null && jsonObject.get("lat") != null
				&& !jsonObject.get("lng").toString().isEmpty() && !jsonObject.get("lat").toString().isEmpty()) {
			String lng = jsonObject.getString("lng");
			String lat = jsonObject.getString("lat");

			jsonObject.put("original_lng", lng);
			jsonObject.put("original_lat", lat);
			// 移动电信返回的坐标为高德坐标，统一转换为GPS坐标
			double lat_lng[] = CoordinateUtils.gcj02_To_Gps84(Double.parseDouble(lat), Double.parseDouble(lng));
			lng = DoubleUtils.double_format_String(lat_lng[1], "#.########");
			lat = DoubleUtils.double_format_String(lat_lng[0], "#.########");
			jsonObject.put("lng", lng);
			jsonObject.put("lat", lat);
			result = jsonObject.toJSONString();
		}
		return result;
	}

	/**
	 * 移动、电信号码 查询手机号状态是否开通授权
	 * 
	 * @param mturl
	 * @param key
	 * @param secret
	 * @param phone
	 * @param OperatorCode
	 * @param OperatorName
	 * @return
	 */
	public static String getStatus(String mturl, String key, String secret, String phone, String OperatorCode,
			String OperatorName) {
		String url = mturl + "/authlbsstatus/?key=" + key + "&secret=" + secret + "&mobile=" + phone;
		String result = Utilis.requestResult(url);
//		result=unicodeToCn(result);
		result = Utilis.unicodeToCn(result);
		JSONObject jsonObject = JSON.parseObject(result);
		if (jsonObject != null) {
			jsonObject.put("OperatorCode", OperatorCode);
			jsonObject.put("OperatorName", OperatorName);
			jsonObject.put("mobile", phone);
			result = jsonObject.toJSONString();
		}
		return result;
	}

	/**
	 * 移动、电信号码定位关闭
	 * 
	 * @param mturl
	 * @param key
	 * @param secret
	 * @param phone
	 * @param OperatorCode
	 * @param OperatorName
	 * @return
	 */
	public static String closePosition(String mturl, String key, String secret, String phone, String OperatorCode,
			String OperatorName) {
		String url = mturl + "/authlbsclose/?key=" + key + "&secret=" + secret + "&mobile=" + phone;
		String result = Utilis.requestResult(url);
//        "{\"resid\": 0, \"resmsg\": \"提交删除白名单成功\"}";//Utilis.requestResult(url);
		result = Utilis.unicodeToCn(result);
		JSONObject jsonObject = JSON.parseObject(result);
		if (jsonObject != null) {
			jsonObject.put("OperatorCode", OperatorCode);
			jsonObject.put("OperatorName", OperatorName);
			jsonObject.put("mobile", phone);
			result = jsonObject.toJSONString();
		}
		return result;
	}

	/**
	 * 判断运营商
	 * 
	 * @param phone
	 * @return 中国电信=02/中国联通=01/中国移动=00
	 */
	public static String Judge_Operator(String phone) {
		String result = Utilis.requestResult("https://tcc.taobao.com/cc/json/mobile_tel_segment.htm?tel=" + phone);
		result = result.replaceAll("__GetZoneResult_ =", "");
		JSONObject object = JSON.parseObject(result);
		String str_retrun = "-1";
		if (object != null && object.get("catName") != null) {
			str_retrun = object.get("catName").toString();
			switch (str_retrun) {
			case "中国移动":
				str_retrun = "00";
				break;
			case "中国联通":
				str_retrun = "01";
				break;
			case "中国电信":
				str_retrun = "02";
				break;
			default:
				str_retrun = "-1";
				break;
			}
		}
		return str_retrun;
	}
	
	/**
	 * 判断运营商
	 * 
	 * @param phone
	 * @return 中国电信=02/中国联通=01/中国移动=00
	 */
	public static Map<String,String> JudgeOperator(String phone) {
		Map<String,String> parmar=null;
		String result = Utilis.requestResult("https://tcc.taobao.com/cc/json/mobile_tel_segment.htm?tel=" + phone);
		result = result.replaceAll("__GetZoneResult_ =", "");
		JSONObject object = JSON.parseObject(result);
		String str_retrun = "";
		String name_return="-1";
		if (object != null && object.get("catName") != null) {
			name_return=object.get("catName").toString();
			switch (name_return) {
			case "中国移动":
				str_retrun = "00";
				break;
			case "中国联通":
				str_retrun = "01";
				break;
			case "中国电信":
				str_retrun = "02";
				break;
			default:
				str_retrun = "-1";
				break;
			}
			if(!"-1".equals(str_retrun)) {
				parmar=new HashMap<String, String>();
				parmar.put("name", name_return);
				parmar.put("code", str_retrun);
			}
		}
		return parmar;
	}

	/**
	 * 调用联通号码定位方法
	 * 
	 * @param unicomurl
	 * @param lcsclientid
	 * @param password
	 * @param phone
	 * @param OperatorCode
	 * @param OperatorName
	 * @return
	 */
	public static String getUnicomPosition(String unicomurl, String lcsclientid, String password, String phone,
			String OperatorCode, String OperatorName) {
		String param = str2xml(lcsclientid, password, phone);
//        System.out.println(key + "djff:" + param);
		String result_xml = unicomSendPost(unicomurl, param);
		String result = "";
		try {
			result = parsXML(result_xml);
//            "{\"mobile\":\"17596566797\",\"rtime\":\"2019-07-25 21:03:07\",\"lng\":\"120.785\",\"resid\":\"0\",\"lat\":\"30.759\"}";//parsXML(result_xml);
			JSONObject jsonObject = JSON.parseObject(result);
			if (jsonObject != null) {
				jsonObject.put("OperatorCode", OperatorCode);
				jsonObject.put("OperatorName", OperatorName);
				result = jsonObject.toJSONString();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}

	/**
	 * 联通号码定位方法
	 * 
	 * @param unicomurl
	 * @param param
	 * @return
	 */
	public static String unicomSendPost(String unicomurl, String param) {
		String url = unicomurl;
		OutputStreamWriter out = null;
		BufferedReader in = null;
		String result = "";
		try {
			URL realUrl = new URL(url);
			HttpURLConnection conn = null; // 打开和URL之间的连接
			conn = (HttpURLConnection) realUrl.openConnection(); // 发送POST请求必须设置如下两行
			conn.setDoOutput(true);
			conn.setDoInput(true);
			conn.setRequestMethod("POST"); // POST方法
			// 设置通用的请求属性
			conn.setRequestProperty("accept", "*/*");
			conn.setRequestProperty("connection", "Keep-Alive");
			conn.setRequestProperty("user-agent", "Mozilla/4.0 (compatible; MSIE 6.0; Windows NT 5.1;SV1)");
			conn.setRequestProperty("Content-Type", "application/json;charset=utf-8");
			conn.connect(); // 获取URLConnection对象对应的输出流
			out = new OutputStreamWriter(conn.getOutputStream(), "UTF-8"); // 发送请求参数
			out.write(param); // flush输出流的缓冲
			out.flush(); // 定义BufferedReader输入流来读取URL的响应
			in = new BufferedReader(new InputStreamReader(conn.getInputStream()));
			String line;
			while ((line = in.readLine()) != null) {
				result += line;
			}
		} catch (Exception e) {
			System.out.println("发送 POST 请求出现异常！" + e);
			e.printStackTrace();
		} // 使用finally块来关闭输出流、输入流
		finally {
			try {
				if (out != null) {
					out.close();
				}
				if (in != null) {
					in.close();
				}
			} catch (IOException ex) {
				ex.printStackTrace();
			}
		}
		return result;
	}

	/**
	 * 联通接口xml参数形成
	 * 
	 * @param lcsclientid
	 * @param password
	 * @param mobile
	 * @return
	 */
	public static String str2xml(String lcsclientid, String password, String mobile) {
		Document doc = DocumentHelper.createDocument();
		Element REQ = doc.addElement("REQ");

		Element CLIENT = REQ.addElement("CLIENT");
		Element LCSCLIENTID = CLIENT.addElement("LCSCLIENTID");
		LCSCLIENTID.setText(lcsclientid);
		Element PASSWORD = CLIENT.addElement("PASSWORD");
		PASSWORD.setText(password);

		Element ORIGINATOR = REQ.addElement("ORIGINATOR");
		Element ORID = ORIGINATOR.addElement("ORID");
		ORID.setText(mobile);
		Element ORID_TYPE = ORIGINATOR.addElement("ORID_TYPE");
		ORID_TYPE.setText("0");

		Element REQ_PLATFORM = REQ.addElement("REQ_PLATFORM");
		REQ_PLATFORM.setText("1");

		Element SERVICEID = REQ.addElement("SERVICEID");
		SERVICEID.setText(lcsclientid);

		Element LIR = REQ.addElement("LIR");

		Element MSIDS = LIR.addElement("MSIDS");
		Element MSID = MSIDS.addElement("MSID");
		MSID.setText(mobile);
		Element MSID_TYPE = MSIDS.addElement("MSID_TYPE");
		MSID_TYPE.setText("0");

		Element GEO_INFO = LIR.addElement("GEO_INFO");
		Element COORD_SYS = GEO_INFO.addElement("COORD_SYS");
		COORD_SYS.setText("LL");
		Element DATUM = GEO_INFO.addElement("DATUM");
		DATUM.setText("WGS-84");
		Element LL_FORMAT = GEO_INFO.addElement("LL_FORMAT");
		LL_FORMAT.setText("D3");

		Element PQOS = LIR.addElement("PQOS");
		Element RESP_REQ = PQOS.addElement("RESP_REQ");
		RESP_REQ.setText("0");
		Element HOR_ACC = PQOS.addElement("HOR_ACC");
		HOR_ACC.setText("200");
		Element ALT_ACC = PQOS.addElement("ALT_ACC");
		ALT_ACC.setText("200");

		Element PRIO = LIR.addElement("PRIO");
		PRIO.setText("1");

		Element REQ_RESULTFORMAT = LIR.addElement("REQ_RESULTFORMAT");
		REQ_RESULTFORMAT.setText("1");

		StringWriter sw = new StringWriter();
		OutputFormat format = OutputFormat.createPrettyPrint();
		format.setEncoding("utf-8");
		try {
			XMLWriter xmlWriter = new XMLWriter(sw, format);
			xmlWriter.write(doc);
		} catch (IOException ex) {
			Logger.getLogger(Test.class.getName()).log(Level.SEVERE, null, ex);
		} finally {
			try {
				sw.close();
			} catch (IOException ex) {
				Logger.getLogger(Test.class.getName()).log(Level.SEVERE, null, ex);
			}
		}
		return sw.toString();
	}

	/**
	 * 联通接口返回值xml解析
	 * 
	 * @param str_xml
	 * @return
	 * @throws DocumentException
	 */
	public static String parsXML(String str_xml) throws DocumentException {
		Map<String, String> resultMap = new HashMap<>();
		str_xml = str_xml.replaceAll("<!DOCTYPE ANS SYSTEM \"LOCANS.DTD\">", "");

		Document doc = DocumentHelper.parseText(str_xml);
		// 获取到父节点ANS
		Element ANS = doc.getRootElement();
		// 获取子节点LIA
		Element LIA = ANS.element("LIA");
		Element RESULT = LIA.element("RESULT");
		String result = RESULT.getText();
		Element POSINFOS = LIA.element("POSINFOS");
		Element POSINFO = POSINFOS.element("POSINFO");

		Element MSID = POSINFO.element("MSID");
		String mobile = "";
		if (MSID != null)
			mobile = MSID.getText();
		Element LOCALTIME = POSINFO.element("LOCALTIME");
		String localtime = "";
		if (LOCALTIME != null)
			localtime = LOCALTIME.getText();
		// 如果只有8位长度，则要进行转换
		if (localtime.length() >= 14)
			localtime = localtime.substring(0, 4) + "-" + localtime.substring(4, 6) + "-" + localtime.substring(6, 8)
					+ " " + localtime.substring(8, 10) + ":" + localtime.substring(10, 12) + ":"
					+ localtime.substring(12, 14);

		Element LATITUDE = POSINFO.element("LATITUDE");
		String lat = "";
		if (LATITUDE != null)
			lat = LATITUDE.getText();

		Element LONGITUDE = POSINFO.element("LONGITUDE");
		String lng = "";
		if (LONGITUDE != null)
			lng = LONGITUDE.getText();
		String resmsg = "";
		if ("0".equals(result))
			resmsg = "调用成功";
		else
			resmsg = "调用失败";
		resultMap.put("resid", result);
		resultMap.put("mobile", mobile);
		resultMap.put("resmsg", resmsg);
		resultMap.put("rtime", localtime);
		resultMap.put("original_lng", lng);
		resultMap.put("original_lat", lat);
		resultMap.put("lng", lng);
		resultMap.put("lat", lat);
		Object o = JSONArray.toJSON(resultMap);

		return o.toString();
	}
}
