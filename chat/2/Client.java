//2. 입력하면 다시 Client로 보내서 뿌리기
import java.net.*;
import java.io.*;

public class Client {
	Socket s;
	BufferedReader br, br1;
	PrintWriter pw;
	String str;
	public void go(){
		try{

			s = new Socket("127.0.0.1",5435);
			pw = new PrintWriter(s.getOutputStream(),true);
			br = new BufferedReader(new InputStreamReader(System.in));
			br1 = new BufferedReader(new InputStreamReader(s.getInputStream()));
			while ( (str = br.readLine()) != null ){
				pw.println(str);
				System.out.println(br1.readLine());
			}
			s.close();
		}catch (IOException e){
			System.out.println(e.getMessage());
		}
		
	}
	public static void main(String [] args){
		System.out.println("Client");
		Client c = new Client();
		c.go();
	}
}

