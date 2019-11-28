# 建库建表SQL语句

## 创建数据库

```SQL
CREATE DATABASE `rollcall` CHARACTER SET 'utf8mb4';
```

## 创建表

### 教学班表

```SQL
CREATE TABLE `rollcall`.`rollcall_teaching_class`  (
  `class_id` int(9) UNSIGNED ZEROFILL NOT NULL AUTO_INCREMENT COMMENT '教学班id',
  `class_name` varchar(40) NOT NULL COMMENT '课程名称',
  `teacher_id` int(9) UNSIGNED NOT NULL COMMENT '任课教师id',
  `class_session` tinyint(2) UNSIGNED NOT NULL COMMENT '上课讲次，第一个数代表周几，第二个数代表第几讲',
  `course_code` int(6) UNSIGNED ZEROFILL NOT NULL COMMENT '课程代码',
  `classroom` varchar(40) NOT NULL DEFAULT '' COMMENT '教室',
  `class_intro` varchar(255) NOT NULL DEFAULT '' COMMENT '课程简介',
  `semester` smallint(5) UNSIGNED ZEROFILL NOT NULL COMMENT '上课学期',
  `credit` tinyint(0) UNSIGNED NOT NULL COMMENT '学分',
  `weeks` varchar(40) NOT NULL DEFAULT '' COMMENT '周次',
  PRIMARY KEY (`class_id`)
) ENGINE = InnoDB COMMENT = '教学班表'
```

### 教师表

```SQL
CREATE TABLE `rollcall`.`rollcall_teacher`  (
  `teacher_id` int(9) UNSIGNED NOT NULL COMMENT '教师工号',
  `teacher_name` varchar(40) NOT NULL COMMENT '教师姓名',
  `department` varchar(40) NOT NULL COMMENT '教师所属部门',
  `gender` bit(1) NOT NULL COMMENT '1: 男; 0: 女',
  `password` varchar(40) NOT NULL COMMENT '密码',
  `title` varchar(20) NOT NULL COMMENT '职称',
  PRIMARY KEY (`teacher_id`)
) ENGINE = InnoDB COMMENT = '教师表'
```

### 学生表

```SQL
CREATE TABLE `rollcall`.`rollcall_student`  (
  `student_id` int(9) UNSIGNED NOT NULL COMMENT '学号',
  `student_name` varchar(40) NOT NULL COMMENT '学生姓名',
  `gender` bit(1) NOT NULL COMMENT '1: 男; 0: 女',
  `password` varchar(40) NOT NULL COMMENT '密码',
  `major` varchar(40) NOT NULL COMMENT '专业',
  PRIMARY KEY (`student_id`)
) ENGINE = InnoDB COMMENT = '学生表'
```

### 点名表

```SQL
CREATE TABLE `rollcall`.`rollcall_rollcall_record`  (
  `rollcall_id` int(9) UNSIGNED ZEROFILL NOT NULL AUTO_INCREMENT COMMENT '点名id',
  `rollcall_type` tinyint(1) UNSIGNED NOT NULL COMMENT '点名类型  1: 点名; 2: 提问',
  `rollcall_time` timestamp(0) NOT NULL DEFAULT CURRENT_TIMESTAMP(0) COMMENT '点名时间',
  `presence` char(2) NOT NULL COMMENT '点名情况',
  `class_id` int(9) UNSIGNED NOT NULL COMMENT '教学班id',
  `student_id` int(9) NOT NULL COMMENT '学号',
  PRIMARY KEY (`rollcall_id`)
) ENGINE = InnoDB COMMENT = '点名表'
```

### 选课记录表

```SQL
CREATE TABLE `rollcall`.`rollcall_class_selection`  (
  `selection_id` int(9) UNSIGNED ZEROFILL NOT NULL AUTO_INCREMENT COMMENT '选课操作id',
  `student_id` int(9) UNSIGNED NOT NULL COMMENT '学号',
  `class_id` int(9) UNSIGNED ZEROFILL NOT NULL COMMENT '课程id',
  PRIMARY KEY (`selection_id`)
) ENGINE = InnoDB COMMENT = '选课表（学生-教学班关系表）'
```