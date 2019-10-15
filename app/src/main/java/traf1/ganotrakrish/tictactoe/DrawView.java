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
    float lineLen;
    float lineWidth;
    float leftHoriz;
    float rightHoriz;
    float topVert;
    float bottomVert;
    float dist;
    float[] posX;
    float[] posY;

    public DrawView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    protected void onLayout(boolean changed, int left, int top, int right, int bottom) {
        super.onLayout(changed, left, top, right, bottom);
        board = new int[9];

        //Helper variables to draw lines
        lineLen = getWidth()-20;
        lineWidth = 20;
        leftHoriz = (float)(getWidth()/2.0 - lineLen/2.0);
        rightHoriz = (float)(getWidth()/2.0 + lineLen/2.0);
        topVert = (float)(getHeight()/2.0 - lineLen/2.0);
        bottomVert = (float)(getHeight()/2.0 + lineLen/2.0);
        dist = (float)(getWidth()/6);

        posX = new float[3];
        posX[0] = (float)(getWidth()/2.0 - dist*2.0);
        posX[1] = (float)(getWidth()/2.0);
        posX[2] = (float)(getWidth()/2.0 + dist*2.0);

        posY = new float[3];
        posY[0] = (float)(getHeight()/2.0 - dist*2.0);
        posY[1] = (float)(getHeight()/2.0);
        posY[2] = (float)(getHeight()/2.0 + dist*2.0);

    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        paint.setColor(Color.parseColor("#404042"));
        canvas.drawRect(0,0,getWidth(), getHeight(), paint);

        paint.setColor(Color.WHITE);

        //Draw Horizontal lines
        canvas.drawRoundRect(leftHoriz, (float)(getHeight()/2.0 - dist - lineWidth/2.0), rightHoriz, (float)(getHeight()/2.0 - dist + lineWidth/2.0), 20, 20, paint);
        canvas.drawRoundRect(leftHoriz, (float)(getHeight()/2.0 + dist - lineWidth/2.0), rightHoriz, (float)(getHeight()/2.0 + dist + lineWidth/2.0), 20, 20, paint);

        //Draw Vertical lines
        canvas.drawRoundRect((float)(getWidth()/2.0 - dist - lineWidth/2.0),topVert, (float)(getWidth()/2.0 - dist + lineWidth/2.0), bottomVert, 20, 20, paint);
        canvas.drawRoundRect((float)(getWidth()/2.0 + dist - lineWidth/2.0),topVert, (float)(getWidth()/2.0 + dist + lineWidth/2.0), bottomVert, 20, 20, paint);

        for(int i=0; i<board.length; i++){
            if(board[i]==1){
                canvas.drawCircle(posX[i%3], posY[i/3], dist-30, paint);
            }
        }

        invalidate();
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        float X = event.getX();
        float Y = event.getY();

        if(X<getWidth()/2.0 - dist && Y<getHeight()/2.0 - dist){
            System.out.println("Top Left");
            board[0] = 1;
        }
        else if(X<getWidth()/2.0 + dist && Y<getHeight()/2.0 - dist){
            System.out.println("Top Middle");
            board[1] = 1;
        }
        else if(X>getWidth()/2.0 + dist && Y<getHeight()/2.0 - dist){
            System.out.println("Top Right");
            board[2] = 1;
        }
        else if(X<getWidth()/2.0 - dist && Y<getHeight()/2.0 + dist){
            System.out.println("Middle Left");
            board[3] = 1;
        }
        else if(X<getWidth()/2.0 - dist && Y>getHeight()/2.0 + dist){
            System.out.println("Bottom Left");
            board[6] = 1;
        }
        else if(X<getWidth()/2.0 + dist && Y<getHeight()/2.0 + dist){
            System.out.println("Middle Middle");
            board[4] = 1;
        }
        else if(X<getWidth()/2.0 + dist && Y>getHeight()/2.0 + dist){
            System.out.println("Bottom Middle");
            board[7] = 1;
        }
        else if(X>getWidth()/2.0 + dist && Y<getHeight()/2.0 + dist){
            System.out.println("Middle Right");
            board[5] = 1;
        }
        else if(X>getWidth()/2.0 + dist && Y>getHeight()/2.0 + dist){
            System.out.println("Bottom Right");
            board[8] = 1;
        }

        invalidate();
        return false;
    }
}