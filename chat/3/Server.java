//3. 여러 사용자 접속가능하게. 입력하면 자기쪽에만 뿌리기
import java.net.*;
import java.io.*;

public class Server{
	ServerSocket ss;
	Socket s;
	ServerThread st;
	public void go(){
		try{
			ss = new ServerSocket(5438);
			System.out.println("접속준비중........");
			System.out.println("서버 가동");
			while ( true ){
				s = ss.accept();
				st = new ServerThread(s);
				st.start();
			}
		}catch (IOException e){
			try{
				s.close();
				ss.close();
			}catch (IOException ie){}
			System.out.println(e.getMessage());
		}
	}
	public static void main(String [] args) throws IOException{
		Server server = new Server();
		server.go();
	}
}

class ServerThread extends Thread{
	Socket s;
	BufferedReader br;
	PrintWriter pw;
	String str;
	public ServerThread(Socket s) throws IOException {
		this.s = s;
		br = new BufferedReader(new InputStreamReader(s.getInputStream()));
		pw = new PrintWriter(new OutputStreamWriter(s.getOutputStream()),true);
		System.out.println(s.getInetAddress()+"(이)가 접속했습니다");
	}
	public void sendMessage(String str){
		pw.println(str);
	}
	public void run(){
		try{
			while ( (str = br.readLine()) != null ){
				sendMessage(str);
			}
		}catch (IOException e){
			System.out.println(s.getInetAddress()+"의 연결이 종료되었습니다.");
			try{
				s.close();
			}catch (IOException ie){	}
		}	
	}
}
