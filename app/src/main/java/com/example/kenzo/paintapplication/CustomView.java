package com.example.kenzo.paintapplication;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.PorterDuff;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Toast;

import java.util.ArrayList;

public class CustomView extends View {

    private float mx ,my;
    private static final float TOUCH_TOLERANCE = 4;
    private ArrayList<Path> paths = new ArrayList<Path>();
    private ArrayList<Path> undonePaths = new ArrayList<Path>();
    private ArrayList<Paint> paints = new ArrayList<Paint>();
    private ArrayList<Paint> undonePaints = new ArrayList<Paint>();
    private Bitmap mBitmap;
    private Canvas mCanvas;
    public int mColor;
    private Path mPath;
    public Paint mPaint;



    //オブジェクトを生成した時のコンストラクタ
    public CustomView(Context context){
        super(context);
    }

    //スタイルを適用する際のコンストラクタ
    public CustomView(Context context, AttributeSet attrs, int defStyle){
        super(context,attrs,defStyle);
    }

    //XMLを呼び出す際のコンストラクタ
    public CustomView(Context context,AttributeSet attrs){
        super(context,attrs);
        setFocusable(true);
        initPaint();
    }

    private void initPaint(){
        mPath = new Path();
        mPaint = new Paint();
        paths.add(mPath);
        paints.add(mPaint);
        //文字やラインを滑らかに見せる処理
        mPaint.setAntiAlias(true);
        //色を滑らかにする
        mPaint.setDither(true);
        mPaint.setColor(Color.RED);
        mPaint.setStyle(Paint.Style.STROKE);
        mPaint.setStrokeJoin(Paint.Join.ROUND);
        mPaint.setStrokeCap(Paint.Cap.ROUND);
        mPaint.setStrokeWidth(12);
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        mBitmap = Bitmap.createBitmap(w,h,Bitmap.Config.ARGB_8888);
        mCanvas = new Canvas(mBitmap);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        for (int i = 0;i<paths.size()-1;i++){
                canvas.drawPath(paths.get(i), paints.get(i));
        }
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        float x = event.getX();
        float y = event.getY();

        switch (event.getAction()){
            case MotionEvent.ACTION_DOWN:
                touch_start(x,y);
                break;
            case MotionEvent.ACTION_MOVE:
                touch_move(x,y);
                break;
            case MotionEvent.ACTION_UP:
                touch_up();
                break;
        }
        invalidate();
        return true;

    }
    public void setColor(int color){
        mPaint.setColor(color);
        mPaint.setAntiAlias(true);
        //色を滑らかにする
        mPaint.setDither(true);
        mPaint.setStyle(Paint.Style.STROKE);
        mPaint.setStrokeJoin(Paint.Join.ROUND);
        mPaint.setStrokeCap(Paint.Cap.ROUND);
        mPaint.setStrokeWidth(12);
    }

    private void touch_start(float x, float y){
        this.setColor(mColor);
        mPath.reset();
        mPath.moveTo(x,y);
        mx = x;
        my = y;
    }

    private void touch_move(float x, float y){
        //@param dx dy 移動量
        float dx = Math.abs(x - mx);
        float dy = Math.abs(y - my);

        if(dx >= TOUCH_TOLERANCE || dy >=TOUCH_TOLERANCE){
            mPath.quadTo(mx,my,(x+mx)/2,(y+my)/2);
            mx = x;
            my = y;
        }
    }

    private void touch_up(){
        mPath.lineTo(mx,my);
        mCanvas.drawPath(mPath,mPaint);
        mPath = new Path();
        mPaint = new Paint();
        paths.add(mPath);
        paints.add(mPaint);
    }

    public void all_delete(){
        mCanvas.drawColor(0, PorterDuff.Mode.CLEAR);
        paths.clear();
        paints.clear();
        invalidate();
    }

    public void onClickUndo(){
        if(paths.size()>0){
            undonePaths.add(paths.remove(paths.size()-1));
            undonePaints.add(paints.remove(paints.size()-1));
            invalidate();
        }else{
            Toast toast = Toast.makeText(getContext(), "戻れないよ", Toast.LENGTH_LONG);
            toast.show();
        }
    }

    public void onClickRedo(){
        if(undonePaths.size()>0){
            paths.add(undonePaths.remove(undonePaths.size()-1));
            paints.add(undonePaints.remove(undonePaints.size()-1));
            invalidate();
        }else{
            Toast toast = Toast.makeText(getContext(), "戻れないよ", Toast.LENGTH_LONG);
            toast.show();
        }

    }


}
