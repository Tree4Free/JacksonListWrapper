package com.schuetz.jacksonlistwrapper.serialization

import com.fasterxml.jackson.core.JsonParser
import com.fasterxml.jackson.databind.BeanProperty
import com.fasterxml.jackson.databind.DeserializationContext
import com.fasterxml.jackson.databind.JavaType
import com.fasterxml.jackson.databind.JsonDeserializer
import com.fasterxml.jackson.databind.deser.ContextualDeserializer
import com.fasterxml.jackson.databind.deser.std.StdDeserializer
import com.fasterxml.jackson.databind.type.CollectionType
import com.fasterxml.jackson.databind.type.MapType
import com.schuetz.jacksonlistwrapper.ListWrapper

open class ListWrapperDeserializer : StdDeserializer<ListWrapper<*>>(ListWrapper::class.java), ContextualDeserializer {

    lateinit var objectType: JavaType
    lateinit var collectionType: CollectionType
    lateinit var mapType: MapType


    override fun deserialize(p: JsonParser, ctxt: DeserializationContext): ListWrapper<*> {
        val map = ctxt.readValue<Map<String, List<*>>>(p, mapType)

        return createListWrapperFromMapEntry(map.entries.first())
    }

    override fun createContextual(ctxt: DeserializationContext, property: BeanProperty?): JsonDeserializer<*> {
        this.objectType = ctxt.contextualType.bindings.getBoundType(0)
        this.collectionType = ctxt.typeFactory.constructCollectionType(List::class.java, objectType)
        val stringType = ctxt.typeFactory.constructType(String::class.java)
        this.mapType = ctxt.typeFactory.constructMapType(Map::class.java, stringType, collectionType)
        return this
    }

    private fun createListWrapperFromMapEntry(mapEntry: Map.Entry<String, List<*>>): ListWrapper<*> {
        val (propertyName, list) = mapEntry
        return ListWrapper(propertyName, list)
    }
}