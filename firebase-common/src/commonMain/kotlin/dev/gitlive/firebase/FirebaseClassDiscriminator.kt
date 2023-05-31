package dev.gitlivecgangaro.firebase

import kotlinx.serialization.InheritableSerialInfo

@InheritableSerialInfo
@Target(AnnotationTarget.CLASS)
annotation class FirebaseClassDiscriminator(val discriminator: String)