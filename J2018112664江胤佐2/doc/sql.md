

```MySQL
CREATE TABLE `chat`.`chat_user`  (
  `user_id` int(9) UNSIGNED ZEROFILL NOT NULL AUTO_INCREMENT COMMENT '�û�id',
  `user_name` varchar(40) NOT NULL DEFAULT '' COMMENT '�û���',
  `password` varchar(20) NOT NULL DEFAULT 123456 COMMENT '����',
  `intro` varchar(100) NOT NULL COMMENT '���˼��',
  `avatar` varchar(255) NOT NULL DEFAULT '' COMMENT 'ͷ��',
  PRIMARY KEY (`user_id`)
);
```

```MySQL
CREATE TABLE `chat`.`chat_room`  (
  `group_id` int(9) UNSIGNED ZEROFILL NOT NULL AUTO_INCREMENT COMMENT 'Ⱥ��id',
  `group_name` varchar(40) NOT NULL COMMENT 'Ⱥ������',
  `group_intro` varchar(100) NOT NULL DEFAULT '' COMMENT 'Ⱥ�ļ��',
  `group_avatar` varchar(255) NOT NULL DEFAULT '' COMMENT 'Ⱥ��ͷ��',
  PRIMARY KEY (`group_id`)
);
```

```MySQL
CREATE TABLE `chat`.`chat_friendship`  (
  `friendship_id` int(9) UNSIGNED ZEROFILL NOT NULL AUTO_INCREMENT COMMENT '���ѹ�ϵ����',
  `user1_id` int(9) UNSIGNED ZEROFILL NOT NULL COMMENT '�û�1id',
  `user2_id` int(9) UNSIGNED ZEROFILL NOT NULL COMMENT '�û�2id',
  `user1_category` varchar(40) NOT NULL DEFAULT 'Ĭ�Ϸ���' COMMENT '�û�1���û�2�µķ���',
  `user2_category` varchar(40) NOT NULL DEFAULT 'Ĭ�Ϸ���' COMMENT '�û�2���û�1�µķ���',
  PRIMARY KEY (`friendship_id`)
);
```

```MySQL
CREATE TABLE `chat`.`chat_message`  (
  `message_id` int(9) UNSIGNED NOT NULL COMMENT '��Ϣid',
  `message_type` tinyint(3) UNSIGNED NOT NULL COMMENT '��Ϣ���� 1: �ı� 2: ͼƬ 3: �ļ�',
  `message_content` varchar(255) NOT NULL COMMENT '��Ϣ����',
  `send_from` int(9) UNSIGNED ZEROFILL NOT NULL COMMENT '������id',
  `send_to` int(9) UNSIGNED ZEROFILL NOT NULL COMMENT '������id',
  `send_time` timestamp(0) NOT NULL COMMENT '����ʱ��',
  PRIMARY KEY (`message_id`)
);
```