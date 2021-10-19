# Java se基础

1. Junit单元测试
2. 反射
3. 注解

## Junit单元测试：

* 测试分类：
  1. 黑盒测试：不需要写代码。给输入的值，看程序能否输出期望的值。
  2. 白盒测试：需要写代码。关注程序的具体执行流程。
* Junit使用：白盒测试
  * 步骤：
    1. 定义一个测试类（测试用例）
       * 建议：
         * 测试类名：被测试类名+Test
         * 包名：xxx.xxx.xx.test
    2. 定义测试方法：可以独立运行
       * 建议：
         * 方法名：test测试的方法名
         * 返回值：void
         * 参数列表：空参
    3. 给方法加@Test注解
    4. 导入junit依赖环境
  * 判定结果：
    1. 红色：失败
    2. 绿色：成功
    3. 一般我们会使用断言操作来处理结果
       * Assert.assertEquals( 期望值 , 结果值 )
  * 补充：
  	1. @Before	
  		* 修饰的方法会在测试方法之前被自动执行
  	2. @After
  		* 修饰的方法会在测试方法执行后自动执行

## 反射：框架设计的灵魂

* 框架：半成品软件。可以在框架的基础上进行软件的开发，简化编码

* 反射：将类的各个组成部分封装为其他对象，这就是反射机制
	* 好处：
		1. 可以在程序的运行过程中，操作这些对象。
		2. 可以解耦，提高程序的可扩展性
	
* 获取Class对象的方式：

	1. Calss.forName("全类名")：将字节码文件加载进内存，返回Class对象
		* 多用于配置文件，将类名定义在配置文件中。读取文件，加载类
	2. 类名.class：通过类名的属性class获取
		* 多用于参数的传递
	3. 对象.getClass()：getClass()方法在Object类中定义着。
		* 多用于一个对象的获取字节码的方式

	* **结论：**

		​	同一个字节码文件（*.class）在一次程序的运行过程中，只会被加载一次，不论是通过哪一种方式获取的Class对象都是同一个。

* Class对象功能：

	* 获取功能：

		1. 获取成员变量们

			- Field[]     getFields()                                                                                                  获取所有public修饰的成员变量
			- Field        getField(String name)                                                                             获取指定名称的public修饰的成员变量

			

			* Field[]     getDeclaredFields()                                                                                  获取所有成员变量，不考虑修饰符
			* Field        getDeclaredField(String name)

		1. 获取构造方法们

			* Constructor<?>[]        getConstructors()

			* Constructor<?>          getConstructor(类<?>...parameterTypes)

				

			* Constructor<?>[]        getDeclaredConstructors()
			* Constructor<?>          getDeclaredConstructor(类<?>...parameterTypes)

		2. 获取成员方法们

			* Method[] 					getMethods()                                                                      获取所有public修饰的方法

			* Method                        getMethod(String name,类<?>...parameterTypes)

				

			* Method[] 					getDeclaredMethods()                                                      含有Declared的方法不会获取父类的方法
			* Method                        getDeclaredMethod(String name,类<?>...parameterTypes)

		3. 获取类名

			* getName()

* Field：成员变量

	* 操作：

		1. 设置值

			* set(Object obj,  Object value)

		2. 获取值

			* get(Object obj)

		3. 忽略访问权限修饰的安全检查

			* ```java
				declaredFielName.setAccessible(true);//暴力反射
				```

* Constructor：构造方法

	* 创建对象：

		* newInstance(Object... initargs)
			
		* 如果使用空参构造方法创建对象，操作是可以简化：Class对象的newInstance方法

* Method：方法对象

	* 执行方法：
		* invoke(Object obj, Object... args)
	* 获取方法名称：
		* String getName:获取方法名

* 案例：

  * 需求：写一个“框架”，在不能改变该类的任何代码的前提下，可以执行创建任意类的对象，可以去执行任意方法。

  	* 实现：
  		1. 配置文件
  		2. 反射

  	* 步骤：
  		1. 将需要创建的对象的全类名和需要执行的方法定义在配置文件中
  		2. 在程序中加载读取配置文件
  		3. 使用反射技术来加载类文件进内容
  		4. 创建对象
  		5. 执行方法

# 注解：

* 概念：说明程序的，给计算机看的
* 注释：用文字描述程序的。



* 定义：注解（Annotation），从JDK5开始,Java增加对元数据的支持，也就是注解，注解与注释是有一定区别的，可以把注解理解为代码里的特殊标记，这些标记可以在编译，类加载，运行时被读取，并执行相应的处理。通过注解开发人员可以在不改变原有代码和逻辑的情况下在源代码中嵌入补充信息。
* 概念描述：
	1. JDK1.5之后的新特性
	2. 说明程序的
	3. 使用注解：@注解名称
* 作用分类：
	1. 编写文档：通过代码里面的标识的元数据生成文档【生成文档doc文档】
	2. 代码分析：通过代码里标识的元数据对代码进行分析【使用反射】
	3. 编译检查：通过代码里标识的元数据让编译器能够实现基本的编译检查【Override】
	
* JDK中预定义的一些注解

  * @Override ：检测被该注解标注的方法是否是继承自父类（接口）的
  * @Deprecated ：将该注解标注的内容，表示已过时
  * @SuppressWarnings ：压制警告的
  	* 一般传递参数"all"    @SuppressWarnings("all")

* 自定义注解

  * 格式：

  	元注解

  	public	@interface 注解名称{}

  * 本质：注解的本质就是一个接口，该接口默认继承Annotation接口

  	```java
  	public interface com.xian.Annotation.MyAnno extends java.lang.annotation.Annotation {
  	    //属性列表；
  	}
  	```

  * 属性：接口中可以定义的成员方法

  	* 要求：
  		1. 属性的返回值类型有下列取值
  			* 基本数据类型
  			* String(字符串)
  			* 枚举
  			* 注解
  			* 以上类型的数组
  		2. 定义了属性，在使用时需要给属性赋值
  			* 如果定义属性时，使用default关键字给属性默认初始化值，则使用注解时，可以不进行属性的赋值。
  			* 如果只有一个属性需要赋值，且属性的名字叫value，则value可以省略，直接定义值即可。
  			* 数组赋值时，值使用{}包裹。如果数组中只由一个值，则{}省略。

  * 元注解：用于描述注解的注解
  	* @Target：描述注解能够作用的位置
  		* ElementType取值：
  			* TYPE：可以作用于类上
  			* METHOD：可以作用于方法上
  			* FIELD：可以作用于成员变量上
  	* @Retention：描述注解被保留的一个阶段
  		* @Retention(RetentionPolicy.RUNTIME) ：当前被描述的注解，会保留到class字节码文件中，并被JVM读取到
  	* @Documented：描述注解是否被抽取到API文档中
  	* @Inherited：描述注解是否被子类继承

