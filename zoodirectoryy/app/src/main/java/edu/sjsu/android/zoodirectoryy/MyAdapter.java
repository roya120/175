package edu.sjsu.android.zoodirectoryy;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;


import androidx.recyclerview.widget.RecyclerView;

import java.util.List;


public class MyAdapter extends RecyclerView.Adapter<MyAdapter.ViewHolder> {

    private List<int[]> lists;
    private Context context;


    public MyAdapter(List<int[]> data, Context context) {
        lists = data;
        this.context = context;
    }


    public void remove(int position) {
        lists.remove(position);
        notifyItemRemoved(position);
    }

    public void add(int position, int[] item) {
        lists.add(position, item);
        notifyItemInserted(position);
    }

    @Override
    public MyAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.layout, parent, false);

        ViewHolder viewholder = new ViewHolder(view);
        return viewholder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, final int position) {

        final int[] data = lists.get(position);

        holder.Text.setText(data[0]);
        holder.Image.setImageResource(data[2]);

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(final View view) {
                final Intent intent = new Intent(view.getContext(), AnimalDetailActivity.class);
                if (position == getItemCount() - 1) {
                    AlertDialog.Builder builder = new AlertDialog.Builder(context);
                    builder.setTitle("Dangerous Animal");
                    builder.setCancelable(true);
                    builder.setMessage(R.string.warning)
                            .setPositiveButton(R.string.yes, new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog, int id) {
                                    intent.putExtra("title", data[0]);
                                    intent.putExtra("description", data[1]);
                                    intent.putExtra("image", data[2]);
                                    view.getContext().startActivity(intent);
                                }
                            })
                            .setNegativeButton(R.string.no, new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog, int id) {
                                    dialog.cancel();
                                }
                            });
                    AlertDialog alert = builder.create();
                    alert.show();

                } else {
                    intent.putExtra("title", data[0]);
                    intent.putExtra("description", data[1]);
                    intent.putExtra("image", data[2]);
                    view.getContext().startActivity(intent);
                }
            }
        });
    }


    @Override
    public int getItemCount() {
        return lists.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public TextView Text;
        public ImageView Image;
        public View layout;

        public ViewHolder(View view) {
            super(view);
            layout = view;
            Text = (TextView) view.findViewById(R.id.textView);
            Image = (ImageView) view.findViewById(R.id.icon);
        }
    }
}
