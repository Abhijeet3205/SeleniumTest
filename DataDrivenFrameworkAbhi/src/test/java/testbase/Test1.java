package testbase;

public class Test1 {

	public static void main(String[] args) {
		int a =10;
		int b = 20;
		int c = 30;
		System.out.println("a: "+a +" b: "+b +" c: "+c);
		a = b;
		b = c;
		c = a;
		System.out.println("a: "+a +" b: "+b +" c: "+c);//a =20 , b= c =
	}

}
