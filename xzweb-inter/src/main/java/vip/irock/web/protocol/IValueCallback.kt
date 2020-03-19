package vip.irock.web.protocol

interface IValueCallback<T> {
    fun onReceiveValue(value: T)
}