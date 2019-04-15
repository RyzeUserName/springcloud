# Spring cloud

 简介：

随着互联网的快速发展，单机架构的应用已不能满足需求，架构的发展为 单体---分布式---SOA---微服务

**单体**应用架构在java领域就是一个web应用，表现层---业务层---数据访问层---DB层。

**分布式**应用架构将业务垂直切分，切分成不同的单体架构，通过api 相互调用。

**SOA**面向服务的软件体系结构，不同应用的不同组件通过网络上的通信协议向其他应用组件提供服务或者消费服务。

**微服务**可以说是SOA的继续发展的成果，是一种架构风格，对于一个大型复杂的业务系统，它的业务功能可以拆分成

多个相互独立的微服务，之间是松耦合的，通过各种远程协议相互同步/ 异步通信，可以单独部署，扩/缩容以及升/降级。

**各种微服务的对比**

|               | Springcloud         | Dubbo         | Motan        | MSEC             | 其他                |
| ------------- | ------------------- | ------------- | ------------ | ---------------- | ------------------- |
| 功能          | 微服务完整整方案    | 服务治理框架  | 服务治理框架 | 服务开发运营框架 |                     |
| 通信方式      | REST/Http           | RPC协议       | RPC/Hessian2 | Protocol buffer  | grpc  thrift        |
| 服务发现/注册 | eureka              | ZK,Nacos      | ZK/Consul    | 只有服务发现     | Etcd                |
| 负载均衡      | Ribbon              | 客户端负载    | 客户端负载   | 客户端负载       | Ngnix+Lua           |
| 容错机制      | 6种容错策略         | 6种容错策略   | 2种容错策略  | 自动容错         | keepalive，hertbeat |
| 熔断机制      | Hystrix             | 无            | 无           | 无               | 无                  |
| 配置中心      | spring cloud config | Nacos         | 无           | 无               | Apollo Nacos        |
| 网关          | Zuul,Gateway        | 无            | 无           | 无               | Kong 自研           |
| 服务监控      | Hystrix+Turbine     | Dubbo+Monitor | 无           | Monitor          | ELK                 |
| 链路监控      | Sleuth+Zipkin       | 无            | 无           | 无               | Pinpoint            |
| 多语言        | Rest支持多语言      | java          | java         | java c++ php     | java node php       |
| 社区活跃      | 高                  | 高            | 一般         | 位置             | 略                  |



**解决方案**：

​	1.spring cloud 为基础的	

| 组件       | 方案1                                                | 方案2  | 方案3        |
| :--------- | ---------------------------------------------------- | ------ | ------------ |
| 服务发现   | Eureka                                               | Consul | etcd / Nacos |
| 公用组件   | Fegin Ribbo Hytrix                                   | 同左边 | 同左边       |
| 网关       | Zuul（性能低）/spring cloud Gateway（推介）          | 同左边 | 同左边       |
| 配置中心   | Spring cloud Config/ 携程阿波罗/阿里Nacos            | 同左边 | 自研网关组件 |
| 全链路监控 | zikpin（不推介）/Pinpoint（不推介）/Skywalking(推介) | 同左边 | 同左边       |
| 搭配使用   | 分布式事务 、容器化、Spring Cloud与DDD、gRPC         | 同左边 | 同左边       |

​	2.dubbo

​	dubbo+Nacos+其他

中间组件：

​	操作系统+中间件+数据库 为传统基础软件的 三驾马车，随着互联网的发展，中间件不断演进扩大自己的边界，常见的中间件：服务治理中间件、配置中心、全链路监控、分布式事务、分布式定时任务、消息中间件、API网关、分布式缓存、数据库中间件等，spring cloud 也是一个中间件。

​	当然也包含很多中间件也可以很好地与各个组件无缝对接：

​		服务治理中间件  （服务治理注册  容错 熔断 负载 自我保护等）

​		配置中心中间件  （配置文件 统一管理 实时发布）

​		网关中间件 （第一代 zuul 第二代Gateway）

​		全链路监控中间件  (推介 Pinpoint Skywalking)

# 1.核心组件

## 	1.Eureka

​	 ------------------------------------------eureka-1   eureka-2 文件夹

### 		1.背景

​			单体架构：调用为 接口+appId+appkey

​			SOA架构：ngnix维护可用服务（1.本机维护本机服务 2.调用方维护服务 ）

​			微服务：作为一个单独中间组件 或者 ngnix+监控组件 或者 ngix+动态load

### 		2.简介

​				是Netflix公司开源的一款服务发现组件，包括server+client（java 编写的）

​			AP，即维护可用和容错，维护最终一致性，server端采用p2p的复制模式，保证最终一致性

​			注册时带个期限，并通过心跳通信，一旦不健康就是剔除，而consul采用的Raft 算法，提供强一致性保证

### 		3.api 

​			server 提供了很多 rest api 供操作 各个应用实例

​				

| 操作                       | http动作                                                     | 描述                                |
| -------------------------- | ------------------------------------------------------------ | ----------------------------------- |
| 注册新的应用实例           | POST /eureka/apps/{appId}                                    | 成功204 json/xml                    |
| 注销应用实例               | DELETE /eureka/apps/{appId}/{instanceId}                     | 成功返回200                         |
| 应用实例发送心跳           | PUT /eureka/apps/{appId}/{instanceId}                        | 成功返回200 不存在instanceId返回404 |
| 查询所有应用               | GET /eureka/apps                                             | 成功返回200 json/xml                |
| 查询指定appId的实例        | GET /eureka/apps/{appId}                                     | 成功返回200 json/xml                |
| 根据instacneId和 appId查询 | GET /eureka/apps/{appId}/{instanceId}                        | 成功返回200 json/xml                |
| 根据instanceId 查询        | GET /eureka/instances/{instanceId}                           | 成功返回200 json/xml                |
| 暂定应用实例               | PUT /eureka/apps/{appId}/{instanceId}/status?value=OUT_OF_SERVICE | 成功200 失败500                     |
| 恢复应用实例               | DELETE /eureka/apps/{appId}/{instanceId}/status?value=UP(参数可以不传) | 成功200 失败500                     |
| 更新元数据                 | PUT /eureka/apps/{appId}/{instanceId}/metadata?key=value     | 成功200 失败500                     |
| 根据vip地址查询            | GET /eureka/vips/{vipAddress}                                | 成功返回200 json/xml                |
| 根据svip地址查询           | GET /eureka/svips/{svipAddress}                              | 成功返回200 json/xml                |

### 	4.核心类

​		**InstanceInfo 类 注册服务实例**

​		![1551755128080](.\assets\1551755128080.png)

​		**LeaseInfo 类 应用实例的租约信息**

​	![1551755738010](.\assets\1551755738010.png)

​		

​		**ServiceInstance 是Spring cloud对服务发现的实例信息的抽象接口** 约定了服务实例应该有的信息

![1551756858996](.\assets\1551756858996.png)

​	Eureka对其实现 是EurekaRegistration  同时还实现了Closeable  即优雅的关闭 eurekaClient.shutdown()

​	

​	InstanceInfo 的状态 枚举![1551757620150](.\assets\1551757620150.png)

