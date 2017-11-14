package yoryky.com.demo.activity;

import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.annotation.Nullable;
import android.widget.ListView;

import yoryky.com.demo.R;

public class RepositoryActivity extends BaseActivity {
    private ListView lvChild;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_repository);
    }
}
