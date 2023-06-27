import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import test.*;
public class xyz2 extends xyz4{

	
	int i=2;
	//@Test
	public void a1() {
		int n=4514;
		int temp=n;
		int sum=0;
		int lastdigit;
		while (temp>0) {
			lastdigit=temp%10;
			
			sum=(sum*10)+lastdigit;
			temp=temp/10;
			
		}
		
		
		if(n==sum)
			System.out.println("Yes");
		else
			System.out.println("no");

		
	
	}
	
	//@Test
	public void fibo() {
		int n1=0;
		int n2=1;
		int count =10;
		int n3;
		System.out.println(n1);
		System.out.println(n2);

		
		for (int i=0;i<count-2;i++) {
			n3=n1+n2;
			
			System.out.println(n3);
			n1=n2;
			n2=n3;
		}
	
	
	}
	
	
	@Test
	public void armstrong() {
		int number=1634;
		int tmp=number;
		int count =0;
		
		while(tmp>0) {
			tmp=tmp/10;
			count++;
			
		}
		
		tmp=number;
		int sum=0;
		while(tmp>0) {
			int lastdigit = tmp%10;
			sum+=Math.pow(lastdigit, count);
			tmp=tmp/10;
		}
		if(number==sum)
			System.out.println("Yes");
		else
			System.out.println("no");
	
	}
	
	
	
	//@Test
	public void asdffdgf() {
		//xyz a=new xyz2();
		//System.out.println(a.i);
		//a.a1();
		//a.c();
		this.a1();
	
	}
	}
