技术栈
JDK 17、Spring Boot 3.2、MyBatis、MySQL、Redis、RocketMQ

快速启动
1. MySQL：创建数据库 `qzdemo`，执行 `src/main/resources/db/schema.sql`
2. Redis：本地默认启动 `127.0.0.1:6379`
3. RocketMQ：启动 NameServer 和 Broker
4. 配置：修改 `application.yml` 中的数据库密码
5. 运行：`mvn spring-boot:run`
6. 访问：`http://localhost:8080`

#接口列表
GET /api/products                           商品列表
POST /api/orders                             下单
GET /api/orders?userId=                     查订单
POST /api/orders/{orderNo}/pay?userId=       支付
GET /api/metrics/orders-created             MQ累计下单次数

