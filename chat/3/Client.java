//3. 여러 사용자 접속가능하게. 입력하면 자기쪽에만 뿌리기
import java.net.*;
import java.io.*;

public class Client {
	Socket s;
	BufferedReader br, br1;
	PrintWriter pw;
	String str;
	public void go(){
		try{

			s = new Socket("127.0.0.1",5438);
			pw = new PrintWriter(s.getOutputStream(),true);
			br = new BufferedReader(new InputStreamReader(System.in));
			br1 = new BufferedReader(new InputStreamReader(s.getInputStream()));
			while ( ( str = br.readLine()) != null ){
				pw.println(str);
				System.out.println( br1.readLine() );
			}
		}catch (IOException e){
			try{
				s.close();
			}catch (IOException ie){
			}
			System.out.println(e.getMessage());
		}	
	}
	public static void main(String [] args){
		System.out.println("Client");
		Client c = new Client();
		c.go();
	}
}
