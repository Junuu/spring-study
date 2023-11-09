package com.example.study

class SynchronizedCounter {

    private var count = 0

    @Synchronized
    fun increaseCount(){
        count++
    }

    fun getCurrentCount(): Int{
        return count
    }
}
