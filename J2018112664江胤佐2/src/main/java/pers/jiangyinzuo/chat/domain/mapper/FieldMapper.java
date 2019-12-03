package pers.jiangyinzuo.chat.domain.mapper;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 用于将数据库字段映射为实体类成员变量, 不能反过来映射
 * @author Jiang Yinzuo
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)
public @interface FieldMapper {

    /**
     * column" 表示可以直接映射的字段; "reference" 表示该成员为实体类, 还需要关联查询
     * @return 类型字符串
     */
    String type() default "column";

    /**
     * 字段名, 若type = "reference",
     * 则进行 `SELECT * FROM TableMapper.value() WHERE FieldMapper.joinName() = ?`
     * 的查询
     * @return 字段名字符串
     */
    String name() default "";

    /**
     * type = "reference"时, 实体对应的另一张表中的字段名
     * @return
     */
    String joinName() default "";
}
