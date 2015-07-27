package com.movile.up.seriestracker.business.adapters.gridadapters;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.movile.up.seriestracker.R;
import com.movile.up.seriestracker.activities.ShowDetailsActivity;
import com.movile.up.seriestracker.util.ImageTypes;
import com.movile.up.seriestracker.util.InformationKeys;
import com.movile.up.seriestracker.interfaces.view.ShowElementClick;
import com.movile.up.seriestracker.model.models.Show;

import java.util.List;

/**
 * Created by android on 7/23/15.
 */
public class GridShowAdapter extends ArrayAdapter<Show> {

    private List<Show> showList;

    public GridShowAdapter(Context context, int resource) {
        super(context, resource);

    }

    @Override
    public int getCount() {
        return showList == null ? 0 : showList.size();
    }

    @Override
    public Show getItem(int position) {
        return showList.get(position);
    }

    public void updateGrid(List<Show> shows) {
        this.showList = shows;
        this.notifyDataSetChanged();

    }

    @Override
    public View getView(int position, View view, ViewGroup parent) {
        ViewHolder holder;

        if (view == null) {
            int resource = R.layout.show_image_layout;

            view = LayoutInflater.from(getContext())
                    .inflate(resource, parent, false);

            holder = new ViewHolder(view, getContext());
            view.setTag(holder);
        } else {
            holder = (ViewHolder) view.getTag();
        }
        populateViewFromHolder(holder, position);

        return view;
    }

    private void populateViewFromHolder(final ViewHolder holder, int position) {
            ImageView showImage = holder.getShowImage();
            final Show show = this.showList.get(position);

        Glide.with(getContext()).
                load(show.images().poster().get(ImageTypes.IMAGE_FULL)).
                centerCrop().
                into(showImage);

           holder.getRoot().setOnClickListener(new View.OnClickListener() {
               @Override
               public void onClick(View v) {
                   holder.onShowElementClick(show);
               }
           });


    }

    private static class ViewHolder implements ShowElementClick {

        private Context context;
        private ImageView showImage;
        private View root;
        public ViewHolder(View root, Context context) {
            this.context = context;
            this.showImage = (ImageView) root.findViewById(R.id.showThumb);
            this.root = root;
        }

        public Context getContext(){

            return  this.context;
        }

        public ImageView getShowImage(){

            return this.showImage;
        }

        public View getRoot(){
            return  this.root;
        }

        @Override
        public void onShowElementClick(Show show) {
            Intent intent = new Intent(this.context, ShowDetailsActivity.class);
            intent.putExtra(InformationKeys.SHOW_SLUG,show.ids().slug());
            intent.putExtra(InformationKeys.SHOW_TITLE,show.title());
            context.startActivity(intent);
        }
    }


}
