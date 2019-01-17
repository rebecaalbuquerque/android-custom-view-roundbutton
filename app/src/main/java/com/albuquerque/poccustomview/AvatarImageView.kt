package com.albuquerque.poccustomview

import android.content.Context
import android.graphics.Color
import android.support.constraint.ConstraintLayout
import android.support.v4.content.ContextCompat
import android.util.AttributeSet
import android.view.View
import kotlinx.android.synthetic.main.avatar_image_view.view.*

class AvatarImageView: ConstraintLayout {

    constructor(context: Context?) : this(context, null)

    constructor(context: Context?, attrs: AttributeSet?) : this(context, attrs, 0)

    constructor(context: Context?, attrs: AttributeSet?, defStyleAttr: Int) : super(context, attrs, defStyleAttr){
        init(context, attrs, defStyleAttr)
    }

    private fun init(context: Context?, attrs: AttributeSet?, defStyleAttr: Int){
        View.inflate(context, R.layout.avatar_image_view, this)

        val attributes = context?.obtainStyledAttributes(attrs, R.styleable.AvatarImageView)

        val avatarSize = attributes?.getDimensionPixelSize(R.styleable.AvatarImageView_avatarSize, (100 * resources.displayMetrics.density).toInt() )
        val avatarSrc = attributes?.getResourceId(R.styleable.AvatarImageView_avatarSrc, 0)
        val buttonPosition = attributes?.getInt(R.styleable.AvatarImageView_buttonPosition, 8)
        val buttonSize = attributes?.getDimensionPixelSize(R.styleable.AvatarImageView_buttonSize, 0 )
        val buttonColor = attributes?.getColor(R.styleable.AvatarImageView_buttonColor, ContextCompat.getColor(context, R.color.colorPrimary))
        val buttonSrc = attributes?.getResourceId(R.styleable.AvatarImageView_buttonSrc, 0)
        val hasBorder = attributes?.getBoolean(R.styleable.AvatarImageView_hasBorder, false)

        avatarSrc?.let {
            avatar.setImageResource(it)
        }

        avatarSize?.let {
            avatar.layoutParams.height = it
            avatar.layoutParams.width = it

            (roundButton.layoutParams as ConstraintLayout.LayoutParams).circleRadius = (it/2)

        }

        buttonSize?.let {
            if(it != 0)
                roundButton.setButtonSize(it)
            else
                roundButton.setButtonSize( avatar.layoutParams.width/3 )
        }

        buttonPosition?.let {

            val rrLayoutParams = roundButton.layoutParams as ConstraintLayout.LayoutParams

            rrLayoutParams.circleAngle = when(it){
                1 -> 0.toFloat()
                2 -> 180.toFloat()
                3 -> 270.toFloat()
                4 -> 90.toFloat()
                5 -> 315.toFloat()
                6 -> 45.toFloat()
                7 -> 225.toFloat()
                else -> 135.toFloat()
            }

            rrLayoutParams.marginEnd = roundButton.layoutParams.width/2
            roundButton.layoutParams = rrLayoutParams

        }

        buttonColor?.let { roundButton.setupButtonColor(it) }

        buttonSrc?.let { roundButton.setImageResource(it) }

        hasBorder?.let {
            if(it){
                roundButton.setupStroke(6, Color.WHITE)
            }
        }

        attributes?.recycle()
    }

    fun hideRoundButton() { roundButton.visibility = GONE }

    fun getAvatar() = avatar

}