package com.scandemo.Demo;

import java.util.Hashtable;

import com.example.scandemo.R;
import com.google.zxing.BarcodeFormat;
import com.google.zxing.EncodeHintType;
import com.google.zxing.WriterException;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.QRCodeWriter;

import android.app.Activity;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

public class CreateImageActivity extends Activity {
	private ImageView refImage;
	private String url;
	private EditText edit;
	private int QR_WIDTH = 360, QR_HEIGHT = 360;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.create);
		edit = (EditText)findViewById(R.id.create_url);
		
		Button create = (Button)findViewById(R.id.create);
		refImage = (ImageView)findViewById(R.id.ref_image);
		
		
		create.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO �Զ����ɵķ������
				url = edit.getText().toString();
				//��ʾ��һ��ImageView����
				Bitmap bitmap = createQRImage(url);
				refImage.setImageBitmap(bitmap);
			}
		});
		
	}
	
	//Ҫת���ĵ�ַ���ַ���,����������
	public Bitmap createQRImage(String url)
	{
		Bitmap bitmap = null;
		try
		{
			//�ж�URL�Ϸ���
			if (url == null || "".equals(url) || url.length() < 1)
			{
				return null;
			}
			Hashtable<EncodeHintType, String> hints = new Hashtable<EncodeHintType, String>();
			hints.put(EncodeHintType.CHARACTER_SET, "utf-8");
			//ͼ������ת����ʹ���˾���ת��
			BitMatrix bitMatrix = new QRCodeWriter().encode(url, BarcodeFormat.QR_CODE, QR_WIDTH, QR_HEIGHT, hints);
			int[] pixels = new int[QR_WIDTH * QR_HEIGHT];
			//�������ﰴ�ն�ά����㷨��������ɶ�ά���ͼƬ��
			//����forѭ����ͼƬ����ɨ��Ľ��
			for (int y = 0; y < QR_HEIGHT; y++)
			{
				for (int x = 0; x < QR_WIDTH; x++)
				{
					if (bitMatrix.get(x, y))
					{
						pixels[y * QR_WIDTH + x] = 0xff000000;
					}
					else
					{
						pixels[y * QR_WIDTH + x] = 0xffffffff;
					}
				}
			}
			//���ɶ�ά��ͼƬ�ĸ�ʽ��ʹ��ARGB_8888
			bitmap = Bitmap.createBitmap(QR_WIDTH, QR_HEIGHT, Bitmap.Config.ARGB_8888);
			bitmap.setPixels(pixels, 0, QR_WIDTH, 0, 0, QR_WIDTH, QR_HEIGHT);
			
			return bitmap;
		}
		catch (WriterException e)
		{
			e.printStackTrace();
		}
		return bitmap;
	}
}
