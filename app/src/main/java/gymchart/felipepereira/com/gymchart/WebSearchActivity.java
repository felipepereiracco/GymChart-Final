package gymchart.felipepereira.com.gymchart;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.webkit.WebView;

public class WebSearchActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_web_search);

        WebView searchWeb = findViewById(R.id.webSearch);

        String exercise = getIntent().getStringExtra("parameter").trim().replace(" ", "+");
        StringBuilder url = new StringBuilder();

        url.append("https://www.google.com/search?tbm=isch&source=hp&ei=YUy4XOG5D9aZ1fAPtPO90Aw&q=");
        url.append("+" + exercise+" exercise");
        //url.append("+ gif");
        searchWeb.getSettings().setDomStorageEnabled(true);
        searchWeb.getSettings().setJavaScriptEnabled(true);
        searchWeb.getSettings().setUseWideViewPort(true);
        searchWeb.loadUrl(url.toString());

    }
}
