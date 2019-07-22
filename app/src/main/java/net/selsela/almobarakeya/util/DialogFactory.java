package net.selsela.almobarakeya.util;

import android.app.Activity;
import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.annotation.StringRes;
import android.support.v7.app.AlertDialog;

import com.afollestad.materialdialogs.DialogAction;
import com.afollestad.materialdialogs.MaterialDialog;
import net.selsela.almobarakeya.R;
import net.selsela.almobarakeya.ui.auoth.GuestScreenActivity;
import net.selsela.almobarakeya.ui.auoth.LoginActivity;
import com.tapadoo.alerter.Alerter;

public final class DialogFactory {

    public static Dialog createSimpleOkErrorDialog(Context context, String title, String message) {
        AlertDialog.Builder alertDialog = new AlertDialog.Builder(context)
                .setTitle(title)
                .setMessage(message)
                .setNeutralButton(R.string.dialog_action_ok, null);
        return alertDialog.create();
    }

    public static Dialog createSimpleOkErrorDialog(Context context,
                                                   @StringRes int titleResource,
                                                   @StringRes int messageResource) {

        return createSimpleOkErrorDialog(context,
                context.getString(titleResource),
                context.getString(messageResource));
    }

    public static Dialog createGenericErrorDialog(Context context, String message) {
        AlertDialog.Builder alertDialog = new AlertDialog.Builder(context)
                .setTitle(context.getString(R.string.dialog_error_title))
                .setMessage(message)
                .setNeutralButton(R.string.dialog_action_ok, null);
        return alertDialog.create();
    }

    public static Dialog createGenericErrorDialog(Context context, @StringRes int messageResource) {
        return createGenericErrorDialog(context, context.getString(messageResource));
    }

    public static ProgressDialog createProgressDialog(Context context, String message) {
        ProgressDialog progressDialog = new ProgressDialog(context);
        progressDialog.setMessage(message);
        return progressDialog;
    }

    public static ProgressDialog createProgressDialog(Context context,
                                                      @StringRes int messageResource) {
        return createProgressDialog(context, context.getString(messageResource));
    }

    public static void showAlertDialog(Activity activity, String message) {
        Alerter.create(activity)
                .setText(message)
                .setDuration(3000)
                .enableVibration(true)
                .setBackgroundColorRes(R.color.md_red_500)
                .show();
    }


    public static void showSuccsesDialog(Activity activity, String message) {
        Alerter.create(activity)
                .setText(message)
                .setDuration(3000)
                .enableVibration(true)
                .hideIcon()
                .setBackgroundColorRes(R.color.md_green_500)
                .show();
    }

    public static void showLoginDialog(final Context context, final String simpleName) {

        MaterialDialog materialDialog = new MaterialDialog.Builder(context)
                .title(R.string.login_label)
                .content(R.string.signin_confrimation)
                .positiveText(R.string.login_label)
                .onPositive(new MaterialDialog.SingleButtonCallback() {
                    @Override
                    public void onClick(@NonNull MaterialDialog dialog, @NonNull DialogAction which) {
                        Intent intent = new Intent(context, LoginActivity.class);
                        intent.putExtra(Const.TYPE, simpleName);
                        context.startActivity(intent);
                    }
                })
                .cancelListener(new DialogInterface.OnCancelListener() {
                    @Override
                    public void onCancel(DialogInterface dialog) {
                        dialog.dismiss();
                    }
                }).build();
        materialDialog.show();


    }

    public static void showLoginWithGuestOptionDialog(final Context context, final String simpleName) {
        MaterialDialog materialDialog = new MaterialDialog.Builder(context)
                .title(R.string.login_label)
                .content(R.string.signin_confrimation)
                .positiveText(R.string.login_label)
                .negativeText(R.string.title_activity_guest)
                .onPositive(new MaterialDialog.SingleButtonCallback() {
                    @Override
                    public void onClick(@NonNull MaterialDialog dialog, @NonNull DialogAction which) {
                        Intent intent = new Intent(context, LoginActivity.class);
                        intent.putExtra(Const.TYPE, simpleName);
                        context.startActivity(intent);
                    }
                })
                .onNegative(new MaterialDialog.SingleButtonCallback() {
                    @Override
                    public void onClick(@NonNull MaterialDialog dialog, @NonNull DialogAction which) {
                        Intent intent = new Intent(context, GuestScreenActivity.class);
                        intent.putExtra(Const.TYPE, simpleName);
                        context.startActivity(intent);
                    }
                })
                .cancelListener(new DialogInterface.OnCancelListener() {
                    @Override
                    public void onCancel(DialogInterface dialog) {
                        dialog.dismiss();
                    }
                }).build();
        materialDialog.show();
    }

}
