package vip.irock.web.protocol

import java.io.File
import java.io.InputStream

interface ICacheManager {

    fun getCacheFileBaseDir(): File

    fun cacheDisabled(): Boolean

    fun getCacheFile(var0: String, var1: Map<String, String>): Any

    fun getCacheFile(var0: String, var1: Boolean): InputStream
}