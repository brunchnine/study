import java.io.*;
import java.net.*;
import java.util.*;

class NetworkEx1 {
        public static void main(String[] args){
                String address = "http://www.naver.com:80/a/b.html";
                try{
			URL url = new URL(address);
                        String host = url.getHost();
                        String path = url.getPath();
                        int port = url.getDefaultPort();
                    // debu
			System.out.println(host+"");
			System.out.println(path+"");
			System.out.println(port+"");


					

                        // 소켓 생성 연결 요청
                        Socket socket = new Socket(host, port);
			// output Stream new
                        Writer out = new OutputStreamWriter(socket.getOutputStream());
			// Buffer 
                        //BufferedReader stdin = new BufferedReader(new InputStreamReader(System.in));


                       // for(String line; (line = stdin.readLine()) !=null;)
			String line ="GET / HTTP / 1.0";
                                out.write(line + "\r\n");
                        out.flush();

                        InputStream in = socket.getInputStream();
                        for(int ch; (ch = in.read()) != -1;)
                                System.out.write(ch);
                        System.out.flush();





                } catch (ConnectException ce){
                        ce.printStackTrace();
                } catch (IOException ie){
                        ie.printStackTrace();
                } catch (Exception e){
                        e.printStackTrace();
                }
        }
}
