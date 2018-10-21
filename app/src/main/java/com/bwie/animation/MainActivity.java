package com.bwie.animation;

import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.view.animation.AccelerateInterpolator;
import android.view.animation.AnticipateInterpolator;
import android.view.animation.BounceInterpolator;
import android.view.animation.CycleInterpolator;
import android.view.animation.LinearInterpolator;
import android.view.animation.TranslateAnimation;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    private static final String TAG = "MainActivity";
    private Button btnJianBian;
    private Button btnPingYi;
    private Button btnXuanZhuan;
    private Button btnSuoFang;
    private Button btnVPingYi;
    private Button btnOZuHeDongHua;
    private Button btnBianZhi;
    private ImageView imgOB;
    private ObjectAnimator animatorTransation;
    private Button btnEnd;
    private Button btnCancle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //1 控件
        getinit();

        //2 属性动画
           //不仅仅可以作用在View上，还可以作用在普通变量上，只要有set方法即可


    }

    //点击之后  动画
    @Override
    public void onClick(View v) {
      switch (v.getId()){

          case R.id.btn_bianzhi://点击按钮 改变值
              //属性动画中的第一个比较重要的类
              ValueAnimator animatorBianZhi = new ValueAnimator();
              animatorBianZhi.setIntValues(1,2,3,4,5,6,7);
              //方法二  静态方法
              //ValueAnimator animatorBianZhi = ValueAnimator.ofInt(1,2,3,4,5);
              //设置时间
              animatorBianZhi.setDuration(3000);
              animatorBianZhi.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
                  @Override
                  public void onAnimationUpdate(ValueAnimator animation) {
                      Object value = animation.getAnimatedValue();
                      //打印出改变的shuzhi
                      Log.i(TAG, "onAnimationUpdate: "+value);
                  }
              });
              animatorBianZhi.start();
              break;

          case R.id.btn_jianbian://点击按钮 渐变
              //1 改变透明度（从透明到不透明）
              ObjectAnimator animator = ObjectAnimator.ofFloat(imgOB,
                      "alpha",
                      1,
                      0);
              animator.setDuration(3000);
              animator.start();

             //2
              /*imgOB.setScaleX();*/
              /*ObjectAnimator animator = ObjectAnimator.ofFloat(imgOB,
                      "scaleX",
                      1,
                      100);*/
              animator.setInterpolator(new AnticipateInterpolator());
              animator.setDuration(2000);
              animator.start();
              break;

          case R.id.btn_pingyi://点击按钮 平移
              //objectAnimator继承自ValueAnimator
              ObjectAnimator objectAnimator =  ObjectAnimator.ofInt(imgOB,
                      "backgroundColor",
                      Color.parseColor("#000000"),
                      Color.parseColor("#ffffff"));
              //方式二
             /* ObjectAnimator objectAnimator = ObjectAnimator.ofFloat(imgOB,
                      "translationX",
                      0,
                      500);*/
                objectAnimator.setDuration(3000);
                objectAnimator.start();
                      //属性动画平移,真实的改变了控件的属性  发生了真实的动画效果

              //方式三 使用补间动画平移  只是视觉上的动画效果
              /*TranslateAnimation translateAnimation = new TranslateAnimation(0,500,0,500);
              translateAnimation.setDuration(3000);*/
              break;

          case R.id.btn_xuanzhuan://点击按钮 旋转
             /* imgOB.setRotation();*/
          ObjectAnimator animator1 = ObjectAnimator.ofFloat(imgOB,
                  "rotationX",
                  0,
                  600000);
               //加速器
              //animator1.setInterpolator(new AccelerateInterpolator());
              //减速器
              //animator1.setInterpolator(new AccelerateDecelerateInterpolator());
               //先加后减
               animator1.setInterpolator(new AccelerateDecelerateInterpolator());
               //匀速插值器
              // animator1.setInterpolator(new LinearInterpolator());
              //回弹效果
              animator1.setInterpolator(new AnticipateInterpolator());
              //阻尼效果
              //animator1.setInterpolator(new BounceInterpolator());
              //animator1.setInterpolator(new CycleInterpolator(2));
               animator1.setDuration(20000);
               animator1.start();
              break;

          case R.id.btn_suofang://点击按钮 缩放
              //LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(100,100);
              break;

          case R.id.btn_vpingyi://点击按钮 Value平移
              //引用外部定义动画

              break;

          case R.id.btn_Ozuhedonghua://点击按钮 组合动画
              animatorTransation = ObjectAnimator.ofFloat(imgOB,
                      "translationX",
                      0,
                      500);
              animatorTransation.setDuration(3000);
              //设置重复次数（参数 0：代表不重复  1代表走两次即重复1次  -1代表一直重复）
              animatorTransation.setRepeatCount(0);

              ObjectAnimator animator2 = ObjectAnimator.ofFloat(imgOB,
                      "rotationX",
                      0,
                      270);
              animator2.setDuration(3000);

              ObjectAnimator animator3 = ObjectAnimator.ofFloat(imgOB,
                      "rotationY",
                      0,
                      360);
              animator2.setDuration(3000);

              /*animator2.start();
              animatorTransation.start();*/



              //with  代表两个一起执行
              //before 先执行谁  后执行谁
              //after  代表 在什么之后再执行……
              AnimatorSet set =new AnimatorSet();
                     /* set.play(animator3);
                      set.play(animatorTransation).before(animator2);*/
                      set.start();

              break;

          case R.id.btn_end://点击按钮 到终点
              animatorTransation.end();
              break;

          case R.id.btn_cancle://点击按钮 停止

              animatorTransation.cancel();
              break;
      }
    }

    //1 控件
    private void getinit() {
        btnBianZhi = findViewById(R.id.btn_bianzhi);
        btnJianBian = findViewById(R.id.btn_jianbian);
        btnPingYi = findViewById(R.id.btn_pingyi);
        btnXuanZhuan = findViewById(R.id.btn_xuanzhuan);
        btnSuoFang = findViewById(R.id.btn_suofang);
        btnVPingYi = findViewById(R.id.btn_vpingyi);
        imgOB = findViewById(R.id.img_ob);
        btnOZuHeDongHua = findViewById(R.id.btn_Ozuhedonghua);

        btnEnd = findViewById(R.id.btn_end);
        btnCancle = findViewById(R.id.btn_cancle);

        //监听
        btnBianZhi.setOnClickListener(this);
        btnJianBian.setOnClickListener(this);
        btnPingYi.setOnClickListener(this);
        btnXuanZhuan.setOnClickListener(this);
        btnSuoFang.setOnClickListener(this);
        btnVPingYi.setOnClickListener(this);
        btnOZuHeDongHua.setOnClickListener(this);
        btnEnd.setOnClickListener(this);
        btnCancle.setOnClickListener(this);

    }

}
