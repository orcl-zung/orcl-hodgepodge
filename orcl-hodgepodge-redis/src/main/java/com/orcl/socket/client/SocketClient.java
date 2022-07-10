package com.orcl.socket.client;

import java.io.*;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Scanner;

/**
 * @description:
 * @author: orcl
 * @since: 2022/5/11-17:14
 * @history: 2022/5/11 created by orcl
 */
public class SocketClient {

    private static class ClientMsg {
        public static final String LOGIN_SUCCEED_CODE = "1001";
        public static final String LOGIN_SUCCEED      = "登陆成功，您现在可以使用查字服务了。";
        public static final String LOGIN_FAIL         = "登陆失败，用户名或者密码错误。";
        public static final String NOT_FOUND_CODE     = "2001";
        public static final String NOT_FOUND          = "对不起，您查找的汉字尚未收录本字典";
    }

    private static final String hostname       = "127.0.0.1";
    private static final int    port           = 8888;
    private static final int    requestTimeout = 60 * 1000;

    private static Socket         clientSocket;
    private static BufferedReader recvBuffer;
    private static PrintWriter    sendBuffer;

    static {
        try {
            clientSocket = new Socket();
            clientSocket.connect(new InetSocketAddress(hostname, port), requestTimeout);
            recvBuffer = new BufferedReader(new InputStreamReader(clientSocket.getInputStream(), "UTF-8"));
            sendBuffer = new PrintWriter(new OutputStreamWriter(clientSocket.getOutputStream(), "UTF-8"), true);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 向套接字的发送缓存写入信息
     *
     * @throws IOException
     */
    public static void send(String msg) throws IOException {
        sendBuffer.println(msg);
    }

    /**
     * 从套接字的接收缓存读取信息
     *
     * @return
     * @throws IOException
     */
    public static String receive() throws IOException {
        return recvBuffer.readLine();
    }

    public static void main(String[] args) {

        try {
            // 欢迎信息
            System.out.println(receive());
            // 登陆信息
            Scanner scanner = new Scanner(System.in);
            boolean flag = false;
            while (!flag) {
                System.out.println(receive());
                String username = scanner.nextLine();
                send(username);
                System.out.println(receive());
                String password = scanner.nextLine();
                send(password);

                String result = receive();
                if (result.equals(ClientMsg.LOGIN_SUCCEED_CODE)) {
                    System.out.println(ClientMsg.LOGIN_SUCCEED);
                    flag = true;
                } else {
                    System.out.println(ClientMsg.LOGIN_FAIL);
                }
            }

            // 查字
            System.out.println("请输入您要查询的汉字：");
            String word = scanner.nextLine();
            while (!word.equals("#")) {
                if (word.length() != 1) {
                    System.out.println("您的输入为空或者多于一个汉字！");
                } else {
                    // 查词
                    send(word);
                    String result = receive();
                    if (result.equals(ClientMsg.NOT_FOUND_CODE)) {
                        System.out.println(ClientMsg.NOT_FOUND);
                    } else {
                        System.out.println(result);

                    }
                    System.out.println("--------------------------------");
                }
                System.out.println("请输入您要查询的汉字：");
                word = scanner.nextLine();
            }
        } catch (UnknownHostException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                System.out.println("欢迎下次使用！");
                sendBuffer.close();
                recvBuffer.close();
                clientSocket.close();

            } catch (IOException e) {
                e.printStackTrace();
            }
        }

    }

}
