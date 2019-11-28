package com.schuetz.jacksonlistwrapper

import com.fasterxml.jackson.core.Version
import com.fasterxml.jackson.databind.JsonDeserializer
import com.fasterxml.jackson.databind.module.SimpleModule
import com.schuetz.jacksonlistwrapper.serialization.ListWrapperDeserializer
import com.schuetz.jacksonlistwrapper.serialization.ListWrapperSerializer

/**
 * Module that adds a custom de-/serializer for [com.schuetz.jacksonlistwrapper.ListWrapper]
 */
class ListWrapperModule: SimpleModule(
    "jacksonlistwrapper",
    Version(1,0,0,null, "com.schuetz","jacksonlistwrapper"),
    mutableMapOf(ListWrapper::class.java to ListWrapperDeserializer() as JsonDeserializer<*>),
    listOf(ListWrapperSerializer())
)