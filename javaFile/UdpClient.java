import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.UnknownHostException;

public class UdpClient {
	public void start() throws IOException, UnknownHostException {
		DatagramSocket datagramSocket = new DatagramSocket();
		InetAddress serverAddress = InetAddress.getByName("127.0.0.1");

		// 데이터가 저장될 공간으로 byte배열을 생성한다.
		byte[] msg = new byte[100];

		for(i=0; i<=99; i++){
			msg.add = i%2;

		}

		// byte 에 0101 루프 돌려서 저장하고 날리면 되는겨?
		

		DatagramPacket outPacket = new DatagramPacket (msg, 1, serverAddress, 7777);
		DatagramPacket inPacket = new DatagramPacket (msg,msg.length);

		datagramSocket.send(outPacket); //DatagramPAcket 을 전송
		datagramSocket.receive(inPacket); // DatagramPacket 수신
		
		System.out.println("current server time :" + new String(inPacket.getData()));

		datagramSocket.close();
	}

	public static void main(String args[]){
		try{
			new UdpClient().start();
		} catch(Exception e){
			e.printStackTrace();
		}
	}
}
