# Java程序设计实验2019

课程《Java程序设计实验（0474014）》的实验任务，分阶段逐步完成。  

## 设计项目：网络聊天系统

### 系统说明

类似于QQ、飞秋等的网络聊天工具，支持群聊、私聊等。  
涉及技术：面向对象、GUI、IO、数据库、网络、多线程、异常、集合...  

### 主要功能

包含但不限于以下功能：

#### 1、聊天服务器

服务器管理（开启、暂停、关闭）；  
通信处理（接收、发送、转发数据）；  
聊天数据存储；  
用户用户验证。  

#### 2、服务管理端

用户管理（用户注册审核、用户登录审核、在线用户显示、在线人数统计、登录日志管理、上下线处理、用户信息管理）；  
群组管理（群组注册审核、群组信息管理）；  
聊天管理（敏感信息管理、敏感信息过滤（加分项）、聊天记录管理）；

#### 3、用户端功能

用户注册、找回密码、修改信息；用户登录；  
好友管理（添加好友、删除好友、查看好友信息、好友分组、上线提示）；  
群组管理（建群、建组、成员管理、群组管理）；  
聊天（发送信息、私聊、群聊、私聊记录、群聊记录）信息包括文本、表情（加分项）、图片（加分项）；能文件传递（加分项）；  
客户端（交互界面、请求服务）即  

### 验证型实验

实验教材上的题目，自选不低于50%的题目，要能在题目上进行适当变化进行验证。

## 其他说明

### 开发环境

#### JDK

使用JDK8或JDK11

#### Eclipse

[https://www.eclipse.org/downloads/packages/](https://www.eclipse.org/downloads/packages/)  
下载eclipse-java-2019-06-R-win32-x86_64.zip，解压到预定目录。  

#### Eclipse安装egit插件

启动Eclipse，【帮助】->【Install New Software...】，弹窗中点击Add...，然后输入：  
Name：egit  
Location: https://download.eclipse.org/egit/updates  
勾选3个：  
Git integration for Eclipse  
Git integration for Eclipse - experimental features  
Java implementation of Git  
按步骤安装  

### 实验项目要求

#### 项目名称

在加入课程团队后，gitee.com中登录自己的账号，  
Fork课程项目[https://gitee.com/tjhe/java2019a](https://gitee.com/tjhe/java2019a)到自己的私人仓库  
然后在自己的gitee中找到Fork的项目，使用git客户端克隆（非下载）到自己计算机的工作目录，如../java2019a。  
然后在java2019a中使用Eclipse（或其他工具）创建项目，项目名称：
J学号姓名2，如“J2017110001张三2”  
在该项目中实现作业任务的所有功能  
其中验证性的实验任务放在名称为ex的包中如：

``` 验证型实验
ex.ch01.ex0101  //表示第一章第一个实验题目
ex.ch01.ex0102  //表示第一章第二个实验题目
ex.ch02.ex0201
ex.ch02.ex0202
```

#### 代码要求

代码要详细注释  
代码规范参考阿里或华为的规范  

#### 进度要求

每周至少提交一次版本更新，并用MarkDown格式撰写周报  

总体时间安排：  

##### Java语言编程基础（第1-3周）

Java开发环境构建、Java语言编程基础（控制结构、数组、字符串、基本输入输出）

##### Java面向对象编程（第4-8周）

项目的分析、设计；类的设计、基于控制台文本菜单方式的交互

##### Java GUI编程（8-11周）

包括基于JavaFx或Swing的图形用户界面、基于文件的数据存储等  

##### Java 综合编程（12-16周

基于数据库存储及复杂功能的完善。

#### 设计报告

内容包括：  
包括需求分析（功能需求、数据需求、用户交互需求）；  
总体设计（功能模块划分及描述、程序架构、类图等）；  
详细设计（模块、类详细设计、数据结构或数据库设计）、设计结果（使用说明等）；  
包含实验任务的分析、设计、实现、实验测试运行结果）、实验体会（含实验过程中的问题解决、实验收获、建议意见等）  
提交电子版（体现不断改进完善的迭代过程），在git项目中可采用MarkDown格式。  

期末以纸质版、电子版；将编写的主要代码作为文档附录，电子版Word文档格式。 要有封面、目录、页码，建议五号字，排版要规范  
