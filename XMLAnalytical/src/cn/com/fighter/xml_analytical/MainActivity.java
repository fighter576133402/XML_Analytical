package cn.com.fighter.xml_analytical;

import java.io.FileOutputStream;
import java.io.InputStream;
import java.util.List;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import cn.com.fighter.model.Book;
import cn.com.fighter.parser.BookParser;
import cn.com.fighter.parser.SaxBookParser;

public class MainActivity extends Activity implements OnClickListener{

	private static final String TAG = "XML";  
    
    private BookParser parser;  
    private List<Book> books; 
    private Button readXml = null;
    private Button writeXml = null;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        readXml = (Button)findViewById(R.id.button1);
        writeXml = (Button)findViewById(R.id.button2);
        
        readXml.setOnClickListener(this);
        writeXml.setOnClickListener(this);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.activity_main, menu);
        return true;
    }

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		switch (v.getId()) {
		case R.id.button1:
			try {  
                InputStream is = getAssets().open("books.xml");  
                parser = new SaxBookParser();  //创建SaxBookParser实例  
                books = parser.parse(is);  //解析输入流  
                for (Book book : books) {  
                    Log.i(TAG, book.toString());  
                }  
            } catch (Exception e) {  
                Log.e(TAG, e.getMessage());  
            }  
			break;
		case R.id.button2:
			 try {  
                 String xml = parser.serialize(books);  //序列化  
                 FileOutputStream fos = openFileOutput("books.xml", Context.MODE_PRIVATE);  
                 fos.write(xml.getBytes("UTF-8"));  
             } catch (Exception e) {  
                 Log.e(TAG, e.getMessage());  
             } 
			break;
		}
		
	}

    
}
