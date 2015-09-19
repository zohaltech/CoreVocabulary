package widgets;

import android.content.Context;
import android.support.design.widget.Snackbar;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.zohaltech.app.corevocabulary.R;
import com.zohaltech.app.corevocabulary.classes.App;


public class MySnackbar {
    View      layout;
    ImageView image;
    TextView  text;


    //private MySnackbar(Context context) {
        //LayoutInflater inflater = LayoutInflater.from(App.context);
        //layout = inflater.inflate(R.layout.my_toast, null);
        //image = (ImageView) layout.findViewById(R.id.image);
        //text = (TextView) layout.findViewById(R.id.text);
        ////set(layout);
    //}

    public static void show(View view, String message, int duration) {
        Snackbar snackbar = Snackbar.make(view, message, duration);
        View snackbarView = snackbar.getView();
        snackbarView.setBackgroundResource(R.color.primary_darker);
        TextView textView = (TextView) snackbarView.findViewById(android.support.design.R.id.snackbar_text);
        textView.setTextColor(view.getContext().getResources().getColor(R.color.white));
        snackbar.show();
    }

    //public static void show(String message, int duration, int imageResId) {
    //    MySnackbar myToast = new MySnackbar(App.context);
    //    myToast.image.setVisibility(View.VISIBLE);
    //    myToast.image.setImageResource(imageResId);
    //    myToast.text.setText(message);
    //    myToast.setDuration(duration);
    //    myToast.show();
    //}
}
