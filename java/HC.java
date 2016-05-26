import java.io.*;
import java.net.*;
import java.util.*;

public class HttpCrawling {
  private static Scanner sc;
  public static void main(String[] args){
    
    // 크롤링 대상 URL 입력받음
    
    sc = new Scanner(System.in);
    System.out.println("URL 입력 | 형식: ex)http://naver.com:80/a/b.html");
    String address = sc.nextLine();
    
// substring 만 사용하기 
    
    boolean prtcCheck = address.startsWith("http://");
    boolean w3Check = address.startsWith("www.");
    
    //http:// & www Deleted
    if(prtcCheck || w3Check){
      // (메서드화 하여 돌려보자...)
      address = address.replaceFirst("http://","");
      address = address.replaceFirst("www.","");
    }else{
      
    }
    // 구분 기준 결정
    int hsEnd = address.lastIndexOf(":"); // Host 끝점  & Port 시작점 
    int hsSrt = address.indexOf("/"); // Port 끝점  , Path 시작점 
    if(hsSrt==-1){
      hsSrt=address.length();
    }
  
    
    String getHostSub = address.substring(0,hsEnd); //getHost 가져오기 
        int getPortSub = Integer.parseInt(address.substring(hsEnd+1,hsSrt)); // port 가져오기
        String getPathSub = address.substring(hsSrt,address.length());
    
        
        /* 체크 시
    System.out.println(address);
    System.out.println(prtcCheck);
    System.out.println(w3Check);
    
    System.out.println(getHostSub);
    System.out.println(getPortSub);
    System.out.println(getPathSub);
    체크 끝 */ 
        
        

    
    try{
          
               
            
    // 소켓 생성 연결 요청
                Socket socket = new Socket(getHostSub, getPortSub);
                InetAddress inetaddr = socket.getInetAddress();
                                
                        
                // 반환받은 socket으로부터 inputStream과 OutputStream을 구
                OutputStream out = socket.getOutputStream();
                InputStream in = socket.getInputStream();
                       
                // InputStreamdms 을 BufferedReader 형식으로 변환
                //OutputStream은Writer형식으로 변환
                PrintWriter pw = new PrintWriter(new OutputStreamWriter(out));
                BufferedReader br = new BufferedReader(new InputStreamReader(in));
                      
                //String line = null;
                String cm="\r\n\r\n\r\n";
                String getting= "GET / HTTP / 1.1"+cm; //+getPathSub+
               
               pw.write(getting);
                 pw.flush();
                 
                 String line;
                 while((line = br.readLine()) !=null){
                
                 System.out.println(line);
                 
                 int clstart = line.indexOf("Content-Length:");
               
//               if(clstart>=0){
//                 String contenlenth = line.substring(clstart, clstart+14);
//               
//                 System.out.println(contenlenth+"");
//               }
                 
                 /*HTTP/1.0 200 OK
                kitri.re.kr:80
                HTTP/1.1 200 OK
                Date: Thu, 26 May 2016 14:01:23 GMT
                Server: Apache/2.2.15 (CentOS)
                Accept-Ranges: bytes
                ETag: W/"717-1423546509000"
                Last-Modified: Tue, 10 Feb 2015 05:35:09 GMT
                Content-Length: 717
                Connection: close
                Content-Type: text/html; charset=UTF-8
                         */
                 //int rnLast = line.indexOf("\r\n");
                 
                 
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
