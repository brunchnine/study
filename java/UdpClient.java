import java.net.*;
import java.io.*;

public class UdpClient {
	public void start() threows IOException, UnknowHostException{
		DatagramSocket datagramSocket = new DatagramSocket();
		InetAddress serverAddress = InetAddress.getByName("127.0.0.1");

		// 데이터가 저장될 공간으로 byte배열을 생성한다.
		byte[] msg = new byte[100];

		DatagramPacket outPacket =
			new DatagramPacket (msg, 1, serverAddress, 7777);
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



public class UdpServer {
	public void start() throws IOException {
		// 포트 7777번을 사용하는 소켓을 생성
		DatagramSocket socket = new DatagramSocket(7777);
		DatagramPacket inPacket, outPacket;

		byte[] inMsg = new byte[10];
		byte[] outMsg;

		while(true) {
			// 데이터를 수신하기 위한 패킷을 생성
			inPacket = new DatagramPAcket(inMsg, inMsg.length);

			// 패킷을 통해 데이터를 수신(receive)한다
			socket.receive(inPacket);

			// 수신한 패킷으로 부터 client의 IP주소와 Port를 얻음
			InetAddress address = inPacket.getAddress();
			int port = inPacket.getPort();

			// 서버의 현재시간 시분초 ([hh:mm:ss])
			SimpleDataFormat sdf = new SimpleDateFormat("[hh:mm:ss]");
			String time = sdf.format(new Date());
			outMsg = time.getBytes(); // time을 byte배열로 변환
			
			// 패킷을 생성해서 client에게 전송
			outPacket =
				new DatagramPacket(outMsg, outMsg.length, address, port);
		}
	}

	public static void main(String args[]){
		try{
			new UdpServer().start(); // UDP start
		} catch (IOException e){
			e.printStackTrace();
		}
	}
}


