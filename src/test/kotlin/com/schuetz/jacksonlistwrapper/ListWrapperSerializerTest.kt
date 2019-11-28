package com.schuetz.jacksonlistwrapper

import com.fasterxml.jackson.core.type.TypeReference
import com.fasterxml.jackson.module.kotlin.jacksonObjectMapper
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import java.util.*

internal class ListWrapperSerializerTest {
    class SampleClass(val test: String) {
        override fun equals(other: Any?): Boolean = when {
            other === this -> true
            other is SampleClass -> test == other.test
            else -> super.equals(other)
        }

        override fun hashCode(): Int = Objects.hash(test)
    }

    private val objectMapper = jacksonObjectMapper().registerModule(ListWrapperModule())

    @Test
    fun `Can serialize ListWrapper`() {
        val customListMapper = ListWrapper("customClasses", listOf(SampleClass("Hello"), SampleClass("World")))
        val resultString = objectMapper.writeValueAsString(customListMapper)

        val expectedJson = """{"customClasses":[{"test":"Hello"},{"test":"World"}]}"""
        assertThat(resultString).isEqualTo(expectedJson)
    }

    @Test
    fun `Can deserialize ListWrapper`() {
        val customListMapperJson = """{"customClasses":[{"test":"Hello"},{"test":"World"}]}"""
        val resultListWrapper = objectMapper.readValue(customListMapperJson, object : TypeReference<ListWrapper<SampleClass>>() {})

        assertThat(resultListWrapper.propertyName).isEqualTo("customClasses")
        assertThat(resultListWrapper.list).hasSize(2)
        assertThat(resultListWrapper.list).containsExactly(SampleClass("Hello"), SampleClass("World"))
    }
}