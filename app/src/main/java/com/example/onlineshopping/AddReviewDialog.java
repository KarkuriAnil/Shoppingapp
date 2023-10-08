package com.example.onlineshopping;

import static com.example.onlineshopping.groceryActivity2.GROCERY_STORE_KEY;

import android.app.AlertDialog;
import android.app.Dialog;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;

import java.text.SimpleDateFormat;
import java.util.Calendar;

public class AddReviewDialog extends DialogFragment {
    public interface AddReview{
        void onAddReviewResult(Review review);
    }
    private AddReview addreview;
    private TextView name,warning;
    private EditText e11,e22;
    private Button addbtn;
    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        View view=getActivity().getLayoutInflater().inflate(R.layout.dialog_add_review,null);
        intilview(view);
        AlertDialog.Builder builder=new AlertDialog.Builder(getActivity())
                .setView(view);
        Bundle bundle=getArguments();
        if(null!=bundle)
        {
            GroceryItem item=bundle.getParcelable(GROCERY_STORE_KEY);
            if(null!=item)
            {
               name.setText(item.getName());
               addbtn.setOnClickListener(new View.OnClickListener() {
                   @Override
                   public void onClick(View v) {
                       String username=e11.getText().toString();
                       String comment=e22.getText().toString();
                       String date=getCurrentDate();
                       if(username.equals("")||comment.equals(""))
                       {
                           warning.setText("Fill the blanks");
                           warning.setVisibility(View.VISIBLE);
                       }
                       else {
                           warning.setVisibility(View.GONE);
                        //   Review review=new Review(item.getId(),username,comment,date);
                           try {
                          //     Review review=new Review(item.getId(),username,comment,date);
                               addreview=(AddReview) getActivity();
                               addreview.onAddReviewResult(new Review(item.getId(),username,comment,date));
                               dismiss();
                           }catch (ClassCastException e)
                           {
                               e.printStackTrace();
                               dismiss();
                           }
                       }
                   }
               });
            }}

        return   builder.create();
    }
    private String getCurrentDate()
    {
        Calendar calendar=Calendar.getInstance();
        SimpleDateFormat sdf=new SimpleDateFormat("MM-dd-yyyy");
        return sdf.format(calendar.getTime());
    }
    private void intilview(View view)
    {
        name=view.findViewById(R.id.t2);
        e11=view.findViewById(R.id.e1);
        e22=view.findViewById(R.id.e2);
        warning=view.findViewById(R.id.warning);
        addbtn=view.findViewById(R.id.addbtn);
    }
}
