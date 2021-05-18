package skfo763.ably.base.extension

import android.content.Context

fun Int.DP(context: Context): Float = (this * context.resources.displayMetrics.density)