package com.noah.demo.butterknife;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.BindViews;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.OnTextChanged;
import butterknife.OnTouch;
import butterknife.Unbinder;

public class MainActivity extends AppCompatActivity {


    //    @BindViews	绑定多个view id为一view的list变量
    @BindViews({R.id.pswEdt, R.id.userEdt})
    List<EditText> editTextsList; // editTextsList保含用户名和密码输入框
    //    @BindView	绑定一个view id为一个view 变量
    @BindView(R.id.loginBtn)
    Button loginBtn;
//    @BindArray	绑定string里面array数组,@BindArray(R.array.city ) String[] citys ;
//    @BindBitmap	绑定图片资源为Bitmap，@BindBitmap( R.mipmap.wifi ) Bitmap bitmap;
//    @BindBool	绑定真假boolean
//    @BindColor	绑定color,@BindColor(R.color.colorAccent) int black;
//    @BindDimen	绑定Dimen,@BindDimen(R.dimen.borth_width) int mBorderWidth;
//    @BindDrawable	绑定Drawable,@BindDrawable(R.drawable.test_pic) Drawable mTestPic;
//    @BindFloat	绑定float
//    @BindInt	绑定int
//    @BindString	绑定一个String id为一个String变量,@BindString( R.string.app_name ) String meg;


    /**
     * 把传入的views集合所有的view设置为disenabled，这里把editTextsList中两个输入框设置为disenabled
     */
    static final ButterKnife.Action<View> DISABLE = new ButterKnife.Action<View>() {
        @Override
        public void apply(View view, int index) {
            view.setEnabled(false);
        }
    };

    //Setter接口设置属性
    static final ButterKnife.Setter<View, Boolean> ENABLED = new ButterKnife.Setter<View, Boolean>() {
        @Override
        public void set(View view, Boolean value, int index) {
            view.setEnabled(value);
        }
    };

    /**
     * 给不同views集合设置不同的背景色，对比Action，这里可传入后面的颜色列表
     */
    static final ButterKnife.Setter<View, List<Integer>> CHANGE_BACKGRUND = new ButterKnife.Setter<View, List<Integer>>() {
        @Override
        public void set(@NonNull View view, List<Integer> value, int index) {
            view.setBackgroundColor(value.get(index));
        }
    };

    /**
     * 绑定View
     */
    @BindView(R.id.userEdt)
    EditText userTdt;

    private Unbinder unbinder;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        unbinder = ButterKnife.bind(this);//activity绑定Butterknife，用插件会自动生成

        //设置多个view的属性
        //方式1 传递值
        ButterKnife.apply(editTextsList, ENABLED, false);
        //方式2 指定值
        ButterKnife.apply(editTextsList, DISABLE);
        //方式3 设置View的Property
        ButterKnife.apply(editTextsList, View.ALPHA, 0.8f);
        //Action接口设置属性

        List<Integer> colors = new ArrayList<>();
        colors.add(Color.RED);
        colors.add(Color.GREEN);
        colors.add(Color.YELLOW);
        ButterKnife.apply(buttuonLists, CHANGE_BACKGRUND, colors);
    }

    @BindViews({R.id.loginBtn, R.id.registerBtn, R.id.forgetPswBtn})
    List<Button> buttuonLists;

    @Override
    protected void onDestroy() {
        super.onDestroy();
        unbinder.unbind();
    }

    /**
     * 设置OnClick，可命名成自定义的方法
     */
    @OnClick(R.id.loginBtn)
    public void onLogin() {
        Intent intent = new Intent(MainActivity.this, FragmentActivity.class);
        startActivity(intent);
    }

    /**
     * 设置onTextChanged事件
     */
    @OnTextChanged(R.id.pswEdt)
    void OnTextChanged(CharSequence text) {
        Toast.makeText(MainActivity.this, "password text changed", Toast.LENGTH_SHORT).show();
    }

    /**
     * 设置OnBeforeChanged事件
     */
    @OnTextChanged(value = R.id.pswEdt, callback = OnTextChanged.Callback.BEFORE_TEXT_CHANGED)
    void OnBeforeChanged(CharSequence text) {
        Toast.makeText(MainActivity.this, "password text changed", Toast.LENGTH_SHORT).show();
    }

    /**
     * 设置onTouch
     */
    @OnTouch(R.id.forgetPswBtn)
    boolean onTouch(View v, MotionEvent event) {
        Toast.makeText(this, "Touched->>" + event.getAction(), Toast.LENGTH_SHORT).show();
        return false;
    }


    /***
     * 设置多个onclick
     * @param view
     */
    @OnClick({R.id.registerBtn, R.id.forgetPswBtn})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.registerBtn:
                Toast.makeText(MainActivity.this, "register button onclick", Toast.LENGTH_SHORT).show();
                break;
            case R.id.forgetPswBtn:
                Toast.makeText(MainActivity.this, "forget button onclick", Toast.LENGTH_SHORT).show();
                break;
        }
    }

//    @OnClick	点击事件
//    @OnCheckedChanged	选中，取消选中
//    @OnEditorAction	软键盘的功能键
//    @OnFocusChange	焦点改变
//    @OnItemClick	item被点击(注意这里有坑，如果item里面有Button等这些有点击的控件事件的，需要设置这些控件属性focusable为false)
//    @OnItemLongClick	item长按(返回真可以拦截onItemClick)
//    @OnItemSelected	item被选择事件
//    @OnLongClick	长按事件
//    @OnPageChange	页面改变事件
//    @OnTextChanged	EditText里面的文本变化事件
//    @OnTouch	触摸事件
//    @Optional	选择性注入，如果当前对象不存在，就会抛出一个异常，为了压制这个异常，可以在变量或者方法上加入一下注解,让注入变成选择性的,如果目标View存在,则注入, 不存在,则什么事情都不做=如下代码

}
