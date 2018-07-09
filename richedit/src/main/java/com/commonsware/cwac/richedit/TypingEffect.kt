package com.commonsware.cwac.richedit

import android.text.Spannable
import com.commonsware.cwac.richtextutils.Selection


abstract class TypingEffect<T> : Effect<T>() {
    var usingStyle: Boolean = false
    var initialPosition: Int = 0
        protected set
    var lastPosition: Int = 0
        protected set

    fun toggleUsingStyle(editText: RichEditText) {
        usingStyle = !usingStyle
        if (usingStyle) {
            initialPosition = editText.selectionStart
        } else {
            lastPosition = editText.selectionStart
        }
    }
    abstract fun applyToSpannable(str: Spannable, selection: Selection, add: Boolean, addingChar: Boolean)
}