//2. �Է��ϸ� �ٽ� Client�� ������ �Ѹ���
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
			System.out.println("�����غ���........");
			System.out.println("��������");
			s = ss.accept();
			System.out.println(s.getInetAddress()+"��(��) �����߽��ϴ�");
			br = new BufferedReader(new InputStreamReader(s.getInputStream()));
			pw = new PrintWriter(new OutputStreamWriter(s.getOutputStream()),true);
			while ( (str = br.readLine()) != null ){
				pw.println("�� ����"+str);
			}
			s.close();
			ss.close();
		}catch (IOException e){
			System.out.println("������ ����Ǿ����ϴ�.");
		}
	}
	public static void main(String [] args) throws IOException{
		Server server = new Server();
		server.go();
	}
}
