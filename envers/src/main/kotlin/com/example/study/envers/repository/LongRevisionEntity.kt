package com.example.study.envers.repository

import jakarta.persistence.*
import org.hibernate.envers.RevisionEntity
import org.hibernate.envers.RevisionNumber
import org.hibernate.envers.RevisionTimestamp
import java.io.Serializable
import java.text.DateFormat
import java.util.*

@Entity
@RevisionEntity
@Table(name = "rev_info")
open class LongRevisionEntity(

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @RevisionNumber
    @Column(name = "rev_id")
    var id: Long? = null,

    @RevisionTimestamp
    @Column(name = "rev_timestamp")
    var timestamp: Long? = null
) : Serializable {

    private fun getRevisionDate() = timestamp?.let { Date(it) }

    override fun toString(): String =
        "LongRevisionEntity(id = $id, revisionDate = ${DateFormat.getDateTimeInstance().format(getRevisionDate())}"
}
