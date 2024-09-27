package com.example.study.domain

import com.example.study.controller.ProductContinuation

enum class LoanProductStatus(
    val productContinuation: ProductContinuation, // 사용자에게 어떤 화면을 노출해야할지를 담고있는 프로퍼티
    val isUserCancelable: Boolean = false, // 취소 가능여부를 담고 있는 프로퍼티
){
    내부심사중(productContinuation = ProductContinuation.심사_진행중, isUserCancelable = true){
        override val allowedNextStatus by lazy { setOf(내부심사부결,내부심사통과,사용자심사취소)}
    },
    내부심사통과(productContinuation = ProductContinuation.심사_진행중, isUserCancelable = true){
        override val allowedNextStatus by lazy { setOf(외부심사중,사용자심사취소)}
    },
    내부심사부결(productContinuation = ProductContinuation.심사_부결, isUserCancelable = true){
        override val allowedNextStatus by lazy { setOf(사용자부결확인)}
    },
    사용자심사취소(productContinuation = ProductContinuation.종료, isUserCancelable = false){
        override val allowedNextStatus by lazy { END_STATUS }
    },
    사용자부결확인(productContinuation = ProductContinuation.종료, isUserCancelable = false){
        override val allowedNextStatus by lazy { END_STATUS}
    },
    외부심사중(productContinuation = ProductContinuation.심사_진행중, isUserCancelable = false) {
        override val allowedNextStatus by lazy { setOf(외부심사통과)}
    },
    외부심사통과(productContinuation = ProductContinuation.약정_가능,isUserCancelable = false) {
        override val allowedNextStatus by lazy { setOf(대출실행)}
    },
    대출실행(productContinuation = ProductContinuation.대출실행완료) {
        override val allowedNextStatus by lazy { END_STATUS }
    };

    // by lazy를 활용하면 맨 처음 변수에 접근할 때 초기화가 이루어진다.
    // 대안으로 init을 활용하여 변경 가능한 상태를 정의하는 방법이 존재하지만 상태와 변경가능한 코드가 분리되기 때문에 응집도가 떨어지고, 가독성이 나빠지게 된다.
    abstract val allowedNextStatus: Set<LoanProductStatus>

    fun isTransferable(to: LoanProductStatus): Boolean{
        return this.allowedNextStatus.contains(to)
    }

    companion object{
        val END_STATUS = emptySet<LoanProductStatus>()
    }
}
