import java.io.*;
import java.net.*;
import java.util.*;
import java.lang.*;

class NetworkEx4 {
        public static void main(String[] args){
                String address = "http://naver.com:80/a/b.html";
		boolean prot = address.startsWith("http://");
		String address1=""; 
		if(prot){
			address1 = address.replaceFirst("http://","");
		}
		System.out.println(address1+"");

                
                // 변수 address 에서 :값의 인덱스값을 hsEnd 에 넣
               int hsEnd = address1.lastIndexOf(":"); //host 끝 |port 시작 index num
               int hsSrt = address1.indexOf("/"); // port 끝 Path 시작
               
               String getHostN = address1.substring(0,hsEnd); //getHost 가져오기 
               String getPortN = address1.substring(hsEnd+1,hsSrt); // port 가져오기
               String getPathN = address1.substring(hsSrt,address1.length());
               // str.substring(str.indexOf("-"),str.lastIndexOf("-")) -> 07
             
                
                
          try{
       		URL url = new URL(address);
       		String host = url.getHost();
                String path = url.getPath();
                int port = url.getDefaultPort();
                    
                System.out.println(host+"");
                System.out.println(getHostN+"  <--substring 으로 가져온것");
		System.out.println(path+"");
		System.out.println(getPathN+"  <-- substring으로 가져온 ");
		System.out.println(port+"");
		System.out.println(getPortN+"  <-- substring으로 가져온 ");
						
						
		// 소켓 생성 연결 요청
                Socket socket = new Socket(host, port);
                InetAddress inetaddr = socket.getInetAddress();
                       
                //접속표시
                System.out.println("호스트IP:"+inetaddr.getHostAddress());
                        
                // 반환받은 socket으로부터 inputStream과 OutputStream을 구
                OutputStream out = socket.getOutputStream();
                InputStream in = socket.getInputStream();
                       
                // InputStreamdms 을 BufferedReader 형식으로 변환
                //OutputStream은Writer형식으로 변환
                PrintWriter pw = new PrintWriter(new OutputStreamWriter(out));
                BufferedReader br = new BufferedReader(new InputStreamReader(in));
                      
                //String line = null;
                String cm="\r\n\r\n\r\n";
                String getting= "GET/HTTP/1.1"+path+cm;
               
            	 pw.write(getting);
              	 pw.flush();
               
                    	 
                 String line;
                 while((line = br.readLine()) !=null){
              	 System.out.println(line);
                 }
                 //IO객체와 소켓의 close() 메소드 호출
                 pw.close();
                 br.close();
                 socket.close();

                } catch (ConnectException ce){
                        ce.printStackTrace();
                } catch (IOException ie){
                        ie.printStackTrace();
                } catch (Exception e){
                        e.printStackTrace();
                }
        }
}

