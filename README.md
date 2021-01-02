# Dubbo

版本信息：
1）JDK1.8

2）dubbo：2.6.2
maven依赖：
<dependency>
      <groupId>com.alibaba</groupId>
      <artifactId>dubbo</artifactId>
      <version>2.6.2</version>
    </dependency>

<!-->注册中心使用的是zookeeper,引入操作zookeeper的客户端<-->
<!--dubbo 2.6以前的版本引入zkclint 操作zookeeper
    dubbo 2.6以及以后的版本引入curator操作zookeeper><!-->
 <dependency>
      <groupId>org.apache.curator</groupId>
      <artifactId>curator-framework</artifactId>
      <version>2.12.0</version>
 </dependency>