### 	5.核心操作

​	对于服务发现来说，围绕服务实例主要有：

​		服务注册（register）

​		服务下线 (cancel)

​		服务租约( renew)

​		服务剔除( evict)

​	围绕这些功能 **Eureka** 设计核心操作类

​		LeaseManager

​		LookupService

​		InstanceRegistry

​		AbstractInstanceRegistry

​		PeerAwareInstanceRegistryImpl

​	**Spring Cloud Eureka** 围绕这些抽象和定义了几个核心类

​		InstanceRegistry

​		ServiceRegistry

​		EurekaServiceRegistry

​		EurekaRegistration

​		EurekaClientAutoConfiguration

​		EurekaClientConfigBean

​		EurekaInstanceConfigBean

​	**LeaseManager**接口定义了应用服务实例在服务中心的几个操作

​		register cancel renew evict（剔除是服务端的方法）

​	**LookupService**接口定义了client从服务中心获取服务实例的查询方法

​		![1551865704138](.\assets\1551865704138.png)

### 		6.设计理念

​		1.概述

​		作为一个服务注册中心其实要解决一下问题：

​		**服务实例如何注册到服务中心**

​		调用eureka的 rest api

​		对于java 可以用eureka client 的api

​		对于spring cloud 基于spring boot自动实现服务信息注册

​		**服务实例如何从服务中心剔除**

​		在服务实例关闭的时候通过钩子或者其他生命周期的回调方法去调用Eureka Server的 de-register方法，

​		删除自身的服务实例信息，当然还会存在另外一个问题，不主动删除怎么办，通过心跳，即客户端需要向注册

​		中心发送心跳，续租，倘若没有那么服务端会主动剔除该服务实例。

​		**服务实例信息一致性**

​		首先注册中心不是单点的，也可以是集群的，如何保持一致性：

​		有以下几点：

​		①**AP优于CP**

​			分区容错性都是需要的，而Eureka是部署在AWS背景下设计的,其设计者认为 失败是不可避免的，希望在

​			产生网络分区或者其他情况下还能够提供服务注册和发现，在实践生产中，服务注册中心保留可用以及一

​			些过期数据总比丢掉要好，当然需要配合负载均衡和失败重试 以达到更好地效果，也就是说**不是强一致性**

​		②**Peer to Peer**

​			一般的分布式系统的数据在副本之间的复制方式分为：主从复制 和 对等复制

​			**主从复制**即Master-slave 模式，写操作由主负责，其他从才从主机更新信息，从机可以分担读请求，主的

​			写操作压力是整个系统的瓶颈（同步更新，异步更新，同步与异步混合更新）

​			 **对等复制**即peer to peer，副本之间不区分主和从，任何副本都可以接收写操作，然后副本之间进行相互的

​			数据更新。但是副本之间的**数据同步**以及**冲突处理**是一个比较棘手的问题

​				客户端：

​					优先选择与实例所在的分区，如果没找到则默认defaultZone，客户端使用一个Set维护一个不可

​					用Eureka Server列表，优先在可用列表中选择，如果失败切换到下一个Eureka Server重试,重试

​					默认次数是3，另外为了防止每个Client都按照配置文件的指定顺序进行请求造成Eureka Server

​					节点请求分布不均衡的情况，Client端有个定时任务，默认5分钟执行一次刷新并随机化Eureka

​					Server的列表

​				服务端：

​					Eureka Server本身依赖Eureka Client，即Server 通过Client获取信息，在Server启动

​					时，会有个synUp的操作，通过Eureka Client向其他的Eureka Server 节点中的一个节点获取注

​					册的应用实例信息，然后复制到peer 节点，为了避免循环复制，http header是

​					HEADER_REPLICATION 来标记该请求是复制操作，那么就解决了数据同步，但是还有个数据

​					冲突问题，Server端复制信息版本号对比，高的优先，当然Eureka  不是采用版本号，而是采用

​					lastDirtyTimestamp字段对比，SyncWhenTimestampDiffers配置开启（默认开启），当	

​					lastDirtyTimestamp不为空，当请求方请求本地Server时，进行对比

​					本地Server小的话说明，本地Server数据落后，返回404，要求应用实例重新注册；

​					本地Server大的话，说明请求方的数据落后，返回409，要求其同步自己最新的数据信息。

​					当然节点之间的复制并一定都会成功，Eureka还通过应用实例与Server 之间的心跳进行服务租

​					约续期，完成数据最终修复。

​		③**Zone以及Region设计** 高可用

​					因为设计这个的公司的大部分服务在 Amazon上，因此的 Eureka的设计一部分也是基于Amazon

​					的Zone以及Region，Amazon的云服务器是托管在全球的各个地方，Region代表一个独立的地

​					理区域，每个Region下面又是多个AvailabityZone，同一个Rrgion下的AvailabityZone之间

​					相互复制，跨Region不会进行复制，AvailabityZone就像是一个个机房，独立，为Region提供高

​					可用，Eureka Server的高可用类似于 AvailabityZone 。

​					Eureka Client支持perferSameZone，就是获取EurekaServer的serviceUrl 优先拉取跟应用实例

​					同在一个AvailabityZone的Eureka Server的地址列表，一个AvailabityZone 可设置多个Eureka

​					Server，它们之间构成peer节点，然后采用peer to peer的复制

​		④**SELF PRESERVATION**(自我保护) 假死-延时处理

​				处理好网络偶尔抖动或暂时不可用造成的误判，**即假死**，Eureka Server和Eureka Client端出现网络分

​				区，极端情况下Eureka Server清空部分服务实例列表，这将影响Eureka Server的高可用，因此引入

​				了自我保护机制。

​				客户端定时向服务端发送心跳租约，服务端根据注册实例数，去计算每分钟从应用实例接收的心跳

​				数，如果最近一分钟接收到的续约次数小于等于指定阈值，关闭租约失效剔除，禁止定时任务剔除

​				失效实例，即 **延时处理**。

​				生产环境可以适当降低阈值,降低门槛，

​				eureka.instance.lease-renewal-interval-in-seconds= 10 # 默认30

​				eureka.server.renewal-percent-threshold =0.49 #默认 0.85

### 	7.Eureka的参数调优及监控

​		**1.Client**

​			基本参数

​				是否注册，发送心跳的时间间隔等

​			定时任务

​				定时续期，定时刷新，定时发送心跳等参数设置

​			http参数

​				连接超时，连接池等参数设置

​		**2.Server**

​			基本参数

​				指定自我保护等参数，剔除实例的頻率，等

​			response cache参数

​				提供了 ConcurrentMap 的readOnlyCacheMap

​				和Guava Cache的ReadWriteCacheMap

​				设置这些缓存的更新时间頻率等

​			peer相关参数

​				指定从配置文件更新serviceUrl 和更新 peer nodes状态信息时间间隔

​			http相关参数

​				连接超时，连接池等参数设置

​		**3.问题**

​			1.服务下线了，Server 还会返回该服务实例信息

​			2.服务上线了，Client 不能及时获取到

​			3.出现自我保护的一堆红字等

​			解决：Eureka Server 不是强一致的，因此会存在一些过期的信息：

