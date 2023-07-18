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
include("actuator")

include("kafka:instance1")
findProject(":kafka:instance1")?.name = "instance1"
include("kafka:instance2")
findProject(":kafka:instance2")?.name = "instance2"
