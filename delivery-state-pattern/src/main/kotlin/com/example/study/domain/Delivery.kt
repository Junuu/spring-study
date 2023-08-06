package com.example.study.domain

data class Delivery(
    var invoiceCode: String? = null,
    var state: DeliveryState,
){
    fun nextProcess(invoiceCode: String?= null){
        if(this.invoiceCode.isNullOrBlank()){
            this.invoiceCode = invoiceCode
        }
        state.next(this)
    }

    fun cancel(){
        state.cancel(this)
    }

    fun currentState(): DeliveryStatus{
        return state.getCurrentState()
    }

    fun refund() {
        state.refund(this)
    }
}

enum class DeliveryStatus{
    WAITING,
    READY,
    DELIVERING,
    COMPLETE,
    REFUND,
}
