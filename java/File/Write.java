import java.io.*;

public class Write {
	public static void main(String[] args){
		//characterA();
		//byteBufferB();
		characterBufferC();
	}	
	
	public static void characterA(){
		try{
			InputStreamReader isr = new InputStreamReader(System.in);
			File file = new File("E:\\068\\workspace\\test.txt");
			FileWriter out = new FileWriter(file);
			
			System.out.println("Input :");
			int x = isr.read();
			System.out.println("Console output :" +x);
			out.write(x);
			
			isr.close();
			out.close();
		}
		catch(IOException e){
			e.printStackTrace();
		}
	}
	
	public static void byteBufferB(){
		try{
			BufferedInputStream in = new BufferedInputStream(System.in);
			File file = new File("E:\\068\\workspace\\test.txt");
			BufferedOutputStream out = new BufferedOutputStream(new FileOutputStream(file));
			
			System.out.println("Input :");
			int x = in.read();
			System.out.println("Console output :" +x);
			out.write(x);
			
			in.close();
			out.close();
			
		}catch(IOException e){
			e.printStackTrace();
		}
	}
	
	public static void characterBufferC(){
		try{
			BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
			File file = new File("E:\\068\\workspace\\test.txt");
			BufferedWriter out = new BufferedWriter(new FileWriter(file));
		
			System.out.println("Input :");
			int x = in.read();
			System.out.println("Console output :" +x);
			out.write(x);
			
			in.close();
			out.close();
			
		}catch(IOException e){
			e.printStackTrace();
		}
	
	}
	
}
