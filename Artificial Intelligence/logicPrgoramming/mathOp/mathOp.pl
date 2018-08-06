/*x is 2 mane x=2
addition/3
addition(x,y,z) = x+y=z
*/
/*0+X=X*/
addition(0,X,X).
addition(X,Y,Z):-
	write(X),
	X>0, X1 is X-1,
	addition(X1,Y,Z1),
	Z is Z1+1.
/*X-0=X*/
substraction(X,0,X).
substraction(X,Y,Z):-
	write(X),
	Y>0, Y1 is Y-1,
	substraction(X,Y1,Z1),
	Z is Z1-1.


factorial(N,F):-
	write(N),
	N>0,
	N1 is N-1,
	factorial(N1,F1),
	F is F1*N.
factorial(0,1).

/*X=small, Y=large, Z=gcd goshagu*/
gcd(0,X,X).
gcd(A,B,C):-
	write(A),
	A>0,
	X is mod(B,A),
	gcd(X,A,C).
	
	
elementOf(X,[X|_]).				/*_ jai thakuk na keno*/
elementOf(X,[_|Z]):-
	elementOf(X,Z).
	
addList([],X,X).
addList([X|XL],Y,[X|ZL]):-
	addList(XL,Y,ZL).
	


	
	
	

