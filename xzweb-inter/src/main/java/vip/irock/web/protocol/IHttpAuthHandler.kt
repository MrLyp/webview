package vip.irock.web.protocol

interface IHttpAuthHandler {
    fun proceed(var1: String, var2: String)

    fun cancel()

    fun useHttpAuthUsernamePassword(): Boolean
}