package widgets;

import android.content.Context;
import android.graphics.Typeface;
import android.support.v7.widget.AppCompatTextView;
import android.util.AttributeSet;

import com.zohaltech.app.corevocabulary.classes.App;


public class MyTextView extends AppCompatTextView {

    public MyTextView(Context context) {
        super(context);
        initialize();
    }

    public MyTextView(Context context, AttributeSet attrs) {
        super(context, attrs);
        initialize();
    }

    public MyTextView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        initialize();
    }

    private void initialize() {
        setLineSpacing(1f, 1.2f);
    }

    @Override
    public void setTypeface(Typeface tf) {
        super.setTypeface(App.persianFont);
    }

    @Override
    public void setTypeface(Typeface tf, int style) {
        if (style == Typeface.BOLD) {
            super.setTypeface(App.persianFontBold);
        } else {
            super.setTypeface(App.persianFont);
        }
    }
}
