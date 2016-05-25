import java.net.*;
import java.io.*;
import java.util.*;
import java.text.*;

public class TcpIpServer{
	public static void main(String arg[]){
		ServerSocket serverSocket = null;

		try{
			// 서비스 소켓 생성,  7777번 포트와 결합 (bind)
			serverSocket = new ServerSocket(7777);
			System.out.println(getTime()+"서버준비됨");
			
		} catch(IOException e){
			e.printStackTrace();
		}

		while(true){
			try{
				System.out.println(getTime()+"연결요청 기다림");
				// 서비스소켓 클라이언트의 연결 요청 올때까지 기다림
				// 클라이언트이ㅡ 연결 요청 오면 소켓과 통신할 새로운 소켓 생성
				Socket socket = serverSocket.accept();
				System.out.println(getTime()+ socket.getInetAddress()+"에서 연결요청 오셨음");
				
				// 소켓 출력스트림 얻기
				OutputStream out = socket.getOutputStream();
				DataOutputStream dos = new DataOutputStream(out);

				//원격 소켓(remote socket)에 데이터를 보냄
				dos.writeUTF("[Notice] Test Message1 form server");
				System.out.println(getTime() + "데이턴 전송했슈");

				// 스트림과 소켓 닫아줌
				dos.close();
				socket.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

	// 현재시간 문자열 반환 함수
	static String getTime(){
		SimpleDateFormat f = new SimpleDateFormat("[hh:mm:ss]");
		return f.format(new Date());
	}
}


