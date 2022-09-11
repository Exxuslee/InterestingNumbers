package com.exxuslee.interestingnumbers.utils

import android.view.View
import androidx.lifecycle.LifecycleCoroutineScope
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.collect


inline fun <T : View> T.showIf(condition: (T) -> Boolean) {
    visibility = if (condition(this)) {
        View.VISIBLE
    } else {
        View.GONE
    }
}

fun <T> Flow<T>.launchWhenStarted(lifeCycleScope: LifecycleCoroutineScope) {
    lifeCycleScope.launchWhenStarted { this@launchWhenStarted.collect() }
}

