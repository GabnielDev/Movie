package com.example.movie.helper

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.content.res.Resources
import android.net.Uri
import android.widget.Toast
import androidx.paging.PagingSource
import com.example.movie.base.NetworkResult

fun showToast(
    context: Context?,
    message: String?,
    duration: Int = Toast.LENGTH_SHORT
) {
    Toast.makeText(context, message, duration).show()
}

fun gotoYoutube(
    context: Context?,
    key: String?
) {
    val url = "vnd.youtube:$key"
    val intent = Intent(Intent.ACTION_VIEW).apply {
        data = Uri.parse(url)
        addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
    }
    context?.startActivity(intent)
}

fun gotoWhatsApp(
    context: Context,
    text: String?
) {
    val intent = Intent(Intent.ACTION_SEND).apply {
        setPackage("com.whatsapp")
        type = "text/plain"
        putExtra(Intent.EXTRA_TEXT, text)
    }
    context.startActivity(intent)
//    else {
//        showSnackbarWithButton(
//            root,
//            activity.getString(string.notif_wa_not_installed),
//            activity.getString(string.notif_download),
//            Snackbar.LENGTH_LONG
//        ) {
//            getWhatsappFromPlayStore(activity)
//        }


}

fun buildYouTubeThumbnailURL(key: String): String {
    return "https://img.youtube.com/vi/$key/0.jpg"
}

inline infix fun <T, Value : Any> NetworkResult<T>.pagingSucceeded(
    predicate: (data: T) -> PagingSource.LoadResult<Int, Value>
): PagingSource.LoadResult<Int, Value> {
    return if (this is NetworkResult.Success && this.data != null) {
        predicate.invoke(this.data)
    } else {
        PagingSource.LoadResult.Error(Resources.NotFoundException())

    }
}