# MorseCodeKt
![Latest Version](https://img.shields.io/maven-central/v/com.jacobtread.morse/morse-code?label=LATEST%20VERSION&style=for-the-badge)
![License](https://img.shields.io/github/license/jacobtread/MorseCode?style=for-the-badge)
[![Gradle Build](https://img.shields.io/github/workflow/status/jacobtread/MorseCode/gradle-build?style=for-the-badge)](https://github.com/jacobtread/MorseCode/actions/workflows/gradle.yml)
![Total Lines](https://img.shields.io/tokei/lines/github/jacobtread/MorseCode?style=for-the-badge)

This is a morse code library for encoding and decoding morse code in kotlin


**Maven**:

```xml
<dependency>
    <groupId>com.jacobtread.morse</groupId>
    <artifactId>morse-code</artifactId>
    <version>{VERSION}</version>
</dependency>
```

**Groovy**:

```groovy

repositories {
    mavenCentral()
}

dependencies {
    implementation 'com.jacobtread.morse:morse-code:{VERSION}'
}
```

**Kotlin DSL**:

```kotlin
repositories {
    mavenCentral()
}

dependencies {
    implementation("com.jacobtread.morse:morse-code:{VERSION}")
}
```


## Example Usage

Encoding morse code 

```kotlin
import com.jacobtread.morse.MorseCode

val text = "Example Text"
val result = MorseCode.encodeText(text) 
```

Which produces 
```
. -..- .- -- .--. .-.. . / - . -..- -
```


Decoding morse code

```kotlin
import com.jacobtread.morse.MorseCode

val morseCode = ". -..- .- -- .--. .-.. . / - . -..- -"
val result = MorseCode.decodeText(morseCode) 
```

Which produces
```
EXAMPLE TEXT
```