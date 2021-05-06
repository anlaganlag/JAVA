package cn.stylefeng.guns.cloud.system.core.util;

import cn.stylefeng.guns.cloud.libs.config.properties.FtpProperties;
import cn.stylefeng.guns.cloud.libs.context.SpringContext;
import org.apache.commons.net.ftp.FTPClient;
import org.apache.commons.net.ftp.FTPFile;
import org.apache.commons.net.ftp.FTPReply;

import java.io.*;


import org.apache.commons.net.ftp.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletResponse;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @Author: pengfei.yang
 * @Date: 2020/5/21 9:52
 * @Version: 1.0
 **/
@Component
public class FtpUtil {


    //本地字符编码
    private static String LOCAL_CHARSET="GBK";
    //FTP默认文件名编码
    private static String SERVER_CHARSET=FTPClient.DEFAULT_CONTROL_ENCODING;

    //服务端UTF8的支持选项
    private static String COMMAND_OPEN_UTF8="OPTS UTF8";
    //开启状态
    private static String OPEN_STATUS="ON";
    //路径分隔符
    private static final String SPECTOR="/";
    //中文字符匹配正则
    private static Pattern P = Pattern.compile("[\u4e00-\u9fa5]");


    /**
     * @Author: pengfei.yang
     * @Description: 与FTP服务器建立连接
     * @Date: 2020/5/21 10:16
     * @return: org.apache.commons.net.ftp.FTPClient
     **/
    public FTPClient getFtpConnection()throws Exception{
        FTPClient ftpClient = new FTPClient();
        /*FTPClientConfig clientConfig=new FTPClientConfig();
        clientConfig.setDefaultDateFormatStr("yyyy-MM-dd HH:mm:ss");*/
        /*
            保存要由FTP控件连接使用的字符编码。某些FTP服务器要求以非ASCII编码（如UTF-8）发布命令，以便可以指定具有多字节字符表示形式（例如Big 8）的文件名。
            请注意，必须在建立连接之前进行设置。一般情况下默认不设置该项
            ftpClient.setControlEncoding("UTF-8");
            FTP默认_controlEncoding为'ISO-8859-1',若已明确FTP服务器支持UTF-8且该项设置了UTF-8
            则SERVER_CHARSET需由'ISO-8859-1'改为"UTF-8"否则连接之后的后续操作如果包含中文则会导致文件名乱码;
        */

        ftpClient.connect("192.168.0.216", 21);
        ftpClient.login("DwSelect","Jinchang001");
        int reply=ftpClient.getReplyCode();
        if(!FTPReply.isPositiveCompletion(reply)) {
            ftpClient.disconnect();
        }else{
            if (FTPReply.isPositiveCompletion(ftpClient.sendCommand(COMMAND_OPEN_UTF8, OPEN_STATUS))) {
                LOCAL_CHARSET = "UTF-8";
            }
        }
        //在设置了连接模式之后如果再调用任何连接方法，会重置连接模式为主动模式
        ftpClient.enterLocalPassiveMode();
        return ftpClient;
    }