​				1.应用剔除依赖于Server 端的EvictionTask的定时任务

​				2.Server端存在缓存

​				3.Server端引入自我保护

​				1解决-> 调整 EvictionTask任务的时间间隔，减小该值

​				eureka.server.eviction-interval-timer-in-ms 默认60*1000 即60秒

​				2解决-> 缓存可以

​				eureka.server.use-read-only-response-cache= false 关闭只读缓存

​				或者

​				eureka.server.response-cache-auto-expiration-in-seconds= 180

​				默认 180秒 调整小即可

​				当然针对测试环境 服务获取不及时可以调整 客户端拉取Server端信息的頻率

​				eureka.server.registry-fetch-interval-seconds =30 默认 30s 可以调小

​				3解决->关闭自我保护

​				eureka.server.enable-self-preservation=false

​		**4.指标监控**

​			spring boot 2.x  支持Netflix Spectator 获取全部的Monitor

### 	8.实战

​			eureka-2 文件夹

​			1. 在线扩容 OnlineExpansion     根据配置中心的配置变更进行动态扩容

​			 2. 多Zone MultiZone   配置不同的Zone

​			3. 支持 远程Region RemoteRegion  

​			4.开启http basic 认证 HttpBasic

​			5.https  Https

​			6.admin  EurekaAdmin

​			7.**故障演练  FaultDrill**

​				1.Eureka Server 全部不可用

​					① 应用服务启动前不可用

​						应用可以启动，有报错信息为：ConnectionException：connnection refused

​						针对服务端负载均衡或者服务端ip固定，eureka.client.backup-registry-impl 属性

​						可以在读取不到注册信息时，从这back registry读取作为 fallback

​						实现BackupRegistry接口

​					② 应用服务运行时不可用

​						client端有个定时任务CacheRefreshThread 从server端拉取注册信息到本地，如果有挂掉

​						的话，该定时任务会报异常 ConnectionException：connnection refused，本地的

​						localRegionApps变量不会得到更新	

​				2.Eureka Server 部分不可用

​					①client 端

​						client 端有个定时任（AsyncResolver.updateTask）去拉取serviceUrl变更，如果有变更的

​						话会动态变更，拉取完之后 client端 进行随机化 ServerUrl，Client端会维护一个不可用

​						Server列表,当该列表超过阈值时，会被清空，一般拉取完server列表会剔除不可用列表，

​						但是当剔除完列表就空了的话，就不剔除了。当一个被剔除了，加入不可用列表，拉取信息

​						就从剩下的可用中拉取信息

​					① Server端

​						服务端是peer to peer ，当一台挂了则服务端的 replication会受影响，PeerEurekaNodes

​						有个定时任务（peersUpdateTask），会从配置文件拉取avaliabilityZones以及ServiceUrl

​						信息，然后运行时更新peerEurekaNodes信息，当一台挂了，人工介入会进行剔除，不剔

​						除的话，会报错connection to peer  localhost  retrying after delay

​								 ConnectionException：connnection refused

​						服务端目前没有对peerEurekaNodes 进行健康检查。

​				3.Eureka高可用

​						1.Region  基于Amazon的Region，默认不会拉取远端的region，client可以配置成拉取的

​						2.AvilabilityZone 默认拉取 配置的zone的信息，如果没配的话，那就是第一个Zone

​					client 端高可用

​						1.启动前，可以配置fallback 的注册server

​						2.启动后，server全不可用，那么本地localRegion不会被更新，定时更新线程

​						cacheRefershThread会报错

​						3.启动后，server部分不可用，可以人工介入，修改client的配置文件，动态加载，一般

​						client 太多，也不会这么操作，client 会维护不可用列表，剔除

​					Server端

​						是peer to peer 也就无所谓高可用了，主要的高可用在客户端就可以了，服务端也支持从远

​						Region 获取服务，remote-region-urls-with-name 可以设置远端的server 作为fallback,

​						另外一点就是，Server端的自我保护，即对 一分钟内剔除的服务实例数量达到规定的阈值

​						时，禁止定时任务剔除实例。从而保护服务实例（也解决了 假死等）					

## 	2.Feign 

​		----------------------------------------------------------feign

### 	1.概述

​		Feign是一个声明式，模板化的http客户端，使用Feign 只需要创建一个接口，写上注解，就可以调用远

​		程接口了，支持编解码，特性：

​		1.可插拔的注释支持，包括Feign注解和JAX-RS注解

​		2.支持可插拔的http编解码器

​		3.支持Hystrix和它的fallback，整合好了

​		4.支持Ribbon，整合好了

​		5.支持http的请求和响应压缩

### 	2.入门

​		----------------feign

### 	3.原理

​	**@EnableFeignClients** 开启spring 容器扫描 @FeignClient  并加入到IOC容器，当其调用时，通过jdk的代理方式，生成具体的RequestTemplate，为每个接口方法都生成一个RequestTemplate 对象，该对象封装了HTTP请求的全部信息，如参数等，然后RequestTemplate 生成 Request，发送给Client，最后Client 被封装到LoadBalanceClient类，这个类结合Ribbon负载均衡发起服务之间的调用。

​	@EnableFeignClients  

​	属性 defaultConfiguration 可以指定配置类，配置类中配置对全部 feign 默认参数

​	也可以在啊application 文件配置 feign.client.config.default.XXX=XXX

​	默认 配置文件会覆盖java 代码，feign.client.default-to-properties=false 改变feign的配置生效优先级

​	**@FeignClient** 是在接口上，

​	属性 name 指定 FeignClient 的名称，如果使用Ribbon，name会作为微服务的名称，用于服务发现。

​	属性 url 一般用于调试，可以手动指定调用地址

​	属性 decode404 发生404 时如果是true 会调用解码，否则跑出FeignException

​	属性 configuration 自定义Feign的Encoder  Decoder LogLevel Contract

​	属性 fallback 定义容错的处理类，远程调用失败或者超时时会去调用 容错处理类

​	属性 fallbackFactory 工厂类，用于生成fallback类示例，通过这个通用的容错类减少重复代码

​	属性 path 定义当前 FeignClient 的统一 前缀

​	当然也可以通过 application 文件配置特定 feignNane 的

​	feign.client.config.feignNane.XXX=XXXX

​	**开启日志**

​		1.application 配置 日志级别  logging.level.XXXXpackage=DEBUG

​		2.配置

```java
@Configuration
public class FeignConfig {
    /**
     * Logger.Level 的具体级别如下：
     * NONE：不记录任何信息
     * BASIC：仅记录请求方法、URL以及响应状态码和执行时间
     * HEADERS：除了记录 BASIC级别的信息外，还会记录请求和响应的头信息
     * FULL：记录所有请求与响应的明细，包括头信息、请求体、元数据
     * @return
     */
    @Bean
    Logger.Level getLevel() {
        return Logger.Level.FULL;
    }
}
```

​	**超时设置**

​	Feign的调用分两层，Ribbon的调用和Hystrix的调用，高版本的Hystrix默认是关闭的。

​	当出现：read timed out executing 。。。SocketTimeoutException：Read timed out

