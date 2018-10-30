package com.dionisiofilho.dialog.custom

import android.annotation.SuppressLint
import android.app.Dialog
import android.content.Context
import android.support.annotation.ColorInt
import android.support.annotation.StringRes
import android.support.v4.content.ContextCompat
import android.view.View
import android.view.WindowManager
import android.widget.TextView
import com.dionisiofilho.dialog.R
import org.jetbrains.annotations.NotNull

class CustomDialog(ctx: Context) : View(ctx) {

    companion object {

        @SuppressLint("StaticFieldLeak")
        @JvmStatic
        private var customDialog: CustomDialog? = null

        fun Builder(ctx: Context): CustomDialog {
            if (customDialog == null) {
                customDialog = CustomDialog(ctx)
            }
            return customDialog as CustomDialog
        }
    }

    private var title: String = ""
    private var message: String = ""
    private var listenerCancel: (() -> Unit)? = null
    private var customDialogTypel: CustomDialogType = CustomDialogType.CONFIRMATION_ALERT

    private lateinit var view: View
    private lateinit var dialog: Dialog
    private lateinit var listenerConfirm: () -> Unit
    private lateinit var viewHolderDialog: ViewHolderDialog

    fun setTitle(title: String): CustomDialog {
        this.title = title
        return this
    }

    fun setTitle(@StringRes title: Int): CustomDialog {
        this.title = context.getString(title)
        return this
    }

    fun setMessage(message: String): CustomDialog {
        this.message = message
        return this
    }

    fun setMessage(@StringRes message: Int): CustomDialog {
        this.message = context.getString(message)
        return this
    }

    fun setDialogType(@NotNull customDialogType: CustomDialogType): CustomDialog {
        this.customDialogTypel = customDialogType
        return this
    }

    fun listenerConfirm(@NotNull lConfirm: () -> Unit): CustomDialog {
        this.listenerConfirm = lConfirm
        return this
    }

    fun listenerCancel(lCancel: () -> Unit): CustomDialog {
        this.listenerCancel = lCancel
        return this
    }

    fun build() {
        this.show()
    }

    private fun initDialog() {
        if (context != null) {
            view = inflate(context, R.layout.dialog_custom, null)
            viewHolderDialog = ViewHolderDialog(view)
            getTypeDialog()
        }
    }


    fun getTypeDialog() {

        when (this.customDialogTypel) {

            CustomDialogType.CONFIRMATION_ALERT -> {
                prepareDialog(ContextCompat.getColor(context, R.color.colorPrimary))

            }
            CustomDialogType.ERROR_ALERT -> {
                prepareDialog(ContextCompat.getColor(context, R.color.red_300))

            }
            CustomDialogType.INFORMATION_ALERT -> {
                prepareDialog(ContextCompat.getColor(context, R.color.colorAccent))

            }

        }

    }

    private fun prepareDialog(@ColorInt color: Int) {

        viewHolderDialog.title.setBackgroundColor(color)
        viewHolderDialog.title.text = title
        viewHolderDialog.message.text = message


        if (listenerCancel == null) {
            viewHolderDialog.btnCancel.visibility = View.GONE
        } else {
            viewHolderDialog.btnCancel.setOnClickListener {
                dialog.dismiss()
                listenerCancel?.let { function -> function.invoke() }?:run { return@setOnClickListener }
            }
        }

        viewHolderDialog.btn_confirm.setOnClickListener {
            dialog.dismiss()
            listenerConfirm()
        }
    }


    private fun show() {

        try {

            initDialog()

            dialog = Dialog(context)
            dialog.setCancelable(false)
            dialog.setContentView(view)

            val lp = WindowManager.LayoutParams()
            lp.copyFrom(dialog.window.attributes)
            lp.width = WindowManager.LayoutParams.MATCH_PARENT
            lp.height = WindowManager.LayoutParams.WRAP_CONTENT

            dialog.window.attributes = lp

            dialog.show()
        } catch (e: Exception) {
            throw  e
        }
    }


    open class ViewHolderDialog(view: View) {
        val title = view.findViewById(R.id.tv_dialog_title) as TextView
        val message = view.findViewById(R.id.tv_dialog_message) as TextView
        val btn_confirm = view.findViewById(R.id.btn_confim_dialog) as TextView
        val btnCancel = view.findViewById(R.id.btn_cancel_dialog) as TextView
    }


    enum class CustomDialogType {
        CONFIRMATION_ALERT,
        ERROR_ALERT,
        INFORMATION_ALERT,
    }
}