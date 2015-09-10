package widgets;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.zohaltech.app.corevocabulary.R;
import com.zohaltech.app.corevocabulary.classes.App;


public class MyToast extends Toast {
    ImageView image;
    TextView  text;

    private MyToast(Context context) {
        super(context);
        LayoutInflater inflater = LayoutInflater.from(App.context);
        View layout = inflater.inflate(R.layout.my_toast, null);
        image = (ImageView) layout.findViewById(R.id.image);
        text = (TextView) layout.findViewById(R.id.text);
        setView(layout);
    }

    public static void show(String message, int duration) {
        MyToast myToast = new MyToast(App.context);
        myToast.image.setVisibility(View.GONE);
        myToast.text.setText(message);
        myToast.setDuration(duration);
        myToast.show();
    }

    public static void show(String message, int duration, int imageResId) {
        MyToast myToast = new MyToast(App.context);
        myToast.image.setVisibility(View.VISIBLE);
        myToast.image.setImageResource(imageResId);
        myToast.text.setText(message);
        myToast.setDuration(duration);
        myToast.show();
    }
}
