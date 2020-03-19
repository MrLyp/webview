package vip.irock.web.protocol


interface IConsoleMessage {

    abstract fun messageLevel(): MessageLevel

    abstract fun message(): String

    abstract fun sourceId(): String

    abstract fun lineNumber(): Int

    enum class MessageLevel private constructor() {
        TIP,
        LOG,
        WARNING,
        ERROR,
        DEBUG
    }
}