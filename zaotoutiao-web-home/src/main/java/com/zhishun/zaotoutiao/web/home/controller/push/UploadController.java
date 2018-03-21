package com.zhishun.zaotoutiao.web.home.controller.push;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import java.io.IOException;

/**
 * 异步上传文件
 * 
 * @author 王佳田
 *
 */
@Controller
@RequestMapping("/upload")
public class UploadController {

    /*@Autowired
    private UpYunFileManager upYunFileManager;

    @RequestMapping(value = "uploadPic")
    public void uploadPicList(@RequestParam(required = false) List<MultipartFile> pic,
                              HttpServletResponse response)
            throws Exception {
        if (pic != null && pic.size() > 0) {
            for (int i = 0; i < pic.size(); i++) {
                // 扩展名
                String ext = FilenameUtils.getExtension(pic.get(i).getOriginalFilename());
                byte[] bytes = pic.get(i).getBytes();
                // 1)保存大图
                String path_s = this.uploadPic(bytes, ext, response);
                // 2)生成APP使用小图
                // 取得根目录路径
                String rootPath = getClass().getResource("/").getFile().toString();
                System.out.println("##########" + rootPath);
                File file = UploadHead.uploadHead(pic.get(i).getInputStream(), rootPath, path_s);
                // 3)保存小图
                this.uploadPicSmall(UploadHead.File2byte(file), path_s, ext);
            }
        }
    }

    *//**
     * 上传图片
     *//*
    private String uploadPic(byte[] bytes,
                             String ext,
                             HttpServletResponse response)
            throws Exception {
        // 生成策略
        DateFormat df = new SimpleDateFormat("yyyyMMddHHmmssSSS");
        String format = df.format(new Date());
        Random r = new Random();
        for (int i = 0; i < 3; i++) {
            format += r.nextInt(10);
        }
        // 实例jersey
        Client client = new Client();

        // 保存db
        String path = "upload/" + format + "." + ext;
        // 保存db：APP使用（小图）
        String path_s = format + "_s" + "." + ext;
        String ip = ImgServerPathConfig.getImgServerUrl();

        // 另一台服务器的请求路径
        String url = ip + path;
        // 设置请求路径
        WebResource resource = client.resource(url);
        // 发送开始
        resource.put(String.class, bytes);

        // 返回
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("url", url);
        map.put("path", path);
        response.setContentType("text/html");
        response.getWriter().write(JacksonMapperUtil.beanToJson(map));
        response.flushBuffer();
        // 返回
        return path_s;
    }

    *//**
     * 上传小图
     *//*
    private void uploadPicSmall(byte[] bytes,
                                String path_s,
                                String ext)
            throws Exception {
        // 实例jersey
        Client client = new Client();
        // 保存db
        String path = "upload/" + path_s;
        String ip = ImgServerPathConfig.getImgServerUrl();
        // 另一台服务器的请求路径
        String url = ip + path;
        // 设置请求路径
        WebResource resource = client.resource(url);
        // 发送开始
        resource.put(String.class, bytes);
    }

    @ResponseBody
    @RequestMapping(value = "/uploadFile")
    public Object uploadFile(HttpServletResponse response,
                             HttpServletRequest request,
                             @RequestParam(value = "uploaderImage", required = false) MultipartFile file,
                             Boolean secretKey,
                             String elementId)
            throws Exception {
        String[] strs = new String[2];
        String path = request.getContextPath();
        String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path + "/";
        String rootC = basePath + "merchantAuth";
        String msg = "false";
        msg = uploadPic(file, elementId, secretKey);
        strs[0] = msg;
        strs[1] = rootC + msg;
        return strs;
    }

    private String uploadPic(MultipartFile file,
                             String elementId,
                             Boolean secretKey)
            throws Exception {
        MultipartFile fileUpload = file;
        // 后缀名
        String extensionName = null;
        // 文件名
        String fileNameLong = null;
        // 文件名
        String fileName = null;
        // 文件路径
        String filePath = null;
        // 判断是否有文件
        if (!fileUpload.isEmpty()) {
            // 获取文件上传路径名称
            fileNameLong = fileUpload.getOriginalFilename();
            // 获取文件扩展名
            extensionName = fileNameLong.substring(fileNameLong.lastIndexOf(".") + 1);
            Image src = javax.imageio.ImageIO.read(fileUpload.getInputStream());
            if (src == null) {
                return "请上传正常图片";
            }
        } else {
            return "上传文件不能为空";
        }
        InputStream image = null;
        image = fileUpload.getInputStream();
        Calendar cal = Calendar.getInstance();// 使用日历类
        int year = cal.get(Calendar.YEAR);// 得到年
        int month = cal.get(Calendar.MONTH) + 1;// 得到月
        fileName = elementId + "_" + System.currentTimeMillis() + "." + extensionName;
        filePath = "/m/auth/" + year + month + "/" + fileName;
        try {
            // 上传
            if (secretKey) {

                upYunFileManager.writeFile(filePath, toByteArray(image), "aa123456");
            }

        } catch (Exception e) {
            return "上传图片出错";
        } finally {
            if (image != null) {
                image.close();
            }
        }
        return filePath;
    }

    *//**
     * 字节流转换
     * 
     * @param in
     * @return
     * @throws IOException
     * @author 徐大伟
     * @date 2015年5月20日
     *//*
    private static byte[] toByteArray(InputStream in) throws IOException {
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        byte[] buffer = new byte[1024 * 4];
        int n = 0;
        while ((n = in.read(buffer)) != -1) {
            out.write(buffer, 0, n);
        }
        return out.toByteArray();
    }*/

}
