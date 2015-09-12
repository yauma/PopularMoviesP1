package com.example.jaimequeralt.popularmovies;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

import static android.widget.ImageView.ScaleType.CENTER_CROP;

/**
 * Created by jaimequeralt on 9/6/15.
 */
public class ImageAdapter extends BaseAdapter {

    private Context mContext;
    private ArrayList<String> listPostersPaths;

    public ImageAdapter(Context c, ArrayList<String> listPostersPaths) {
        mContext = c;
        this.listPostersPaths = listPostersPaths;
    }

    @Override
    public int getCount() {
        return listPostersPaths.size();
    }

    @Override
    public Object getItem(int i) {
        return null;
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        SquaredImageView view = (SquaredImageView) convertView;
        if (view == null) {
            view = new SquaredImageView(mContext);
            //view.setScaleType(CENTER_CROP);

        }
        String url = "http://image.tmdb.org/t/p/w342/"+listPostersPaths.get(position);
        // Trigger the download of the URL asynchronously into the image view.
        Picasso.with(mContext)
                .load(url)
                .resize(300, 300)
                .into(view);

        return view;
    }
}

/**
 * An image view which always remains square with respect to its width.
 */
final class SquaredImageView extends ImageView {
    public SquaredImageView(Context context) {
        super(context);
    }

    public SquaredImageView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        setMeasuredDimension(getMeasuredWidth(), getMeasuredWidth());
    }
}

