package com.mhtech.platform.msrv.sharing.http;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.security.GeneralSecurityException;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSession;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

import org.apache.http.Header;
import org.apache.http.HeaderElement;
import org.apache.http.HeaderElementIterator;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.protocol.HttpClientContext;
import org.apache.http.config.SocketConfig;
import org.apache.http.conn.ConnectionKeepAliveStrategy;
import org.apache.http.conn.ssl.SSLConnectionSocketFactory;
import org.apache.http.conn.ssl.TrustStrategy;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.StringEntity;
import org.apache.http.entity.mime.MultipartEntityBuilder;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.DefaultClientConnectionReuseStrategy;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.impl.conn.PoolingHttpClientConnectionManager;
import org.apache.http.message.BasicHeaderElementIterator;
import org.apache.http.protocol.HTTP;
import org.apache.http.protocol.HttpContext;
import org.apache.http.ssl.SSLContextBuilder;
import org.apache.http.util.EntityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.MediaType;
import org.springframework.util.StringUtils;

/**
 * HTTP CLIENT 连接池 适用访问源相对集中的并发场景
 * 
 * @author GM
 *
 */
public class HttpClientPoolUtils {

	private static final Logger logger = LoggerFactory
			.getLogger(HttpClientPoolUtils.class);

	private static CloseableHttpClient httpClient;
	private static ScheduledExecutorService executorService = Executors
			.newSingleThreadScheduledExecutor();
	private static PoolingHttpClientConnectionManager cm;

	private HttpClientPoolUtils() {
	}

	static {
		cm = new PoolingHttpClientConnectionManager();
		cm.setMaxTotal(100);
		cm.setDefaultMaxPerRoute(100);
		SocketConfig socketConfig = SocketConfig.custom().setTcpNoDelay(true) // 是否立即发送数据，设置为true会关闭Socket缓冲，默认为false
				.setSoReuseAddress(true) // 是否可以在一个进程关闭Socket后，即使它还没有释放端口，其它进程还可以立即重用端口
				.setSoTimeout(60 * 1000) // 接收数据的等待超时时间，单位ms
				.setSoLinger(60) // 关闭Socket时，要么发送完所有数据，要么等待60s后，就关闭连接，此时socket.close()是阻塞的
				.setSoKeepAlive(true) // 开启监视TCP连接是否有效
				.build();
		cm.setDefaultSocketConfig(socketConfig);
		ConnectionKeepAliveStrategy myStrategy = new ConnectionKeepAliveStrategy() {
			public long getKeepAliveDuration(HttpResponse response,
					HttpContext context) {
				// Honor 'keep-alive' header
				HeaderElementIterator it = new BasicHeaderElementIterator(
						response.headerIterator(HTTP.CONN_KEEP_ALIVE));
				while (it.hasNext()) {
					HeaderElement he = it.nextElement();
					String param = he.getName();
					String value = he.getValue();
					if (value != null && param.equalsIgnoreCase("timeout")) {
						try {
							return Long.parseLong(value) * 1000;
						} catch (NumberFormatException ignore) {
						}
					}
				}
				return 10 * 1000;
			}
		};
		httpClient = HttpClients
				.custom()
				.setConnectionManager(cm)
				.setKeepAliveStrategy(myStrategy)
				.evictExpiredConnections()
				.evictIdleConnections(30, TimeUnit.SECONDS)
				.setConnectionReuseStrategy(
						DefaultClientConnectionReuseStrategy.INSTANCE).build();
		// 定时打印连接池状态
		executorService.scheduleAtFixedRate(() -> {
			logger.info(cm.getTotalStats().toString());
		}, 100L, 1000L, TimeUnit.MILLISECONDS);
	}

