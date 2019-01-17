package com.albuquerque.poccustomview

import android.content.Context
import android.graphics.Color
import android.graphics.drawable.Drawable
import android.graphics.drawable.GradientDrawable
import android.support.v4.content.ContextCompat
import android.util.AttributeSet
import android.view.View
import android.widget.RelativeLayout
import kotlinx.android.synthetic.main.round_button.view.*


class RoundButton: RelativeLayout {

    constructor(context: Context) : this(context, null)

    constructor(context: Context, attrs: AttributeSet?) : super(context, attrs){
        init(context, attrs)
    }

    private fun init(context: Context, attrs: AttributeSet?){
        View.inflate(context, R.layout.round_button, this)

        val attributes = context.obtainStyledAttributes(attrs, R.styleable.RoundButton)
        val size = attributes?.getDimensionPixelSize(R.styleable.RoundButton_size, (25 * resources.displayMetrics.density).toInt() )
        val color = attributes?.getColor(R.styleable.RoundButton_color, ContextCompat.getColor(context, R.color.colorPrimary))
        val src = attributes?.getResourceId(R.styleable.RoundButton_src, 0)
        val txt = attributes?.getString(R.styleable.RoundButton_txt)

        // Configurando as exceptions
        if( !attributes.hasValue(R.styleable.RoundButton_src) && !attributes.hasValue(R.styleable.RoundButton_txt) ){
            throw RuntimeException(toString() + " é preciso ter o atributo srcButton ou textButton")
        }

        if( attributes.hasValue(R.styleable.RoundButton_src) && attributes.hasValue(R.styleable.RoundButton_txt) ){
            throw RuntimeException(toString() + " é preciso ter apenas atributo srcButton ou textButton")
        }

        attributes?.recycle()

        // Configurando o estilo
        size?.let {
            setButtonSize(it)
        }

        color?.let {
            setupButtonColor(it)
        }

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

    fun setImageResource(res: Int){
        icon.setImageResource(res)
    }

    fun getText(): String?{
        return if(icon != null) text.text.toString() else null
    }

    fun setText(txt: String){
        if(icon.drawable != null) return
        text.text = txt
    }

    fun setButtonSize(size: Int){
        (layout.layoutParams).height = size
        (layout.layoutParams).width = size

        (icon.layoutParams).height = (size * 0.7).toInt()
        (icon.layoutParams).width = (size * 0.7).toInt()

    }

    fun setupButtonColor(color: Int){
        (layout.background as GradientDrawable).setColor(color)
    }

    fun setupStroke(size: Int, color: Int){
        (layout.background as GradientDrawable).setStroke(size, color)
    }

}