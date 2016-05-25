import java.net.*;
import java.io.*;

public class TcpIpClient{
	public static void main(String args[]){
		try{
			String serverIp = "127.0.0.1";
			
			System.out.println(" 서버에 연결중. IP는"+serverIp);

			// 소켓을 생성해서 연결 요청
			Socket socket = new Socket(serverIp, 7777);

			//소켓의 입력스트림을 얻는다.
			InputStream in = socket.getInputStream();
			DataInputStream dis = new DataInputStream(in);

			//소켓으로 부터 받은 데이터 출력
			System.out.println("서버로부터 받은 메시지"+dis.readUTF());
			System.out.println("연결 종료");
			
			// 스트림과 소켓 닫기
			dis.close();
			socket.close();
			System.out.println("연결이 종료되었습니다.");
		} catch (ConnectException ce) {
			ce.printStackTrace();
		}catch (IOException ie){
			ie.printStackTrace();
		}catch (Exception e){
			e.printStackTrace();
		}
	}
}

