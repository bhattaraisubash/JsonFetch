package com.bhattaraisubash.jsonfetch.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.bhattaraisubash.jsonfetch.R;
import com.bhattaraisubash.jsonfetch.entity.Post;

import java.util.List;

/**
 * JsonFetch
 * Created on 2/14/2017.
 *
 * @author Subash Bhattarai
 */

public class ListAdapter extends ArrayAdapter<Post> {
    private Context context;

    public ListAdapter(Context context, List<Post> postList) {
        super(context, 0, postList);
        this.context = context;
    }

    @NonNull
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View view = LayoutInflater.from(context).inflate(R.layout.single_post, null);
        TextView id = (TextView) view.findViewById(R.id.id);
        TextView title = (TextView) view.findViewById(R.id.title);

        Post post = getItem(position);

        id.setText(String.valueOf(post.getId()));
        title.setText(post.getTitle());

        return view;
    }
}
