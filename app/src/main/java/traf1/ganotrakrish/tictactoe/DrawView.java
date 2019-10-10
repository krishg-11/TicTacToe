package traf1.ganotrakrish.tictactoe;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;

import androidx.annotation.Nullable;

public class DrawView extends View {
    Paint paint = new Paint();
    int[] board;

    public DrawView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        paint.setColor(Color.parseColor("#404042"));
        canvas.drawRect(0,0,getWidth(), getHeight(), paint);

        paint.setColor(Color.WHITE);


        //Helper variables to draw lines
        float lineLen = getWidth()-20;
        float lineWidth = 20;
        float leftHoriz = (float)(getWidth()/2.0 - lineLen/2.0);
        float rightHoriz = (float)(getWidth()/2.0 + lineLen/2.0);
        float topVert = (float)(getHeight()/2.0 - lineLen/2.0);
        float bottomVert = (float)(getHeight()/2.0 + lineLen/2.0);
        float dist = (float)(getWidth()/6);


        //Draw Horizontal lines
        canvas.drawRoundRect(leftHoriz, (float)(getHeight()/2.0 - dist - lineWidth/2.0), rightHoriz, (float)(getHeight()/2.0 - dist + lineWidth/2.0), 20, 20, paint);
        canvas.drawRoundRect(leftHoriz, (float)(getHeight()/2.0 + dist - lineWidth/2.0), rightHoriz, (float)(getHeight()/2.0 + dist + lineWidth/2.0), 20, 20, paint);

        //Draw Vertical lines
        canvas.drawRoundRect((float)(getWidth()/2.0 - dist - lineWidth/2.0),topVert, (float)(getWidth()/2.0 - dist + lineWidth/2.0), bottomVert, 20, 20, paint);
        canvas.drawRoundRect((float)(getWidth()/2.0 + dist - lineWidth/2.0),topVert, (float)(getWidth()/2.0 + dist + lineWidth/2.0), bottomVert, 20, 20, paint);



        invalidate();

    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {


        invalidate();
        return false;
    }
}