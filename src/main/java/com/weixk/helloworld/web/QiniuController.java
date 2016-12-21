package com.weixk.helloworld.web;

import com.qiniu.common.Zone;
import com.qiniu.http.Response;
import com.qiniu.storage.Configuration;
import com.qiniu.storage.UploadManager;
import com.qiniu.util.Auth;
import com.weixk.helloworld.domain.Result;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.UUID;

/**
 * 图片保存到七牛云中
 * Created by weixk on 16/12/14.
 */
@RestController
@RequestMapping(value = "/qiniu")
public class QiniuController {

    private static final Logger log = LoggerFactory.getLogger(QiniuController.class);
    @Value(value = "${qiniu.accesskey}")
    private String accessKey;
    @Value(value = "${qiniu.secretkey}")
    private String screctKey;
    @Value(value = "${qiniu.domain}")
    private String domain;
    @PostMapping(value = "/upload")
    public Result<String> upload(@RequestParam(value = "file") MultipartFile file) {
        String key = UUID.randomUUID().toString();
        Auth auth = Auth.create(accessKey, screctKey);
        Zone zone = Zone.autoZone();
        Configuration configuration = new Configuration(zone);
        UploadManager uploadManager = new UploadManager(configuration);
        String uploadToken = auth.uploadToken("test");
        try {
            Response response = uploadManager.put(file.getBytes(), key, uploadToken);
            if (response.isOK())
                return new Result<>(1, "上传成功", response.bodyString());
            return new Result<>(0, response.bodyString());
        } catch (IOException e) {
            return new Result<>(0, e.getMessage());
        }
    }

    @GetMapping(value = "download")
    public Result<String> download(@RequestParam(value = "key") String key) {
        Auth auth = Auth.create(accessKey, screctKey);
        String url = "http://" + domain + "/" + key;
        String downloadUrl = auth.privateDownloadUrl(url);
        if (!StringUtils.hasLength(downloadUrl))
            return new Result<>(0, "获取下载路径失败");
        return new Result<>(1, "获取下载路径成功", downloadUrl);
    }
}
