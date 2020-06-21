package functionAppApear

import android.app.Activity
import android.support.v4.content.ContextCompat
import android.view.WindowManager

class ChangeStatusBarColor{
    /**
     * Change status bar color
     */
    fun changeSatusColor(color: Int, context: Activity){
        val window = context.getWindow()

        // clear FLAG_TRANSLUCENT_STATUS flag:
        window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS)
        // add FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS flag to the window
        window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS)

        // finally change the color
        window.setStatusBarColor(ContextCompat.getColor(context, color))
    }
}