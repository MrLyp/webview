package vip.irock.web.cache

import java.io.InputStream

interface WebResourceCache {

    fun get(url: String, header: Map<String, String>?): InputStream?
}