package com.tao;


import com.aliyun.oss.OSS;
import com.aliyun.oss.OSSClientBuilder;
import com.aliyun.oss.model.PutObjectResult;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.io.InputStream;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

/**
 * tao
 */
@Component
public class OssUpLoadUtil {

    @Value("${spring.cloud.config.profile}")
    private String environment;

    private static String ENDPOINT = "http://oss-cn-zhangjiakou.aliyuncs.com";
    private static String ACCESS_KEY_ID = "LTAITB73t0NKMV88";
    private static String ACCESS_KEY_SECRET = "9C3RJ5IBIJu4Y3yl4PhipJIGv4qC1J";

    private static String BUCKET_NAME_SIT = "dwart-public";
    private static String BUCKET_NAME_Test = "dwart-test2";
    String urlSit = "https://img.dwhs.cn/";
    String urlTest = "http://dwart-test2.oss-cn-zhangjiakou.aliyuncs.com/";

    public Map<String,Object> upload(InputStream inputStream,String suffix){
        String BUCKET_NAME = "";
        String url = "";
        if (environment.equals("dev") || environment.equals("DEV")) {
            BUCKET_NAME = BUCKET_NAME_Test;
            url = urlTest;
        } else {
            BUCKET_NAME = BUCKET_NAME_SIT;
            url = urlSit;
        }
        Map<String,Object> result = new HashMap<>();
        OSS ossClient = null;
        try {
            StringBuilder builder = new StringBuilder();
            builder.append("DD/cv/");
            builder.append(UUID.randomUUID().toString());
            builder.append(new Date().getTime());
            builder.append(suffix);
            // 创建OSSClient实例。
            ossClient = new OSSClientBuilder().build(ENDPOINT, ACCESS_KEY_ID, ACCESS_KEY_SECRET);
            PutObjectResult putObjectResult = ossClient.putObject(BUCKET_NAME, builder.toString(), inputStream);
            String tag = putObjectResult.getETag();
            ossClient.shutdown();
            inputStream.close();
            result.put("url",url + builder.toString());
            result.put("code",200);
            result.put("result",tag);
            return result;
        } catch (Exception e) {
            e.printStackTrace();
            result.put("result",e.getMessage());
            result.put("code",500);
            return result;
        }
    }
}
