package pers.jiangyinzuo.rollcall.domain.mapper;


import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * ����ӳ��ʵ��������ݿ����
 * @author Jiang Yinzuo
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
public @interface TableMapper {

    // ���ݿ����
    String value();
}
