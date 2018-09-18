package com.example.pc.demoproject.adapter;

import android.content.Context;
import android.content.DialogInterface;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.pc.demoproject.DetailListActivity;
import com.example.pc.demoproject.R;
import com.example.pc.demoproject.database.DBHandler;
import com.example.pc.demoproject.database.modelClass.ClientDetailsModel;

import java.util.ArrayList;

public class DetailsListAdapter extends RecyclerView.Adapter<DetailsListAdapter.ViewHolder> {

    private Context context;
    private ArrayList<ClientDetailsModel> modelList;

    public DetailsListAdapter(Context context, ArrayList<ClientDetailsModel> modelList) {
        this.context = context;
        this.modelList = modelList;
    }

    @Override
    public DetailsListAdapter.ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {

        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.row_list, viewGroup, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(DetailsListAdapter.ViewHolder viewHolder, final int position) {
        viewHolder.cName.setText(modelList.get(position).getC_name());
        viewHolder.cMobile.setText(modelList.get(position).getC_mobile());
        viewHolder.cEmail.setText(modelList.get(position).getC_email());
        viewHolder.aName.setText(modelList.get(position).getA_name());
        viewHolder.aMobile.setText(modelList.get(position).getA_mobile());
        viewHolder.cAddress.setText(modelList.get(position).getC_address());
        viewHolder.cStatus.setText(modelList.get(position).getC_status());
        viewHolder.product.setText(modelList.get(position).getProduct_name());
        viewHolder.date.setText(modelList.get(position).getDate());
        viewHolder.referece.setText(modelList.get(position).getReference_name());

        viewHolder.mDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                AlertDialog alertDialog = new AlertDialog.Builder(context).create();
                alertDialog.setTitle("Delete");
                alertDialog.setMessage("Do you want to delete this item!!!");
                alertDialog.setButton(AlertDialog.BUTTON_POSITIVE, "OK",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int which) {

                                String id = modelList.get(position).getId();
                                modelList.remove(position);
                                notifyDataSetChanged();
                                DBHandler db = new DBHandler(context);
                                long result = db.deleteData(id);
                                dialog.dismiss();
                            }
                        });
                alertDialog.show();
            }
        });

    }

    @Override
    public int getItemCount() {
        return modelList.size();
    }


    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView cName;
        TextView cMobile;
        TextView cEmail;
        TextView aName;
        TextView aMobile;
        TextView cAddress;
        TextView cStatus;
        TextView product;
        TextView date;
        TextView referece;
        LinearLayout mDelete;

        public ViewHolder(View itemView) {
            super(itemView);

            cName = itemView.findViewById(R.id.cName);
            cMobile = itemView.findViewById(R.id.cMobile);
            cEmail = itemView.findViewById(R.id.cEmail);
            aName = itemView.findViewById(R.id.aName);
            aMobile = itemView.findViewById(R.id.aMobile);
            cAddress = itemView.findViewById(R.id.cAddress);
            cStatus = itemView.findViewById(R.id.cStatus);
            product = itemView.findViewById(R.id.product);
            date = itemView.findViewById(R.id.date);
            referece = itemView.findViewById(R.id.reference);
            mDelete = itemView.findViewById(R.id.delete);
        }
    }
}