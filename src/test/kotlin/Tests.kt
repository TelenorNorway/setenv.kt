import no.telenor.kt.env.Environment
import kotlin.test.Test
import kotlin.test.assertEquals

class Tests {
	@Test
	fun `setenv(name, value)`() {
		val randomValue1 =
			Math.random().toString() + Math.random().toString() + Math.random().toString() + Math.random().toString()
		val randomValue2 =
			Math.random().toString() + Math.random().toString() + Math.random().toString() + Math.random().toString()
		Environment.set(randomValue1, randomValue2)
		assertEquals(System.getenv(randomValue1), randomValue2)
	}
}
