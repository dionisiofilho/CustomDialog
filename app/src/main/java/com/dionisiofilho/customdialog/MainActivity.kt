package com.dionisiofilho.customdialog

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import com.dionisiofilho.dialog.custom.CustomDialog

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        CustomDialog.Builder(this)
                .setTitle("Título")
                .setMessage("Mensagem")
                .setDialogType(CustomDialog.CustomDialogType.ERROR_ALERT)
                .listenerConfirm { }
                .listenerCancel { }
                .build()
    }
}
