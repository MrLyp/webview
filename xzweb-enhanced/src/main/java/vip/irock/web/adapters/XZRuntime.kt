package vip.irock.web.adapters

object XZRuntime : IRuntime {

    private var mDelegate: IRuntime? = null

    fun init(runtime: IRuntime) {
        mDelegate = runtime
    }

    override fun isX5Enabled(): Boolean {
        return mDelegate?.isX5Enabled() ?: false
    }
}