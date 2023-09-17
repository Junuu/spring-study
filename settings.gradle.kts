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
include("spring-security-method-role")

include("kafka:instance1")
findProject(":kafka:instance1")?.name = "instance1"
include("kafka:instance2")
findProject(":kafka:instance2")?.name = "instance2"