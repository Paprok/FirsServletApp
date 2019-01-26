package helpers;

import javax.annotation.ManagedBean;
import javax.servlet.http.HttpServletRequest;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.HashMap;
import java.util.Map;

@ManagedBean
public class ServletHelper implements Parser{
    public ServletHelper() {
    }

    private  Map<String, String> parseFormData(String formData) throws UnsupportedEncodingException {
        Map<String, String> map = new HashMap<>();
        String[] pairs = formData.split("&");
        for(String pair : pairs){
            String[] keyValue = pair.split("=");
            // We have to decode the value because it's urlencoded. see: https://en.wikipedia.org/wiki/POST_(HTTP)#Use_for_submitting_web_forms
            String value = new URLDecoder().decode(keyValue[1], "UTF-8");
            map.put(keyValue[0], value);
        }
        return map;
    }

    public Map<String, String> parseRequest(HttpServletRequest request) throws IOException {
        BufferedReader br = request.getReader();
        String formData = br.readLine();
        System.out.println(formData);
        return parseFormData(formData);
    }
}
