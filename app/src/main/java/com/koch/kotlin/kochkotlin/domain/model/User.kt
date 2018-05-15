package com.koch.kotlin.kochkotlin.domain.model

import io.objectbox.annotation.Entity

const val PROFILE_ID = 1L
const val NO_FIRST_NAME = ""
const val NO_LAST_NAME = ""

@Entity
data class User constructor(
        var firstName: String = NO_FIRST_NAME,
        var lastName: String = NO_LAST_NAME
) : BaseModel(id = PROFILE_ID)

fun User?.isProfileCorrect()
        = !(this == null || firstName.isEmpty() || lastName.isEmpty())