//2. 입력하면 다시 Client로 보내서 뿌리기
import java.net.*;
import java.io.*;

public class Server{
	ServerSocket ss;
	Socket s;
	BufferedReader br;
	PrintWriter pw;
	String str;
	public void go(){
		try{
			ss = new ServerSocket(5435);
			System.out.println("접속준비중........");
			System.out.println("서버가동");
			s = ss.accept();
			System.out.println(s.getInetAddress()+"이(가) 접속했습니다");
			br = new BufferedReader(new InputStreamReader(s.getInputStream()));
			pw = new PrintWriter(new OutputStreamWriter(s.getOutputStream()),true);
			while ( (str = br.readLine()) != null ){
				pw.println("나 서버"+str);
			}
			s.close();
			ss.close();
		}catch (IOException e){
			System.out.println("연결이 종료되었습니다.");
		}
	}
	public static void main(String [] args) throws IOException{
		Server server = new Server();
		server.go();
	}
}
