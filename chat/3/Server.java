//3. ���� ����� ���Ӱ����ϰ�. �Է��ϸ� �ڱ��ʿ��� �Ѹ���
import java.net.*;
import java.io.*;

public class Server{
	ServerSocket ss;
	Socket s;
	ServerThread st;
	public void go(){
		try{
			ss = new ServerSocket(5438);
			System.out.println("�����غ���........");
			System.out.println("���� ����");
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
		System.out.println(s.getInetAddress()+"(��)�� �����߽��ϴ�");
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
			System.out.println(s.getInetAddress()+"�� ������ ����Ǿ����ϴ�.");
			try{
				s.close();
			}catch (IOException ie){	}
		}	
	}
}
