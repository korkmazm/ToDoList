package com.korkmaz.todolist;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.widget.TextView;

public class TodoListItemView extends TextView{

	public TodoListItemView(Context context, AttributeSet attrs,
			int defStyleAttr) {
		super(context, attrs, defStyleAttr);
		init();
	}
	
	public TodoListItemView(Context context, AttributeSet attrs) {
		super(context, attrs);
		init();
	}
	
	public TodoListItemView(Context context) {
		super(context);
		init();
	}
	
	private Paint marginPaint;
	private Paint linePaint;
	private int paperColor;
	private float margin;
	
	private void init(){
		//Kaynak tablomuz için bir referans alın
		Resources myResources = getResources();
		
		//onDraw metodunda kullanacağımız bir boya fırçası oluşturun
		marginPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
		marginPaint.setColor(myResources.getColor(R.color.notepade_margin));
		linePaint = new Paint(Paint.ANTI_ALIAS_FLAG);
		linePaint.setColor(myResources.getColor(R.color.notepad_lines));
		
		//Kenar genişiliğini ve arka plan rengini alın
		paperColor = myResources.getColor(R.color.notepade_paper);
		margin = myResources.getDimension(R.dimen.notepad_margin);
	}
	
	@Override
	public void onDraw(Canvas canvas){
		
		//Kağıt rengi
		canvas.drawColor(paperColor);
		
		//çizgilerin çizilmesi
		canvas.drawLine(0, 0, getMeasuredHeight(), 0, linePaint);
		canvas.drawLine(0, getMeasuredHeight(), getMeasuredWidth(), getMeasuredHeight(), linePaint);
		
		//margin çizimi
		canvas.drawLine(margin, 0, margin, getMeasuredHeight(), marginPaint);
		
		//Metni margin boyunca hareket ettirin
		canvas.save();
		canvas.translate(margin, 0);
		
		//Metni render etmek için textview kullanın
		super.onDraw(canvas);
		
		canvas.restore();
	}

}
