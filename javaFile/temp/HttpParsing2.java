import java.io.*;
import java.util.*;
import java.net.*;
 
public class HttpParsing2 extends StringCutting{

	public static void main(String[] args){
		
		// URL 입력 받기 
		System.out.println("URL을 입력해주세요.");
		Scanner sc = new Scanner(System.in);
		
		String scurl = sc.nextLine();
		
	
		// URL Cutting Start
		StringCutting sCut = new StringCutting();
		String address = sCut.addressCut(scurl);
		
		
		
		// host 
		String host = sCut.hostCut(address);
		System.out.println(host);
		// port
		int port = sCut.portCut(address);
		
		// path
		String path = sCut.pathCut(address);
		
		System.out.println(host);
		System.out.println(port);
		System.out.println(path);
		try{
			
			//Socket Connect !!!!!
			Socket socket = new Socket(host, port);
	        InetAddress inetaddr = socket.getInetAddress();
	        
	        System.out.println("접속성공?");
	        // 반환받은 socket으로부터 inputStream과 OutputStream
            OutputStream out = socket.getOutputStream();
            InputStream in = socket.getInputStream();
	                
	        // InputStreamdms 을 BufferedReader 형식으로 변환
	        //OutputStream은 Writer형식으로 변환
	        PrintWriter pw = new PrintWriter(new OutputStreamWriter(out));
	        BufferedReader br = new BufferedReader(new InputStreamReader(in));
	              
			RegexData cd = new RegexData();
	        pw.write(cd.getOrder()+path);
	        pw.flush();
	        pw.write("\r\n\r\n\r\n");
	        pw.flush();


	         // Request Read
	         String line;
	         String conts = "";

	         while((line = br.readLine()) !=null){
	             pw.write(line);
	             pw.flush();
	             conts = conts+line+"\r\n";

         }

			socket.close();

         //String Cutting에 날려서 head , body 분석후 가져옴
	     System.out.println(conts);
	     
	         String statusCode = sCut.tagSearch(conts, cd.getStatusStr());
	         String contLength = sCut.tagSearch(conts, cd.getContLength());
	         String contType = sCut.tagSearch(conts, cd.getContType()); 
	         
	     int aCount = sCut.bodyCounter(conts, cd.getSrcLink());
	     int imgCount = sCut.bodyCounter(conts, cd.getSrcImg());
	     
	     // 결과 출력
	     System.out.println("status code 는 "+statusCode);
	     System.out.println("Contents Length는 "+contLength);
	     System.out.println("Contents Type은 "+contType);
	     System.out.println("body의 링크는"+aCount+"개 존재하며 이미지는"+imgCount+"개 입니다.");

		} catch (IOException ie) {
			 ie.printStackTrace();
		} 
	}
}