​	**是Ribbon 超时** 

​	ribbon.ReadTimeout: 120000

​	ribbon.ConnectTimeout: 30000

​	**开启了Hystrix** ----HystrixRuntimeException

​	hystrix.shareSecurityContext=true

​	hystrix.command.default.circuitBreaker.sleepWindowInMlliseconds= 100000

​	hystrix.command.default.circuitBreaker.foreClosed= true

​	hystrix.command.default.execution.isolation.thread.timeoutInMilliseconds= 600000

### 	4.实战

​	1.普通调用+替换成httpclient+okhttp（默认是jdk 的URLConnection 发送http 请求，没有连接池，但对每个地址保持

​	一个长连接，即利用Http的persistence conncetion 可以用Http Client 和 okhttp 替换feign 默认的Client）

​	2.springMVC get绑定pojo 解决

​	3.文件上传（feign-form）

​	4.图片的输出 可以转成byte[]

​	5.Feign传递 token （类似于一个 拦截器相当于2）

​	6.venus-cloud-feign

​		为了解决：① feign 上注解与Spring MVC 上的注解没有关系，需要写两遍

​				  ② feign 上注解 并不支持 Spring MVC 全部注解 （当然可以通过feign拦截器）

## 	3.Ribbon

### 	1.概述

​		Netflix公司负载均衡组件，丰富的负载策略、重试机制、支持多协议的异步和响应式模型、容错、缓存与批处理

​		负载均衡分硬件（F5）和软件（ngnix等）负载均衡，即利用特定方式将流量分摊到多个操作单元上的一种手

​		段，它对系统吞吐量与系统处理能力有着质的提升。毫不夸张的说，当今极少有企业没有用到负载均衡器或者

​		负载均衡策略的。

​			其他分类的负载均衡还有**集中式负载均衡**和**进程内负载均衡**。**集中式负载均衡**指位于因特网与服务提供者

​		之间，并负责把网络请求转发到各个提供单位，ngnix与f5是一类，也叫服务端负载均衡

​		**进程内负载均衡**是指在实例库选取一个实例进行流量导入，实例库一般是在eureka 或者zookpeer等，也叫客户

​		端负载均衡，赋予了应用一些支配HTTP与TCP行为的能力。

### 	2.入门

​	------------------------------------------------------------------------------------------bibbonhello

### 	3.实战

​	1.负载策略

​		

| 策略类                    | 命名             | 描述                                                         |
| ------------------------- | ---------------- | ------------------------------------------------------------ |
| RandomRule                | 随机策略         | 随机选择Server                                               |
| RoundRobinRule            | 轮询策略         | 按顺序循环选择                                               |
| RetryRule                 | 重试策略         | 在一个配置时间段内选择server不成功，则会一直尝试选择一个可用的server |
| BestAvaliableRule         | 最低并发策略     | 逐个考察Server 如果server断路器打开，则忽略，在选择其他并发连接最低的Server |
| AvailabilityFilteringRule | 可用过滤策略     | 过滤掉一致连接失败并被标记为circuit tripped的server，过滤掉哪些高并发连接的server（active connections 拆过配置的阈值） |
| ResponseTimeWeughtedRule  | 响应时间加权策略 | 根据server的响应时间分配权重，响应时间越长权重越低，概率也就越低，直接跟响应时间挂钩 |
| ZoneAvoidanceRule         | 区域权衡策略     | 综合判断server所在区域的性能和server的可用性轮询选择server，并且判断一个AWS Zone的运行性能是否可用，剔除不可用的Zone的所有server |

​	自定义配置：

​			①全局配置 直接返回对应策略的 bean

​			②通过配置 特定的配置类 在@FeignClient 的configuration 属性为那个特殊的配置类

​				当然也可以在@FeignClients 俩面配置多个@RibbonClinet

​			③还可以通过配置文件配置,指定对应应用的负载均衡策略

```yml
client: #客户端 spring.application.name
  ribbon:
    NFLoadBalancerRuleClassName: #对应的配置类 上面列表
```

​	2.超时与重试（容错）

​	3.饥饿加载（为了防止第一次超时）

​	4.利用配置文件配置（指定特定 实现一些类）

​	5.脱离Eureka使用 （失去了动态列表）

```yml
ribbon:
  eureka:
    enabled: false
client:
    ribbon:
      listOfServers: http://localhost:7070,http://localhost:7071
```

### 	4.进阶

​	1.核心工作原理

​	

| 接口                      | 描述                                                    | 默认实现                       |
| ------------------------- | ------------------------------------------------------- | ------------------------------ |
| IClientConfig             | 定义Ribbon中管理配置的接口                              | DefaultClientConfigImpl        |
| IRule                     | 定义Ribbon中负载均策略的接口                            | ZoneAvoidanceRule              |
| IPing                     | 定义定期ping服务检查可用性的接口                        | DummyPing                      |
| ServerList< Server>       | 定义获取服务列表方法的接口                              | ConfigurationBaseServerList    |
| ServerFilterList< Server> | 定义特定期望获取服务列表方法的接口                      | ZonePreferenceServerListFilter |
| ILoadBalancer             | 定义负载均衡选择服务的核心方法的接口                    | ZoneAwareLoadBalancer          |
| ServerListUpdater         | 为DynamicServerListLoadBalancer定义动态更新服务列表接口 | PollingServerListUpdater       |

​	

​	2.源码导读

​		从@LoadBalanced 开始 看到RestTemplate 使用LoadBalancerClient

![1552990503932](.\assets\1552990503932.png)

​	LoadBalancerClient 继承自 ServiceInstanceChooser 继承了 choose 选择服务实例 以及各种执行

![1553052583233](.\assets\1553052583233.png)

​	同级目录下 LoadBalancerAutoConfiguration.class

![1553053699985](.\assets\1553053699985.png)

​	loadBalancerClient  生成 LoadBalancerRequestFactory

​	LoadBalancerRequestFactory + loadBalancerClient 生成 LoadBalancerInterceptor

​	LoadBalancerInterceptor 最终 被绑定到 restTemplate 上

​	LoadBalancerInterceptor 

​	![1553053828083](.\assets\1553053828083.png)

​	调用loadBalancerClient   的excute

​	![1553054395342](.\assets\1553054395342.png)

​	![1553054410540](.\assets\1553054410540.png)

![1553054474983](.\assets\1553054474983.png)



## 	4.Hystrix

### 	1.概述

​		Hystrix 是一个延迟和容错库，旨在隔离远程系统、服务和第三方库，组织级联故障，在负载的分布式系统中实

​	现恢复能力，迅速失败和快速恢复，在合理情况下回退和优雅降级，开启近实时监控 告警和操作控制

### 	2.实战

#### 		**1.** 普通断路器

​	hystrix1 

#### 	        **2.**  feign中使用断路器 

​	hystrix2

#### 		**3.** 仪表盘Dashboard+ turbine

​	+缓存+异常+缓存+线程隔离+线程合并

​	hystrix- dashboard 

#### 		**4.**Hystrix 异常处理 fallback 

​			回滚情况:

​			①FAILURE: 实行失败，抛出异常

​			②TIMEOUT: 失败超时

