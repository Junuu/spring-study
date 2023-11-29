package com.example.study

import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.withTimeout
import org.springframework.boot.ApplicationArguments
import org.springframework.boot.ApplicationRunner
import org.springframework.stereotype.Component

//@Component
class ResourceLeakTutorial: ApplicationRunner {
    override fun run(args: ApplicationArguments?) {
        runBlocking {
            repeat(10_000) { // Launch 10K coroutines
                launch {
                    var resource: Resource? = null // Not acquired yet
                    try {
                        withTimeout(60) { // Timeout of 60 ms
                            delay(50) // Delay for 50 ms
                            resource = Resource() // Store a resource to the variable if acquired
                        }
                        // We can do something else with the resource here
                    } finally {
                        resource?.close() // Release the resource if it was acquired
                    }
                }
            }
        }
    // Outside of runBlocking all coroutines have completed
        println(acquired) // Print the number of resources still acquired
    }
}

var acquired = 0

class Resource {
    init { acquired++ } // Acquire the resource
    fun close() { acquired-- } // Release the resource
}


