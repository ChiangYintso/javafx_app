package pers.jiangyinzuo.chat.domain.mapper;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * ���ڽ����ݿ��ֶ�ӳ��Ϊʵ�����Ա����, ���ܷ�����ӳ��
 * @author Jiang Yinzuo
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)
public @interface FieldMapper {

    /**
     * column" ��ʾ����ֱ��ӳ����ֶ�; "reference" ��ʾ�ó�ԱΪʵ����, ����Ҫ������ѯ
     * @return �����ַ���
     */
    String type() default "column";

    /**
     * �ֶ���, ��type = "reference",
     * ����� `SELECT * FROM TableMapper.value() WHERE FieldMapper.joinName() = ?`
     * �Ĳ�ѯ
     * @return �ֶ����ַ���
     */
    String name() default "";

    /**
     * type = "reference"ʱ, ʵ���Ӧ����һ�ű��е��ֶ���
     * @return
     */
    String joinName() default "";
}