​			③SHORT_CIRCUITED: 断路器打开

​			④THREAD_POOL_REJECTED:线程池拒绝

​			⑤SEMAPHORE_REJECTED:信号量拒绝

​		有一种异常是不会出发回滚，BAD_REQUEST,会抛出 HystrixBadRequestException,这类异常是由非法参数或

者系统异常导致的，对于这类异常可以根据响应创建对应的异常封装或者直接处理。

#### 		**5.**hystrix 的**配置**参数

​			（https://github.com/Netflix/Hystrix/wiki/Configuration）

​		下面列举一些常用的，可能需要改动的配置

​	隔离策略：HystrixCommandkey  如果不配的话 默认是方法名

​		默认值：`THREAD` (see `ExecutionIsolationStrategy.THREAD`)

​		可选值 ：SEMAPHORE、THREAD

​		默认属性 ：hystrix.command.default.execution.isolation.strategy

​		实例属性 : hystrix.command.*HystrixCommandKey*.execution.isolation.strategy

​	配置hystrixCommand 命令执行超时事件，单位是毫秒

​		默认值：1000

​		默认属性： hystrix.command.default.execution.isolation.thread.timeoutInMilliseconds

​		实例属性：hystrix.command.HystrixCommandKey.execution.isolation.thread.timeoutInMilliseconds

​	配置HystrixCommand 命令执行超时是否开启

​		默认值： true

​		默认属性： hystrix.command.default.execution.timeout.enabled

​		实例属性：hystrix.command.HystrixCommandKey.execution.timeout.enabled

​	配置HystrixCommand 在发生超时时是否应中断执行

​		默认值: false

​		默认属性: hystrix.command.default.execution.isolation.thread.interruptOnCancel

​		实例属性: hystrix.command.HystrixCommandKey.execution.isolation.thread.interruptOnCancel

​	当隔离策略是信号量时，允许的最大请求数,如果达到此最大并发限制，则将拒绝后续请求

​		默认值：10

​		默认属性： hystrix.command.default.execution.isolation.semaphore.maxConcurrentRequests

​		实例属性： hystrix.command.HystrixCommandKey.execution.isolation.semaphore.maxConcurrentRequests

​	断路器设置打开fallback 并启动fallback的最小错误比率（=或者大于 都会打开）

​		默认值：50

​		默认属性：hystrix.command.default.circuitBreaker.errorThresholdPercentage

​		实例属性：hystrix.command.HystrixCommandKey.circuitBreaker.errorThresholdPercentage

​	强制打开断路器，拒绝所有请求

​		默认值：false

​		默认属性：hystrix.command.default.circuitBreaker.forceOpen

​		实例属性：hystrix.command.HystrixCommandKey.circuitBreaker.forceOpen

​	当为线程隔离时，核心线程池大小

​		默认值：10

​		默认属性：hystrix.threadpool.default.coreSize

​		实例属性：hystrix.threadpool.HystrixThreadPoolKey.coreSize

​	当为线程隔离时,做大线程池大小配置，在1.5.9 之前 核心和最大始终相等，在1.5.9版本之后需要配置

​	allowMaximumSizeToDivergeFromCoreSize 为true

​		默认值：10

​		默认属性：hystrix.threadpool.default.maximumSize

​		实例属性：hystrix.threadpool.HystrixThreadPoolKey.maximumSize

​	allowMaximumSizeToDivergeFromCoreSize 

​		默认值：false

​		默认属性：hystrix.threadpool.default.allowMaximumSizeToDivergeFromCoreSize

​		实例属性：hystrix.threadpool.HystrixThreadPoolKey.allowMaximumSizeToDivergeFromCoreSize

​		**注意：** 实际生产中，需要对超时时间、线程池大小、信号量等进行修改，具体结合业务进行分析

​		一般情况下Ribbon的超时时间 短于 Hystrix超时时间

#### 	**6.**Hystrix 线程调整和计算

​		通过自我预判先发布到生产活着测试，然后查看它具体的运行情况，在调整为更符合业务的配置

​		① 默认超时 1000ms 根据自己的业务调整

​		② 线程池池默认10，根据业务调整

​		③ 灰度发布，成功则保持，随时调整

​		④生产环境运行超过24 小时

​		⑤如果有系统告警和监控空，那么可以依靠他们捕捉问题

​		⑥运行24小时之后，通过延迟百分位和流量来计算有意义的最低满足值

​		⑦生产环境或者测试中实时修改，然后用仪表盘监控

​		⑧断路器产生变化和影响，则需要再次确认这个配置

​		计算的例子： ThreadPool 为10 ，峰值每秒 30个请求

​						每秒请求峰值 * 99%的延迟百分比+预留缓冲值

​						30* 0.2s+ 4=10 预留4个线程数

​		Thread Timeout：预留个足够的时间 250ms 然后加上重试一次的中位数值

​		Connect Timeout & Read Timeout： 100ms 和 250 ms

​	![](.\assets\thread-configuration-1280.png)

#### 	**7.**Hystrix 请求缓存

​		Hystrix的 请求缓存是 Hystrix 在**同一个上下文**请求中缓存请求结果，在同一个请求中缓存，即在进行第一次调

用结束后对结果缓存，然后接下来同参数的请求将会使用第一次的结果，缓存的生命周期只是在这一次的请求有效。

​	HystrixCommand有两种方式，第一种继承，第二种注解，缓存也同时支持这两种

​	1.初始化上下文

​		缓存是在同一个上下文，要不然会报错，可以利用filter过滤器和Interceptor拦截器进行初始化，在这里使用一个

​	拦截器来实现，HandlerInterceptor

​	2.使用类开启其缓存

​	3.使用注解开启缓存

​	4.使用注解清除缓存

​	5.小结

​	@CacheResult 使用后结果会被缓存 要和 @HystrixCommand 注解一起使用

​	@CacheRemove 清除缓存指定 commandKey

​	@CacheKey 请求参数，默认讲方法的所有参数最为key与CacheResult和CacheRemove 组合使用

#### 	**8.**Hystrix Request Collapser

​		Hystrix Request Collapser 是针对多个（异步）请求调用当个后台依赖做的一种优化和节约网络开销的方法

​		当发起多个请求，在没有请求聚合和合并的情况下，会为每个请求开启一个线程，打开一个网络链接

​		这会加重网络开销和线程数池资源，Collapser会把这些同一个后台的请求合并，只需要一个网络连接和

​		占用一个连接池资源。**注意：需要上下文**

#### 	**9.**Hystrix 线程传递及并发策略

​		Hystrix 提供了两种隔离模式进行请求操作，一种是信号量，一种是线程。

​		如果是信号量，Hystrix在请求时会获取一个信号量，如果成功拿到，则继续进行请求，请求在一个线程中执行

​		完毕，如果是线程隔离，Hystrix 会把请求放在线程池执行，这是有可能导致线程1的上下文在线程2是拿不到的

​		Hystrix 接管后，请求放到线程池执行，导致上下文不一致

​		解决

①隔离策略修改为信号量 （不推介），大部分系统都是线程隔离

```yml
hystrix:
  command:
    default:
      execution:
        isolation:
          strategy: SEMAPHORE
          
```

