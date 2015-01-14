package com.project.mobile_application.sixbowls;

import android.app.AlertDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;


/**
 * Created by MattiaBenzoni on 13/01/2015.
 */
public class Alert extends DialogFragment {

    @Override
    public Dialog onCreateDialog(Bundle saveInstanceState){

        AlertDialog.Builder builder= new AlertDialog.Builder(getActivity());
        LayoutInflater inflater=getActivity().getLayoutInflater();
        View v = inflater.inflate(R.layout.dialog_msg,null);
        builder.setMessage("Set name Player");
        //EditText name=(EditText)v.findViewById(R.id.namep);
        builder.setView(v);

        builder.setPositiveButton("OK",new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Toast.makeText(getActivity(),"tasto premuto",Toast.LENGTH_SHORT).show();
            }
        });

        Dialog dialog=builder.create();
        return dialog;
     }


}
