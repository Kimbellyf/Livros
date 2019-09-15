package com.example.livros.View;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;

import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.Fragment;

import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;


//adaptado de Ricardo Lecheta

public class YesOrNoDialog extends DialogFragment {

    private static String messageNoDialogd;
    private Callback callback;


    public static void show(FragmentManager fm, String messageNoDialog, Callback callback) {
        messageNoDialogd = messageNoDialog;
        FragmentTransaction ft = fm.beginTransaction();
        Fragment prev = fm.findFragmentByTag("sim_ou_nao_dialog");
        if (prev != null) {
            ft.remove(prev);
        }
        ft.addToBackStack(null);
        YesOrNoDialog frag = new YesOrNoDialog();
        frag.callback = callback;
        frag.show(ft, "sim_ou_nao_dialog");
    }

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {

        DialogInterface.OnClickListener dialogClickListener = new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                switch (which) {
                    case DialogInterface.BUTTON_POSITIVE:
                        if (callback != null) {
                            callback.metodoSimAoDialog();
                        }
                        break;
                    case DialogInterface.BUTTON_NEGATIVE:
                        break;
                }
            }
        };
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setMessage(messageNoDialogd);
        builder.setPositiveButton("Sim", dialogClickListener);
        builder.setNegativeButton("Não", dialogClickListener);
        return builder.create();
    }

    public static interface Callback {
        public void metodoSimAoDialog();
    }
}