②Hystrix 官方推介使用HystrixConcurrencyStrategy，实现其warpCallable方法，对于依赖ThreadLocal状态的系统至关重

​	要，其实就是将上一个线程的东西传递给下一个线程

#### **10.** 注解详解

​	@HystrixCommand 

​	commandKey :全局唯一标识符，如果不配置默认是方法名

​	defaultFallback : 默认的fallback方法，不能有入参，返回值与方法相同，fallbackMethod 更优先

​	fallbackMethod： fallback的方法，签名要跟原方法一致，而且在同一个类里

​	ignoreExceptions：忽略哪些异常，直接抛出，而不触发fallback

​	commandProperties：配置一些命名的属性，如执行的隔离策略等

​	threadPoolProperties：配置一些线程池相关的属性

​	groupKey：全局唯一表示服务分组的名称，内部会根据这个键值展示 统计数和仪表盘等信息

​			默认的线程划分是根据这命令组的名称进行的，一般会在创建HystrixCommand 时指定命令组来实现

​			默认的线程池划分

​	threadPoolKey： 对服务的线程池信息进行设置，用于HystrixThreadPool监控、metrics、缓存等用途

​	observableExecutionMode：执行命令的模式 默认EAGER 同步， LAZY 异步

## 	5.Zuul

### 	1.概述

​	Netflix 孵化，在动态路由、监控、弹性、服务治理以及安全方面起着举足轻重的作用，面向服务治理、服务编排的组

​	件，Zuul 是从设备和网站到应用程序所有请求的前门，为内部服务提供可配置的对外URL到服务的映射关系，基于

​	JVM的后端路由,基本功能：

​		认证授权

​		压力控制

​		灰度发布

​		动态路由

​		负载削减

​		静态响应处理

​		主动流量管理

​	内层是基于Servlet，本质组件是一系列Filter所构成的责任链。

### 	2.入门案例

​		zuul-hello

### 	3.典型配置

#### 		1.路由配置

```yml
#简单实例ServiceId 映射
zuul:
  routes:
    client-a:
      path: /client/**
      serviceId: client-a  # 将/client/**   的url转到 client-a的服务实例上
#同第一个
zuul:
  routes:
    client-a: /client/**    # 将/client/**   的url转到 client-a的服务实例上
#默认啥也不配
zuul:
  routes:
    client-a:     # 默认将  /client-a/**   的url转到 client-a的服务实例上
    
#单实例url映射
zuul:
  routes:
    client-a:
      path: /client/**
      url: http://localhost:7070 #将 /client-a/**  直接映射到 这个路径
#多实例路由，默认情况下Zuul 会使用Eureka中集成的基本负载均衡功能，如果想使用Ribbon的负载均衡，需要指定一个
#serviceId，此操作需要禁止Ribbon使用Eureka
ribbon:
  eureka:
    enabled: false #禁止Ribbon使用Eureka

ribbon-client-a:
  ribbon:
    listOfServers: http://localhost:7070,http://localhost:7071
    NFLoadBalancerRuleClassName: com.netflix.loadbalancer.RandomRule #对应的配置类 上面列表
    NIWSSerrverListClassName:  com.netflix.loadbalancerConfigurationBasedServerList  #基于配置的实例
#本地跳转
zuul:
  routes:
    client-a:
      path: /client/**
      serviceId: forword:/client  # 将/client/**  的url转到 本地的服务实例上 /client 的url controller
#相同路径的加载规则 后面的会覆盖 前面的
zuul:
  routes:
    client-a:
      path: /client/**
      serviceId: client-a  # 将/client/**   的url转到 client-a的服务实例上
    client-b:
      path: /client/**
      serviceId: client-a  # 将/client/**   的url转到 client-b的服务实例上
#路由配置的 通配符
# /** 匹配任意数量的路径与字符  /client/add  /client/ff/gg
# /*  匹配任意数量字符  /client/dddddd  /client/sddd
# /？ 匹配单个字符     /client/a /client/b
      
```

#### 		2.功能配置

```yml
#路由前缀
zuul:
  routes:
    client-a:
      path: /client/**
      serviceId: client-a
      stripPrefix: false #禁止去掉前缀 /pre/client/aa 会被转到 client-a  的 /pre/client/aa
  prefix: pre  #通用的路有前缀  /pre/client/aa 会被转到 client-a  的 /client/aa
  
  
#忽略
zuul:
  ignored-services: client-b #忽略的服务
  ignored-patterns: /**/dev/** #忽略的接口 
# 切断敏感信息
zuul:
  routes:
    client-a:
      path: /client/**
      serviceId: client-a
      sensitiveHeaders: Cookie,Set-Cookie,Authorization #不想传给下游的信息
#重定向 不想暴露真实地址
zuul:
  routes:
    client-a:
      path: /client/**
      serviceId: client-a
  add-host-header: true 
#重试 配合Ribbon重试 此功能慎用
zuul:
  routes:
    client-a:
      path: /client/**
      serviceId: client-a
  add-host-header: true
  retryable: true #开启重试
ribbon:
  MaxAutoRetries: 1#同一个服务的重试次数（除去第一次）
  MaxAutoRetriesNextServer: 1 #切换相同服务器的数量

```

### 	4.Filter

#### 	1.原理

​	 zuul 的 filter 责任链 组成了 丰富的网关操作。

​	类型：路由前，路由后，路由中

​	顺序： 同一类型的，设置filterOrder() 设定其执行顺序

​	条件：filter执行的条件

​	效果：执行filter，产生的效果

​	filter 之间不直接通信，在请求线程中通过RequestContext共享状态（底层是ThreadLocal 实现的），

​	当然也可以通过ThreadLocal 收集共享状态和数据（还扩展了ConcurrentHashMap）

​	![1553349453125](.\assets\1553349453125.png)

大致如图：

​	![](.\assets\1.jpg)

在这个**生命周期** pre： 可以对请求做预处理，限流，鉴权等

​			route：路由动作的执行者，httpClient或者Ribbon构建或发送原始http请求的地方，目前支持okHttp

​			post: 后处理

​			error：发生异常处理，可以做全局异常处理

#### 2.原生filter

​	在zuul 项目上 加入Spring boot Actuator  

​	访问 其 路径/actuator/routes 可查看zuul server 生成的路由规则 再加上 details 可查看明细

​	访问 其 路径/actuator/filters 可查看zuul server 已注册生效的 filter

​	![](.\assets\2.jpg)

​	以上便是使用注解 @EnableZuulProxy 之后的安装filter

​			若是@EnableZuulServer 将缺少 PreDecorationFilter、RibbonRoutingFilter、SimpleHostRoutingFilter

​		 以上自带的都可以禁止，语法为：zuul.< className>.< filterType>.disable=true

​		比如：禁止 SendErrorFilter   zuul.SendErrorFilter.error.disable=true

#### 	3.多级业务处理

​	 我们可以吧一组业务逻辑细分，然后封装到一个紧密结合的Filter中，设置处理顺序，组成一组Filter链

​	1--定义自己的Filter  extend ZuulFilter 

