package com.commonsware.cwac.richedit

import android.text.Editable
import android.text.SpannableStringBuilder
import android.text.TextWatcher
import com.commonsware.cwac.richtextutils.Selection


class TypingStyleTextWatcher(val richEditText: RichEditText) : TextWatcher {

    private var previousPos = 0
    private var charAdded = 0
    private var ignore = false
    var textModified = false

    override fun afterTextChanged(editable: Editable) {
        if (ignore) {
            return
        }
        ignore = true
        textModified = true
        val styleEnd = previousPos + charAdded
        richEditText.currentTypingEffects.forEach {
            it.applyToSpannable(editable, Selection(previousPos, styleEnd), it.usingStyle, true)
        }
    }

    override fun beforeTextChanged(p0: CharSequence?, start: Int, count: Int, after: Int) {

    }

    override fun onTextChanged(sequence: CharSequence, start: Int, before: Int, count: Int) {
        ignore = count < 0
        previousPos = start
        charAdded = count
    }
}