package com.app.graph;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.LinearGradient;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.PointF;
import android.graphics.Shader;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {

    DemoView demoview;
    public double percentage;
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        demoview = new DemoView(this);
        setContentView(R.layout.activity_main);
    }

    private class DemoView extends View {
        public DemoView(Context context){
            super(context);
        }

        @Override protected void onDraw(Canvas canvas) {
            super.onDraw(canvas);


            int width = getWidth();
            int height = getHeight();
            // custom drawing code here
            Paint paint = new Paint();
            Paint paint1 = new Paint();
            paint1.setColor(Color.BLACK);
            paint1.setStyle(Paint.Style.FILL);
            paint.setShader(new LinearGradient(0, height / 2, width, height / 2, Color.GREEN, Color.RED, Shader.TileMode.MIRROR));
            paint.setAntiAlias(true);
//            Log.e("Width", width + "");
//            Log.e("Width",height+"");

            //       canvas.drawRect(10, 5, 500, 300, paint);

            PointF p1 = new PointF(0, height);
            float x = (float) (percentage * width);
            PointF p2 = new PointF(x, height);

            float y = (width * height - height * x) / (float) width;
            PointF p3 = new PointF(x, y);
            Path path = new Path();
            path.moveTo(p1.x, p1.y);
            path.lineTo(p2.x, p2.y);
            path.lineTo(p3.x, p3.y);
            path.close();
//            Log.d("First", p1.toString());
//            Log.d("second", p2.toString());
//            Log.d("third", p3.toString());

            PointF a = new PointF(0,height);
            PointF b = new PointF(width,height);
            PointF c = new PointF(width,0);
            Path path1 = new Path();
            path1.moveTo(a.x, a.y);
            path1.lineTo(b.x, b.y);
            path1.lineTo(c.x, c.y);
            canvas.drawPath(path1, paint1);
            canvas.drawPath(path,paint);
        }
    }
    public void setPercentage(double percentage) {
        //double percentage=1.0;
        //return percentage;
        this.percentage=percentage;
    }
    public void change(View view)
    {
        double p;
        EditText editText=(EditText)findViewById(R.id.get_val);
        p=Double.parseDouble(editText.getText().toString());
        setPercentage(p);
        setContentView(demoview);
    }
    @Override
    public void onBackPressed() {
        setContentView(R.layout.activity_main);
    }
}
