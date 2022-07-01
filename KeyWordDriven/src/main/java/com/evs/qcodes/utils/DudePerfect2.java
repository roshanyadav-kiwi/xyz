package com.evs.qcodes.utils;

import java.util.Scanner;

public class DudePerfect2 {

	public static void main(String[] args) {
// TODO Auto-generated method stub
		/*
		 * // Scanner a= new Scanner(System.in); //
		 * System.out.println("give the input"); // int b=a.nextInt(); // for (int i=1;
		 * i<=10; i++) { // int c=b*i; // System.out.println(b+ "*"+ i+"="+c); // // }
		 * // for (char a='a'; a<='z'; a++) { // //System.out.println(a); // int b=a; //
		 * System.out.println(a+"="+b); // } // int a =65; // char b=(char)a; //
		 * System.out.println(b);
		 */		roshan();
	
	//int b=a.compareTo("yadav");
	//System.out.println(b);
	}
		
	
	public static void roshan() {
		System.out.println("Task 1");
		for(int i=1; i<=4; i++) {
		for (int j=1; j<=i;j++)
			System.out.print(" ");	
		for(int k=4; k>=i; k--) {
			System.out.print(" *");
		}
		System.out.println();

		}
		System.out.println("Task 2");
		for(int i=1; i<=4; i++) {
			for (int j=4; j>=i; j--) {
				System.out.print(" ");
			}
			for (int k=1; k<=i; k++) {
				System.out.print("* ");
			}
			System.out.println();
			}
		System.out.println("Task 3");
		for (int i=1; i<=4; i++) {
		for (int j =4; j>=i; j--) {
			System.out.print(" *");
	}
		System.out.println();
		
		
	}

		System.out.println("Task 4");
		for (int i=1; i<=4; i++) {
			for(int j=1; j<=i; j++) {
				System.out.print(" *");
			}
			System.out.println();
			}

	System.out.println("Task 5");
	for (int i=1; i<=4; i++) {
		for (int j =3; j>=i; j--) {
			System.out.print(" ");
		}
		for (int k=1;k<=i; k++) {
			System.out.print("*");
		}
		System.out.println();
		
		
	}
	System.out.println("Task 6");
		for(int i=1; i<=4; i++) {
		for (int j=1; j<=i;j++)
			System.out.print(" ");	
		for(int k=4; k>=i; k--) {
			System.out.print("*");
		}
		System.out.println();

		}
	System.out.println("Task 7");     
	for (int i=1; i<=3; i++) {
	for (int j=1; j<=2; j++) {
		System.out.print("**  ");
	}

	System.out.println();
	}
	for (int k=1; k<=6; k++) {
	System.out.print("*");
	}	
	System.out.println();
	for (int i=1; i<=3; i++) {
	System.out.println("    **");
	
					}


	/*Scanner  obj= new Scanner(System.in);
	int input=obj.nextInt();
	for (int i=1; i<=10; i++) {
		 int result=input*i;
		System.out.println(result);
		// this is used to give the input in console then its show the table which number you give ::::::
	   							   
	}*/
	
	System.out.println("Task 8");
	//Print star
	/// step 1
	for(int i=1; i<=4; i++) {
			System.out.print("     ");
			for (int j=4; j>=i;j--)
				System.out.print(" ");	
			for(int k=1; k<=i; k++) {
				System.out.print("* ");
			}
//			for(int a=1; a<=i; a++) {
//				System.out.print("*");
//			}
		System.out.println();
//
		}						
		///Step 2
		for(int i=1; i<=4; i++) {
		for (int j=1; j<=i;j++)
			System.out.print(" ");	
		for(int k=4; k>=i; k--) {
			System.out.print("*");
		}
		for (int x=1; x<=7; x++) {
			System.out.print(" ");
		}
			for(int z=4; z>=i; z--) {
				System.out.print("*");
			}
	System.out.println(" ");
	}
		///step 3
		for(int i=1; i<=4; i++) {
			
			for(int k=4; k>=i;k--)
				System.out.print(" ");
			for(int j=1; j<=i; j++) {
				System.out.print("*");
			}
		for (int a=1; a<=8; a++) {
			System.out.print(" ");	
		}
		for(int z=1; z<=i; z++) {
			System.out.print("*");
		}
		System.out.println();
		}	
		//Step 4
	for(int i=1; i<=4; i++) {
		System.out.print("     ");
		for(int k=1; k<=i;k++)
			System.out.print(" ");
		for(int j=4; j>=i; j--) {
			System.out.print("* ");
		}
//	for(int a=4; a>=i; a-- ) {
//		System.out.print("*");
//	}
System.out.println();
}
	System.out.println("task 9");
//task 9 char to assciii	
	
	System.out.print("Enter a character: ");  
	Scanner sc = new Scanner(System.in);  
	char chr = sc.next().charAt(0);  
	int asciiValue = chr;  
System.out.println("ASCII value of " +chr+ " is: "+asciiValue);  
// task 10 char to ascii;
	for (char ch='A'; ch<='z'; ch++) {
	//	int i=ch;
	//	System.out.println(ch +": " +i );
	System.out.println("task 10");
//checking driving liscense
//Scanner a=new Scanner (System.in);
//		System.out.println("input the candidate age");
//		int inpt_age=a.nextInt();
//		int age=inpt_age;
//		
//		if(age<=17) {
//		System.out.println("You are not eligble for driving licesense");	
//		}
//		else  if (age<80) {
//			System.out.println("You are  eligble for driving liscense");
//		}
//	else {
//		System.out.println("You are not eligble for driving liscense");
//	
//	}		
	System.out.println("task 11");
		//Odd even number checking 
//	System.out.println("enter the number what you want to check ");
//	int sum=a.nextInt();
//	if (sum%2==0) {
//		System.out.println("The number is sum");
//	}
//	else {
//		System.out.println("The number is odd");
//	}
	System.out.println("task 12");
	// for printing
  /*1
	12
	123
	1234
	12345*/
//for(int i=1; i<=1;  i++) {
//System.out.print(i);
//}
//System.out.println();
//for(int i=1;i<=2; i++) {
//System.out.print(i);
//
//}
//System.out.println();
//for(int i=1; i<=3; i++) {
//	System.out.print(i);
//}
//System.out.println();
//for(int i=1; i<=4; i++) {
//	System.out.print(i);
//	}
//System.out.println();
// for(int i=1; i<=5; i++) {
//	System.out.print(i);
//	}
//System.out.println();
//}
	System.out.println("task 13");
	int a,b,c;
	a=0;
	b=1;
	for(int i=1; i<=10; i++) {
	c=a+b;
	System.out.println(c);	
	a=b;
	b=c;
//}
//	Scanner user= new Scanner(System.in);
//	int val=user.nextInt();
//	switch (val) {
//	case (1):
//		System.out.println("sunday");
//		break;
//	case (2):
//		System.out.println("monday");
//		break;
//	case (3):
//		System.out.println("tuesday");
//		break;
//	case (4):
//		System.out.println("wednesday");
//		break;
//	case (5):
//		System.out.println("thrusday");
//		break;
//	case (6):
//		System.out.println("friday");
//		break;
//	case (7):
//		System.out.println("saturday");
//		break;
//	default:
//		System.out.println("No day");
//		break;
	System.out.println("task 14");
	//swapping the two numbers without using third varriable	
//		int a=0; 
//		int b=0;
//		Scanner inpt= new Scanner(System.in);
//		a=inpt.nextInt();
//		b=inpt.nextInt();
//		System.out.println("before swapping the numbers are :" + a + " " +b);
//		a=a+b;
//		b=a-b;
//		a=a-b;
//		System.out.println("after swapping the numbers : " + a + " " + b);
//	}
	System.out.println("task 15");
//  swapping two numbers with using third varriable
//		int a,b,c;
//		Scanner scn=new Scanner(System.in);
//		System.out.println("Enter the first number : ");
//		a=scn.nextInt();
//		System.out.println("Enter the second number : ");
//		b=scn.nextInt();
//		System.out.println("Before swapping  numbers are : "+ a + " " + b);
//		c=a;
//		a=b;
//		b=c;
//		System.out.println("After the  swapping numbers are : "+ a+" "+ b );
		
		}
	}
	}
}

