package org.example;

import org.example.picture.BasePicConvert;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.*;

public class TestBasePic {

    private static Logger logger = LoggerFactory.getLogger(TestBasePic.class);

    public static void main(String[] args) {

        InputStreamReader inputStream = new InputStreamReader(System.in);
        BufferedReader reader = new BufferedReader(inputStream);
        System.out.println("请输入你需要的操作: 1:base64码转图片; 2:图片转base64码");
        String operate = null;
        try {
            operate = reader.readLine();
            if(operate.equals("1")){
                System.out.println("请输base64码所在文件路径:D:\\\\loghome\\\\");
                String path = reader.readLine();

                File file = new File(path);
                FileInputStream fis = new FileInputStream(file);
                BufferedReader readFile = new BufferedReader(new InputStreamReader(fis));
                String base64Code = null;
                StringBuffer buffer = new StringBuffer();
                while((base64Code = readFile.readLine()) != null){
                    System.out.println(base64Code);
                    buffer.append(base64Code);
                }
                //base64Code = base64Code.replaceAll("\\s+", "").replaceAll("\\n","");
                //System.out.println("base64Code : " + buffer.toString());
                base64Code = buffer.toString().replaceAll("\\s+", "").replaceAll("\\n","");
                readFile.close();
                fis.close();
                System.out.println("请输入要转换base64码转回新的图片路径和图片名称,例如: D:\\\\tpdataPic\\\\taipingnew.jpg");
                String imageUrlPicName = reader.readLine();
                boolean res = BasePicConvert.Base64ToImage(base64Code, imageUrlPicName);
                if(res){
                    System.out.println("转换结束, 可以到图片设定路径下查看...");
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        if(operate.equals("2")){
            System.out.println("请输入要转换base64码的图片路径和图片名称,例如: D:\\\\tpdataPic\\\\taiping.jpg");
            try {
                String imageUrlPicName = reader.readLine();         // 例如: D:\\tpdataPic\\taiping.jpg"
                String base64Code = BasePicConvert.ImageToBase64ByLocal(imageUrlPicName);
                logger.info("base64Code:");
                logger.info(base64Code);
                logger.info("base64Code去空格换行:");
                logger.info(base64Code.replaceAll("\\s+", "").replaceAll("\\n",""));
                System.out.println("转换结束, 可以到日志文件查看base64Code...");
            }catch (IOException e) {
                e.printStackTrace();
            }
        }

    }

}
