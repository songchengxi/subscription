package com.scx.util;

import org.apache.log4j.Logger;
import sun.net.ftp.FtpClient;
import sun.net.ftp.FtpDirEntry;
import sun.net.ftp.FtpProtocolException;

import java.io.*;
import java.net.InetSocketAddress;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * @Author 宋程玺
 * @Date 2017/1/11 14:49
 * @Desc 连接FTP服务器工具类
 */
public class FtpUtil {

    private final static Logger log = Logger.getLogger(FtpUtil.class);

    private String ip = "";

    private String username = "";

    private String password = "";

    private int port = 21;

    private String path = "";

    FtpClient ftpClient = null;

    OutputStream os = null;

    InputStream is = null;

    public FtpUtil(String serverIP, String username, String password) {
        this.ip = serverIP;
        this.username = username;
        this.password = password;
    }

    public FtpUtil(String serverIP, int port, String username, String password) {
        this.ip = serverIP;
        this.username = username;
        this.password = password;
        this.port = port;
    }

    /**
     * 连接ftp服务器
     */
    public FtpClient connectServer() {
        ftpClient = FtpClient.create();
        try {
            ftpClient.connect(new InetSocketAddress(this.ip, this.port));
            ftpClient.login(this.username, this.password.toCharArray());
            if (this.path.length() != 0) {
                ftpClient.changeDirectory(this.path);//path是ftp服务下主目录的子目录
            }
            log.info("已登录到\"" + ftpClient.getWorkingDirectory() + "\"目录");
        } catch (IOException e) {
            e.printStackTrace();
            log.error("登录失败", e);
        } catch (FtpProtocolException e) {
            e.printStackTrace();
            log.error("登录失败", e);
        }
        return ftpClient;
    }

    /**
     * 取得相对于当前连接目录的某个目录下所有文件列表
     *
     * @param path 文件路径
     * @return
     */
    public List getFileList(String path) {
        List list = new ArrayList();
        Iterator<FtpDirEntry> entries;
        try {
            entries = ftpClient.listFiles(path);
            while (entries.hasNext()) {
                FtpDirEntry entry = entries.next();
                list.add(entry.getName());
            }
        } catch (IOException e) {
            e.printStackTrace();
            log.error("获取文件失败", e);
        } catch (FtpProtocolException e) {
            e.printStackTrace();
            log.error("获取文件失败", e);
        }
        return list;
    }

    /**
     * 取得相对于当前连接目录文件
     *
     * @param ftpFile     文件路径
     * @param charsetName 编码格式
     * @return
     */
    public String getFile(String ftpFile, String charsetName) {
        StringBuilder sb = new StringBuilder();
        String str;
        BufferedReader br;
        try {
            // 获取ftp上的文件
            is = ftpClient.getFileStream(ftpFile);
            //转为字节流
            br = new BufferedReader(new InputStreamReader(is, charsetName));
            while ((str = br.readLine()) != null) {
                sb.append(str);
            }
        } catch (FtpProtocolException e) {
            e.printStackTrace();
            log.error("获取文件失败", e);
        } catch (IOException e) {
            e.printStackTrace();
            log.error("获取文件失败", e);
        }
        return sb.toString();
    }

    /**
     * 断开与ftp服务器连接
     */
    public boolean closeServer() {
        try {
            if (is != null) {
                is.close();
            }
            if (os != null) {
                os.close();
            }
            if (ftpClient != null) {
                ftpClient.close();
            }
            log.info("已从ftp服务器断开");
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }
}
