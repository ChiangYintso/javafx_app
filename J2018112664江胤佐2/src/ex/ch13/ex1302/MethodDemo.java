package ex.ch13.ex1302;

import java.lang.reflect.Method;
import java.lang.reflect.Modifier;

public class MethodDemo {
    public static void main(String[] args) {
        try {
            Class clazz = Class.forName("java.lang.String");
            Method[] methods = clazz.getMethods();
            for (Method method : methods) {
                int mod = method.getModifiers();
                System.out.println(Modifier.toString(mod));
                Class reType = method.getReturnType();
                System.out.println(" " + reType.getName());
                System.out.println(" " + method.getName());
                Class[] paramType = method.getParameterTypes();
                for (int i = 0; i < paramType.length; ++i) {
                    if (i > 0) {
                        System.out.println(",");
                    }
                    System.out.println(paramType[i].getName());
                }
                System.out.println(");");
            }
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
}
