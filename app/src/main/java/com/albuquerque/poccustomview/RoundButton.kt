package com.albuquerque.poccustomview

import android.content.Context
import android.graphics.drawable.Drawable
import android.support.constraint.ConstraintLayout
import android.support.v4.content.ContextCompat
import android.util.AttributeSet
import android.view.View
import kotlinx.android.synthetic.main.round_button.view.*

class RoundButton: ConstraintLayout {

    constructor(context: Context) : this(context, null)

    constructor(context: Context, attrs: AttributeSet?) : super(context, attrs){
        init(context, attrs)
    }

    private fun init(context: Context, attrs: AttributeSet?){
        View.inflate(context, R.layout.round_button, this)

        val attributes = context.obtainStyledAttributes(attrs, R.styleable.RoundButton)
        val size = attributes?.getDimensionPixelSize(R.styleable.RoundButton_sizeButton, (25 * resources.displayMetrics.density).toInt() )
        val color = attributes?.getColor(R.styleable.RoundButton_colorButton, ContextCompat.getColor(context, R.color.colorPrimary))
        val src = attributes?.getResourceId(R.styleable.RoundButton_srcButton, 0)
        val txt = attributes?.getString(R.styleable.RoundButton_textButton)

        // Configurando as exceptions
        if( !attributes.hasValue(R.styleable.RoundButton_srcButton) && !attributes.hasValue(R.styleable.RoundButton_textButton) ){
            throw RuntimeException(toString() + " é preciso ter o atributo srcButton ou textButton")
        }

        if( attributes.hasValue(R.styleable.RoundButton_srcButton) && attributes.hasValue(R.styleable.RoundButton_textButton) ){
            throw RuntimeException(toString() + " é preciso ter apenas atributo srcButton ou textButton")
        }

        attributes?.recycle()

        // Configurando o estilo
        size?.let {
            val cardButtonLayoutParams = layout.layoutParams
            val iconButtonLayoutParams = icon.layoutParams

            cardButtonLayoutParams.height = it
            cardButtonLayoutParams.width = it

            iconButtonLayoutParams.height = (it * 0.7).toInt()
            iconButtonLayoutParams.width = (it * 0.7).toInt()

            icon.layoutParams = iconButtonLayoutParams
        }

        color?.let { layout.background.setTint(it) }

        src?.let {
            icon.setImageResource(it)
        } ?: kotlin.run {
            icon.visibility = GONE
        }

        txt?.let {
            text.text = it
        } ?: kotlin.run {
            text.visibility = GONE
        }

    }

    fun getIcon(): Drawable?{
        return if(icon != null) icon.drawable else null
    }

    fun setIcon(drawable: Drawable?){
        if(drawable == null || text.text.isNotEmpty()) return
        icon.setImageDrawable(drawable)
    }

    fun getText(): String?{
        return if(icon != null) text.text.toString() else null
    }

    fun setText(txt: String){
        if(icon.drawable != null) return
        text.text = txt
    }

}