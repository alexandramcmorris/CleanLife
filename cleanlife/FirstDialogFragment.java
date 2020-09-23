package com.example.cleanlife;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.Fragment;

import android.provider.MediaStore;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toolbar;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;


public class FirstDialogFragment extends DialogFragment {

    View root;

    Button btnNext;

    RadioButton rdbtnb, rdbtni, rdbtna;
    RadioGroup radioGroup;

    private int btncheck = 0;   //moves users preference

    private MoveCheckedItem mCallBack;

    public interface MoveCheckedItem{
        void DifficultyChanged(int userchoice);
    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);

        if (context instanceof MoveCheckedItem)
        {
            mCallBack = (MoveCheckedItem) context;
        }
        else
        {
            throw new ClassCastException(context.toString() + "Must implement MoveCheckedItem");
        }

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        root = inflater.inflate(R.layout.fragment_first_dialog, container, false);



        btnNext = (Button)root.findViewById(R.id.btnFirstDialog);

        rdbtnb = (RadioButton)root.findViewById(R.id.rbtnBeginner);
        rdbtni = (RadioButton)root.findViewById(R.id.rbtnIntermediate);
        rdbtna = (RadioButton)root.findViewById(R.id.rbtnAdvanced);

        radioGroup = (RadioGroup)root.findViewById(R.id.radiopreference);

        ActionBar actionBar = ((AppCompatActivity)getActivity()).getSupportActionBar();

        if (actionBar != null)
        {
            actionBar.hide();
        }
        setHasOptionsMenu(false);

        btnNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (onRadioButtonClicked())
                {
                    // send the radio button to the next fragment
                    switch(radioGroup.getCheckedRadioButtonId()) {
                        case R.id.rbtnBeginner:
                            btncheck = 1;
                            break;
                        case R.id.rbtnIntermediate:
                            btncheck = 2;
                            break;
                        case R.id.rbtnAdvanced:
                            btncheck = 3;
                            break;
                    }





                    AppCompatActivity activity = (AppCompatActivity)view.getContext();

                    activity.getSupportFragmentManager()
                            .beginTransaction()
                            .add(android.R.id.content, new SecondDialogFragment())
                            .addToBackStack(null)
                            .commit();

                    dismiss();
                    Log.d("firstdialog", btncheck + " ");

                    mCallBack.DifficultyChanged(btncheck);



                }
                else
                {
                    Snackbar.make(view, "Must select a Difficulty", Snackbar.LENGTH_LONG)
                            .setAction("Action", null).show();
                }
            }
        });

        return root;
    }

    public boolean onRadioButtonClicked() {
        boolean checkedb = rdbtnb.isChecked();
        boolean checkedi = rdbtni.isChecked();
        boolean checkeda = rdbtna.isChecked();


        if (checkedb || checkedi || checkeda)
        {
            return true;
        }
        else
        {
            return false;
        }
    }


    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        Dialog dialog =  super.onCreateDialog(savedInstanceState);

        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        return dialog;
    }
}