

```MySQL
CREATE TABLE `chat`.`chat_user`  (
  `user_id` int(9) UNSIGNED ZEROFILL NOT NULL AUTO_INCREMENT COMMENT '用户id',
  `user_name` varchar(40) NOT NULL DEFAULT '' COMMENT '用户名',
  `password` varchar(20) NOT NULL DEFAULT 123456 COMMENT '密码',
  `intro` varchar(100) NOT NULL COMMENT '个人简介',
  `avatar` varchar(255) NOT NULL DEFAULT '' COMMENT '头像',
  PRIMARY KEY (`user_id`)
);
```

```MySQL
CREATE TABLE `chat`.`chat_room`  (
  `group_id` int(9) UNSIGNED ZEROFILL NOT NULL AUTO_INCREMENT COMMENT '群聊id',
  `group_name` varchar(40) NOT NULL COMMENT '群聊名称',
  `group_intro` varchar(100) NOT NULL DEFAULT '' COMMENT '群聊简介',
  `group_avatar` varchar(255) NOT NULL DEFAULT '' COMMENT '群聊头像',
  PRIMARY KEY (`group_id`)
);
```

```MySQL
CREATE TABLE `chat`.`chat_friendship`  (
  `friendship_id` int(9) UNSIGNED ZEROFILL NOT NULL AUTO_INCREMENT COMMENT '好友关系主键',
  `user1_id` int(9) UNSIGNED ZEROFILL NOT NULL COMMENT '用户1id',
  `user2_id` int(9) UNSIGNED ZEROFILL NOT NULL COMMENT '用户2id',
  `user1_category` varchar(40) NOT NULL DEFAULT '默认分组' COMMENT '用户1在用户2下的分组',
  `user2_category` varchar(40) NOT NULL DEFAULT '默认分组' COMMENT '用户2在用户1下的分组',
  PRIMARY KEY (`friendship_id`)
);
```

```MySQL
CREATE TABLE `chat`.`chat_message`  (
  `message_id` int(9) UNSIGNED NOT NULL COMMENT '消息id',
  `message_type` tinyint(3) UNSIGNED NOT NULL COMMENT '消息类型 1: 文本 2: 图片 3: 文件',
  `message_content` varchar(255) NOT NULL COMMENT '消息内容',
  `send_from` int(9) UNSIGNED ZEROFILL NOT NULL COMMENT '发送者id',
  `send_to` int(9) UNSIGNED ZEROFILL NOT NULL COMMENT '接收者id',
  `send_time` timestamp(0) NOT NULL COMMENT '发送时间',
  PRIMARY KEY (`message_id`)
);
```