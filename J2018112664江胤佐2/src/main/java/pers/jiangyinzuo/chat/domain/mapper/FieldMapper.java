package pers.jiangyinzuo.chat.domain.mapper;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 用于映射实体类成员变量和表字段
 * @author Jiang Yinzuo
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)
public @interface FieldMapper {

    // "column" 表示可以直接映射的字段; "reference" 表示该成员为实体类, 还需要关联查询
    String type() default "column";

    // 字段名, 若`type` = "reference", 则可以为空
    String name() default "";
}
