package com.okunan.tunahan.retrofidmarvelornek;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

import retrofit2.Call;
import retrofit2.Response;


public class HeroFragment extends Fragment {

    List<Hero> mHeroList;
    RecyclerView mRecyclerView;

    public static HeroFragment newInstance() {
        HeroFragment heroFragment = new HeroFragment();
        return heroFragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_hero, container, false);
        mRecyclerView = (RecyclerView) view.findViewById(R.id.recycler_view);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));

        new FetchMarvel().execute("https://www.simplifiedcoding.net");
        new FetchItem().execute();
        return view;
    }

    public class HeroHolder extends RecyclerView.ViewHolder {
        TextView mNameTextView;
        ImageView mImageView;

        public HeroHolder(View itemView) {
            super(itemView);
            mNameTextView = (TextView) itemView.findViewById(R.id.item_name);
            mImageView = (ImageView) itemView.findViewById(R.id.image_view);
        }

        public void bindHero(Hero hero) {
            mNameTextView.setText(hero.getİmageurl());
            loadImageFromUrl(hero.getİmageurl().toString());
        }

        private void loadImageFromUrl(String url) {
            Picasso.with(getActivity()).load(url).placeholder(R.mipmap.ic_launcher)
                    .error(R.mipmap.ic_launcher)
                    .into(mImageView, new com.squareup.picasso.Callback() {
                        @Override
                        public void onSuccess() {

                        }

                        @Override
                        public void onError() {

                        }
                    });
        }

    }


    public class HeroAdapter extends RecyclerView.Adapter<HeroHolder> {

        List<Hero> mHeros;

        public HeroAdapter(List<Hero> heros) {
            this.mHeros = heros;
        }

        @Override
        public HeroHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View view = LayoutInflater.from(getActivity()).inflate(R.layout.item_recycler_view, parent, false);
            return new HeroHolder(view);
        }

        @Override
        public void onBindViewHolder(HeroHolder holder, int position) {
            Hero hero = mHeros.get(position);
            holder.bindHero(hero);
        }

        @Override
        public int getItemCount() {
            return mHeros.size();
        }
    }

    public class FetchItem extends AsyncTask<Void, Void, List<Hero>> {

        @Override
        protected List<Hero> doInBackground(Void... params) {
            Api api = Ws.getService();
            Call<List<Hero>> servicesCall = api.getHeroes();
            Response<List<Hero>> response = null;

            try {
                response = servicesCall.execute();
            } catch (IOException e) {
                Log.d("HeroFragment", "Hata" + e.getMessage());
                e.printStackTrace();
            }

            return response.body();
        }

        @Override
        protected void onPostExecute(List<Hero> heros) {
            super.onPostExecute(heros);
            mHeroList = heros;
            setUpdate();
        }
    }

    private void setUpdate() {
        if (isAdded()) {
            mRecyclerView.setAdapter(new HeroAdapter(mHeroList));
        }
    }


    public class FetchMarvel extends AsyncTask<String, Void, String> {

        String result = "";

        @Override
        protected String doInBackground(String... params) {
            try {
                result = new MarvelFetch().getUrlStrings(params[0]);
            } catch (IOException e) {
                e.printStackTrace();
            }
            return result;
        }

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);
            Toast.makeText(getActivity(), s, Toast.LENGTH_SHORT).show();

        }
    }

}
