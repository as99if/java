import java.util.Random;


public class Algorithms {
	static Random myRand = new Random();
	public static void main(String...args)
	{
		NQueen init = generateRandomly(5);
		
		System.out.println(init);
		System.out.println(init.getQuality());
		
		
		//System.out.println(Gibs(1000, 4));
		System.out.println(RandomizedHillClimbing(1000, 4));
		
	}
	public static NQueen RandomizedHillClimbing(int maxIter,int n)
	{
		NQueen s=generateRandomly(n);
		if(s.getQuality()==0) return s;
		int iter=0;
		while(iter++<maxIter)
		{
			NQueen sprime = generateRandomNeighbor(s);
			if(sprime.getQuality()==0)
			{	
				System.out.println(iter);
				return sprime;
			}
			if(sprime.getQuality()<=s.getQuality())
                s=sprime;
		}
		return null;
	}
	public static NQueen GreedyHillClimbing(int maxIter,int n)
	{
		NQueen s=generateRandomly(n);
		int t=n*(n-1);
		if(s.getQuality()==0) return s;
		int iter=0;
		NQueen sprime=new NQueen(n);
		while(iter++<maxIter)
		{
			int k=1;
			while(k++<=t)
			{
				NQueen temp=generateRandomNeighbor(s);
				if(temp.getQuality()<s.getQuality())
					sprime=temp;
			}
			if(sprime.getQuality()==0)
			{	
				System.out.println(iter);
				return sprime;
			}
			
                s=sprime;
		}
		return null;
	}
	
/*	public static NQueen Gibs(int maxIter,int n)
	{
		NQueen s=generateRandomly(n);
		if(s.getQuality()==0) return s;
		int iter=0;
		while(iter++<maxIter)
		{
			NQueen sprime = generateRandomNeighbor(s);
			if(sprime.getQuality()==0)
			{	
				System.out.println(iter);
				return sprime;
			}
			s=sprime;
		}
		return null;
	}
	
	*/
	public static NQueen MonteCarlo(int maxIter,int n)
	{
		int iter=0;
		while(iter++<maxIter)
		{
			NQueen s = generateRandomly(n);
			if(s.getQuality()==0)
			{	
				System.out.println(iter);
				return s;
			}
		}
		return null;
	}
	public static NQueen 
		generateRandomNeighbor(NQueen other)
	{
			NQueen temp = new NQueen(other);
			int rq=myRand.nextInt(temp.n);
			int rv=myRand.nextInt(temp.n);
			temp.queens[rq]=rv;
			
			return temp;
	}
	public static NQueen generateRandomly(int n)
	{
		NQueen obj = new NQueen(n);
		for(int i=0;i<n;i++)
		{
			obj.queens[i]=myRand.nextInt(n);
		}
		return obj;
	}
	
}