* 在程序中使用（解析）注解：获取注解中定义的属性值

  1. 获取注解定义的位置的对象        （Class, Method,Field）

  2. 获取指定的注解

  	* getAnnotation(Class)

  		```java
  		//其实就是在内存中生成了一个该注解接口的子类实现对象
  		/*
  			public class ProImpl implements Pro{
  				public String className(){
  					return "com.xian.Annotation.Demo1";
  				}
  				public String methodName(){
  					return "show";
  				}
  			}
  		*/
  		```

  		

  3. 调用注解中的抽象方法获取配置的属性值

* 小结：
  1. 以后大多数时候，我们会使用注解，而不是自定义注解
  2. 注解给谁用的？
  	1. 编译器
  	2. 解析程序
  3. 注解不是程序的一部分，可以理解为注解就是一个标签

# 数据库连接池

1. 概念：其实就是一个容器（集合），存放数据库连接的容器。

	​		当系统初始化，容器被创建，容器中会申请一些连接对象，当用户来访问数据库时，从容器中获取连接对象，用户访问完之后，会将连接对象归还给容器。

2. 好处

	* 节约资源
	* 用户访问高效

3. 实现：

	1. 标准接口：DataSource 		javax.sql包下的
		* 方法：
			* 获取连接：getConnection()
			* 归还连接：Connection.close() 如果归还对象Connection是从连接池中获取的，那么调用Connection.close()方法，则不会关闭连接了，而是归还连接对象
	2. 一般我们不去实现它，有数据库厂商来实现它
		* C3P0：数据库连接池技术
		* Druid：数据库连接池实现技术，由阿里巴巴提供的（高效）

4. C3P0：数据库连接池技术

	* 步骤：
		1. 导入jar包 	两个 	c3p0-0.9.5.5.jar     和	mchange-commons-java-0.2.19.jar    **不要忘记导入jdbc.jar**
		2. 定义配置文件
			- c3p0.properties 或者 c3p0-config.xml
			- 路径：直接将文件放在src目录下即可。
		3. 创建核心对象   数据库连接池连接对象  ComboPooledDataSource
		4. 获取连接：getConnection()
	
5. Druid：数据库连接池实现技术，由阿里巴巴提供的（高效）

	* 步骤：
		1. 导入jar包 	druid-1.2.6.jar
		2. 定义配置文件：
			* 是properties形式的
			* 可以叫任意名称，可以放在任意目录下
		3. 加载配置文件：properties
		4. 获取数据库连接池对象：通过工厂类来获取  DruidDataSourceFactory
		5. 获取连接：getConnection()
	* 定义一个工具类
		1. 定义一个类 	JDBCUtils
		2. 提供静态代码块加载配置文件，初始化连接池对象
		3. 提供方法
			1. 获取连接方法：通过数据库连接池获取连接
			2. 释放资源
			3. 获取连接池方法

# Spring JDBC

* Spring提供框架对JDBC进行简单的封装，提供了一个JDBCTemplate的开发
* 步骤：
	1. 导入jar包
	2. 创建JDBCTemplate对象。依赖于数据源DataSource
		* JDBCTemplate template = new JDBCTemplate(datasource);
	3. 调用JDBCTemplate的方法来完成CRUD的操作
		* update()：执行DML语句。增、删、改语句
		* queryForMap()：查询结果，将结果集封装为map集合
			* 注意：这个方法查询的结果集长度只能为1，就是只能查询一条数据
		* queryForList()：查询结果，将结果封装为list集合
			* 注意：将每一条记录封装为一个map集合，再将map集合装载到list集合中
		* query：查询结果，将结果封装为JavaBean对象
			* query的参数：RowMapper
				* 一般我们使用BeanPropertyRowMapper实现类。完成数据到JavaBean的自动封装
				* new BeanPropertyRowMapper<类型>(类型.class));
		* queryForObject：查询结果，将结果封装为对象
			* 一般用于聚合函数的查询

# JavaScript

* 概念：一门客户端脚本语言
	* 运行在客户端浏览器中的，每一个浏览器都有JavaScript的解析引擎
	* 脚本语言：不需要编译，直接就可以被浏览器解析执行了
	
* 功能：
	* 可以来增强用户和html页面的交互过程，可以来控制html元素，让页面有一些动态的效果，增强用户的体验
	
* JavaScript发展史：

  

  

  * javaScript = ECMAScript + JavaScript自己特有的东西(BOM+DOM)

* ECMAScript：客户端脚本语言的标准

  1. 基本语法：

  	  1. 与HTML结合方式

  	   2. 内部式：定义<script>，标签体内容就是js代码

  	   3. 外部式：定义<script>，通过src属性引入的外部的js文件

  	  		注意：
  	  	
  	  		* <script>可以定义在html页面的任意位置，但是定义的位置会影响执行顺序
  	  	
  	  		* <script>可以定义多个。

  	  1. 注解
  	   2. 单行注解：//注解内容
  	   3. 多行注解：/*  注释内容 */
  	  4. 数据类型
  	   5. 原始数据类型(基本数据类型)：
  		  		1. number：数字。整数/小数/NaN（not a number 一个不是数字类型的数字类型）
  		    		2. string：字符串。字符串      “abc” “a” 'abc' (无字符串数据类型)
  		      		3. boolean：true和false
  		        		4. null：一个对象为空的占位符
  		          		5. undefiend：未定义。如果一个变量没有给初始值，则会被默认赋值为undefined
  	   6. 引用数据类型：对象
  	  7. 变量
  		     1. 变量：一小块存储数据的内存空间
  	   8. Java是强类型语言，而Javascript是弱类型语言
  		  		1. 强类型：在开辟变量存储空间时，定义了空间将来存储的数据的数据类型。只能存储指定的数据类型的数据
  		    		2. 弱类型：在开辟变量存储空间时，不定义空间将来存储数据类型，可以存放任意类型的数据。
  	   9. 语法：
  		1. var 变量名 = 初始值;
  	  10. 运算符
  		  1. 一元运算符
  		  2. 算数运算符
  		  3. 赋值运算符 
  		  4. 比较运算符
  		  5. 逻辑运算符
  		  6. 三元运算符
  	  11. 流程控制语句
  	  12. JS特殊语法

  2. 基本对象

* BOM

* DOM：

	* 事件

# BootStrap

一个前端开发的框架

* 框架：一个半成品软件

## 响应式布局

* 同一套页面可以兼容不同的分辨率的设备。

* 实现：依赖于栅格系统，将一行平均分为12个格子，可以指定元素占几个格子

