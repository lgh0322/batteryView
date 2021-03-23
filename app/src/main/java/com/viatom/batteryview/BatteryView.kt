package com.viatom.batteryview

import android.content.Context
import android.graphics.*
import android.util.AttributeSet
import android.view.View
import androidx.core.content.ContextCompat


class BatteryView : View {
    var canvas: Canvas? = null
    var batteryValue=100
    private var data: Int = 33
    private val wavePaint = Paint()

    constructor(context: Context?) : super(context) {
        init()
    }

    constructor(context: Context?, attrs: AttributeSet?) : super(context, attrs) {
        init()
    }

    constructor(context: Context?, attrs: AttributeSet?, defStyle: Int) : super(
        context,
        attrs,
        defStyle
    ) {
        init()
    }

    private fun init() {
        wavePaint.apply {
            color = getColor(R.color.battery_black)
            style = Paint.Style.FILL
        }

    }

    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)
        this.canvas = canvas
        drawWave(canvas)

    }


    private fun drawWave(canvas: Canvas) {
        canvas.drawColor(getColor(R.color.white))

        val xx = BitmapFactory.Options()
        xx.inScaled = false
        xx.inPreferredConfig = Bitmap.Config.ARGB_8888

        val mi = BitmapFactory.decodeResource(resources, R.drawable.home_power_icon, xx)


        canvas.drawBitmap(mi,Rect(0,0,48,24),Rect(0,0,width,height),wavePaint)

        if(batteryValue>100){
            data=33
        }
        if(batteryValue<0){
            data=0
        }else{
            data=batteryValue*33/100
        }
        data=data*width/48
        canvas.drawRect(Rect(5*width/48,5*height/24,5*width/48+data,19*height/24),wavePaint)
    }

    private fun getColor(resource_id: Int): Int {
        return ContextCompat.getColor(context, resource_id)
    }
}