​	2 ---业务实战  zuul-hello

​	![](.\assets\业务.jpg)

### 	5.权限集成

#### 	1.概述

​	服务之间的无状态，难免力不从心，传统的譬如：单点登录（SSO）或者分布式Seesion，要么致使权限服务器集中化导致流量臃肿，姚蜜需要实现一套复杂的存储同步机制，都不是好的解决方案作为Spring Cloud 微服务体系流量前门的Zuul,除去了与它特性毫无相关的实现方式，比较好的：

​	 ① 自定义权限Filter，缺点：维护成本增加，电泳链路变得紊乱

​	 ② OAth2.0+JWT

​	**OAth2.0** 如下图

​	![](.\assets\oauth.jpg)

​	**JWT** 是一种使用JSON 格式来规定Token或者Session的协议。由于传统认证方式免不了会生成一个凭证，这个凭证可以是Token或者Session,保存与服务端或者其它持久化工具中，这样一来，存取很麻烦，JWT的出现打破这一瓶颈，实现了“客户端Session” 

​	JWT组成： **Header**头部（JWT使用的签名算法）+Payload**载荷**（自定义与非自定义的认证信息）+**Signature**签名（ 将头部与载荷使用“.” 连接之后，是用头部的签名算法生成签名信息，并拼装到末尾）

​	使用OAth2.0协议的思想拉取认证生成Token，使用JWT瞬时保存这个Token，在客户端与资源端进行对称或者非对称加密，使得这个规约定时、定量的授权认证功能，从而免去Token存储带来的安全或者系统扩展问题

#### 	2.OAuth2.0+JWT 实战

​	zuul-oauth-jwt

​	OAuth2.0 扩展: OAuth 2.0 is the industry-standard protocol for authorization

### 	6.限流

​	构建一个自我修复性系统一直是个大企业进行架构设计的难点所在，除了Hystrix的熔断器，对异常流量进行降级处理，我们哈可以通过一些其它操作保护我们的系统免受“雪崩”，比如：流量排队、限流、分流等

#### 	1.限流算法

​	**漏桶** 

​		底部有漏孔的桶，桶上方有一个入水口，水不断地流进桶内，桶下方 的漏孔会以一个相对恒定的速率漏水，

​	当入大于出，进水的多余会溢出，而入小于出，漏桶不起作用，当我们的请求或者具有一定体量的数据流涌来的时候

​	流量会被整形，不能满足要求的部分会被削减掉，而溢出的流量可以被利用起来，收集到一个队列里，做流量排队

​	**令牌桶**

​		桶里放令牌，令牌以一个恒定的速率被加入桶内，可以积压，可以溢出，当数据流涌来时，量化的请求御用获

​	取令牌，如果不能取到令牌，请求则被丢弃。**令牌桶可以存放一定数量的令牌，可能存在一定程度上的流量突发**。

#### 	2.限流实战

​	zuul-limit

​	选择spring-cloud-zuul-ratelimit 针对Zuul 编写的限流库 提供了多粒度策略：

​	版本号 2.0.6.RELEASE

- user：认证用户或者匿名，针对某个用户粒度限流
- origin: 客户机ip，针对请求客户机ip 粒度进行限流
- url: 特定url，针对某个请求url粒度进行限流
- serviceId:特定服务，针对某个服务id粒度进行限流
- IN_MEMEORY:基于本地内存，底层是ConcurrentHashMap
- REDIS: Redis k/v存储
- CONSUL: Consul的K/V存储
- JPA: SpringData JPA ,基于数据库
- BUKETSJ：一个使用Java编写的基于令牌桶算法的限流库，四种模式，JCache、Hazelcast、Apache Ignite、Inifinispan,其中后三个支持异步

### 	7.动态路由

#### 1.概述

​	目前两种方案解决：

- spring config+bus 动态刷新配置文件
- 重新zull 的配置读取方式，采用事件刷新机制，基于数据库

#### 2.原理

1. DiscoveryClientRouteLocator

   此类是zuul中对于路由配置信息读取与新节点注册变更的操作类

   ![1553764946450](.\assets\1553764946450.png)

2. SimpleRouteLocator

   ![1553765849698](.\assets\1553765849698.png)

3. ZuulServerAutoConfiguration

   ![1553767894523](.\assets\1553767894523.png)

   

4. ZuulHandlerMapping

   ![1553768703649](.\assets\1553768703649.png)



​	其实在构建动态路由的时候，只需要重写SimpleRouteLocator类的locateRoutes ()方法，并实现RefreshableRouteLocator接口的refresh()方法,再在内部调用SimpleRouteLocator类的doRefresh()方法，就可以构建起一个由zuul内部事件触发的自定义动态路由加载器

#### 3.基于DB的动态路由实战

### 	8.灰度发布

#### 	1.概述 

​	系统迭代新功能时的一种平滑过度的上线发布方式，在原有的系统的基础上，额外增加个新的版本，这个版本有许多我们需要待验证的新功能，随后用负载均衡，引一小部分流量过来，如果没有任何差错，在平滑的将线上的系统逐步替换

#### 	2.实战

​	灰度发布的实现方式有很多种，这里是基于eureka的一种方式

​	eureka里有两种元数据

​	1.标准元数据：服务的各种注册信息，存储在专门的注册表

​	2.自定义元数据： eureka.instance.metadata-map.< key>=< value>  该map 可被保存在注册信息，对微服务生态没

​	关系

​	根据自定义元数据完成灰度发布

### 	9.文件上传

​	zuul-upload

​	网关自己的上传controller，走的是mvc 

​	网关映射的路径的客户端，可以在网关选择走zuul servlet而不走网关的mvc 

​	只需要在代理路径前 加上zuul 当然可以解决字符乱码问题

### 	10.实用技巧

1. 饥饿加载 

   zuul 内部使用的是Ribbon调用远程服务，由于Ribbon的原因，初始化是懒加载。

   ```yml
   ribbon:
     eager-load:
       enabled: true
   ```

2. 请求体修改

   基于filter 实现

3. okhttp 替换httpClient

   apache 的httpClient 难以扩展等诸多原因，已被许多技术栈舍弃

   okhttp 优点：

   ​	支持SPDY，可以合并请求（同一个HOST）

   ​	链接复用机制

   ​	使用GZIP减少传输

   ​	对响应缓存，避免重复网络

   引入依赖：

   ​	 

   ```xml
   <dependency>
       <groupId>com.squareup.okhttp3</groupId>
       <artifactId>okhttp</artifactId>
   </dependency>
   ```

   

   ```yml
   ribbon:
     httpClient:
       enabled: false
     okhttp:
       enabled: true
   ```

4. 重试机制

   引入依赖：

   ```xml
   <!-- https://mvnrepository.com/artifact/org.springframework.retry/spring-retry -->
   <dependency>
       <groupId>org.springframework.retry</groupId>
       <artifactId>spring-retry</artifactId>
   </dependency>
   
   ```

   

   ```yml
   spring:
     cloud:
       loadbalancer:
         retry:
           enabled: true
   ribbon:
     ConnectTimeout: 3000
     ReadTimeout: 30000
     MaxAutoRetries: 1 #对第一次请求服务的重试次数
     MaxAutoRetriesNextServer: 1 #要重试的下一个服务对的最大数量（不包括第一个服务）
     OkToRetryOnAllOperations: true
   zuul:
     retryable: true
           
   ```

   zuul.route.xxxx.retryable= true 开启单个映射规则的重试

   