* 步骤：

	1. 定义容器。相当于之前的table
		- 容器的分类：
			- container ：
			- container-fluid ：
	2. 定义行。相当于之前的tr     样式：row
	3. 定义元素。指定该元素在不同的设备上，所占的格子数目。   样式：col-设备代号-格子数目
		* 设备代号：
			* xs：超小屏幕   手机   (<768px) ：col-xs-12
			* sm：小屏幕   平板   (>=768px) ：col-sm-12
			* md：中等屏幕   桌面显示器  (>=992px)：col-md-12
			* lg：大屏幕   大桌面显示器   (>=1200px)

	* 注意：
		1. 一行中如果格子数目超过12，则超出部分自动的换行
		2. 栅格类属性可以向上兼容，栅格类适用于与屏幕宽度大于或等于分界点大小的设备
		3. 如果真实设备的宽度小于了设备的代号的最小值，会默认一个元素占满一整行。向下不兼容

## CSS样式和JS插件

全局CSS样式

* 按钮
* 图片
* 表格
* 表单

组件

* 导航条
* 分页条

插件

* 轮播图

## XML

概念：Extensible Markup Language 可扩展标记语言

* 可扩展：标签都是自定义的。<user>  <student>
* 功能
	* 存储数据
		1. 配置文件
		2. 在网络中传输
* xml与html的区别
	* xml标签都是自定义的，html标签是预定义的
	* xml的语法严格。html的语法松散
	* xml是存储数据的，html是展示数据的

* w3c：万维网联盟

语法：

* 基本语法：
	* xml文档的后缀名是.xml
	* xml第一行必须定义文档声明
	* xml文件有且仅有一个根标签
	* 属性值必须使用引号(单双都可)引起来
	* 标签必须正确关闭
	* xml区分大小写
	
* 快速入门：

* 组成部分：
  1. 文档声明
  	1. 格式：<?xml 属性列表 ?>
  	2. 属性列表：
  		* version：版本号，必须的属性
  		* encoding：编码格式。告知解析当前文档使用的字符集，默认值：ISO-8859-1
  		* standalone：是否独立
  			* 取值：
  				* yes：不依赖其他文件
  				* no：依赖其他文件
  	
  2. 指令(了解)：结合CSS的

  	- ```xml
  		<?xml-stylesheet type="text/css" href="css/a.css" ?>
  		```

  3. 标签：标签名称自定义的

  	* 规则：
  		1. 名称可以含字母、数字以及其他的字符
  		2. 名称不能以数字或者标点符号开始
  		3. 名称不能以字符 “xml”（或者 XML、Xml）开始
  		4. 名称不能包含空格

  4. 属性：

  	- id属性值唯一

  5. 文本：

  	- 小于号---> & lt;
  	- 大于号---> & gt;
  	- CDATA区域：原样展示数据
  		- <![CDATA[]]>

* 约束：规定xml文档的书写规则

	* 作为框架的使用者（程序员）：
		* 能够在xml文件中引入约束文档
		* 能够简单的读懂约束文档
	* 分类：
		* DTD：一种简单的约束技术
		* Schema：一种复杂的约束技术
	* DTD：
		* 引入DTD文档到xml文档中
			* 内部dtd：将约束规则定义在xml文档中
			* 外部dtd：将约束的规则定义在外部的dtd文件中
				* 本地：<!DOCTYPE 根标签  SYSTEM "dtd文件的位置">
				* 网络：<!DOCTYPE 根标签 PUBLIC "dtd文件名" "dtd文件的位置URL">
	* Schema：
		* 引入：
			1. 填写xml文档的根元素
			2. 引入xsi前缀。      xmlns ：xsi="http://www.w3.org/2001/XMLSchema-instance"
			3. 引入xsd文件命名空间。     xsi:schemaLocation="http://www/itcast.cn/xml     student.xsd"
			4. 为每一个xsd约束声明一个前缀，作为标识     xmlns="http://www.itcast.cn/xml"

解析：操作xml文档，将文档中的数据读取到内存中

* 操作xml文档
	1. 解析(读取)：将文档中的数据读取到内存中
	2. 写入：将内存中的数据保存到xml文档中。持久化保存数据
* 解析xml的方式：
	* DOM：将标记语言文档一次性加载进内存生成一颗DOM树
		1. 优点：操作方便，可以对文档进行CRUD的所有操作
		2. 缺点：比较的占内存，不适用与内存较小的设备
	* SAX：逐行读取，基于事件驱动的
		1. 优点：不占内存
		2. 缺点：只能读取，不能CRUD
* xml常见的解析器：
	* JAXP：sun公司提供的解析器，支持dom和sax两种思想
	* DOM4J：一款非常优秀的解析器
	* Jsoup：jsoup 是一款Java的HTML解析器，可以直接解析某个URL地址、HTML文本内容。她提供了一套非常省力的API，可通过DOM，CSS以及类似于jQuery的操作方法来取出数据和操作数据。
	* PULL：Android操作系统内置的解析器，sax方式的。
* Jsoup：jsoup 是一款Java的HTML解析器，可以直接解析某个URL地址、HTML文本内容。她提供了一套非常省力的API，可通过DOM，CSS以及类似于jQuery的操作方法来取出数据和操作数据。
  * 快速入门
  	* 步骤
  		1. 导入jar包
  		2. 获取Document对象
  		3. 获取对应的标签Element对象
  		4. 获取数据
  * 对象的使用：
    * Jsoup：工具类，可以解析html或xml文档，返回Document
    	* parse：解析html或xml文档，返回Document
    		* parse(File in, String charsetName) : 解析xml或html文件的。
    		* parse(String html) ：解析xml或html字符串的。
    		* parse(URL url,int timeoutMillis) ：通过网络路径获取指定html或xml的文档
    * Document：文档对象。代表内存中的dom树
    	* 获取Element对象
    		* getElementById(String id) ：根据id属性值获取唯一的元素对象
    		* getElementsByTag(String tagName) ：根据标签名称获取元素对象集合
    		* getElementsByAttribute(String key) ：根据属性名获取元素对象集合
    		* getElementsByAttributeValue(String key, String value)  ：根据对应的属性名和属性值获取元素对象集合
    * Elements：元素Element对象的集合。可以当做ArrayList<Element>来使用
    * Element：元素对象
    	1. 获取子元素对象
    		* getElementById(String id) ：根据id属性值获取唯一的元素对象
    		* getElementsByTag(String tagName) ：根据标签名称获取元素对象集合
    		* getElementsByAttribute(String key) ：根据属性名获取元素对象集合
    		* getElementsByAttributeValue(String key, String value)  ：根据对应的属性名和属性值获取元素对象集合
    	2. 获取属性值
    		- attr(String key) ：根据属性名称获取属性值
    	3. 获取文本内容
    		- String Text() ：获取文本内容
    		- String html() ：获取文本内容及字类标签及内容
    * Node：节点对象
  * 快捷查询方式：
  	* selector：选择器
  		* 使用的方法：select(String cssQuery) ：
  			* 语法：参考Sekector类中定义的语法
  	* XPath：**XPath 是一门在 XML 文档中查找信息的语言。XPath 用于在 XML 文档中通过元素和属性进行导航。**
  		* 使用Jsoup的XPath需要额外导入一个jar包
  		* 查询w3cschool参考手册，使用XPath的语法完成查询

