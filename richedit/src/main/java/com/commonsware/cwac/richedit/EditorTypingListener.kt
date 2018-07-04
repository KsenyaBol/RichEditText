package com.commonsware.cwac.richedit


interface EditorTypingListener {
    fun addTypingEffect(typingEffect: TypingEffect<Any>)
    fun removeTypingEffect(typingEffect: TypingEffect<Any>)
}