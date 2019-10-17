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
    int backgroundColor;
    int OColor;
    int XColor;
    boolean isXTurn;
    int drawingO;
    int drawingX;

    public DrawView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    protected void onLayout(boolean changed, int left, int top, int right, int bottom) {
        super.onLayout(changed, left, top, right, bottom);
        isXTurn = true;
        board = new int[9];
        drawingO = -1;
        drawingX = -1;

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

        backgroundColor = Color.parseColor("#404042");
        OColor = Color.WHITE;
        XColor = Color.WHITE;

    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        paint.setColor(backgroundColor);
        canvas.drawRect(0,0,getWidth(), getHeight(), paint);

        paint.setColor(Color.WHITE);
        //Draw Horizontal lines
        canvas.drawRoundRect(leftHoriz, (float)(getHeight()/2.0 - dist - lineWidth/2.0), rightHoriz, (float)(getHeight()/2.0 - dist + lineWidth/2.0), 20, 20, paint);
        canvas.drawRoundRect(leftHoriz, (float)(getHeight()/2.0 + dist - lineWidth/2.0), rightHoriz, (float)(getHeight()/2.0 + dist + lineWidth/2.0), 20, 20, paint);

        //Draw Vertical lines
        canvas.drawRoundRect((float)(getWidth()/2.0 - dist - lineWidth/2.0),topVert, (float)(getWidth()/2.0 - dist + lineWidth/2.0), bottomVert, 20, 20, paint);
        canvas.drawRoundRect((float)(getWidth()/2.0 + dist - lineWidth/2.0),topVert, (float)(getWidth()/2.0 + dist + lineWidth/2.0), bottomVert, 20, 20, paint);

//        if(drawingX != -1){
//            makeX(canvas, drawingX);
//        }
        if(drawingO != -1){
            makeRing(canvas, drawingO);
        }

        for(int i=0; i<board.length; i++){
            if(board[i]==1){
                drawX(canvas, i);
            }
            else if(board[i] == 2){
                drawRing(canvas, i);
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
            board[0] = isXTurn ? 1: 2;
        }
        else if(X<getWidth()/2.0 + dist && Y<getHeight()/2.0 - dist){
            System.out.println("Top Middle");
            board[1] = isXTurn ? 1: 2;
        }
        else if(X>getWidth()/2.0 + dist && Y<getHeight()/2.0 - dist){
            System.out.println("Top Right");
            board[2] = isXTurn ? 1: 2;
        }
        else if(X<getWidth()/2.0 - dist && Y<getHeight()/2.0 + dist){
            System.out.println("Middle Left");
            board[3] = isXTurn ? 1: 2;
        }
        else if(X<getWidth()/2.0 - dist && Y>getHeight()/2.0 + dist){
            System.out.println("Bottom Left");
            board[6] = isXTurn ? 1: 2;
        }
        else if(X<getWidth()/2.0 + dist && Y<getHeight()/2.0 + dist){
            System.out.println("Middle Middle");
            board[4] = isXTurn ? 1: 2;
        }
        else if(X<getWidth()/2.0 + dist && Y>getHeight()/2.0 + dist){
            System.out.println("Bottom Middle");
            board[7] = isXTurn ? 1: 2;
        }
        else if(X>getWidth()/2.0 + dist && Y<getHeight()/2.0 + dist){
            System.out.println("Middle Right");
            board[5] = isXTurn ? 1: 2;
        }
        else if(X>getWidth()/2.0 + dist && Y>getHeight()/2.0 + dist){
            System.out.println("Bottom Right");
            board[8] = isXTurn ? 1: 2;
        }
        isXTurn = !isXTurn;

        invalidate();
        return false;
    }

    public void makeRing(Canvas canvas, int i){
        int marginOut = 30;
        int marginIn = 50;
        RectF ovalOut = new RectF(posX[i%3]-dist+marginOut, posY[i/3]-dist+marginOut, posX[i%3]+dist-marginOut, posY[i/3]+dist-marginOut);
        RectF ovalIn = new RectF(posX[i%3]-dist+marginIn, posY[i/3]-dist+marginIn, posX[i%3]+dist-marginIn, posY[i/3]+dist-marginIn);

        for(int arcLen=0; arcLen<=360; arcLen+=1){
            paint.setColor(OColor);
            canvas.drawArc(ovalOut, -90, arcLen, true, paint);
            paint.setColor(backgroundColor);
            canvas.drawArc(ovalIn, -90, arcLen, true, paint);
            try {
                //set time in mili
                Thread.sleep(200);

            }catch (Exception e){
                e.printStackTrace();
            }
        }
        return ;
    }

    public void drawRing(Canvas canvas, int i){
        paint.setColor(OColor);

        canvas.drawCircle(posX[i%3], posY[i/3], dist-30, paint);
        paint.setColor(backgroundColor);
        canvas.drawCircle(posX[i%3], posY[i/3], dist-50, paint);

        return;
    }
    public void drawX(Canvas canvas, int i){
        float midX = posX[i%3];
        float midY = posY[i/3];

        paint.setColor(XColor);
        paint.setStrokeWidth(20);
        paint.setStrokeCap(Paint.Cap.ROUND);
        float margin = 40;
        canvas.drawLine(midX-dist+margin, midY-dist+margin, midX+dist-margin, midY+dist-margin, paint);
        canvas.drawLine(midX+dist-margin, midY-dist+margin, midX-dist+margin, midY+dist-margin, paint);
    }
}