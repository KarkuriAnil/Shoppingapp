package com.example.onlineshopping;

import static com.example.onlineshopping.R.id.anil;

import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.DialogFragment;

import java.util.ArrayList;

public class AllcategoriesDialog extends DialogFragment {
    public interface GetCategory
    {
        void onGetCategoryResult(String category);
    }
    private GetCategory getCategory;
    public static final String ALL_CATEGORIES="categories";
    public static final String CALLING="calling";
    public static final String CATEGORY="category";
    //private ListView listView;
    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        View view=getActivity().getLayoutInflater().inflate(R.layout.dialog_all_categories,null);
        AlertDialog.Builder builder=new AlertDialog.Builder(getActivity())
                .setView(view);
        Bundle bundle=getArguments();
        if(null!=bundle)
        {
           final String calling=bundle.getString(CALLING);
             final ArrayList<String> categories=bundle.getStringArrayList(ALL_CATEGORIES);
            if(null!=categories)
            {
             ListView listView;
                listView = view.findViewById(anil);
                ArrayAdapter<String> adapter=new ArrayAdapter<String>(getActivity(), android.R.layout.simple_list_item_1,categories);
                listView.setAdapter(adapter);
                listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                        switch (calling)
                        {
                            case "main_Activity":
                                Intent intent=new Intent(getActivity(),SearchActivity.class);
                                intent.putExtra(CATEGORY,categories.get(position));
                                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK|Intent.FLAG_ACTIVITY_NEW_TASK);
                                getActivity().startActivity(intent);
                                dismiss();
                                break;
                            case "search_Activity":
                                try {
                                    getCategory=(GetCategory) getActivity();
                                    getCategory.onGetCategoryResult(categories.get(position));
                                    dismiss();
                                }
                                catch (ClassCastException e)
                                {
                                    e.printStackTrace();
                                }
                                break;
                            default:
                                break;

                        }
                    }
                });
            }
        }
        return builder.create();
    }
}
