package com.project.nox.coinclicker.play.fragments.shop;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.project.nox.coinclicker.R;
import com.project.nox.coinclicker.play.Data;

import java.util.List;

public class ItemAdapter extends RecyclerView.Adapter<ItemAdapter.ItemViewHolder> {

    private Context mCtx;

    private List<Item> itemList;

    private Data data;

    public ItemAdapter(Context mCtx, List<Item> itemList, Data data) {
        this.mCtx = mCtx;
        this.itemList = itemList;
        this.data= data;
    }

    @Override
    public ItemViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mCtx).inflate(R.layout.item_list,parent, false);
        return new ItemViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ItemViewHolder holder, final int position) {
        final Item item = itemList.get(position);

        holder.textViewTitle.setText(item.getTitle());
        holder.textViewShortDesc.setText(item.getShortdesc());

        //<editor-fold desc="switch (item.getId())">
        switch (item.getId())
        {
            case 1:
            {
                holder.textViewCount.setText(String.valueOf(("Count: "+data.getSeriousClickNum())));
                holder.textViewPrice.setText(String.valueOf(item.getPrice()*data.getSeriousClickNum()+"$"));
                break;
            }
            case 2:
            {
                holder.textViewCount.setText(String.valueOf("Count: "+data.getGreedyPiggyNum()));
                holder.textViewPrice.setText(String.valueOf(item.getPrice()*(data.getGreedyPiggyNum()+1)+"$"));
                break;
            }
            case 3:
            {
                holder.textViewCount.setText(String.valueOf("Count: "+data.getLittleTommyNum()));
                holder.textViewPrice.setText(String.valueOf(item.getPrice()*(data.getLittleTommyNum()+1)+"$"));
                break;
            }
            case 4:
            {
                holder.textViewCount.setText(String.valueOf("Count: "+data.getBusinessPackNum()));
                holder.textViewPrice.setText(String.valueOf(item.getPrice()*(data.getBusinessPackNum()+1)+"$"));
                break;
            }
            case 5:
            {
                holder.textViewCount.setText(String.valueOf("Count: "+data.getSlyMarioNum()));
                holder.textViewPrice.setText(String.valueOf(item.getPrice()*(data.getSlyMarioNum()+1)+"$"));
                break;
            }
        }
        //</editor-fold>

        holder.imageView.setImageDrawable(mCtx.getResources().getDrawable(item.getImage()));

        //<editor-fold desc="setOnClickListener(new View.OnClickListener){">
        holder.relative_layout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                switch (item.getId())
                {
                    case 1: {
                        if (data.getCoinsValue() >= item.getPrice() * data.getSeriousClickNum()) {
                            data.setCoinsValue(data.getCoinsValue() - item.getPrice() * data.getSeriousClickNum());
                            data.setSeriousClickNum(data.getSeriousClickNum() + 1);

                            Toast.makeText(mCtx, R.string.serious_click_bought, Toast.LENGTH_SHORT).show();
                        } else {
                            Toast.makeText(mCtx, R.string.not_enough_money, Toast.LENGTH_SHORT).show();
                        }
                        break;
                    }
                    case 2:{
                        if (data.getCoinsValue() >= item.getPrice() * (data.getGreedyPiggyNum()+1)) {
                            data.setCoinsValue(data.getCoinsValue() - item.getPrice() * (data.getGreedyPiggyNum()+1));
                            data.setGreedyPiggyNum(data.getGreedyPiggyNum() + 1);

                            Toast.makeText(mCtx, R.string.greedy_piggy_bought, Toast.LENGTH_SHORT).show();
                        } else {
                            Toast.makeText(mCtx, R.string.not_enough_money, Toast.LENGTH_SHORT).show();
                        }
                        break;
                    }
                    case 3:{
                        if (data.getCoinsValue() >= item.getPrice() * (data.getLittleTommyNum()+1)) {
                            data.setCoinsValue(data.getCoinsValue() - item.getPrice() * (data.getLittleTommyNum()+1));
                            data.setLittleTommyNum(data.getLittleTommyNum() + 1);

                            Toast.makeText(mCtx, R.string.little_tommy_hired, Toast.LENGTH_SHORT).show();
                        } else {
                            Toast.makeText(mCtx, R.string.not_enough_money, Toast.LENGTH_SHORT).show();
                        }
                        break;
                    }
                    case 4:{
                        if (data.getCoinsValue() >= item.getPrice() * (data.getBusinessPackNum()+1)) {
                            data.setCoinsValue(data.getCoinsValue() - item.getPrice() * (data.getBusinessPackNum()+1));
                            data.setBusinessPackNum(data.getBusinessPackNum() + 1);

                            Toast.makeText(mCtx, R.string.business_pack_captured, Toast.LENGTH_SHORT).show();
                        } else {
                            Toast.makeText(mCtx, R.string.not_enough_money, Toast.LENGTH_SHORT).show();
                        }
                        break;
                    }
                    case 5:{
                        if (data.getCoinsValue() >= item.getPrice() * (data.getSlyMarioNum()+1)) {
                            data.setCoinsValue(data.getCoinsValue() - item.getPrice() * (data.getSlyMarioNum()+1));
                            data.setSlyMarioNum(data.getSlyMarioNum() + 1);

                            Toast.makeText(mCtx, R.string.sly_mario_captured, Toast.LENGTH_SHORT).show();
                        } else {
                            Toast.makeText(mCtx, R.string.not_enough_money, Toast.LENGTH_SHORT).show();
                        }
                        break;
                    }
                }

                notifyItemChanged(position);
            }
        });
        //</editor-fold>
    }


    @Override
    public int getItemCount() {
        return itemList.size();
    }

    View.OnClickListener mClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            ItemViewHolder holder = (ItemViewHolder) view.getTag();
            int position = holder.getAdapterPosition();
        }
    };

    class ItemViewHolder extends RecyclerView.ViewHolder {

        TextView textViewTitle, textViewShortDesc, textViewCount, textViewPrice;
        ImageView imageView;
        RelativeLayout relative_layout;

        public ItemViewHolder(View itemView) {
            super(itemView);

            textViewTitle = itemView.findViewById(R.id.textViewTitle);
            textViewShortDesc = itemView.findViewById(R.id.textViewShortDesc);
            textViewPrice = itemView.findViewById(R.id.textViewPrice);
            textViewCount = itemView.findViewById(R.id.textViewCount);
            imageView = itemView.findViewById(R.id.imageView);
            relative_layout = itemView.findViewById(R.id.relative_layout);
        }
    }
}
