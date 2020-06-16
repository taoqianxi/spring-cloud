package com.tao;


import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

/**
 * tao
 */
public class TZipUtil {

    public void getJar(List<JarBean> jarBeanList, HttpServletResponse response) throws Exception{
        ZipOutputStream out = new ZipOutputStream(response.getOutputStream());
        InputStream is = null;
        for(int i = 0; i < jarBeanList.size(); i++ ){
            try {
                //添加ZipEntry，并ZipEntry中写入文件流
                //这里，加上i是防止要下载的文件有重名的导致下载失败
                out.putNextEntry(new ZipEntry(i + jarBeanList.get(i).getFileName()));
                is = jarBeanList.get(i).getInputStream();
                byte[] b = new byte[1024];
                int length = 0;
                while((length = is.read(b))!= -1){
                    out.write(b, 0, length);
                    out.flush();
                }
                out.closeEntry();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        try {

        } finally {
            out.flush();
            out.close();
        }
    }
}
