package com.weixk.helloworld.web;

import com.weixk.helloworld.domain.Result;
import org.apache.catalina.servlet4preview.http.HttpServletRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.util.List;

/**
 *
 * Created by weixk on 16/12/13.
 */
@RestController
@RequestMapping(value = "/img")
public class ImageController {

    private static final Logger log = LoggerFactory.getLogger(ImageController.class);
    private static final String IMG_PATH = "/Users/www1/img/";
    private static final String SERVER = "http://127.0.0.1:8080";
//    @RequestMapping(value = "/upload", method = RequestMethod.POST)
//    public Result<String> uploadImg(HttpServletRequest request) {
//        List<MultipartFile> files = ((MultipartHttpServletRequest)request).getFiles("file");
//        BufferedOutputStream stream = null;
//        StringBuilder builder = new StringBuilder();
//        for (MultipartFile file : files) {
//            if (file.isEmpty()) continue;
//            try {
//                String fileName = IMG_PATH + file.getOriginalFilename();
//                builder.append(SERVER).append("/download").append(fileName).append(";");
//                byte[] bytes = file.getBytes();
//                stream = new BufferedOutputStream(new FileOutputStream(new File(fileName)));
//                stream.write(bytes);
//                stream.flush();
//                stream.close();
//            } catch (IOException e) {
//                return new Result<String>(0, "上传图片失败", e.getMessage());
//            }
//        }
//        return new Result<String>(1, "上传图片成功", builder.toString());
//    }

    @RequestMapping(value = "/upload", method = RequestMethod.POST)
    public Result<String> uploadImg(@RequestParam(value = "file") MultipartFile file) throws IOException {
        if (!file.isEmpty()) {
            String path = IMG_PATH + file.getOriginalFilename();
            BufferedOutputStream outputStream = new BufferedOutputStream(new FileOutputStream(new File(path)));
            outputStream.write(file.getBytes());
            outputStream.flush();
            outputStream.close();
            return new Result<String>(1, "图片上传成功", SERVER + "/img/download/" + file.getOriginalFilename());
        }
        return new Result<String>(0, "图片上传失败");
    }

    @RequestMapping(value = "/download/{path}", method = RequestMethod.POST)
    public void downloadImg(@PathVariable String path, HttpServletResponse response) throws IOException {
        log.info("path : {}", IMG_PATH + path + ".jpg");
        File file = new File(IMG_PATH + path + ".jpg");
        ServletOutputStream outputStream = response.getOutputStream();
        BufferedOutputStream bufferedOutputStream = new BufferedOutputStream(outputStream);
        FileInputStream inputStream = new FileInputStream(file);
        BufferedInputStream bufferedInputStream = new BufferedInputStream(inputStream);
        byte[] b = new byte[1024];
        if (inputStream.read(b, 0, b.length) >= 0) {
            bufferedOutputStream.write(b, 0, b.length);
        }
        bufferedOutputStream.flush();
        bufferedInputStream.close();
        bufferedOutputStream.close();
        inputStream.close();
    }
}
