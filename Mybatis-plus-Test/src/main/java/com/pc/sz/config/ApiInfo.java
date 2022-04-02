package com.pc.sz.config;


import lombok.Data;
import org.springframework.http.HttpHeaders;
import org.springframework.util.MultiValueMap;

@Data
public class ApiInfo {

    /**
     * api 相对路径
     */
    private String apiPath;

    /**
     * api 相对路径
     */
    private String[] headers;

    public MultiValueMap<String, String> getTokenMap() {
        String[] headers = getHeaders();
        if (headers == null || headers.length == 0) {
            return new HttpHeaders();
        }
        HttpHeaders httpHeaders = new HttpHeaders();
        for (String header : headers) {
            String[] split = header.split("=");
            httpHeaders.add(split[0], split[1]);
        }
        return httpHeaders;
    }
}
