/**
 * @company 杭州智顺文化传播有限公司
 * @copyright Copyright (c) 2018 - 2018
 */
package com.zhishun.zaotoutiao.common.util;

import org.apache.commons.io.IOUtils;

import javax.imageio.IIOImage;
import javax.imageio.ImageIO;
import javax.imageio.ImageWriteParam;
import javax.imageio.ImageWriter;
import javax.imageio.stream.FileImageOutputStream;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.awt.image.ColorModel;
import java.io.*;
import java.text.DecimalFormat;
import java.util.Iterator;

/**
 * @author 闫迎军(YanYingJun)
 * @version $Id: ImageUtil, v0.1 2018年03月24日 14:29闫迎军(YanYingJun) Exp $
 */
public class ImageUtil {

    /**
     * @param is 文件流
     * @param path 文件存放路径
     * @param newFileName 文件名
     * @return 文件
     * @throws Exception
     */
    public static File uploadHead(InputStream is,
                                  String path,
                                  String newFileName)
            throws Exception {
        // 文件流
        BufferedImage sourceImg = javax.imageio.ImageIO.read(is);
        // 原文件宽度
        int width = sourceImg.getWidth();
        // 原文件高度
        int height = sourceImg.getHeight();

        // 等比缩放为200*200的图片，如果宽和高有一个大于200的就进行截取
        if (width > 200 || height > 200) {
            DecimalFormat df = new DecimalFormat("0.000");
            // 计算等比
            double bili = 0.000;
            // 根据像素大的一方进行等比缩放
            if (width > height) {
                bili = Double.parseDouble(df.format(width / 200.0));
            } else {
                bili = Double.parseDouble(df.format(height / 200.0));
            }
            width = (int) (width / bili);
            height = (int) (height / bili);
        }

        // 根据计算好的宽高新建画布
        BufferedImage src = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
        src.getGraphics().drawImage(sourceImg.getScaledInstance(width, height, Image.SCALE_SMOOTH), 0, 0, null);

        File file = new File(path, newFileName);
        // 输出图像
        ImageIO.write(src, "JPEG", file);
        is.close();
        // 返回
        return file;
    }

    /**
     * 文件转换成字节
     */
    public static byte[] File2byte(File file) {
        byte[] buffer = null;
        try {
            FileInputStream fis = new FileInputStream(file);
            ByteArrayOutputStream bos = new ByteArrayOutputStream();
            byte[] b = new byte[1024];
            int n;
            while ((n = fis.read(b)) != -1) {
                bos.write(b, 0, n);
            }
            fis.close();
            bos.close();
            buffer = bos.toByteArray();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return buffer;
    }
}
