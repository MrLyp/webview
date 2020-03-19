package vip.irock.web.webkit

import android.webkit.ConsoleMessage
import vip.irock.web.protocol.IConsoleMessage

class WebkitConsoleMessage(private val message: ConsoleMessage):
    IConsoleMessage {
    override fun messageLevel(): IConsoleMessage.MessageLevel {
        return when(message.messageLevel()) {
            ConsoleMessage.MessageLevel.DEBUG ->
                IConsoleMessage.MessageLevel.DEBUG
            ConsoleMessage.MessageLevel.LOG ->
                IConsoleMessage.MessageLevel.LOG
            ConsoleMessage.MessageLevel.TIP ->
                IConsoleMessage.MessageLevel.TIP
            ConsoleMessage.MessageLevel.WARNING ->
                IConsoleMessage.MessageLevel.WARNING
            else ->
                IConsoleMessage.MessageLevel.ERROR
        }
    }

    override fun message(): String {
        return message.message()
    }

    override fun sourceId(): String {
        return message.sourceId()
    }

    override fun lineNumber(): Int {
        return message.lineNumber()
    }
}