    /**
     * 从FTP下载文件至浏览器
     * @param parentPath 文件父路径
     * @param sourceName ftp上存储的文件名
     * @param targetName 下载到浏览器的文件名
     * @param response http响应对象
     **/
    public void downloadToBrowser(String parentPath, String sourceName,String targetName, HttpServletResponse response)throws Exception{
        InputStream inputStream= null;
        OutputStream outputStream= null;
        try {
            //判断文件是否存在，存在则返回连接，否则抛出异常
            FTPClient client = checkExists(parentPath, sourceName);
            //获取文件输入流
            inputStream = client.retrieveFileStream(new String(sourceName.getBytes(LOCAL_CHARSET),SERVER_CHARSET));
            //设置流式响应头，以及设置文件名
            response.setContentType("application/octet-stream");
            response.setHeader("Content-Disposition", "attachment;filename=" + java.net.URLEncoder.encode(targetName, "UTF-8"));
            //获取响应流，write流到目标浏览器
            outputStream = response.getOutputStream();
            if (inputStream != null) {
                byte[] bytes = new byte[1024];
                int readBytes;
                while ((readBytes = inputStream.read(bytes)) != -1) {
                    outputStream.write(bytes, 0, readBytes);
                }
            }
            if (inputStream != null) {
                inputStream.close();
            }
            if (!client.completePendingCommand()) {
                client.logout();
                client.disconnect();
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if(null!=outputStream){
                outputStream.close();
            }
            if (null!= inputStream) {
                inputStream.close();
            }
        }
    }


    /**
     * 删除ftp文件
     * @param path ftp文件父路径（上一级）
     * @param fileName ftp文件名称
     * @return
     * @throws Exception
     */
    public boolean deleteFile(String path, String fileName) throws Exception {
        //获取连接
        FTPClient ftp = getFtpConnection();
        //切换工作目录至文件所在目录
        boolean flag = ftp.changeWorkingDirectory(new String(path.getBytes(LOCAL_CHARSET), SERVER_CHARSET));
        if (!flag) {
            throw new Exception("文件路径[" + path + "]不正确,或没有操作权限");
        }
        //删除文件
        return ftp.deleteFile(new String(fileName.getBytes(LOCAL_CHARSET),SERVER_CHARSET));
    }

    //获取文件输入流
    public InputStream getFileInputStream(String parentPath,String sourceName)throws Exception{
        FTPClient client = checkExists(parentPath, sourceName);
        return client.retrieveFileStream(new String(sourceName.getBytes(LOCAL_CHARSET),SERVER_CHARSET));
    }

    //获取文件输出流
    public OutputStream getFileOutPutStream(String parentPath,String sourceName)throws Exception{
        FTPClient client = checkExists(parentPath, sourceName);
        return client.storeFileStream(new String(sourceName.getBytes(LOCAL_CHARSET),SERVER_CHARSET));
    }

    //判断文件是否存在
    private FTPClient checkExists(String parentPath, String sourceName) throws Exception {
        FTPClient client = getFtpConnection();
        parentPath = parentPath.replaceAll("\\\\", "/");
        if (!parentPath.endsWith(SPECTOR)) {
            parentPath += SPECTOR;
        }
        String filePath = parentPath + sourceName;
        client.changeWorkingDirectory(parentPath);
        if (!existFile(filePath)) {
            throw new FileNotFoundException("文件不存在");
        }
        return client;
    }
    /**
     * @Author: pengfei.yang
     * @Description: 单文件上传
     * @Date: 2020/5/21 10:31
     * @Param: inputStream【文件输入流】
     * @Param: parentPath【FTP文件父路径】
     * @Param: ftpFileName【上传到FTP的文件名】
     * @return: void
     **/
    public void uploadFile(InputStream inputStream,String parentPath,String ftpFileName)throws Exception{
        FTPClient client=null;
        try {
            parentPath=parentPath.replaceAll("\\\\","/");
            try {
                client=getFtpConnection();
            } catch (Exception e) {
                throw new RuntimeException("FTP连接失败");
            }
            //若目标目录不存在则创建,并切换工作目录
            createDirIfNotExists(client,parentPath);
            client.setFileType(FTP.BINARY_FILE_TYPE);
            if(!client.storeFile(new String(ftpFileName.getBytes(LOCAL_CHARSET),SERVER_CHARSET),inputStream)){
                throw new Exception("upload failed");
            }
            client.logout();
        } catch (Exception e) {
            throw new Exception(e.getMessage(),e);
        } finally {
            if(null!=inputStream){
                inputStream.close();
            }
            if(null!=client&&client.isConnected()){
                client.disconnect();
            }
        }

    }

    /**
     * @Author: pengfei.yang
     * @Description: 判断文件路径是否存在不存在则创建并且切换工作目录至该路径的最后一层目录
     * @Date: 2020/5/21 13:57
     * @Param: client【FTP客户端连接】
     * @Param: path【目录路径】
     * @return: void
     **/
    private void createDirIfNotExists(FTPClient ftpClient,String path)throws Exception{
        //判断FTP连接是否关闭，如果关闭则新建连接
        if(new File(path).isDirectory()){
            String[] dirs=path.split("/");
            for (String dir:dirs) {
                dir=new String(dir.getBytes(LOCAL_CHARSET),SERVER_CHARSET);
                if(ftpClient.changeWorkingDirectory(dir)){
                    continue;
                }
                if(ftpClient.makeDirectory(dir)){
                    ftpClient.changeWorkingDirectory(dir);
                }else{
                    throw new Exception("目录["+path+"]创建失败");
                }
            }
        }
    }

    /**
     * @Author: pengfei.yang
     * @Date: 2020/5/21 14:30
     * @Param: path【路径】
     * @return: void
     **/
    public void createDirIfNotExists(String path)throws Exception{
        FTPClient client=getFtpConnection();
        createDirIfNotExists(client,path);
    }

    /**
     * 判断字符串中是否包含中文
     * @param str
     * 待校验字符串
     * @return 是否为中文
     * @warn 不能校验是否为中文标点符号
     */
    public static boolean isContainChinese(String str) {
        Matcher m = P.matcher(str);
        return m.find();
    }

    // 判断ftp服务器文件是否存在
    private boolean existFile(FTPClient client,String path) throws Exception {
        boolean flag = false;
        FTPFile[] ftpFileArr = client.listFiles(path);
        if (ftpFileArr.length > 0) {
            flag = true;
        }
        return flag;
    }

    /**
     * @Author: pengfei.yang
     * @Description: 对外提供的方法
     * @Date: 2020/5/21 14:28
     * @Param: path【文件路径】
     * @return: boolean
     **/
    public boolean existFile(String path){
        FTPClient ftpClient=null;
        try {
            ftpClient=getFtpConnection();
            boolean exists=existFile(ftpClient,path);
            ftpClient.logout();
            return exists;
        } catch (Exception e) {
         //   throw new Exception(e.getMessage(),e);
        } finally {
            if(null!=ftpClient&&ftpClient.isConnected()){
                try {
                    ftpClient.disconnect();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return false;
}
}
