import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Client {
    public static void main(String[] args) throws Exception {
        System.out.println("Client starting up");
        System.out.println();

        try (DatagramSocket socket = new DatagramSocket()) {
            InetAddress address = InetAddress.getByName("127.0.0.1");
            int port = 5000;

            while (true) {
                String clientTime = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date());

                System.out.println("当前客户端时间：" + clientTime);

                byte[] data = clientTime.getBytes();
                DatagramPacket packet = new DatagramPacket(data, data.length, address, port);
                socket.send(packet);

                byte[] buffer = new byte[1024];
                DatagramPacket receivePacket = new DatagramPacket(buffer, buffer.length);
                socket.receive(receivePacket);

                String serverTime = new String(receivePacket.getData(), 0, receivePacket.getLength());
                System.out.println("收到服务器时间: " + serverTime);

                System.out.println();

                // 等待 3 秒
                Thread.sleep(3000);
            }
        }
    }
}