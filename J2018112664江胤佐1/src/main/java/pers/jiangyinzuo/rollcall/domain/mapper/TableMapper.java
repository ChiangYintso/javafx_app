package pers.jiangyinzuo.rollcall.domain.mapper;


import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 用于映射实体类和数据库表名
 * @author Jiang Yinzuo
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
public @interface TableMapper {

    // 数据库表名
    String value();
}
