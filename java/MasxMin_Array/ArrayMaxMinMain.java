//23-06 Project 02 Max Min from Array
public class ArrayMaxMinMain {

	
	public static void main(String[] args) {
		int[] a = {2, 3, 9, 8, 12, 1, 5, 19, 15, 0, 4};
		
		int max=0, min=100;
		
		for(int i=0; i<a.length; i++){
			if(a[i]>=max)
				max=a[i];
			if(a[i]<=min)
				min=a[i];
		}
		
		/*for(int x: a){			//enhanced for loop kaj kore na
			if(x>=max)
				max=x;
			if(x<=min)
				min=x;
		}*/
		
		System.out.println("Max : " +max +" , Min : " +min);

	}
}
