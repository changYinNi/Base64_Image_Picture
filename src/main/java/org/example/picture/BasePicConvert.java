package org.example.picture;

import org.apache.commons.lang3.StringUtils;
import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;

public class BasePicConvert {

    /**
     * 本地图片转为base64码文件
     * @param imageFile: 本地图片路径
     * @return
     */
    public static String ImageToBase64ByLocal(String imageFile){
        InputStream is = null;
        byte[] data = null;
        try {
            is = new FileInputStream(imageFile);
            data = new byte[is.available()];
            is.read(data);
            is.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

        BASE64Encoder encoder = new BASE64Encoder();
        String res = encoder.encode(data);
        return res;
    }

    /**
     * 在线图片转为base64字符串
     * @param imageurl 图片线上路径
     * @return
     */
    public static String ImagetoBase64ByOnline(String imageurl){
        ByteArrayOutputStream os =  new ByteArrayOutputStream();
        try {
            URL url = new URL(imageurl);
            byte[] array = new byte[4096];
            //创建链接
            HttpURLConnection connection = (HttpURLConnection)url.openConnection();
            connection.setRequestMethod("GET");
            connection.setConnectTimeout(5000);
            InputStream stream = connection.getInputStream();
            //将内容读取到内存中
            int len = -1;
            while((len = stream.read(array)) != -1){
                os.write(array, 0, len);
            }
            stream.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        //对字节数组Base64编码
        BASE64Encoder encoder = new BASE64Encoder();
        String res = encoder.encode(os.toByteArray());
        return res;
    }


    /**
     * base64码转为图片
     * @param imageString: base64码字符串
     * @param imageFilePath: 图片路径
     * @return
     */
    public static boolean Base64ToImage(String imageString, String imageFilePath){

        if(StringUtils.isEmpty(imageString)){
            return false;
        }

        try {
            BASE64Decoder decoder = new BASE64Decoder();

            byte[] array = decoder.decodeBuffer(imageString);
            for(int index = 0; index < array.length; index++){
                if(array[index] < 0){
                    array[index] += 256;
                }
            }

            OutputStream os = new FileOutputStream(imageFilePath);
            os.write(array);
            os.flush();
            os.close();

            return true;
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }

    }

}
