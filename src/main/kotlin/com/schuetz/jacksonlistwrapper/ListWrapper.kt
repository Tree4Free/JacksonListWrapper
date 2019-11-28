package com.schuetz.jacksonlistwrapper

/**
 * Wrapper object to wrap list type responses.
 *
 * Example:
 * ListWrapper("customValues", listOf("Hello", "World")
 *
 * When serializing this object the resulting Json looks like:
 * {
 *     "customValues": [
 *         "Hello", "World"
 *     ]
 * }
 */
class ListWrapper<T>(val propertyName: String, val list: List<T>)