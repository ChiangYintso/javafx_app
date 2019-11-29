package pers.jiangyinzuo.chat.domain.mapper;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * ����ӳ��ʵ�����Ա�����ͱ��ֶ�
 * @author Jiang Yinzuo
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)
public @interface FieldMapper {

    // "column" ��ʾ����ֱ��ӳ����ֶ�; "reference" ��ʾ�ó�ԱΪʵ����, ����Ҫ������ѯ
    String type() default "column";

    // �ֶ���, ��`type` = "reference", �����Ϊ��
    String name() default "";
}
