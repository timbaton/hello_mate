package com.example.kyrs.utils

import android.content.Context
import android.graphics.Color
import androidx.core.content.ContextCompat
import android.text.SpannableString
import android.text.Spanned
import android.text.TextPaint
import android.text.method.LinkMovementMethod
import android.text.style.ClickableSpan
import android.view.View
import android.widget.TextView
import android.widget.Toast

/**
 * Project Kyrs
 * Package com.example.kyrs.utils
 *
 *
 *
 * Created by Timur Badretdinov (aka timurbadretdinov) 2019-06-02
 * Copyright © 2018 SuperEgo. All rights reserved.
 */

fun Context.showToast(message: String, duration: Int = Toast.LENGTH_SHORT,
                      otherContext: Context? = null) {
    Toast.makeText(otherContext ?: this, message, duration).show()
}

fun TextView.setSpan(text: String, spannableText: String, spannableTextColor: Int,
                     isSpannableTextUnderline: Boolean, onClick: () -> Unit) {
    val context = this.context

    val ss = SpannableString(text)
    val clickableSpan = object : ClickableSpan() {

        override fun onClick(textView: View) {
            onClick()
        }

        override fun updateDrawState(ds: TextPaint) {
            super.updateDrawState(ds)
            ds.color = ContextCompat.getColor(context, spannableTextColor)
            ds.isUnderlineText = isSpannableTextUnderline
        }
    }

    //extracting needed fragment of text
    val startPos = text.indexOf(spannableText)
    val endPos = startPos + spannableText.length
    ss.setSpan(clickableSpan, startPos, endPos, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE)

    //setting spannable text
    this.text = ss
    this.movementMethod = LinkMovementMethod.getInstance()
    this.highlightColor = Color.TRANSPARENT
}