5. Header传递

   ​	继承ZuulFilter ，run方法里

   ```java
   RequestContext currentContext = RequestContext.getCurrentContext();
   currentContext.addZuulRequestHeader("name","vale");
   ```

6. 整合swagger2调试

   ```xml
   <dependency>
       <groupId>io.springfox</groupId>
       <artifactId>springfox-swagger-ui</artifactId>
       <version>2.7.0</version>
   </dependency>
   <dependency>
       <groupId>io.springfox</groupId>
       <artifactId>springfox-swagger2</artifactId>
       <version>2.7.0</version>
   </dependency>
   ```

   ```java
   @Configuration
   @EnableSwagger2
   public class SwaggerConfig {
   
   @Autowired
   ZuulProperties properties;
   
   @Primary
   @Bean
   public SwaggerResourcesProvider swaggerResourcesProvider() {
   return () -> {
   List<SwaggerResource> resources = new ArrayList<>();
   properties.getRoutes().values().stream()
   .forEach(route -> resources.add(createResource(route.getServiceId(), route.getServiceId(), "2.0")));
   return resources;
   };
   }
   
   private SwaggerResource createResource(String name, String location, String version) {
   SwaggerResource swaggerResource = new SwaggerResource();
   swaggerResource.setName(name);
   swaggerResource.setLocation("/" + location + "/v2/api-docs");
   swaggerResource.setSwaggerVersion(version);
   return swaggerResource;
   }
   
   }
   ```

### 	11.多层负载

​	当业务体量猛增之后，服务可以加节点，但是仅加服务不加网关会有性能瓶颈，单一的zuul处理能力有限，在扩展节点往往需要zuul一起扩张，然后再请求层加上一层负载，通常是Ngnix，但是ngnix与zuul并没有任何的关联性，如果服务宕掉，ngnix还会把请求分发过来

​	OpenResty整合了Ngnix与Lua，实现了可伸缩的Web平台，我们可以使用Lua脚本模块与注册中心构建一个服务动态增减的机制，通过Lua获取注册中心状态位UP的服务，动态加入到Ngnix的巨恒列表中去，由于郑重架构模式涉及的不止一个负载均衡器，我们称之为 多层负载

​	![](.\assets\多层负载.jpg)



### 	12.应用优化

​	1.概述

		1. 容器优化（tomcat 换成 Undertow）
		2. 组件优化 (Hystrix线程优化,Ribbon,HttpClient与OkHttp)
			1. zuul 默认集成 Hystrix，由于断路器懒加载，第一次请求很容易出问题解决： 加大触发断路器时间/禁止			掉断路器；
				hystrix的隔离策略，外网线程隔离，内网信号量隔离
			2. Ribbon 超时时间设置，设置太小导致请求失败，设置太大导致熔断变差
		3. JVM参数优化
			网关需要的是吞吐量。推介晒用Parallel Scavenge收集器，即并行的垃圾回收器
			-XX:+UseAdaptiveSizePolicy 打开，JVM会自动选择年轻代区的大小和响应的Survivor区比例
			但是作者测试的并不理想，建议关闭，改为-XX:-UseAdaptiveSizePolicy，并根据实际情况调整Eden区
			和Survivor区的比例。以降低FGC
			-XX:TargetSurvivorRatio,即Servivor区对象的利用率，默认是50%，建议加大，将FGC留给新生代
			老年代使用Parallel Old收集器，让网关应用彻底面向吞吐量 参数-XX:+ScavengeBeforeFullGC
			FGC前进行一次YGC
		4. 内部优化
			zuul.max.host.connections 属性拆成了
			zuul.host.maxTotalConnections =200 // 服务HTTP 客户端最大连接数
			zuul.host.maxPerRouteConnections =20  // 每个路由规则 HTTP客户端最大连接数
			使用httpClient是有效的，如果是OkHttp则无效
			使用serviceId映射走的时候 ribbon ribbon.ReadTimeout  ribbon.SocketTimeout生效
			视同url映射，应该设置zuul.hostconnect-timeout-millis与zuul.host.socket-timeout-millis参数


### 	13原理与源码解析	

​	生命周期:

​	![https://raw.githubusercontent.com/RyzeUserName/springcloud/master/assets/zuul生命周期.jpg?token=AiWh_0DUpU23a_y3Nacn0PhmmYLFzUzIks5crxVywA%3D%3D](https://raw.githubusercontent.com/RyzeUserName/springcloud/master/assets/zuul%E7%94%9F%E5%91%BD%E5%91%A8%E6%9C%9F.jpg?token=AiWh_0DUpU23a_y3Nacn0PhmmYLFzUzIks5crxVywA%3D%3D)

​	调用过程：

​		![](<https://raw.githubusercontent.com/RyzeUserName/springcloud/master/assets/zuul%E8%B0%83%E7%94%A8%E8%BF%87%E7%A8%8B.jpg?token=AiWh_ynNM8tJZ44zrAcRIMQrFstj2S2Qks5crx8QwA%3D%3D)

那么@EnableZuulProxy

​	@EnableZuulServer 区别：


![](<https://raw.githubusercontent.com/RyzeUserName/springcloud/master/assets/1555297176813.jpg>)

![](<https://raw.githubusercontent.com/RyzeUserName/springcloud/master/assets/1555297097839.jpg>)

ZuulProxy整合了断路器,从注释上看到ZuulProxy 加入了一些proxy filters

ZuulProxy 导入ZuulProxyMarkerConfiguration 类中并没有什么 link to ZuulProxyAutoConfiguration

ZuulServer 导入ZuulServerMarkerConfiguration 类中并无什么 link to ZuulServerAutoConfiguration

发现 ZuulProxyAutoConfiguration 继承 ZuulServerAutoConfiguration 

ZuulServerAutoConfiguration 的功能：

- 初始化配置加载器
- 初始化路由定位器
- 初始化路由映射器
- 初始化配置刷新监听器
- 初始化zuulServlet加载器
- 初始化zuulController
- 初始化Filter执行解析器
- 初始化一些Filter
- 初始化Metrix监控

ZuulProxyAutoConfiguration 除了以上功能，还额外的功能：

- 初始化服务注册、发现监听器
- 初始化服务列表监听器
- 初始化Zuul自定义Endpoint
- 初始化一些ZuulServverAutoConfiguration中没有的Filter
- 引入HTTP客户端的两种方式：HttpClient与OkHttp

装在完成之后就成了完整的周期，下面具体实现：

Filter 装载

Filter链实现

核心路由实现

# 2.进阶实战

## 	1.Config

## 	2.consul

## 	3.全链路监控

## 	4.Gateway

​	

# 3.解决方案

## 	1.与gRpc

## 	2.版本控制与灰度发布

## 	3.容器化

## 	4.dubbo 迁移

## 	5.分布式事务

## 	6.领域驱动实践