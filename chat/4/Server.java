//4. text기반 채팅. 여러 사용자 접속, 사용 가능
import java.net.*;
import java.io.*;
import java.util.Vector;

public class Server{
	ServerSocket ss;
	Socket s;
	Vector v;
	public Server(){
		v = new Vector(10,10);
	}
	public void addThread(ServerThread st){
		v.addElement(st);
	}
	public void removeThread(ServerThread st){
		v.removeElement(st);
	}
	public void broadcast(String str){
		for ( int i = 0 ; i < v.size() ; i++ ){
			ServerThread st1 = (ServerThread)v.elementAt(i);
			st1.sendMessage(str);
		}
	}
	public void go(){
		try{
			ss = new ServerSocket(5432);
			System.out.println("접속준비중........");
			System.out.println("서버 가동");
			while ( true ){
				s = ss.accept();
				ServerThread st = new ServerThread(this, s);
				this.addThread(st);
				st.start();
			}
		}catch (IOException e){
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
	Server server;

	public ServerThread(Server server,Socket s) throws IOException {
		this.server = server;
		this.s = s;
		br = new BufferedReader(new InputStreamReader(s.getInputStream()));
		pw = new PrintWriter(new OutputStreamWriter(s.getOutputStream()),true);
		System.out.println(s.getInetAddress()+"(이)가 접속했습니다");
	}
	public void sendMessage(String string){
		pw.println(string);
	}
	public void run(){
		try{
			while ( ( str = br.readLine() ) != null ){
				server.broadcast(str);
			}
		}catch (IOException e){
			System.out.println(s.getInetAddress()+"의 연결이 종료되었습니다.");
			server.removeThread(this);
			try{
				s.close();
			}catch (IOException ie){ }
		}
	}
}
