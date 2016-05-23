//http://arabiannight.tistory.com/entry/%EC%9E%90%EB%B0%94Java-ArrayListT-%EC%A0%9C%EB%84%A4%EB%A6%AD%EC%8A%A4Generics%EB%9E%80
// 제네릭스 sample
import java.util.ArrayList;
public class GenericsSample{
    public static void main(String[] args){
    // <String> Generics String
    ArrayList<String> mStringList = new ArrayList<String>();
    mStringList.add("안녕하세요");
    mStringList.add("3");

    for(String s : mStringList){
        System.out.println(s);
    }

    // <Integer> Generics Int
    ArrayList<Integer> mIntegerList = new ArrayList<Integer>();
    mIntegerList.add(1);
    mIntegerList.add(2);

    for(Integer i : mIntegerList){ // 향상된 for문  
        System.out.println(i);
    }
    // <Class name> Generics
    ArrayList<Tv> mTvList = new ArrayList<Tv>();
    mTvList.add(new Tv("New Tv"));

    for(Tv t : mTvList){
        System.out.println(t);
    }

    // No Generics
    // 형변환 해줘야 함
    ArrayList mOriginalList = new ArrayList();
    mOriginalList.add(1);
    mOriginalList.add("String");
    mOriginalList.add(new Tv("original"));

    int a = (Integer) mOriginalList.get(0);
    String b = (String) mOriginalList.get(1);
    Tv c = (Tv) mOriginalList.get(2);
}
}

// Tv Class
class Tv{
    private String caption;
    public Tv(){
        this("Tv Class");
    }
    public Tv (String caption){
        this.caption = caption;
    }
    public String toString () {
        return caption;
    }
}
