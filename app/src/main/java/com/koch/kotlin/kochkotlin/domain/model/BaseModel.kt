package com.koch.kotlin.kochkotlin.domain.model

import io.objectbox.annotation.BaseEntity
import io.objectbox.annotation.Id

const val AUTO_ID = 0L

@BaseEntity
open class BaseModel constructor(
        @Id(assignable = true)
        var id: Long = AUTO_ID
)
