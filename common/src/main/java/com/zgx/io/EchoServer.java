package com.zgx.io;

        import java.io.*;
        import java.net.ServerSocket;
        import java.net.Socket;
        import java.util.concurrent.ExecutorService;
        import java.util.concurrent.Executors;

public class EchoServer {
    public static void main(String[] args) throws Exception {
        ServerSocket echoServer = null;
        Socket clientSocket = null;

        echoServer = new ServerSocket(8000);
        ExecutorService tp = Executors.newFixedThreadPool(5);
        while (true) {
            clientSocket = echoServer.accept();
            System.out.println(clientSocket.getRemoteSocketAddress() + " connect!");
            tp.execute(new HandleMsg(clientSocket));
        }


    }

    private static class HandleMsg implements Runnable {
        private Socket clientSocket;

        public HandleMsg(Socket clientSocket) {
            this.clientSocket = clientSocket;
        }

        @Override
        public void run() {
            //将客户端的输入流包包装成一个缓冲流
            BufferedReader bufferedReader = null;
            PrintWriter bufferedWriter = null;
            try {
                bufferedReader = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
                bufferedWriter = new PrintWriter(clientSocket.getOutputStream(), true);
                //从InputStream当中读取客户端发送的数据
                String inputLine = null;
                long begin = System.currentTimeMillis();
                while ((inputLine = bufferedReader.readLine()) != null){
                    bufferedWriter.print(inputLine);
                }
                long end = System.currentTimeMillis();
                System.out.println("spend:" + (end - begin) + "ns");
            } catch (IOException e) {
                e.printStackTrace();
            } finally {
                if (bufferedReader != null) {
                    try {
                        bufferedReader.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }

                if (bufferedWriter != null) {
                    bufferedWriter.close();
                }
            }
        }
    }
}
