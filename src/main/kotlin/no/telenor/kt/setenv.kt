package no.telenor.kt

import sun.misc.Unsafe

private val UNSAFE = Unsafe::class.java.getDeclaredField("theUnsafe").let {
	it.isAccessible = true
	it.get(null) as Unsafe
}

private val env: MutableMap<String, String?> = System.getenv().let { unwritable ->
	@Suppress("UNCHECKED_CAST")
	UNSAFE.getObject(
		unwritable,
		UNSAFE.objectFieldOffset(unwritable::class.java.getDeclaredField("m"))
	) as MutableMap<String, String?>
}

fun setenv(vararg entries: Pair<String, String?>) {
	for (entry in entries) {
		env[entry.first] = entry.second
	}
}

fun setenv(name: String, value: String?) {
	env[name] = value
}

fun envSnapshot(): EnvironmentSnapshot {
	val snapshot = mutableMapOf<String, String?>()
	snapshot.putAll(env)
	return EnvironmentSnapshot(snapshot)
}

fun clearEnv() {
	env.clear()
}

fun restoreEnv(snapshot: EnvironmentSnapshot) {
	clearEnv()
	env.putAll(snapshot.environment)
}

typealias Environment = Map<String, String?>

class EnvironmentSnapshot internal constructor(internal val environment: Environment)
