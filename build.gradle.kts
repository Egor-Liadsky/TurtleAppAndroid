plugins {
    id("com.android.application").version(PluginVersion.androidApp).apply(false)
    id("org.jetbrains.kotlin.jvm").version(PluginVersion.kotlin).apply(false)
    id("com.android.library") version "8.0.0" apply false
    id("org.jetbrains.kotlin.android") version "1.9.10" apply false
    kotlin("plugin.serialization").version("1.8.0").apply(false)
}
