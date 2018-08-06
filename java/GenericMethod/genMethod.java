
public class genMethod {

	public static void main(String[] args) {
		String[] sarray = {"drmc", "asif", "ahmed", "shuvo"};
		Integer[] iarray = {2,1,9,9};
			
		printKor(sarray);
		printKor(iarray);
	}
	//generic method : argument jei type hok na keno
	// ami generic data type <T> banaye ta use kortesi
	//overloaf kora lagtese na
	public static <T> void printKor(T[] x){
		for(T a : x){
			System.out.print(a +" ");
		}
		System.out.println("");
	}

}
