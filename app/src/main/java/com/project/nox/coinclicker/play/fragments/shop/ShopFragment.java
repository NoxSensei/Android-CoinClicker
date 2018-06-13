package com.project.nox.coinclicker.play.fragments.shop;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.project.nox.coinclicker.R;
import com.project.nox.coinclicker.play.Data;

import java.util.ArrayList;
import java.util.List;

@SuppressLint("ValidFragment")
public class ShopFragment extends Fragment{

    private Data data;
    private List<Item> itemList;

    @SuppressLint("ValidFragment")
    public ShopFragment(Data data)
    {
        this.data = data;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstance)
    {
        return inflater.inflate(R.layout.fragment_shop, null);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        setItemList();

        RecyclerView recyclerView = getView().findViewById(R.id.recyclerView);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        ItemAdapter adapter = new ItemAdapter(getContext(), itemList, data);
        recyclerView.setAdapter(adapter);
    }

    void setItemList()
    {
        itemList = new ArrayList<>();

        itemList.add(new Item(
                1,
                "Serious Click",
                "Get +1 coin when clicking",
                1, 500,R.drawable.serious_finger
        ));

        itemList.add(new Item(
                2,
                "Greedy Piggy",
                "Produces 1 coin per second",
                0, 1000,R.drawable.greedy_piggy
        ));

        itemList.add(new Item(
                3,
                "Little Tommy",
                "Produces 10 coins per second",
                0, 5000,R.drawable.little_tommy
        ));

        itemList.add(new Item(
                4,
                "Business Pack",
                "Produces 50 coins per second",
                0, 20000, R.drawable.business_pack
        ));

        itemList.add(new Item(
                5,
                "Sly Mario",
                "Produces 200 coins per second",
                0, 50000, R.drawable.sly_mario
        ));
    }
}
