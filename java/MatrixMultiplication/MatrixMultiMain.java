//23-06 Project 1 - Matrix Multiplication
import java.util.Scanner;
public class MatrixMultiMain {

	public static void main(String[] args) {
		int[][] a = new int[3][4];
		int[][] b = new int[4][3];
		
		int x = 1;
		Scanner in = new Scanner(System.in);
				
		/* initializing a */
		for(int i=0; i<a.length; i++){					//a.length holo row er length
			for(int j=0; j<a[0].length; j++){			//a[0].length collumn er length
				a[i][j]= x++;
			}
		}
		
		System.out.println("Matrix a :");
		for(int i=0; i<a.length; i++){					
			for(int j=0; j<a[0].length; j++){			
				System.out.print(a[i][j] +"\t");
			}
			System.out.println();
		}
		
		/* initializing b */
		x = 1;
		for(int i=0; i<b.length; i++){					
			for(int j=0; j<b[0].length; j++){			
				b[i][j]= x++;
			}
		}
		
		System.out.println("Matrix b :");
		for(int i=0; i<b.length; i++){					
			for(int j=0; j<b[0].length; j++){			
				System.out.print(b[i][j] +"\t");
			}
			System.out.println();
		}
		
		int[][] c = new int[3][3];
		
		/* initializing c */
		for(int i=0; i<c.length; i++){					
			for(int j=0; j<c[0].length; j++){			
				c[i][j]= 0;
			}
		}
		
		/*Multiplication*/
		for(int i=0; i<3; i++){					
			for(int j=0; j<3; j++){			
				for(int k=0; k<a[0].length; k++){
					c[i][j]= c[i][j] + a[i][k]*b[k][j] ;
				}
			}
		}
		
		System.out.println("Matrix c :");
		for(int i=0; i<c.length; i++){					
			for(int j=0; j<c.length; j++){			
				System.out.print(c[i][j] +"\t");
			}
			System.out.println();
		}
	}
	


}