	public static String doGet(String url) {
		String result = "";
		HttpGet get = new HttpGet(url);
		HttpContext context = HttpClientContext.create();
		CloseableHttpResponse response = null;
		try {
			response = httpClient.execute(get, context);
			HttpEntity entity = response.getEntity();
			result = EntityUtils.toString(entity);
			// 一定有这行代码，这行触发归还连接到连接池
			EntityUtils.consume(entity);
		} catch (Exception e) {
			logger.error(e.getMessage());
		} finally {
			if (response != null) {
				try {
					response.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
		return result;
	}

	public static String doPost(String url, String jsonString)
			throws Exception, IOException {

		HttpPost httpPost = new HttpPost(url);
		httpPost.setHeader(HttpMedia.X_REQUESTED_WITH,
				HttpMedia.XML_HTTP_REQUEST);
		StringEntity stringEntity = new StringEntity(jsonString,
				HttpMedia.CHARSET_UTF8);// 解决中文乱码问题
		stringEntity.setContentEncoding(HttpMedia.CHARSET_UTF8);
		stringEntity.setContentType(HttpMedia.APPLICATION_JSON);
		httpPost.setEntity(stringEntity);

		// 判断是https还是http请求
//		if ("https".equals(httpPost.getURI().getScheme())) {
//			httpClient = createSSLInsecureClient();
//		} else {
//			httpClient = HttpClients.createDefault();
//		}
		String result = "";
		CloseableHttpResponse response = null;
		HttpEntity entity = null;
		try {
			response = httpClient.execute(httpPost);
			entity = response.getEntity();
			Header[] headers = response.getAllHeaders();
			for (Header header : headers) {
//				System.err.println(header.getName() + " : " + header.getValue());
			}
			result = EntityUtils.toString(entity, HttpMedia.CHARSET_UTF8);
		} catch (IOException ex) {
			logger.error(ex.getMessage());
		} finally {
			EntityUtils.consume(entity);
			if (response != null) {
				response.close();
			}
		}
		return result;

	}

	public static String doPost(String url, HttpEntity httpEntity) throws Exception {
		HttpPost httpPost = new HttpPost(url);
		httpPost.setEntity(httpEntity);
		// 判断是https还是http请求
//		if ("https".equals(httpPost.getURI().getScheme())) {
//			httpClient = createSSLInsecureClient();
//		} else {
//			httpClient = HttpClients.createDefault();
//		}
		String result = "";
		HttpEntity entity = null;
		try (CloseableHttpResponse response = httpClient.execute(httpPost)) {
			entity = response.getEntity();
			result = EntityUtils.toString(entity, HttpMedia.CHARSET_UTF8);
		} catch (IOException ex) {
			logger.error(ex.getMessage());
		} finally {
			EntityUtils.consume(entity);
		}
		return result;
	}
	
	/**
	 * 是否存在上传文件
	 * @param request
	 * @return true or false
	 */
	public static boolean isMultipartReqeust(HttpServletRequest request) {
		return request.getContentType().toLowerCase().startsWith(HttpMedia.MULTIPART_FORM_DATA);
	}
	
	public static boolean copyHeadersIfMultipart(CloseableHttpResponse response, HttpServletResponse resp) throws UnsupportedEncodingException {
		Header[] headers = response.getAllHeaders();
		for (Header header : headers) {
			if(header.getName().equalsIgnoreCase("content-type")) continue;
			if(HttpMedia.CONTENT_DISPOSITION.equalsIgnoreCase(header.getName())) {
				resp.setHeader(header.getName(), String.format("%s;filename=%s", "attachment", 
						new String(getMultipartFilename(response).getBytes("UTF-8"), "ISO-8859-1")
						));
				resp.setHeader("Content-Type", MediaType.APPLICATION_OCTET_STREAM_VALUE);
				return true;
			}
		}
		return false;
	}
	
	public static String getMultipartFilename(CloseableHttpResponse response) {
		Header[] headers = response.getAllHeaders();
		try {
			for (Header header : headers) {
				if (HttpMedia.CONTENT_DISPOSITION.equalsIgnoreCase(header
						.getName())) {
					HeaderElement[] els = header.getElements();
					for (HeaderElement el : els) {
						NameValuePair pair = el.getParameterByName("filename");
						if(pair != null)
							return new String(pair.getValue().getBytes("ISO-8859-1"),
									"UTF-8");
					}
				}
			}
		} catch (UnsupportedEncodingException e) {
			logger.error(e.getMessage());
		}
		return null;
	}
	
	public static boolean isMultipart(Part part) {
		Map<String, String> elements = getMultipartHeaderElements(part);
		return part.getHeader(HttpMedia.CONTENT_DISPOSITION) != null && !StringUtils.isEmpty(elements.get("filename"));
	}
	
	public static Map<String, String> getMultipartHeaderElements(Part part) {
		Map<String, String> elements = new HashMap<String, String>();
		part.getHeaderNames().forEach(headerName -> {
			if(HttpMedia.CONTENT_DISPOSITION.equalsIgnoreCase(headerName)) {
				String headerValue = part.getHeader(headerName);
				String[] els = headerValue.split(";");
				try {
					for (int i = 0; i < els.length; i++) {
						String el = els[i].trim();
						String[] keyAndValue = el.split("=");
						elements.put(keyAndValue[0], keyAndValue.length > 1 ? 
								keyAndValue[1].replace("\"", "") : null);
					}
				} catch (Exception e) {
					logger.error(e.getMessage());
				}
			}
		});
		return elements;
	}
	
	private static void addBinaryBody(MultipartEntity me, Part part) {
		Map<String, String> elements = getMultipartHeaderElements(part);
		String path = System.getProperty("user.dir");
		//日志目录
		File file = new File(path + "\\logs\\" + elements.get("filename"));
		try(
				InputStream is = part.getInputStream();
				OutputStream os = new FileOutputStream(file, false);
				) {
			byte[] bytes = new byte[1024];
			while(is.read(bytes) != -1) {
				os.write(bytes);
			}
			os.flush();
			me.addBinaryBody(part.getName(), file);
		} catch (IOException e) {
			logger.error(e.getMessage());
		} finally {
		}
	}
	
	public static void copyMultipartResponse(String targetUrl, HttpServletResponse resp) throws IOException {
		copyMultipartResponse(targetUrl, null, resp);
	}
	
	/**
	 * 复制响应流
	 * @param targetUrl
	 * @param resp
	 * @throws IOException 
	 */
	public static void copyMultipartResponse(String targetUrl, String requestBody, HttpServletResponse resp) throws IOException {
		HttpPost httpPost = new HttpPost(targetUrl);
		
		if(!StringUtils.isEmpty(requestBody)) {
			httpPost.setHeader(HttpMedia.X_REQUESTED_WITH,
					HttpMedia.XML_HTTP_REQUEST);
			StringEntity stringEntity = new StringEntity(requestBody,
					HttpMedia.CHARSET_UTF8);// 解决中文乱码问题
			stringEntity.setContentEncoding(HttpMedia.CHARSET_UTF8);
			stringEntity.setContentType(HttpMedia.APPLICATION_JSON);
			httpPost.setEntity(stringEntity);
		}
		
		
		// 判断是https还是http请求
//		if ("https".equals(httpPost.getURI().getScheme())) {
//			httpClient = createSSLInsecureClient();
//		} else {
//			httpClient = HttpClients.createDefault();
//		}
		HttpEntity entity = null;
		byte[] bytes = new byte[1024];
//		String path = System.getProperty("user.dir");
		try (CloseableHttpResponse response = httpClient.execute(httpPost);
				ByteArrayOutputStream baos = new ByteArrayOutputStream();) {
			if(copyHeadersIfMultipart(response, resp)) {

				//日志目录
//				File file = new File(path + "\\logs\\" + getMultipartFilename(response));
//				OutputStream os = new FileOutputStream(file, false);
				entity = response.getEntity();
				InputStream is = entity.getContent();
				int len = 0;
				while((len = is.read(bytes)) != -1) {
					baos.write(bytes, 0, len);
				}
				resp.getOutputStream().write(baos.toByteArray());
				resp.setContentType(MediaType.APPLICATION_OCTET_STREAM_VALUE);
				baos.flush();
			}
		} catch (IOException ex) {
			logger.error(ex.getMessage());
		} finally {
			EntityUtils.consume(entity);
		}
	}
	
	/**
	 * 转发文件上传请求
	 * @param targetUrl 目标路径
	 * @param request 源请求
	 * @return 响应值
	 * @throws Exception
	 */
	public static String forwardMultipartReqeust(String targetUrl, HttpServletRequest request) throws Exception {
		if(!isMultipartReqeust(request)) return null;
		MultipartEntity me = buildMultipartEntity();
		request.getParts().forEach(part -> {
			if(isMultipart(part)) {
				addBinaryBody(me, part);
			}
		});
		HttpPost httpPost = new HttpPost(targetUrl);
		httpPost.setEntity(me.build());
		// 判断是https还是http请求
//		if ("https".equals(httpPost.getURI().getScheme())) {
//			httpClient = createSSLInsecureClient();
//		} else {
//			httpClient = HttpClients.createDefault();
//		}
		String result = "";
		HttpEntity entity = null;
		try (CloseableHttpResponse response = httpClient.execute(httpPost)) {
			entity = response.getEntity();
			result = EntityUtils.toString(entity, HttpMedia.CHARSET_UTF8);
		} catch (IOException ex) {
			logger.error(ex.getMessage());
		} finally {
			EntityUtils.consume(entity);
		}
		return result;
	}

	// 创建 SSL连接
	public static CloseableHttpClient createSSLInsecureClient() {
		try {
			SSLContext sslContext = new SSLContextBuilder().loadTrustMaterial(
					new TrustStrategy() {
						@Override
						public boolean isTrusted(X509Certificate[] chain,
								String authType) throws CertificateException {
							return true;
						}
					}).build();
			SSLConnectionSocketFactory sslsf = new SSLConnectionSocketFactory(
					sslContext, new HostnameVerifier() {
						@Override
						public boolean verify(String hostname,
								SSLSession session) {
							return true;
						}
					});
			return HttpClients.custom().setSSLSocketFactory(sslsf).build();
		} catch (GeneralSecurityException ex) {
			throw new RuntimeException(ex);
		}
	}
	
	public static MultipartEntity buildMultipartEntity() {
		return new MultipartEntity();
	}
	
	public static final class MultipartEntity {
		private MultipartEntityBuilder builder = MultipartEntityBuilder.create();

		private MultipartEntity() {}
		
		public MultipartEntity addBinaryBody(String name, InputStream stream) {
			builder.addBinaryBody(name, stream);
			return this;
		}
		
		public MultipartEntity addBinaryBody(String name, File file) {
			builder.addBinaryBody(name, file);
			return this;
		}
		
		public MultipartEntity addBinaryBody(String name, InputStream stream, ContentType contentType, String filename) {
			builder.addBinaryBody(name, stream, contentType, filename);
			return this;
		}
		
		public HttpEntity build() {
			return builder.build();
		}
		
		public MultipartEntityBuilder getMultipartEntity() {
			return builder;
		}
	}
}
