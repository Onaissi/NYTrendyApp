package com.onaissi.nytrendy.models;

import android.content.Context;
import android.content.DialogInterface;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;

import com.android.volley.VolleyError;
import com.onaissi.nytrendy.R;
import com.onaissi.nytrendy.callbacks.GeneralCallback;

abstract class Reposotory {

    private @NonNull Context context;
    private @Nullable AlertDialog alertDialog;

    public Reposotory(@NonNull Context context) {
        this.context = context;
        this.alertDialog = this.buildProgressDialog(context);
    }


    private AlertDialog buildProgressDialog(Context context) {
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        LayoutInflater inflate = LayoutInflater.from(context);
        builder.setCancelable(false);
        builder.setView(inflate.inflate(R.layout.progress_layout, null));
        return builder.create();
    }

    public void showProgressDialog(){
        if (alertDialog != null && !alertDialog.isShowing()){
            alertDialog.show();
        }
    }

    public void hideProgressDialog(){
        if (alertDialog != null && alertDialog.isShowing()) {
            alertDialog.dismiss();
        }
    }

    public void processFailBlock(VolleyError error, @Nullable final GeneralCallback callback) {
        hideProgressDialog();
        AlertDialog alert = new AlertDialog.Builder(context)
                .setTitle(context.  getString(R.string.common_error))
                .setMessage(context.getString(R.string.common_conection_error))
                .setCancelable(false)
                .setPositiveButton(context.getString(android.R.string.ok), new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                }).setNeutralButton(context.getString(R.string.common_retry), new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        if (callback != null){
                            callback.handler();
                        }
                    }
                }).create();
        alert.show();
    }
}
