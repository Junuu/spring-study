package com.example.study.domain

data class LoanProductStatusEntity(
    val id: Long,
    var status: LoanProductStatus,
) {
    // 발생할 수 있는 event들 정의

    private fun updateStatus(newStatus: LoanProductStatus){
        if(status.isTransferable(newStatus)){
            status = newStatus
        }
        throw IllegalStateException("$status 에서 $newStatus 로 변경될 수 없습니다. 변경가능한 상태를 확인해야 합니다.")
    }
    fun 사용자대출취소() {
        if(this.status.isUserCancelable.not()){
            throw IllegalArgumentException("현재 상태는 사용자가 대출을 취소할 수 없는 상태입니다.")
        }
        updateStatus(LoanProductStatus.사용자심사취소)
    }

    fun 심사통과() {
        val newStatus = when(this.status){
            LoanProductStatus.내부심사중 -> LoanProductStatus.내부심사통과
            LoanProductStatus.외부심사중 -> LoanProductStatus.외부심사통과
            else -> throw IllegalArgumentException("현재 상태는 사용자가 대출 심사에 통과할 수 없는 상태입니다.")
        }
        updateStatus(LoanProductStatus.사용자심사취소)
    }

    fun 사용자부결확인() {
        if(this.status.isUserCancelable.not()){
            throw IllegalArgumentException("현재 상태는 사용자가 대출을 취소할 수 없는 상태입니다.")
        }
        updateStatus(LoanProductStatus.사용자부결확인)
    }

}

