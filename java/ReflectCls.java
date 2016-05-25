import java.util.ArrayList;
public class ReflectCls {
	
	private String privateStr;
	protected String protectedStr;
	public String publicStr;
	
	public ArrayList<String> getArrayList(){
		System.out.println("인자 없는 getArrayList");
		return new ArrayList<String>();
	}
	public ArrayList<String> getArrayList(int a){
		System.out.println("인자 있는 getArrayList");
		return new ArrayList<String>();
	}
	public String[] getStringArray(){
		return new String[5];
	}
	public int sum( int a, int b){
		return a+b;
	}
	public static int sub(int a, int b){
		return a-b;
	}
	private void setStringAraay(String[] a){
		
	}

}

