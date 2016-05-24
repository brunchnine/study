//http://blog.jdm.kr/68 리플렉션 예제
package Reflection_sample_1;
import java.lang.reflect.*;

public class ReflectTest{
    public static void main(String[] args)throws ClassNotFoundException{
        Class<?> cls = Class.forName("ReflectCls");

        // 가지고 있는 멤버 변수 출력. public 멤버 변수만 가져오기
        Field[] fields = cls.getFields();
        for(Field field : fields){
            System.out.println(field.getType().getName()+" "+field.getName());
        }
        System.out.println("--------------------------------------");

        // 가지고 있는 메소드의 이름을 출력 해보자. public 메소드만 가져온다.

       Method[] methods = cls.getMethods();
       StringBuffer sb = new StringBuffer();
       for( Method method : methods ){
           sb.append(method.getName());

           // 메소드 인자가 있다면 출력하자.
           Class<?>[] argTypes = method.getParameterTypes();
           sb.append("(");
           int size = argTypes.length;
           for( Class<?> argType : argTypes ){
               String argName = argType.getName();
               sb.append(argName + " val");
               if( --size != 0 ){
                   sb.append(",");
               }
           }
           sb.append(")");

           // 리턴 인자 출력
           Class<?> returnType = method.getReturnType();
           sb.append(" : "+ returnType.getName());

           System.out.println(sb);
           sb.setLength(0);
       }
       System.out.println("-----------------------------------------");

       // 가지고 있는 메소드를 씀
       try{
           //객체 생성
           Object obj = cls.newInstance();

           // sum 메소드를 가져와서 합 구하기( 인자 파라미터 나열)
           Method method  = cls.getMethod("sum", int.class, int.class);
           System.out.println(method.invoke(obj, 1, 2));

           // sum 메소드를 가져와서 합 구하기 (클래스 배열 파라미터)
           Class[] param = {int.class , int.class};
           System.out.println(method.invoke(obj, 5, 2));

           // sum 메소드 가져와서 합구하기(다이렉트)
           method = cls.getMethod("sum", new Class[]{int.class, int.class});
           System.out.println(method.invoke(obj,  new Object[]{1, 5}));

           // sub static 메소드를 가져와 차이 구하기
           System.out.println(method.invoke(null, 3,1));

           System.out.println(method.invoke(obj, 3,1));
           System.out.println(method.invoke(cls, 3,1));

           //오버로딩 - 매개 인자 없는 메소드 실행
           method = cls.getMethod("getArrayList", (Class<?>[]) null);
           method.invoke(obj);
           method = cls.getMethod("getArrayList");
           method.invoke(obj);

           // 오버로딩 매개 인자가 있는 메소드 실행
           method = cls.getMethod("getArrayList" , new Class[]{int.class});
           method.invoke(obj, new Object[]{1});

           // 클래스를 형변환해서 테스트
           ReflectCls c = (ReflectCls)obj;
           System.out.println(c.sum(5, 2));
           System.out.println(c.sub(5, 2));
           System.out.println(ReflectCls.sub(5, 2));

       } catch (
               InstantiationException | IllegalAccessException |
               NoSuchMethodException | SecurityException | IllegalArgumentException | InvocationTargetException e)
       {
           e.printStackTrace();
       }
    }
}




