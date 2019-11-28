# JacksonListWrapper

This is a module for jackson enabling serialization and deserialization of ListWrapper Instances.

## Example:
```kotlin
ListWrapper("customValues", listOf("Hello", "World")
```

When serializing this object the resulting Json looks like:
```JSON
{
  "customValues": [
    "Hello", 
    "World"
  ]
}
```

## Usage

To use the custom de-/serializer you need to register the Module with the jackson object mapper
```kotlin
val objectMapper = ObjectMapper()
// register other modules like the kotlin module...
objectMapper.registerModule(ListWrapperModule())
```
