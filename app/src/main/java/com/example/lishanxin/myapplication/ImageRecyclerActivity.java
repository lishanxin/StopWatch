package com.example.lishanxin.myapplication;

import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.target.SimpleTarget;
import com.bumptech.glide.request.transition.Transition;

public class ImageRecyclerActivity extends AppCompatActivity {

    RecyclerView mRecycler;
    RecyclerView.Adapter<ViewHolder> mAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_image_recycler);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
        mRecycler = findViewById(R.id.rv_image);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        mRecycler.setLayoutManager(layoutManager);

        mAdapter = new Adapter<ViewHolder>(){

            @Override
            protected void bind(ViewHolder viewHolder, String s) {
                viewHolder.onBind(s);
            }

            @Override
            protected ViewHolder getViewHolder(View view) {
                return new ViewHolder(view);
            }
        };
        mRecycler.setAdapter(mAdapter);
    }


    private abstract class Adapter<T extends RecyclerView.ViewHolder> extends RecyclerView.Adapter<T>{

        String[] defaultData = new String[]{
                "https://goss4.vcg.com/creative/vcg/800/new/VCG211179149079.jpg",
                "https://goss2.vcg.com/creative/vcg/800/new/VCG41N887463778.jpg",
                "https://mdd.yqxsy.cn/static/uploads/images/20190201/15489533915cd3471d7c111af0bc1d6bd133e35a55.jpg",
                "https://mdd.yqxsy.cn/static/uploads/images/20190201/1548953392d93b6128989462046218f1e96ec44c53.jpg",
                "https://mdd.yqxsy.cn/static/uploads/images/20190201/1548953394e7d4c80449cfe24c593428a59227ade6.jpg",
                "https://mdd.yqxsy.cn/static/uploads/images/20190201/1548953396de2a543bdd9bcddd79e7581eaf7fa0da.jpg",
                "https://mdd.yqxsy.cn/static/uploads/images/20190201/1548953397d7d8ab67e5dc1be064679b36c6497d14.jpg",
                "https://mdd.yqxsy.cn/static/uploads/images/20190201/1548953399b3dd53a104c8b7df11ff881f643c4d32.jpg",
                "https://mdd.yqxsy.cn/static/uploads/images/20190201/154895340162a5fca82fc636bfb490f9bcf10a163a.jpg",
                "https://mdd.yqxsy.cn/static/uploads/images/20190201/15489534020f26e36b9fd17ba125f9ef0ac1bb0569.jpg",
                "https://mdd.yqxsy.cn/static/uploads/images/20190201/15489534043d3270cfec1a9b1672e988ed993aa9c1.jpg",
                "https://mdd.yqxsy.cn/static/uploads/images/20190201/15489534065df45d512a76c76331366873bef73143.jpg",
                "https://mdd.yqxsy.cn/static/uploads/images/20190201/15489534084cf17719ca478c3ce0423922e5473e0e.jpg",
                "https://mdd.yqxsy.cn/static/uploads/images/20190201/154895340978c8ea860432cae210432e3ae3cbf044.jpg",
                "https://mdd.yqxsy.cn/static/uploads/images/20190201/15489534118c8b6613c3d3adb951859099f804e55a.jpg",
                "https://mdd.yqxsy.cn/static/uploads/images/20190201/1548954711cec4772244399a9e88081413c02ea4f7.jpg",
                "https://mdd.yqxsy.cn/static/uploads/images/20190201/154895341373eff15d70fe9bc763bb5443d068bbd0.jpg",
                "https://mdd.yqxsy.cn/static/uploads/images/20190201/1548953415f0e1bba79105409c311eaa5e0c6c545d.jpg",
                "https://mdd.yqxsy.cn/static/uploads/images/20190201/1548953417a587b0c77263aeeec8368f9832bfe3c2.jpg",
                "https://mdd.yqxsy.cn/static/uploads/images/20190201/1548953418ec63f4c6c97ac1d5a9f015b35853f4ef.jpg",
                "https://mdd.yqxsy.cn/static/uploads/images/20190201/1548953420e72db032a72240c74ddd1c009a62306b.jpg",
                "https://mdd.yqxsy.cn/static/uploads/images/20190201/15489534217991b7e8535418727f60703beb3db7f6.jpg",
                "https://mdd.yqxsy.cn/static/uploads/images/20190201/154895342361b6e1af8dc5e11fe160ddda03810a3d.jpg",
                "https://mdd.yqxsy.cn/static/uploads/images/20190201/1548953425da3d2bbec2e1dbae1074f623d54de78c.jpg",
                "https://mdd.yqxsy.cn/static/uploads/images/20190201/1548953427c9f074e9a5382e4c4752e804f7b2f737.jpg",
                "https://mdd.yqxsy.cn/static/uploads/images/20190201/1548953429e602f9125651f54dcf0cf80fb31e8190.jpg",
                "https://mdd.yqxsy.cn/static/uploads/images/20190201/15489534324a7e1d5e1db9764daacc5558c7656583.jpg",
                "https://mdd.yqxsy.cn/static/uploads/images/20190201/1548953433b5d44734e91140e6d3fba3e2c72f140d.jpg",
                "https://mdd.yqxsy.cn/static/uploads/images/20190201/1548953434ee866fb88a31cd6c5bf6463adb09b295.jpg",
                "https://mdd.yqxsy.cn/static/uploads/images/20190201/1548953436387a66abd40a65f131708e9b7e052f8b.jpg"
        };

        String[] mtestData = new String[]{
                "https://goss4.vcg.com/creative/vcg/800/new/VCG211179149079.jpg",
                "https://goss2.vcg.com/creative/vcg/800/new/VCG41N887463778.jpg","https://goss4.vcg.com/creative/vcg/800/new/VCG211179149079.jpg",
                "https://goss2.vcg.com/creative/vcg/800/new/VCG41N887463778.jpg","https://goss4.vcg.com/creative/vcg/800/new/VCG211179149079.jpg",
                "https://goss2.vcg.com/creative/vcg/800/new/VCG41N887463778.jpg","https://goss4.vcg.com/creative/vcg/800/new/VCG211179149079.jpg",
                "https://goss2.vcg.com/creative/vcg/800/new/VCG41N887463778.jpg","https://goss4.vcg.com/creative/vcg/800/new/VCG211179149079.jpg",
                "https://goss2.vcg.com/creative/vcg/800/new/VCG41N887463778.jpg","https://goss4.vcg.com/creative/vcg/800/new/VCG211179149079.jpg",
                "https://goss2.vcg.com/creative/vcg/800/new/VCG41N887463778.jpg","https://goss4.vcg.com/creative/vcg/800/new/VCG211179149079.jpg",
                "https://goss2.vcg.com/creative/vcg/800/new/VCG41N887463778.jpg","https://goss4.vcg.com/creative/vcg/800/new/VCG211179149079.jpg",
                "https://goss2.vcg.com/creative/vcg/800/new/VCG41N887463778.jpg","https://goss4.vcg.com/creative/vcg/800/new/VCG211179149079.jpg",
                "https://goss2.vcg.com/creative/vcg/800/new/VCG41N887463778.jpg"
        };

        String[] mData = defaultData;

        public Adapter(String[] mData) {
            this.mData = mData;
        }

        public Adapter() {

        }

        @NonNull
        @Override
        public T onCreateViewHolder(@NonNull ViewGroup viewGroup, int viewType) {
            LayoutInflater layoutInflater = LayoutInflater.from(viewGroup.getContext());
            View view = layoutInflater.inflate(R.layout.cell_image, viewGroup, false);
            T viewHolder = getViewHolder(view);
            return viewHolder;
        }

        @Override
        public void onBindViewHolder(@NonNull T viewHolder, int position) {
            if (mData != null && mData.length > position){
                bind(viewHolder, mData[position]);
            }
        }

        protected abstract void bind(T viewHolder, String s);

        @Override
        public int getItemCount() {
            return mData.length;
        }

        protected abstract T getViewHolder(View view);
    }


    private class ViewHolder extends RecyclerView.ViewHolder{

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
        }

        public void onBind(String path){
            final ImageView view = itemView.findViewById(R.id.item_image);
            Glide.with(ImageRecyclerActivity.this).load(path).into(new SimpleTarget<Drawable>() {
                @Override
                public void onResourceReady(@NonNull Drawable resource, @Nullable Transition<? super Drawable> transition) {
                    view.setImageDrawable(resource);
                }
            });
        }
    }
}
