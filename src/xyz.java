import org.testng.annotations.Test;

public class xyz {

	int i=1;
	public void a1() {
		System.out.println("BM");
	}
	
	//@Test
	protected void c() {
		String s="fdgh";
		System.out.println(s.length());
	}
	//@Test (priority=-1)
	public void b() {
		String s="fdgvbvnh";
		System.out.println(s.length());
	}
	
}