# Servlet

概念：运行在服务器端的小程序

* servlet就是一个接口，定义了Java类被浏览前访问到(tomcat识别)的规则。
* 将来我们自顶一个类，实现Servlet接口，复写方法。

快速入门：

* 创建JavaEE项目
* 定义一个类，实现Servlet接口
* 实现接口中的抽象方法
* 配置Servlet

执行原理：

1. 当服务器接受到客户端浏览器的请求后，会解析请求的url路径，获取访问的Servlet的资源访问路径
2. 查找web.xml文件，是否有对应的<url-pattern>标签内容。
3. 如果有，则再找到对应的<servlet-class>全类名
4. tomcat会将字节码文件加载进内存，并创建其对象
5. 调用其方法

Servlet中的生命周期：

1. 被创建：执行init方法，只执行一次

	- Servlet什么时候被创建？

		- 默认情况下，第一次被访问时，Servlet被创建

		- 可以配置执行Servlet的创建时机

			在<servlet>标签下配置

			```xml
			<!--指定Servlet创建时机-->
			        <!--
			            1.被访问时，创建
			                * <load-on-startup>的值为负数，默认值-1
			            2.在服务器启动时，创建
			                * <load-on-startup>的值为正数，0或正整数
			        -->
			        <load-on-startup>5</load-on-startup>
			```

	- Servlet的init方法，只执行一次，说明Servlet在内存中存在一个对象，Servlet是单例的

		- 多个用户同时访问时，可能存在线程安全问题
		- 解决： 尽量不要在Servlet中定义成员变量，定义局部变量。即使定义了成员变量也不要赋值

2. 提供服务：执行service方法，执行多次

	- 每次访问Servlet时，Service方法才会被执调用，只被调用一次

3. 被销毁：执行destroy方法，只执行一次

	- Servlet被销毁时执行。服务器关闭时，Servlet被销毁
	- 只有服务器正常关闭时，才会被destroy执行
	- destroy方法在Servlet被销毁之前执行，一般用于释放资源

Servlet3.0 ：

- 好处：

	* 支持注解配置。可以不需要web.xml了。

- 步骤：

	1. 创建JavaEE项目，选择Servlet的版本3.0以上，可以不创建web.xml

	2. 定义一个类，实现Servlet接口

	3. 复写方法

	4. 在类上使用@WebServlet注解，进行配置

		```java
		@WebServlet("资源路径")
		```

IDEA与Tomcat的相关配置

1. ​	IDEA会为每一个tomcat部署的项目单独建立一份配置
	- 查看控制台的log ：Using CATALINA_BASE:   "C:\Users\Xian\AppData\Local\JetBrains\IntelliJIdea2021.1\tomcat\232dd8f3-ce10-41cc-b118-9f2ea5624357"
2. 工作空间项目   和   tomcat部署的web项目
	- tomcat真正访问的是“tomcat部署的web项目”，“tomcat部署的web项目”对应着“工作空间项目”的web目录下的所有资源
	- WEB-INF目录下的资源不可以被浏览器直接访问
	- tomcat断点调试：使用debug启动

Servlet体系结构

​	Servlet --- 接口

​				|

​	GenericServlet --- 抽象类

​				|

​	HttpServlet --- 抽象类

* GenericServlet ：将Servlet接口中其他的方法做了默认的空实现，只将Service方法作为抽象方法
	* 将来定义Servlet类时，可以继承GenericServlet，实现Service方法即可
* HttpServlet ：对Http协议的一种封装，简化我们的操作
	* 定义Servlet类，继承HttpServlet，实现doPost() / doGet()方法

Servlet相关配置

* url-pattern ：访问路径
	* 一个Servlet可以配置多个访问路径
	* 路径的定义规则：
		* /xxx
		* /xxx/xxx
		* *.do

# Http概述

概念：Hyper Text Transfer Protocol 超文本传输协议

* 传输协议 ：定义了，客户端和服务器端通信时，发送数据的格式
* http特点 ：
	* 基于TCP/IP的高级协议(安全)
	* 默认端口号 ：80
	* 基于请求/响应模型的 ：一次请求对应一次响应
	* 无状态的：每次请求之间是相互独立，不能互交数据
* 历史版本 ：
	* 1.0 ：每一次请求响应都会建立新的连接
	* 1.1 ：会复用连接

请求消息数据格式

* 请求行

	请求方式  请求url  请求的协议/版本

	GET /login.html HTTP/1.1

	* 请求方式：
		* HTTP协议中有7种请求方式，常用的有两种
			* GET ：
				1. 请求参数在请求行中，在url后。
				2. 请求的url长度有限制的
				3. 不太安全
			* POST ：
				1. 请求参数在请求体中
				2. 请求的url长度没有限制的
				3. 相对安全

* 请求头

	请求头名称 ：请求头值

	* 常见的请求头 ：user-agent ：浏览器告诉服务器，我访问你使用的浏览器版本信息

		/**可以在服务器端获取该头的信息，解决浏览器的兼容性问题**/

	* Referer : http://localhost/login.html

		/**告诉服务器，我(当前请求)从哪里来？**/

		* 作用：
			* 防盗链 ：
			* 统计工作 ：

* 请求空行

	空行

* 请求体

	

	username=zhangsan

