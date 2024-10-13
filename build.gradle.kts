// Top-level build file where you can add configuration options common to all sub-projects/modules.
@Suppress("DSL_SCOPE_VIOLATION") // TODO: Remove once KTIJ-19369 is fixe
plugins {
    alias(libs.plugins.kotlin.android) apply false
    alias(libs.plugins.android.application) apply false
    kotlin("plugin.serialization") version "1.9.24" apply false
    id("com.google.dagger.hilt.android") version "2.49" apply false
}
true