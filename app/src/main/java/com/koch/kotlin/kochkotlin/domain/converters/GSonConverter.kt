package com.koch.kotlin.kochkotlin.domain.converters


import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import io.objectbox.converter.PropertyConverter

private typealias Target = List<String>

class GSonConverter : PropertyConverter<Target, String> {
    companion object {
        private val converter = Gson()
        private val type_token = object: TypeToken<Target>(){}.type
    }

    override fun convertToEntityProperty(db_value: String)= with(converter){
        fromJson<Target>(db_value, type_token) ?: emptyList()
    }

    override fun convertToDatabaseValue(property: Target?) = with(converter){
        property?.let { toJson(it) } ?: "[]"
    }
}
