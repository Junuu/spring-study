rootProject.name = "study"

include("core")
include("envers")
include("executor")
include("coupon-event-with-mysql")
include("x-forwarded-for-header")
include("kafka")
include("multiple-port")
include("event-listener")
include("jpa-transaction-all-using")
include("jpa-paging")
include("actuator")
include("transactional-and-database-connection")
include("transactional-rollback")
include("transactional-outer-call")
include("delivery-state-pattern")
include("json-serialize-deserialize")
include("rest-template-hands-on")
include("excel-hands-on")
include("spring-filter")
include("spring-batch")
include("spring-security-method-role")
include("redis")
include("logging")
include("deferred-join")
include("proxy-target-class")
include("id-generator-test")
include("server-sent-event")

include("kafka:instance1")
findProject(":kafka:instance1")?.name = "instance1"
include("kafka:instance2")
include("kafka:consumer-lag")
findProject(":kafka:consumer-lag")?.name = "consumer-lag"

include("gatling")
include("mvc-request-param")
include("feign-client")
include("csv-parse")
include("coroutines")
include("jpa-with-json")
include("mongodb-crud-tutorial")
include("hello-big-decimal")
include("monitoring-system-prometheus-grafana")

include("network-timeout:client")
findProject(":network-timeout:client")?.name = "client"
include("network-timeout:server")
findProject(":network-timeout:server")?.name = "server"


include("spring-cloud-config-server")
include("spring-cloud-config-client")

include("mongodb-concurrency-tutorial")
include("kotlin-charset-reflection-annotation")
include("multiple-request-in-single-endpoint")
include("full-text-half-text-tutorial")
include("design-pattern")
include("kotlin-practice")
include("logback")

include("feature-flag")
include("spin-lock")
include("spring-bean-qualifier")
include("hibernate-flush")
include("java-and-spring-bean-name-rule")
