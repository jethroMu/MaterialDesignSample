package example.jethro.com.materialdemo.entity;

import android.content.Intent;

/**
 * Created by lin on 2017/3/1.
 */

public class Demo {

    String name;
    Intent intent;

    public Demo(String name, Intent intent) {
        this.name = name;
        this.intent = intent;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Intent getIntent() {
        return intent;
    }

    public void setIntent(Intent intent) {
        this.intent = intent;
    }
}
