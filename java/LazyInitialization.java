// https://blog.seotory.com/post/2016/03/java-singleton-pattern
// 싱글톤 샘플 
public class LazyInitialization {
	private static LazyInitialization instance;
	private LazyInitialization(){}

	public static LazyInitialization getInstance() {
		if ( instance == null )
			instance = new LazyInitialization();
		return instance;
	}

	public void print () {
		System.out.println("dd");
		System.out.println("in hash" + instance.hashCode());
	}
}

