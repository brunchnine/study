import java.io.*;
import java.net.*;
import java.util.*;
import java.io.*;

public class HttpCrawlingA{
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
                String getting= "GET / HTTP / 1.0"+cm; //+getPathSub+
               
                 pw.write(getting+"\r\n");
                 pw.flush();
               
                       
                 String line;
                 String conts = "";
                 int countA = 0;
                 int countI = 0;
                 boolean containA;
                 boolean containI;
                 
               //String fileName = "contents.txt";
              // BufferedWriter 와 FileWriter를 조합하여 사용 (속도 향상)
                 
               //  BufferedWriter fw = new BufferedWriter(new FileWriter(fileName, false));
            
                 while((line = br.readLine()) !=null){
                     pw.write(line+"\r\n");
                     
                     containA = line.contains("<a");
                     containI = line.contains("<img");
                     if(containA){
                         countA++;
                         
                         containA=false;
                         
                     } 
                    
                     if(containI){
                         countI++;
                         
                         containI=false;
                         
                     }
                     conts= conts+line;
                     pw.flush();
                     
                 }
                 System.out.println(conts);
                 int endPoint = conts.indexOf("!");
                 String spHead = "헤드의 구분점을 찾을수 없습니다.(기본값:!)";
                 String spBody = "헤드의 구분점을 찾을수 없습니다";
                 
                 
                 
                 
                 
                 spHead = conts.substring(0,endPoint);
                 
                 
                 spBody = conts.substring(endPoint,conts.length());
                
                
                 System.out.println("엔드포인트"+endPoint);
                System.out.println("length"+conts.length());
                //헤드 출력
                System.out.println("헤더내용:"+spHead);
                System.out.println("바디내용:"+spBody);
                
                System.out.println("<a 는"+countA+"번 있음");
                System.out.println("IMG 는"+countI+"번 있음");
                if (endPoint==1){
                    System.out.println("헤더 정보를 가져오못했음... ");
                }
                 
                 
                 
                 
                 //// 임시  
                // final String regex = ".txt";
                // System.out.println(fileName);
                // boolean headerline = fileName.matches(regex);
                 
                // System.out.println(headerline);
                 //////임시   
    
                // fw.close();
                
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