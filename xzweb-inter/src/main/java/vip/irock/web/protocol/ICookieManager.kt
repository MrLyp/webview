package vip.irock.web.protocol

interface ICookieManager {
    fun removeSessionCookie()

    fun removeAllCookie()

    fun flush()

    fun removeExpiredCookie()
}