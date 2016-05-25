import java.io.*;
import java.net.*;
import java.util.*;

class NetworkEx3 {
        public static void main(String[] args){
                String address = "http://naver.com:8080/a/b.html";
                
                // 변수 address 에서 :값의 인덱스값을 hsEnd 에 넣
               int hsEnd = address.lastIndexOf(":"); //포트시작 index num
               int hsSrt = address.indexOf("//");
               
               String getHostN = address.substring(hsSrt+2,hsEnd); //getHost 가져오기 
               // str.substring(str.indexOf("-"),str.lastIndexOf("-")) -> 07
             
                
                
                try{
                		URL url = new URL(address);
                		String host = url.getHost();
                        String path = url.getPath();
                        int port = url.getDefaultPort();
                    
                        System.out.println(host+"");
                        System.out.println(getHostN+"  <--substring 으로 가져온것");
						System.out.println(path+"");
						System.out.println(port+"");
                        
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
               String getting= "GET/HTTP/1.1";
               String cm="\r\n\r\n\r\n";
            	 pw.write(getting+cm);
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

