package com.schuetz.jacksonlistwrapper.serialization

import com.fasterxml.jackson.core.JsonGenerator
import com.fasterxml.jackson.databind.SerializerProvider
import com.fasterxml.jackson.databind.ser.std.StdSerializer
import com.schuetz.jacksonlistwrapper.ListWrapper

open class ListWrapperSerializer: StdSerializer<ListWrapper<*>>(ListWrapper::class.java) {
    override fun serialize(value: ListWrapper<*>, gen: JsonGenerator, provider: SerializerProvider) {
        gen.writeStartObject()
        gen.writeArrayFieldStart(value.propertyName)
        value.list.forEach {
            gen.writeObject(it)
        }
        gen.writeEndArray()
        gen.writeEndObject()
    }
}