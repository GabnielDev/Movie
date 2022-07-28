package com.example.movie.helper

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.net.Uri
import android.widget.Toast

fun showToast(
    context: Context?,
    message: String?,
    duration: Int = Toast.LENGTH_SHORT
) {
    Toast.makeText(context, message, duration).show()
}

//fun gotoYoutube(
//    activity: Activity,
//    key: String?
//) {
//    activity.startActivity(
//        Intent(
//            Intent.ACTION_VIEW,
//            Uri.parse("vnd:youtube:$key")
//        ).addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
//    )
//}

fun gotoYoutube(
    activity: Activity,
    key: String?
) {
    val url = "vnd.youtube:$key"
    val intent = Intent(Intent.ACTION_VIEW).apply {
        data = Uri.parse(url)
    }
    activity.startActivity(intent)
}

fun gotoWhatsApp(
    activity: Activity,
    text: String?
) {
    val intent = Intent(Intent.ACTION_SEND).apply {
        setPackage("com.whatsapp")
        type = "text/plain"
        putExtra(Intent.EXTRA_TEXT, text)
    }
    activity.startActivity(intent)
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