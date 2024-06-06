package com.example.study

import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id
import jakarta.persistence.Table

@Entity
@Table(name = "feature_flag")
data class FeatureFlag(
        @Id
        @GeneratedValue(strategy = GenerationType.AUTO)
        val id: Long = 0,
        @Column
        val keyName: String,
        @Column
        var enabled: Boolean,
){
        companion object{
                const val TEST_TOGGLE = "TEST_TOGGLE"
        }
}
