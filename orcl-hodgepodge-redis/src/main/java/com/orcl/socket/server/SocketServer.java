package com.orcl.socket.server;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.HashMap;
import java.util.Map;

/**
 * @description:
 * @author: orcl
 * @since: 2022/5/11-17:14
 * @history: 2022/5/11 created by orcl
 */
public class SocketServer {

    private static class ServerMsg {
        public static final String WELCOMING          = "欢迎使用《明月几时有字典》，请按照提示操作。注：回车后可提交信息，查询汉字为“#”表示退出！";
        public static final String INPUT_USERNAME     = "请输入用户名：";
        public static final String INPUT_PASSWORD     = "请输入密码：";
        public static final String LOGIN_SUCCEED_CODE = "1001";
        public static final String LOGIN_FAIL_CODE    = "1002";
        public static final String NOT_FOUND_CODE     = "2001";
    }

    /**
     * 用户列表
     */
    private static Map<String, String> users;
    private static Map<String, String> dict;

    /**
     * 初始化用户列表和字典
     */
    private static void init() {
        // 初始化用户
        users = new HashMap<>();
        users.put("admin", "123");

        // 初始化字典
        dict = new HashMap<>();
        dict.put("明", "【读音：ming】" + "组词：明天、明亮");
        dict.put("月", "【读音：yue】" + "组词：月亮、月光");
        dict.put("几", "【读音：ji】" + "组词：几个、几乎");
        dict.put("时", "【读音：shi】" + "组词：时间、小时");
        dict.put("有", "【读音：you】" + "组词：没有");
    }

    private static class Worker implements Runnable {

        private Socket         connectionSocket;
        private PrintWriter    sendBuffer;
        private BufferedReader recvBuffer;

        public Worker(Socket connectionSocket) throws IOException {
            this.connectionSocket = connectionSocket;
            sendBuffer = new PrintWriter(new OutputStreamWriter(connectionSocket.getOutputStream(), "UTF-8"), true);
            recvBuffer = new BufferedReader(new InputStreamReader(connectionSocket.getInputStream(), "UTF-8"));
        }

        /**
         * 往套接字的发送缓存写入信息
         */
        private void welcoming() {
            send(ServerMsg.WELCOMING);
        }

        private void login() throws IOException {
            boolean flag = false;
            while (!flag) {
                send(ServerMsg.INPUT_USERNAME);
                String username = receive();
                send(ServerMsg.INPUT_PASSWORD);
                String password = receive();
                if (users.get(username) != null && users.get(username).equals(password)  ) {
                    send(ServerMsg.LOGIN_SUCCEED_CODE);
                    flag = true;
                } else {
                    send(ServerMsg.LOGIN_FAIL_CODE);
                }
            }
        }

        private boolean contains(String word) {
            return dict.containsKey(word);
        }

        private String lookup(String word) {
            return dict.get(word);
        }

        private void send(String msg) {
            sendBuffer.println(msg);
        }

        /**
         * 从套接字的接受缓存读取信息
         * @return
         * @throws IOException
         */
        private String receive() throws IOException {
            return recvBuffer.readLine();
        }

        @Override
        public void run() {
            try {
                // 连接成功发送欢迎信息
                welcoming();

                // 登录
                login();

                // 查词服务
                String word = receive();

                while (!"#".equals(word)) {
                    System.out.println(word);
                    if (contains(word)) {
                        send(lookup(word));
                    } else {
                        send(ServerMsg.NOT_FOUND_CODE);
                    }
                    word = receive();
                }
            } catch (IOException e) {
                e.printStackTrace();
            } finally {
                try {
                    sendBuffer.close();
                    recvBuffer.close();
                    connectionSocket.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

        }

    }

    public static void main(String[] args) {
        init();
        try {
            // 创建监听套接字
            ServerSocket serverSocket = new ServerSocket(8888);

            // 监听端口：10000，等待客户端发起连接，TCP状态：LISTEN
            // 连接套接字负责与客户端通信
            while (true) {
                Socket connectionSocket = serverSocket.accept();
                new Thread(new Worker(connectionSocket)).start();
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

}
