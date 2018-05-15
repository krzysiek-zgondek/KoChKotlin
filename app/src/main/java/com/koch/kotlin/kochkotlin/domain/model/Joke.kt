package com.koch.kotlin.kochkotlin.domain.model

import com.koch.kotlin.kochkotlin.domain.converters.GSonConverter
import io.objectbox.annotation.Convert
import io.objectbox.annotation.Entity

const val EMPTY_JOKE = ""

@Entity
data class Joke constructor(
        var joke: String = EMPTY_JOKE,

        @Convert(converter = GSonConverter::class, dbType = String::class)
        var categories: List<String> = emptyList()
): BaseModel()