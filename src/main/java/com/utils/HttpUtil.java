package com.utils;

import java.io.*;
import java.net.*;
import java.nio.charset.Charset;
import java.util.Set;
import java.util.TreeSet;
import javax.net.ssl.*;

import org.apache.http.*;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.*;
import org.apache.http.entity.StringEntity;
import org.apache.http.entity.mime.HttpMultipartMode;
import org.apache.http.entity.mime.MultipartEntityBuilder;
import org.apache.http.entity.mime.content.FileBody;
import org.apache.http.impl.client.*;
import org.apache.http.message.BasicHeader;
import org.apache.http.protocol.HTTP;
import org.apache.http.util.CharsetUtils;
import org.apache.http.util.EntityUtils;

public class HttpUtil {

    public static String getUrl(String url) {
        String result = null;
        try {
            // 根据地址获取请求
            HttpGet request = new HttpGet(url);

            // 获取当前客户端对象
            CloseableHttpClient httpClient = HttpClients.createDefault();
            // 通过请求对象获取响应对象
            HttpResponse response = httpClient.execute(request);

            // 判断网络连接状态码是否正常(0--200都数正常)
            if (response.getStatusLine().getStatusCode() == HttpStatus.SC_OK) {
                result = EntityUtils.toString(response.getEntity());
            }
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return result;
    }

    public static String getUrl(HttpGet httpGet, String url) {
        String result = null;
        try {
            // 根据地址获取请求
            HttpGet request;
            if (httpGet == null) {
                request = new HttpGet(url);
            } else {
                request = httpGet;
            }


            // 获取当前客户端对象
            CloseableHttpClient httpClient = HttpClients.createDefault();

            // 通过请求对象获取响应对象
            HttpResponse response = httpClient.execute(request);

            // 判断网络连接状态码是否正常(0--200都数正常)
            if (response.getStatusLine().getStatusCode() == HttpStatus.SC_OK) {
                result = EntityUtils.toString(response.getEntity());
            }
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return result;
    }

    /**
     * 发起https请求并获取结果
     *
     * @param requestUrl    请求地址
     * @param requestMethod 请求方式（GET、POST）
     * @param outputStr     提交的数据
     */
    public static String httpRequest(String requestUrl, String requestMethod,
                                     String outputStr) {
        StringBuffer buffer = new StringBuffer();
        try {
            // 创建SSLContext对象，并使用我们指定的信任管理器初始化
            TrustManager[] tm = {new MyTrustManager()};
            SSLContext sslContext = SSLContext.getInstance("SSL", "SunJSSE");
            sslContext.init(null, tm, new java.security.SecureRandom());
            // 从上述SSLContext对象中得到SSLSocketFactory对象
            SSLSocketFactory ssf = sslContext.getSocketFactory();

            URL url = new URL(requestUrl);
            HttpsURLConnection httpUrlConn = (HttpsURLConnection) url
                .openConnection();
            httpUrlConn.setSSLSocketFactory(ssf);

            httpUrlConn.setDoOutput(true);
            httpUrlConn.setDoInput(true);
            httpUrlConn.setUseCaches(false);
            // 设置请求方式（GET/POST）
            httpUrlConn.setRequestMethod(requestMethod);

            if ("GET".equalsIgnoreCase(requestMethod))
                httpUrlConn.connect();

            // 当有数据需要提交时
            if (null != outputStr) {
                OutputStream outputStream = httpUrlConn.getOutputStream();
                // 注意编码格式，防止中文乱码
                outputStream.write(outputStr.getBytes("UTF-8"));
                outputStream.close();
            }

            // 将返回的输入流转换成字符串
            InputStream inputStream = httpUrlConn.getInputStream();
            // InputStreamReader inputStreamReader = new
            // InputStreamReader(inputStream, "utf-8");
            InputStreamReader inputStreamReader = new InputStreamReader(
                inputStream);
            BufferedReader bufferedReader = new BufferedReader(
                inputStreamReader);

            String str = null;
            while ((str = bufferedReader.readLine()) != null) {
                buffer.append(str);
            }
            bufferedReader.close();
            inputStreamReader.close();
            // 释放资源
            inputStream.close();
            inputStream = null;
            httpUrlConn.disconnect();
            return buffer.toString();
        } catch (ConnectException ce) {
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 上传文件
     *
     * @throws ParseException
     * @throws IOException
     */
    public static void postFile(String url, String paramName, String filepath)
        throws ParseException, IOException {
        CloseableHttpClient httpClient = HttpClients.createDefault();
        try {
            // 要上传的文件的路径
            String filePath = new String(filepath);
            // 把一个普通参数和文件上传给下面这个地址 是一个servlet
            HttpPost httpPost = new HttpPost(url);
            // 把文件转换成流对象FileBody
            File file = new File(filePath);
            FileBody bin = new FileBody(file);
            /*
             * StringBody uploadFileName = new StringBody("my.png",
			 * ContentType.create("text/plain", Consts.UTF_8));
			 */
            // 以浏览器兼容模式运行，防止文件名乱码。
            HttpEntity reqEntity = MultipartEntityBuilder.create().setMode(
                HttpMultipartMode.BROWSER_COMPATIBLE)
                .addPart("buffer", bin) // uploadFile对应服务端类的同名属性<File类型>
                // .addPart("uploadFileName", uploadFileName)//
                // uploadFileName对应服务端类的同名属性<String类型>
                .setCharset(CharsetUtils.get("UTF-8")).build();

            httpPost.setEntity(reqEntity);

            System.out.println("发起请求的页面地址 " + httpPost.getRequestLine());
            // 发起请求 并返回请求的响应
            CloseableHttpResponse response = httpClient.execute(httpPost);
            try {
                // 打印响应状态
                System.out.println(response.getStatusLine());
                // 获取响应对象
                HttpEntity resEntity = response.getEntity();
                if (resEntity != null) {
                    // 打印响应长度
                    System.out.println("Response content length: "
                        + resEntity.getContentLength());
                    // 打印响应内容
                    System.out.println(EntityUtils.toString(resEntity, Charset
                        .forName("UTF-8")));
                }
                // 销毁
                EntityUtils.consume(resEntity);
            } finally {
                response.close();
            }
        } finally {
            httpClient.close();
        }
    }

    public static String httpPostWithJSON(String url, String json) throws Exception {
        // 将JSON进行UTF-8编码,以便传输中文
        String encoderJson = URLEncoder.encode(json, HTTP.UTF_8);

        CloseableHttpClient httpClient = HttpClients.createDefault();

        HttpPost httpPost = new HttpPost(url);
        httpPost.addHeader(HTTP.CONTENT_TYPE, "application/json");

        StringEntity se = new StringEntity(encoderJson);
        se.setContentType("text/json");
        se.setContentEncoding(new BasicHeader(HTTP.CONTENT_TYPE,
            "application/json"));
        httpPost.setEntity(se);
        HttpResponse response = httpClient.execute(httpPost);
        HttpEntity entity = response.getEntity();
        String content = EntityUtils.toString(entity);
        return content;
    }


}
