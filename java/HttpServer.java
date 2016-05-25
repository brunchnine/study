import java.io.*;
import java.net.*;

public class HttpServer implements Runnable{
	public static void main(String args[]) throws IOException {
		int port = 80;
		if (args.length >=1)
			port = Integer.parseInt(args[0]);

		String rootDir = ".";
		if (args.length >= 2)
			rootDir = args[1];

		new HttpServer(port, rootDir);
	}

	private int port;
	private String rootDir;
	private ServerSocket server = null;

	public HttpServer(int port, String rootDir) throws IOException {
		this.port = port;
		this.rootDir = rootDir;
		server = new ServerSocket(port);
		new Thread(this).start();
	}

	public void run(){
		Socket socket;

		try{
			socket = server.accept();
		} catch (IOException ex) {
			ex.printStackTrace();
			return;
		}

		new Thread(this).start();

		try{
			OutputStream toClient = socket.getOutputStream();
			BufferedReader formClient = new BufferedReader(
					new InputStreamReader(socket.getInputStream()));
			String path = null;
			byte[] data = null;
			try{
				path = getPath(formClient);
				data = getData(path);
			} catch (IOException ex){
				toClient.write(("HTTP/1.0 400" + ex.getMessage() + "\r\n").getBytes());
				toClient.write("\r\n".getBytes());
				toClient.flush();
				return;
			}

			toClient.write("HTTP/1.0 200 OK\r\n".getBytes());
			toClient.write(
					("Content-length: "+ data.length + "\r\n").getBytes());
			if ( path.endsWith(".html") || path.endsWith( ".htm"))
				toClient.write(
						"Content-Type: text/html\r\n\r\n".getBytes());
			else if (path.endsWith(".java") || path.endsWith(".txt"))
					toClient.write(
						"Content-Type: text/plain\r\n\r\n".getBytes());
					else
					toClient.write(
					"Content-Type: application/octet-stream\r\n\r\n".getBytes());

					toClient.write(data);
					toClient.flush();
					} catch (Exception ex){
					ex.printStackTrace();
					} finally{
						try{
					socket.close();
					} catch (IOException e) {}
				}
			}

			private static String getPath(BufferedReader in)
			throws IOException{
			String line = in.readLine();
			String path="";
			System.out.println(line);
			if (line.startsWith("GET /")){
				int i = line.indexOf(' ',5);
				if( i == -1)
					path = line.substring(5);
				else
					path = line.substring(5,i);
				path = path.replace('/', File.separatorChar);
			}

			//  나머지 헤더 버림
			do{
				line = in.readLine();
				System.out.println( line );
			} while ( line.length() !=0);
			System.out.flush();

			if (path.length() != 0)
				return path;
			else
				throw new IOException("Malformed Header");
			}
	public byte[] getData(String path) throws IOException{
		File f = new File(rootDir + path);
		DataInputStream in = new DataInputStream(new FileInputStream(f));
		byte[] data = new byte[(int)f.length()];
		in.readFully(data);
		in.close();
		return data;
	}
}
