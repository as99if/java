import java.util.ArrayList;
import java.util.Random;


public class Algorithms {
	static Random myRand = new Random();
	public static void main(String...args)
	{
		NQueen init = generateRandomly(5);
		
		System.out.println(init);
		System.out.println(init.getQuality());

		System.out.println( "with restart \n " +
				RandomizedHCwithRestart(5));
		System.out.println( "population \n " +
				PopRandomizedHC(1000, 5));
/*
		System.out.println(
				SimulatedAnnealing(5000, 7));*/
		
	}

	public static NQueen RandomizedHCwithRestart(int n)
	{
		double th = n*(n-1);
		int nonImproving = 0;
		NQueen s=generateRandomly(n);

		if(s.getQuality()==0) return s;

		NQueen globalBest = new NQueen(s);
		int gQuality = s.getQuality();

		int iter=0;
		while(iter++ < 2*(n*n))			// maxIter = 2(n*n)
		{
			NQueen sprime = generateRandomNeighbor(s);
			if(sprime.getQuality()==0)
			{
				System.out.println(iter);
				return sprime;
			}
			if(sprime.getQuality()<gQuality){
				globalBest=sprime;
				gQuality = sprime.getQuality();
				nonImproving = 0;
			}

			else if(sprime.getQuality() == gQuality){
				s=sprime;
				nonImproving++ ;
			}
			else{
				nonImproving++;
			}

			if(nonImproving > th) {				// restart
				nonImproving = 0;
				s=generateRandomly(n);
				iter = 0;
			}
		}
		return null;
	}

	public static NQueen PopRandomizedHC(int maxIter,int n)
	{
		int iter=0;
		ArrayList<NQueen> sList = new ArrayList<NQueen>();			// populated 10 tar jonno
		for(int i=0; i<10; i++) {
			NQueen s= generateRandomly(n);
			sList.add(s);
			if(s.getQuality()==0) {
				//System.out.println(iter);
				return s;
				}

			}

		while(iter++<maxIter)
		{
			for(int i=0; i<10; i++) {
				NQueen sprime = generateRandomNeighbor(sList.get(i));
				if (sprime.getQuality() == 0) {
					System.out.println(iter);
					return sprime;
				}
				if (sprime.getQuality() <= sList.get(i).getQuality()) {
					//System.out.println(iter);
					sList.add(i, sprime);
				}
			}

		}
		return null;
	}
	
	public static NQueen RandomizedHC(int maxIter,int n)
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
	public static NQueen RandomizedHCWithRW(int maxIter,int n,double wp)
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
			else if(myRand.nextDouble()<=wp)
				s=sprime;
		}
		return null;
	}
	public static NQueen 
	SimulatedAnnealing(int maxIter,int n)
	{
		NQueen s=generateRandomly(n);
		if(s.getQuality()==0) return s;
		int iter=0;
		double T = 10000;
		double alpha=0.98;
		while(iter++<maxIter)
		{
			NQueen sprime = generateRandomNeighbor(s);
			int qprime=sprime.getQuality();
			if(qprime==0)
			{	
				System.out.println(iter);
				return sprime;
			}
			int q = s.getQuality();
			if(qprime<=q)
				s=sprime;

			else if(myRand.nextDouble()<=
					Math.exp(-1.0*Math.abs(q-qprime)/T))
			{	
				s=sprime;
				T=T*alpha;
			}
		}
		return null;
	}
	public static NQueen Gibs(int maxIter,int n)
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
			if (sprime.getQuality()<=s.getQuality())
				s=sprime;
		}
		return null;
	}
	
	public static NQueen 
		MonteCarlo(int maxIter,int n)
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
