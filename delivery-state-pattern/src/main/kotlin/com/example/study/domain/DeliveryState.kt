package com.example.study.domain

import java.lang.IllegalStateException

interface DeliveryState {
    fun cancel(delivery: Delivery)
    fun next(delivery: Delivery)
    fun refund(delivery: Delivery)
    fun getCurrentState(): DeliveryStatus
}


object WaitingState : DeliveryState{
    override fun cancel(delivery: Delivery) {
        throw IllegalStateException("관리자 확인전 상태에서는 이전으로 되돌아갈 수 없습니다.")
    }

    override fun next(delivery: Delivery) {
        println("WaitingState is ready for ReadyState.")
        delivery.state = ReadyState

    }

    override fun refund(delivery: Delivery) {
        println("WaitingState is ready for RefundState.")
        delivery.state = RefundState
    }

    override fun getCurrentState(): DeliveryStatus {
        return DeliveryStatus.WAITING
    }
}


object ReadyState : DeliveryState{
    override fun cancel(delivery: Delivery) {
        println("ReadyState is ready for WaitingState.")
        delivery.state = WaitingState
    }

    override fun next(delivery: Delivery) {
        if(delivery.invoiceCode.isNullOrBlank()){
         throw IllegalStateException("invoiceCode가 비어있다면 배송중 상태로 변경할 수 없습니다.")
        }
        println("ReadyState is ready for preparation.")
        delivery.state = DeliveringState
    }

    override fun refund(delivery: Delivery) {
        println("ReadyState is ready for RefundState.")
        delivery.state = RefundState

    }

    override fun getCurrentState(): DeliveryStatus {
        return DeliveryStatus.READY
    }
}

object DeliveringState : DeliveryState{
    override fun cancel(delivery: Delivery) {
        println("DeliveringState is ready for ReadyState.")
        delivery.invoiceCode = null
        delivery.state = ReadyState
    }

    override fun next(delivery: Delivery) {
        println("DeliveringState is ready for ReadyState.")
        delivery.state = CompleteState
    }

    override fun refund(delivery: Delivery) {
        println("DeliveringState is ready for RefundState.")
        delivery.state = RefundState
    }

    override fun getCurrentState(): DeliveryStatus {
        return DeliveryStatus.DELIVERING
    }
}

object CompleteState : DeliveryState{
    override fun cancel(delivery: Delivery) {
        println("CompleteState is ready for DeliveringState.")
        delivery.state = DeliveringState
    }

    override fun next(delivery: Delivery) {
        throw IllegalStateException("배송 완료 상태는 다음 프로세스가 존재하지 않습니다.")
    }

    override fun refund(delivery: Delivery) {
        println("CompleteState is ready for RefundState.")
        delivery.state = RefundState
    }

    override fun getCurrentState(): DeliveryStatus {
        return DeliveryStatus.COMPLETE
    }
}

object RefundState : DeliveryState{
    override fun cancel(delivery: Delivery) {
        println("RefundState is ready for WaitingState.")
        delivery.invoiceCode = null
        delivery.state = WaitingState
    }

    override fun next(delivery: Delivery) {
        throw IllegalStateException("환불 상태는 다음 프로세스가 존재하지 않습니다.")
    }

    override fun refund(delivery: Delivery) {
        throw IllegalStateException("환불 상태에서 또다시 환불을 진행할 수 없습니다.")
    }

    override fun getCurrentState(): DeliveryStatus {
        return DeliveryStatus.REFUND
    }
}