* 字符串格式 ：

	GET /login.html HTTP/1.1

	Host ：localhost

	| Accept | text/html,application/xhtml+xml,application/xml;q=0.9,image/webp,*/*;q=0.8 |
	| ------ | ------------------------------------------------------------ |

	| Accept-Encoding           | gzip, deflate                                                |
	| ------------------------- | ------------------------------------------------------------ |
	| Accept-Language           | zh-CN,zh;q=0.8,zh-TW;q=0.7,zh-HK;q=0.5,en-US;q=0.3,en;q=0.2  |
	| Cache-Control             | max-age=0                                                    |
	| Connection                | keep-alive                                                   |
	| Host                      | localhost:8080                                               |
	| Sec-Fetch-Dest            | document                                                     |
	| Sec-Fetch-Mode            | navigate                                                     |
	| Sec-Fetch-Site            | none                                                         |
	| Sec-Fetch-User            | ?1                                                           |
	| Upgrade-Insecure-Requests | 1                                                            |
	| User-Agent                | Mozilla/5.0 (Windows NT 10.0; Win64; x64; rv:91.0) Gecko/20100101 Firefox/91.0 |

响应消息数据结构





# Request

1. request对象和response对象的原理

	1. request和response对象是由服务器创建的。我们来使用
	2. request对象是来获取请求消息，response对象是来设置响应消息的

2. request对象继承体系结构 ：

	ServletRequest              ---- 接口

	​			 |       继承

	HttpServletRequest      ---- 接口

	​			 |       实现

	org.apache.catalina.connector.RequestFacade  类(tomcat)

3. request功能 ：

  1. 获取请求消息：
        1. 获取请求行数据
        
            	    1. GET /day14/demo1?name=zhangsan HTTP/1.1
                    	  2. 方法：
                                	  1. 获取请求方式 ：GET
                                  
                                         	  	- String getMethod()
                          2. （*）获取虚拟目录 ：/day14
                                - String getContextPath()
                          3. 获取Servlet目录 ：/demo1
                                - String getServletPath()
                          4. 获取get方式的请求参数 ：name=zhangsan
                                - String getQueryString()
                          5. （*）获取请求的URI ：/day14/demo1
                                - String getRequestURI() ：/day14/demo1
                                - String getRequestURL() ：http://localhost:8080//day14/demo1
                         6. URL和URI的区别
                                - URL：统一资源定位符
                               - URI ：统一资源标识符
                          7. 获取协议及版本 ：HTTP/1.1
                                - String getProtocol()
                          5. 获取客户机的IP地址 ：
                                   - String getRemoteAddr()
          
      2. 获取请求头数据
      
        - 方法：
      	  	- (重点)String getHeader(String name) ：通过请求头的名称获取请求头的值
      	  	- Enumeration<String> (当做迭代器)getHeaderNames() ：
      
      3. 获取请求体数据
      
      	  1. 请求体：只有post请求方式，才有请求体，在请求体中封装了post请求的请求参数
      
      	  2. 步骤：
      
      	      1. 获取流对象
      
      	        2. BufferedReader getReader() ：获取字符输入流，只能操作字符数据
      	        3. ServletInputStream getInputStream() ：获取字节输入流，可以操作所有类型的数据
      
      	        ​              **文件上传知识点之后讲解**
      
        - 再从流对象中拿数据
	
	- 其他功能
	
	  1. 获取请求参数通用方式 ：不论get还是post请求方式都可以使用下列方法来获取请求参数
	  	 1. String getParameter(String name) ：根据参数名称获取参数值            username=zs&password=123
	  	
	  	2. String getParameterValues(String name) ：根据参数名称获取参数值的数组（复选框）           hobby=xx&&
	  	
	  	3. Enumeration<String> getParameterNames() ：获取所有请求的参数名称
	  	
	  	4. Map<String,String[]> getParameterMap() ：获取所有键值对的集合
	  	
	  	5. 中文乱码问题：
	  	
	  		- get方式 ：tomcat 8 已经将get方式乱码问题解决了
	  	
	  		- post方式 ：会乱码
	  	
	  			- 解决：在获取参数前，设置request的编码
	  	
	  				req.setCharacterEncoding("utf-8");
	  	
	   2. 请求转发 ：一种在服务器内部的资源跳转方式
	
	            1. 步骤：
	               	1. 通过request对象获取请求转发器对象：RequestDispatcher getRequestDispatcher(String path)
	                     	2. 使用RequestDispatcher对象来进行转发 ：forward(ServletRequest request, ServletResponse reponse)
	            2. 特点 ：
	                   地址栏不发生转变
	                     	2. 只能访问当前服务器内部的资源中
	                              	3. 转发是一次请求
	
	   3. 共享数据 ：
	
	      - 域对象 ：一个有作用范围的对象，可以在范围内共享
	      - request域 ：代表一次请求的范围，一般用于请求转发的多个资源中共享数据 ( 就在转发中使用 ) 
	      - 方法：
	         1. setAttribute(String name,Object obj) ：存储数据
	         2. getAttribute(String name) ：通过键获取数据
	         3. removeAttribute(String name) ：通过键删除键值对
	
	   4. 获取ServletContext
	
	          - ServletContext servletContext

# 案例：用户登录

* 用户登录案例需求 ：

	1. 编写login.html登录页面

		username & password 两个输入框

	2. 使用druid数据库连接池技术，操作mysql，数据库中的user表

	3. 使用JDBCTemplate技术封装JDBC

	4. 登陆成功跳转到SuccessServlet展示 ：登录成功！用户名，欢迎您

	5. 登录失败跳转到FailServlet展示 ：登录失败，用户名或者密码错误


​	

# Http协议 ：

请求消息 ：客户端发送给服务器端的数据

* 数据格式：
	1. 请求行
	2. 请求头
	3. 请求空行
	4. 请求体

响应消息 ：服务器端发送给客户端的数据

* 数据格式：

  1. 响应行
  	1. 组成 ：协议/版本  响应状态码  状态码描述
  	2. 响应状态码 ：服务器告诉客户端浏览器本次请求和响应的一个状态
  		1. 状态码都是3位数
  		2. 分类：
  			1. 1xx ：服务器接收客户端消息，但没有接收完成，等待一段时间后，发送1xx代码
  			2. 2xx ：成功。代表 ：200
  			3. 3xx ：重定向。代表 ：302（重定向），（304）访问缓存
  			4. 4xx ：客户端错误。代表：404（请求路径没有对应缓存）   405（请求方式没有相应的doxxx方法）
  			5. 5xx ：服务器端错误。代表 ：500（服务器内部出现异常）
  2. 响应头
  	1. 格式 ：头名称 ：值
  	2. 常见的乡响应头 ：
  		1. Context-Type ：服务器告诉客户端浏览器本次次响应体数据格式以及编码格式
  		2. Content-disposition ：服务器告诉客户端以什么格式打开响应体数据
  			* 值 ：
  				* in-line ：默认值，在当前页面内打开
  				* attachment;filename=xxx ：以附件形式打开响应体。文件下载
  		3. Content-Length ：字节的个数
  		4. Date ：日期
  3. 响应空行
  4. 响应体 ：传输的数据

* 响应字符串格式

	HTTP/1.1 200  OK
	Content-Length: 0
	Date: Sun, 19 Sep 2021 14:22:41 GMT
	Keep-Alive: timeout=20
	Connection: keep-alive

# Response对象

功能 ：设置响应消息

1. 设置响应行
	1. 格式 ：Http/1.1 200 OK
	2. 设置状态码 ：setStatus(int sc)
2. 设置响应头 ：setHeader(String name,String value)
3. 设置响应体 ：
	- 使用步骤：
		1. 获取输出流
			- 字符输出流 ：PrintWriter getWirter()
			- 字节输出流 ：ServletOutputStream getOutputStream()
		2. 使用输出流，将数据输出到客户端浏览器

案例 ：

1. 完成重定向的操作

  - 资源跳转的一种方式

  - 代码实现：

```java
resp.setStatus(302);
resp.setHeader("location","/resp2");
//简单的重定向方式
resp.sendRedirect("/resp2");
```

  - 重定向的特点：

1. 地址栏发生变换
2. 重定向可以访问非本服务器的资源
3. 重定向是两次请求。不能使用request域对象请求数据

  - 转发的特点：

1. 地址栏路径不变
2. 转发只能访问当前服务器下的资源
3. 转发是一次请求，可以使用request对象来共享数据

  - 路径写法：

1. 路径的分类

	1. 相对路径：通过相对路径不可以确定唯一资源

	2. 绝对路径：通过绝对路径可以确定唯一资源

		- 规则：判断定义的路径是给谁用的？判断请求将来从哪发出

			- 给浏览器是使用：需要加虚拟目录（项目的访问路径）（重定向）

				- 动态的获取虚拟目录

					- String contextPath = request.getContextPath();

						response.sendRedirect(contextPath+"/responseDemo2")

				- 建议虚拟目录动态获取：request.getContextPath();

			- 给服务器使用：不需要加虚拟目录（转发路径）

2. 服务器输出字符数据到浏览器

	* 步骤

		1. 获取字符输出流
		2. 输出数据

	* 注意：

		- 乱码的问题：

			- 原因：编解码的不一致

				1. PrintWriter out = resp.getWriter();获取的流的默认编码是ISO-8859-1

				2. 设置该流的默认编码

				3. 告诉浏览器响应体使用的编码

					```java
					//统一编解码字符集
					//resp.setCharacterEncoding("GBK");(使用下面的方法可以不写这个)
					//告诉浏览器，服务器发送的消息体数据的编码。建议浏览器使用给编码解码
					//resp.setHeader("content-type","text/html;charset=utf-8");
					//简单的形式，设置编码，是在获取流之前设置
					resp.setContentType("text/html;charset=utf-8");
					```

3. 服务器输出字节数据到浏览器

	- 步骤
		1. 获取字节输出流
		2. 输出数据

4. 验证码

	1. 本质：图片
	2. 目的：防止恶意

# ServletContext对象

概念：代表整个web应用，可以和程序的容器（服务器）来通信

获取：

1. 通过request对象获取

	request.getServletContext();

2. 通过HttpServlet获取

	this.getServletContext();

功能：

1. 获取MIME类型：

	- MIME类型：在互联网通信过程中定义的一种文件数据类型
		- 格式：大类型/小类型        text/html         image/jpeg
	- 获取：String getMimeType(String file)

2. 域对象：共享数据的

  1. setAttribute(String name,Object value)
  2. getAttribute(String name)
  3. removeAttribute(String name)

  * ServletContext对象范围：共享所有请求的数据

3. 获取文件的真实（服务器路径）路径

	1. 方法：String getRealPath(String path);（ClassLoader）（默认是web目录，src的用类加载器）

# 案例：文件下载

* 要求：
	1. 页面显示超链接
	2. 点击超链接后弹出下载提示框
	3. 完成图片文件的下载
* 分析：
	1. 超链接指向的资源能被浏览器解析，则在浏览器中展示，如果不能解析，则弹出下载提示框。不满足需求
	2. 任何资源都必须弹出下载提示框
	3. 使用响应头设置资源的打开方式：
		- content-disposition:attachment;filename=xxx
* 步骤：
	1. 定义页面，编辑超链接href属性，指向Servlet，传递资源的名称
	2. 定义Servlet
		1. 获取文件的名称
		2. 使用字节输入流加载文件进内存
		3. 指定response的响应头：content-disposition:attachment;filename=xxx
		4. 将数据写出到response输出流

* 问题：
	* 中文文件问题
		* 解决的思路：
			* 获取客户端使用的浏览器版本信息
			* 根据不同的版本信息，设置filename的编码方式

# 会话技术

会话 ：一次会话中包含多次请求和响应

* 一次会话 ：浏览器第一次给服务器资源发送请求，会话建立，直到有一方断开为止

功能 ：在一次会话的范围的多次请求间，共享数据

方式 ：

1. 客户端会话技术：cookie
2. 服务器端会话技术：session

# Cookie

概念 ：客户端会话技术，将数据保存到客户端的一种技术

快速入门：

* 使用步骤：
	1. 创建Cookie对象，绑定数据
		- new Cookie(String name,String value)
	2. 发送Cookie对象
		- HttpServletResponse.addCookie(Cookie cookie)
	3. 获取Cookie对象，拿到数据
		- Cookie[] HttpServletRequest.getCookies()
* 实现原理：
	* 基于响应头set-cookie和请求头cookie实现
* cookie的细节
	1. 一次可不可以发送多个cookie？
	
		- 可以创建多个cookie对象，使用response调用多次addCookie方法发送cookie
	
	2. cookie在浏览器中保存多长时间？
	
		- 默认情况下，当浏览器关闭后，cookie数据被销毁
		- 设置cookie的生命周期，持久化存储
			- setMaxAge(int seconds)
				1. 正数：将cookie数据写到硬盘的文件中。持久化存储。cookie存活时间。
				2. 负数：默认值，存储在内存
				3. 零：删除cookie信息
	
	3. cookie能不能存储中文？
	
		- 在tomcat 8 之前 cookie中不能直接存储中文数据。
			- 需要将中文数据转码----一般采用URL编码   eg:(%E3)
		- 在tomcat 8 之后，cookie支持中文数据。特殊字符还是不支持，建议使用URL编码存储，URL解码解析
	
	4. cookie共享问题？ 
	
		1. 假设在一个tomcat服务器中，部署了多个web项目，那么在这些web项目中，cookie能不能共享
	
		  - 默认情况下cookie不能共享
	
		  
	
		  * setPath(String path)：设置cookie的获取范围。默认情况下，设置当前的虚拟目录
		  	* 如果要共享，则可以将path设置为"/"
		
		2. 不同的tomcat服务器间cookie共享问题？
		
			- setDomain(String path)：如果设置一级域名相同，那么多个服务器之间cookie可以共享
				- setDomain(".baidu.com")，那么tieba.baidu.com和news.baidu.com中可以共享
		
	5. Cookie的特点和作用
	
		1. cookie存储数据在客户端浏览器
	
		2. 浏览器对于单个cookie的大小有限制（4kb）以及 对同一个域名下的总cookie数量也有限制（20个以内）（持久化存储）
	
			
	
		* 作用：
			1. cookie一般用于存储少量不太敏感的数据
			2. 在不登录的情况下，完成服务器对客户端的身份识别

# 案例：记住上一次访问时间

需求：

1. 访问一个servlet，如果是第一次访问，则提示：你好，欢迎您首次访问。
2. 如果不是第一次访问，则提示：欢迎回来，您上次访问的时间为：显示时间字符串

分析：

1. 可以采用Cookie来完成
2. 在服务器中的Servlet是否有一个名为lastTime的cookie
	1. 有：不是第一次访问
		1. 欢迎回来，您上次访问的时间为：（显示时间字符串）
		2. 写回Cookie：lastTime=2021-9-24-20:35
	2. 没有：是第一次访问
		1. 响应数据：您好，欢迎您首次访问
		2. 写回Cookie：lastTime=2021-9-24-20:35









# JSP

1.指令

* 作用：用于配置jsp页面，导入资源文件

* 格式：

	 		<%@ 指令名称 属性名1=属性值2 属性名2=属性值2 %>

* 分类：

	1. page     ：配置jsp页面的

		- contentType ：等同于response.setContentType()
			1. 设置响应体的mime类型以及字符集
			2. 设置当前jsp页面的编码（只能是高级的IDE才能生效，如果使用低级的IDE开发工具需要设置pageEncoding属性设置当前页面的字符集）
		- import ：导包的
		- errorPage ：当前页面发生异常后，会自动的跳转到指定的错误页面
		- isErrorPage ：标识当前的页面是否是错误页面
			- true ：是，可以使用一个内置对象exception
			- false ：否，默认为false，不可以使用内置对象exception

	2. include ：页面包含的。导入页面的资源文件

		```jsp
		<%@ include file="top.jsp" %>
		```

	3. taglib    ：导入资源文件

		```jsp
		<%@taglib prefix="" uri="http://java.sun.com/jsp/jstl/core" %>
		```

		prefix ：前缀，自定义的

2.注解：

1. html注释
	- <!-- --> ：只能注释html代码片段，数据是会被发送到页面上的
2. jsp注释 ：推荐使用
	- <%-- --%> ：可以注释所有，数据不会被发送到页面上的

3.内置对象

* 在jsp页面中不需要创建，直接使用的对象

* 内置对象一共有九个：

	​						变量名                                         真实类型                                     作用

	1. request（域对象）                     HttpServletRequest                一次请求访问的多个资源（转发）
	2. response（响应对象）              HttpServletResponse              响应对象
	3. session（域对象）                     HttpSession                              一次会话的多个请求
	4. application（域对象）              ServletContext                          所有用户间共享数据
	5. pageContext（域对象）           PageContext                             当前页面共享数据，还可以获取其他八个内置对象
	6. out                                               JspWriter                                    输出对象，数据输出到页面上
	7. page                                            object                                         当前页面（Servlet）的对象     this
	8. config                                          ServletConfig                            servlet的配置对象
	9. exception                                   Throwable                                 异常对象



# MVC开发模式

1. jsp演变历史

	1. 早期只有Servlet，只能使用response输出标签，非常麻烦
	2. 后来有了jsp，简化了servlet的开发，如果过度的使用jsp，在jsp中即写大量的java代码，又写html标签，难于维护，难于分工协作
	3. 再后来，javaweb开发，借鉴MVC开发模式，使得程序的设计更加合理性

2. MVC ：

	1. M ：Model，模型（Javabean）

		1. 业务逻辑操作

		* 完成具体的业务操作，如：查询数据库，封装对象

	2. V ：View，视图（JSP）

		1. 展示数据

	3. C ：Controller，控制器（Servlet）

		1. 获取客户端的输入
		2. 调用模型
		3. 将数据交给视图展示

	* MVC的优缺点：
		* 优点：
			1. 耦合性低，方便维护，可以利于分工协作
			2. 重用性高
			3. 声明周期成本低
			4. 部署快
			5. 可维护性高
		* 缺点：
			1. 没有明确的定义
			2. 不适合小型的应用程序
			3. 使得项目架构变得复杂 

# EL表达式

概念：Expression Language 表达式语言

作用：替换和简化jsp页面中java代码的书写

语法：${表达式}

注意：

* jsp默认是支持el表达式的,

	需要不被解析成el表达式需要添加<%@ page isELIgnored="true" %>

* 需要单个el表达式不被解析可以在$前加\转义

使用：

1. 运算

	- 运算符：
		- 算数运算符：+ - * /(div) %(mod)
		- 比较运算符：> < >= <= == !=
		- 逻辑运算符：&&(and) ||(or) !(not)
		- 空运算符：empty
			- 功能：用于判断字符串、集合、数组对象是否为null 或者 长度是否为0
			- ${empty list}：表示判断字符串集合数组对象是否为null 或者长度=0
			- ${not empty list}：表示判断字符串集合数组对象是否不为null 并且长度>0

2. 获取值

	1. el表达式只能从域对象中获取值

	2. 语法：

		1. ${域名城.键名称} ：从指定的域中获取指定键的数值

			- 域名城：

				1. pageScope  --->  pageContext
				2. requestScope  --->  request
				3. sessionScope  --->   session
				4. applicationScope ---> application(ServletContext)

			- 举例：在request域中存储了name=张三我们该如何取出值？

				${requestScope.name}

		2. ${键名}：表示一次从最小的域中查找是否有对应的值，知道找到为止。

			${name}

		3. 获取对象、List集合、Map集合的值

			1. 对象：${域名城.键名称.属性名}
				- 本质上回去调用对象的getter方法
			2. List集合：${域名城.键名称[索引]}
			3. Map集合：
				- ${域名城.键名称.key名称}
				- ${域名城.键名称["key名称"]}

	3. 隐式对象：

		* el表达式中有11个隐式对象
		* pageContext：
			1. 获取jsp其他八个其他内置对象
				- ${pageContext.request.contextPath}：动态获取虚拟目录

# JSTL标签

概念：Java Server Pages Tag Library JSP标准标签库

* 是由Apache组织提供的开源的免费的jsp标签

作用 ：用于简化和替换jsp页面上的java代码

使用步骤：

1. 导入jstl相关jar包
2. 引入标签库：taglib指令    <%@ taglib uri=""%>
3. 使用标签库

常用的JSTL标签

1. if ：相当于java代码的if语句

	c:if
	  1.属性：
	      * test必须属性，test属性里面接收boolean表达式
	            * 如果表达式为true则显示if标签体内容，如果为false则不显示标签体内容
	            * 一般情况下，test属性值会结合el表达式一起使用
	                  * c:if标签没有else情况，想要else可以在定义一个c:if标签

2. choose       ：相当于java代码的switch语句

3. foreach      ：相当于java代码的for语句

# 三层架构:软件设计架构

界面层（表示层）：用户看的到的界面。用户可以通过界面上的组件和服务器进行交互

业务逻辑层：处理业务逻辑的

数据访问层：(dao Data Access Object)

# Filter ：过滤器

* 生活中的过滤器：净化器，净水器，土匪

* web中的过滤器：当访问服务器的资源时，过滤器可以将请求拦截下来，完成一些特殊的功能。

* 功能：

	* 一般用于完成通用的操作。如：登录验证、统一编码处理、敏感字符过滤...

* 快速入门：

  * 步骤：

  	1. 定义一个类，实现接口Filter
  	2. 复写方法
  	3. 配置拦截路径
  		1. web.xml
  		2. 注解配置

  * 过滤器细节：

    1. web.xml配置

    	```xml
    	  <filter>
    	    <filter-name>demo1</filter-name>
    	    <filter-class>com.xian.filter.filterDemo01</filter-class>
    	  </filter>
    	  <filter-mapping>
    	    <filter-name>demo1</filter-name>
    	      <!--拦截路径-->
    	    <url-pattern>/*</url-pattern>
    	  </filter-mapping>
    	```

    2. 过滤器执行流程

    3. 过滤器生命周期方法

    	1. init ：在服务器启动后，会创建Filter对象，然后调用init方法。只执行一次。用于加载资源
    	2. doFilter ：每一次请求被拦截资源时，会执行。执行多次
    	3. destroy ：在服务器关闭后，Filter对象被销毁。如果服务器是正常关闭，则会执行destroy方法，只执行一次。用于释放资源

    4. 过滤器配置详解

    	- 拦截路径配置：

    		1. 具体资源路径 ：/index.jsp   只有访问index.jsp资源时，过滤器才会被执行
    		2. 目录拦截 ：/user/*   访问/user下的所有资源时，过滤器都会被执行
    		3. 后缀名拦截：*.jsp     访问所有后缀名为jsp资源时，过滤器都会被执行
    		4. 拦截所有资源：/*    访问所有资源时拦截

    	- 拦截方式配置：资源被访问的方式

    		- 注解配置：

    			​		设置dispatcherTypes属性，是数组可以配多个值，用大括号括起来

    			​					REQUEST：浏览器直接请求资源

    			​					FORWARD：转发来访问资源

    			​					INCLUDE：包含访问资源

    			​					ERROR：错误跳转页面

    			​					ASYNC：异步访问资源

    		- web.xml配置

    			- 设置<dispatcher></dispatcher>标签即可

    5. 过滤器链（配置多个过滤器）

## 案例：敏感词汇的过滤

* 需求 ：
	* 访问day17案例的资源，验证其是否登录
	* 敏感词汇参考《敏感词汇.txt》
	* 如果是敏感词汇，替换为 ***
* 分析 ：
	1. 对request对象进行增强
	2. 

* 增强对象的功能 ：

	* 设计模式 ：一些通用的解决问题的方式

	1. 装饰模式
	2. 代理模式
		- 概念：
			1. 真实对象：被代理的对象
			2. 代理对象：
			3. 代理模式：代理对象代理真实对象，达到增强真实对象功能的谜目的
		- 实现方式
			1. 静态代理：有一个类文件描述代理模式
			2. 动态代理：在内存中形成代理类
				- 实现步骤：
					1. 代理对象和真实对象实现相同的接口
					2. 代理对象 = Proxy.newProxyInstance();
					3. 使用代理对象调用方法。
					4. 控制增强方法
				- 增强方式：
					1. 增强参数列表
					2. 增强返回值类型
					3. 增强方法体执行逻辑

# Listener ：监听器

* 概念：web的三大组件之一。
	* 事件的监听机制
		* 事件 ：				一件事情
		* 事件源 ：            事件发生的地方
		* 监听器 ：            一个对象
		* 注册监听 ：        将事件、事件源、监听器绑定在一起。当事件源上发生某个事情后，执行监听器代码

* ServletContextListener ：监听ServletContext对象的创建和销毁

	* 方法：

		* contextDestroyed([ServletContextEvent sce) ：ServletContext对象被销毁之前会调用该方法
		* contextInitialized(ServletContextEvent sce) ：ServletContext对象创建后会调用该方法

	* 步骤 ：

		1. 定义一个类，来实现ServletContextListener接口

		2. 复写方法

		3. 配置

			1. web.xml配置方式

				```xml
				<listener>
				    <listener-class>com.xian.listener.ListenerDemo1</listener-class>
				  </listener>
				```

				指定初始化参数

				```xml
				  <context-param>
				    <param-name>xxx</param-name>
				    <param-value>/WEB-INF/classes/xxx.xml</param-value>
				  </context-param>
				```

			2. 注解配置

				@WebListener



# JQuery

1. 概念：一个JavaScript框架。简化js的开发

	- jQuery是一个快速、简洁的JavaScript框架，是继Prototype之后又一个优秀的JavaScript代码库（框架）于2006年1月由John Resig发布。jQuery设计的宗旨是“write Less，Do More”，即倡导写更少的代码，做更多的事情。它封装JavaScript常用的功能代码，提供一种简便的JavaScript设计模式，优化HTML文档操作、事件处理、动画设计和Ajax交互。
	- JavaScript框架：本质上就是一些js文件，封装了js的原生代码而已

2. 快速入门

	1. 步骤：

		1. 下载jQuery文件

			目前jQuery有三个大版本：

			- 1.x 版本

				兼容ie678,使用最为广泛的，官方只做BUG维护，功能不再新增。因此一般项目来说，使用1.x版本就可以了，最终版本：1.12.4 (2016年5月20日)

			- 2.x 版本

				不兼容ie678，很少有人使用，官方只做BUG维护，功能不再新增。如果不考虑兼容低版本的浏览器可以使用2.x，最终版本：2.2.4 (2016年5月20日)

			- 3.x 版本

				不兼容ie678，只支持最新的浏览器。除非特殊要求，一般不会使用3.x版本的，很多老的jQuery插件不支持这个版本。目前该版本是官方主要更新维护的版本。最新版本：3.5.1（2020年06月29日）

				jQuery-xxx.js 与 jQuery-xxx-min.js 区别 ：

				* jQuery-xxx.js ：开发版本。给程序员看的，有良好的缩进和注释
				* jQuery-xxx-min.js ：生产版本，程序中使用，没有缩进，体积小

		2. 导入jQuery文件

		3. 使用

3. JQuery对象和JS对象区别与转换

	jQuery对象在操作时，更加方便
	jQuery对象和js对象是不通用的
	两者相互的转化
	 js --> jquery ： $(js对象)
	jQuery --> js ： jq对象[索引] 或者 jq对象.get(索引)

4. 选择器：筛选具有相似特征的元素（标签）

	1. 基本语法学习：
		1. 事件绑定
		2. 入口函数
		3. 样式控制

5. DOM操作

6. 案例

# SpringMVC

# Spring

# Mybaits
