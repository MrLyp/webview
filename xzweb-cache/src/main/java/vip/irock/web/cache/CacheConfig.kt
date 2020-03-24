package vip.irock.web.cache

import android.content.Context
import java.io.File

class CacheConfig(
    val cacheDir: String,
    val cacheSize: Long,
    private val cacheable: HashSet<String>
) {

    private constructor(builder: Builder) : this(
        builder.cacheDir,
        builder.cacheSize,
        builder.cacheable
    )

    fun canCache(file: String): Boolean {
        return cacheable.contains(file)
    }

    companion object {
        inline fun build(context: Context, block: Builder.() -> Unit) =
            Builder(context).apply(block).build()
    }

    class Builder(private val context: Context) {

        var cacheDir: String = "${context.cacheDir}${File.separator}cached_webview_force"

        var cacheSize: Long = 100 * 1024 * 1024

        //全局默认的
        val cacheable = HashSet<String>().apply {
            add("html")
            add("htm")
            add("js")
            add("ico")
            add("css")
            add("png")
            add("jpg")
            add("jpeg")
            add("gif")
            add("bmp")
            add("ttf")
            add("woff")
            add("woff2")
            add("otf")
            add("eot")
            add("svg")
            add("xml")
            add("swf")
            add("txt")
            add("text")
            add("conf")
            add("webp")
        }

        fun addCacheableFile(extension: String) {
            cacheable.add(extension)
        }

        fun removeCacheableFile(extension: String) {
            cacheable.remove(extension)
        }

        fun build() = CacheConfig(this)
    }
}