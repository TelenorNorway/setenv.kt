package no.telenor.kt.env

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

object Environment {
	fun set(vararg entries: Pair<String, String?>) {
		for (entry in entries) {
			env[entry.first] = entry.second
		}
	}

	fun set(name: String, value: String?) {
		env[name] = value
	}

	fun get(name: String): String? = System.getenv(name)

	fun snapshot(): EnvironmentSnapshot {
		val snapshot = mutableMapOf<String, String?>()
		snapshot.putAll(env)
		return EnvironmentSnapshot(snapshot)
	}

	fun clear(): EnvironmentSnapshot {
		val snapshot = snapshot()
		env.clear()
		return snapshot
	}

	fun restore(snapshot: EnvironmentSnapshot) {
		env.clear()
		env.putAll(snapshot.environment)
	}
}
