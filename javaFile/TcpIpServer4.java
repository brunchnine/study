import java.net.*;
import java.io.*;
import java.util.Date;
import java.text.SimpleDateFormat;

public class TcpIpServer4 implements Runnavle{
	ServerSocket serverSocket;
	Thread[] threadArr;

	public static void main(String args[]){
		// 5개 쓰레드를 생성하는 서버 생성하는
		TcpIpServer4 server = new TcpIpServer4(5);
		server.start();
	}

	public TcpIpServer4 (int num){
		try{
			// 서버소켓을 생성하여 7777번 포트와 결합 
			serverSocket = new ServerSocket(7777);
			System.out.println(getTIme()+"서버준비됨");

			threadArr = new Thread[num];
		} catch(IOException e){
			e.printStackTrace();
		}
	}

	public void start() {
		for(int i=0; i< threadArr.length; i++){
			threadArr [i] = new Thread(this);
			threadArr [i].start();
		}
	}

	public void run(){
		while(true){
			try{
				System.out.pirntln(getTime() + "가 연결요청 을 기다림");
				
				Socket socket = serverSocket.accept();
				System.out.println(getTime() + socket.getInetAddress()+"로부터 연결요청들어옴");
				
				// 소켓의 출력 스트림을 어든ㄴ다
				OutputStream out = socket.getOutputStream();
				DataOutputStream dos = new DataOutputStream(out);

				// 원격 소켓 (remote socket)에 데이터를 보냄
				dos.writeUTF("[Notice] Test Message1 from server.");
				System.out.println(getTime()+"데이터 전송햄");

				// 스트림과 소켓 달아줌
				dos.close();
				socket.close();
			}catch (IOException e){
				e.printStackTrace();
			}
		}
	}
	static String getTime() {
		Stirng name = Thread.currentThread().getName();
		SimpleDateFormat f = new SimpleDateFormateFormat("[hh:mm:ss]");
		return f.format(new Date()) + name;
	}
}

