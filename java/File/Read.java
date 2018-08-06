import java.io.*;
public class Read {
	public static void main(String[] args){
		//byteA();
		//characterB();
		//bufferC();
	}
	
	public static void byteA(){
		try{
			File file = new File("E:\\068\\workspace\\test.txt");
			FileInputStream in = new FileInputStream(file);
			int x = in.read();
			while(x != -1){
				System.out.print(x +"  ");
				x = in.read();
			}
			
			
			in.close();
		}catch(IOException e){
			e.printStackTrace();
		}
	}
	
	public static void characterB(){
		try{
			File file = new File("E:\\068\\workspace\\test.txt");
			FileReader in = new FileReader(file);
		
			int x = in.read();
			while(x != -1){
				System.out.print(x +"  ");
				x = in.read();
			}
			
			in.close();
		}
		catch(IOException e){
			e.printStackTrace();
		}
	}
	
	public static void bufferC(){
		try{
			File file = new File("E:\\068\\workspace\\test.txt");
			BufferedReader in = new BufferedReader(new FileReader(file));
		
			int x = in.read();
			while(x != -1){
				System.out.print(x +"  ");
				x = in.read();
			}
			
			in.close();
		}catch(IOException e){
			e.printStackTrace();
		}
	
	}
}
