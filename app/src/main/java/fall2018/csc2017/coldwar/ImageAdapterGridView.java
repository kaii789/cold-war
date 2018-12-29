package fall2018.csc2017.coldwar;

import android.content.Context;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.view.View;
import android.widget.GridView;
import android.widget.ImageView;

import java.util.List;

public class ImageAdapterGridView extends BaseAdapter {
    private Context mContext;
    private List<Integer> imageIDs;

    public ImageAdapterGridView(Context c, List<Integer> imageIDs) {
        mContext = c;
        this.imageIDs = imageIDs;
    }

    public int getCount() {
        return imageIDs.size();
    }

    public Object getItem(int position) {
        return null;
    }

    public long getItemId(int position) {
        return 0;
    }

    public View getView(int position, View convertView, ViewGroup parent) {
        ImageView mImageView;

        if (convertView == null) {
            mImageView = new ImageView(mContext);
            mImageView.setLayoutParams(new GridView.LayoutParams(130, 130));
            mImageView.setScaleType(ImageView.ScaleType.CENTER_CROP);
            mImageView.setPadding(16, 16, 16, 16);
        } else {
            mImageView = (ImageView) convertView;
        }
        mImageView.setImageResource(imageIDs.get(position));
        return mImageView;
    }
}