import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Server {
    public static void main(String[] args) throws Exception {
        System.out.println("Server starting up");
        System.out.println();

        try (DatagramSocket socket = new DatagramSocket(5000)) {
            while (true) {
                byte[] buffer = new byte[1024];
                DatagramPacket packet = new DatagramPacket(buffer, buffer.length);
                socket.receive(packet);

                String clientTime = new String(packet.getData(), 0, packet.getLength());

                System.out.println("收到客户端时间：" + clientTime);

                String serverTime = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date());

                System.out.println("发送服务端时间：" + serverTime);

                byte[] responseData = serverTime.getBytes();
                DatagramPacket responsePacket = new DatagramPacket(responseData, responseData.length, packet.getAddress(), packet.getPort());
                socket.send(responsePacket);

                System.out.println();
            }
        }
    }
}