package mohammadsajjad.magikey.adapters;

import android.annotation.SuppressLint;
import android.content.Context;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.style.ForegroundColorSpan;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;
import java.util.ArrayList;
import mohammadsajjad.magikey.R;
import mohammadsajjad.magikey.model.Movies;

public class SearchResultAdapter extends ArrayAdapter {
    /**
     * Constructor
     *
     * @param context  The current context.
     * @param resource The resource ID for a layout file containing a TextView to use when
     */
    String searchingWords;
    Movies movies;
    Context context;


    public SearchResultAdapter(Context context, int resource, ArrayList<Movies> movies ,String searchingWords ) {
        super(context, resource,movies);
        this.searchingWords=searchingWords;
        this.context=context;

    }


    private static class ViewHolder {
        TextView tvName;
        TextView tvDescription;
        TextView tvSentence;
        TextView tvTime;
    }


    @SuppressLint("ResourceAsColor")
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        movies = (Movies) getItem(position);
        ViewHolder viewHolder;
        if (convertView==null) {
            viewHolder = new ViewHolder();
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.searched_items, parent, false);

            viewHolder.tvName = convertView.findViewById(R.id.tvName2);
            viewHolder.tvDescription = convertView.findViewById(R.id.tvDescription2);
            viewHolder.tvSentence = convertView.findViewById(R.id.tvSentence2);
            viewHolder.tvTime = convertView.findViewById(R.id.tvTime2);
            convertView.setTag(viewHolder);

        }
        else {
            viewHolder = (ViewHolder) convertView.getTag();

        }
        viewHolder.tvName.setText(movies.getName().replaceAll(".wav",""));
        if(movies.getDescription().equals("no season/episode")){
            viewHolder.tvDescription.setText("(بدون فصل و قسمت)");

        }
        else {
            viewHolder.tvDescription.setText(movies.getDescription());
        }









        String searched_words=searchingWords;
        if(searched_words!=null) {
            String input = movies.getSentence();
            if (input.contains(searched_words)) {
                String str = movies.getSentence();
                String substr = searched_words;
                String before = str.substring(0, str.indexOf(substr));
                String after = str.substring(str.indexOf(substr) + substr.length());
                SpannableString text = new SpannableString(str);

                text.setSpan(new ForegroundColorSpan(getContext().getResources().getColor(R.color.normal)), text.length() - after.length(), text.length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);

                text.setSpan(new ForegroundColorSpan(getContext().getResources().getColor(R.color.normal)), 0, before.length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
                viewHolder.tvSentence.setText(text);

            }
        }
        else {
            viewHolder.tvSentence.setTextColor(getContext().getResources().getColor(R.color.normal));
            viewHolder.tvSentence.setText(movies.getSentence());

        }




        viewHolder.tvTime.setText(movies.getTime());




        return convertView;
    }


//    public static final Spannable getColoredString(Myadapter context, CharSequence text, int color) {
//        Spannable spannable = new SpannableString(text);
//        spannable.setSpan(new ForegroundColorSpan(color), 0, spannable.length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
//        return spannable;
//    }



}
