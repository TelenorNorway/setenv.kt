# SetEnv

A simple function for Kotlin that can be used in unit tests to set environment variables for the current instance of
Java.

Note that this only works for Java 17+

## Dependency

In your gradle file

_Follow [this guide](https://github.com/testersen/no.ghpkg) on how to set up your environment for GitHub packages._

```kt
plugins {
	id("no.ghpkg") version "0.3.3"
}

repositories {
	git.hub("telenornorway", "env.kt")
	// or <.. the below> if you're spicy 🌶️
	// git.hub("telenornorway")
}

dependencies {
	testImplementation("no.telenor.kt:setenv:<VERSION HERE>")
}
```

## Usage

```kt
import no.telenor.kt.setenv

setenv("my variable", "my value")

setenv(
	"my variable 1" to "my value 1",
	"my variable 2" to "my value 2",
)
```
