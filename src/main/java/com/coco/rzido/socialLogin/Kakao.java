package com.coco.rzido.socialLogin;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.URL;

import javax.net.ssl.HttpsURLConnection;

import com.coco.rzido.social.dto.SocialDTO;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

public class Kakao implements ISocialAuth{

	public enum HttpMethodType { POST, GET, DELETE }
	private static final String API_SERVER_HOST  = "https://kapi.kakao.com";
	private static final String USER_ME_PATH = "/v2/user/me";
	private String token;
	private SocialDTO loginVo;
	
	public Kakao(String token) {
		this.token = token;
	}
	
	private void connection(){
		String request = null;
		
		request = request(USER_ME_PATH);
		
		JsonParser parser = new JsonParser();
        JsonElement jsonElement = parser.parse(request.toString());
		JsonObject asJsonObject = jsonElement.getAsJsonObject();
		String id = asJsonObject.get("id").toString();
		String name = asJsonObject.get("properties").getAsJsonObject().get("nickname").toString();
		
		loginVo = new SocialDTO();
		loginVo.setSocialKey(id.replaceAll("\"", ""));
		loginVo.setName(name.replaceAll("\"", ""));
	}
	
	public SocialDTO userInfo(){
		connection();
		
		return loginVo;
	}
	
	public String request(final String apiPath) {
        return request(HttpMethodType.GET, apiPath, null);
    }

    public String request(final HttpMethodType httpMethod, final String apiPath) {
        return request(httpMethod, apiPath, null);
    }

    public String request(HttpMethodType httpMethod, final String apiPath, final String params) {

        String requestUrl = API_SERVER_HOST + apiPath;
        if (httpMethod == null) {
            httpMethod = HttpMethodType.GET;
        }
        if (params != null && params.length() > 0
                && (httpMethod == HttpMethodType.GET || httpMethod == HttpMethodType.DELETE)) {
            requestUrl += params;
        }

        HttpsURLConnection conn;
        OutputStreamWriter writer = null;
        BufferedReader reader = null;
        InputStreamReader isr = null;

        try {
            final URL url = new URL(requestUrl);
            conn = (HttpsURLConnection) url.openConnection();
            conn.setRequestMethod(httpMethod.toString());
            conn.setRequestProperty("Authorization", "Bearer " + this.token);
            conn.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
            conn.setRequestProperty("charset", "utf-8");

            if (params != null && params.length() > 0 && httpMethod == HttpMethodType.POST) {
                conn.setDoOutput(true);
                writer = new OutputStreamWriter(conn.getOutputStream());
                writer.write(params);
                writer.flush();
            }

            final int responseCode = conn.getResponseCode();
//            System.out.println(String.format("\nSending '%s' request to URL : %s", httpMethod, requestUrl));
//            System.out.println("Response Code : " + responseCode);
            if (responseCode == 200)
                isr = new InputStreamReader(conn.getInputStream(),"UTF-8");
            else
                isr = new InputStreamReader(conn.getErrorStream(),"UTF-8");

            reader = new BufferedReader(isr);
            final StringBuffer buffer = new StringBuffer();
            String line;
            while ((line = reader.readLine()) != null) {
                buffer.append(line);
            }
            return buffer.toString();

        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (writer != null) try { writer.close(); } catch (Exception ignore) { }
            if (reader != null) try { reader.close(); } catch (Exception ignore) { }
            if (isr != null) try { isr.close(); } catch (Exception ignore) { }
        }

        return null;
    }

}
