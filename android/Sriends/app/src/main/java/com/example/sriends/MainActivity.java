package com.example.sriends;

import static android.content.ContentValues.TAG;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.webkit.ValueCallback;
import android.webkit.WebChromeClient;
import android.webkit.WebResourceRequest;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private WebView webView;
    private WebSettings webSettings;
    private long time = 0;
//    private String url = "https://k5d106.p.ssafy.io/";

    public static final int IMAGE_SELECTOR_REQ = 1;
    private ValueCallback mFilePathCallback;
    private final static int FILECHOOSER_NORMAL_REQ_CODE = 0;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        webView = (WebView) findViewById(R.id.webview);
        webSettings = webView.getSettings();
        webSettings.setJavaScriptEnabled(true);         // 자바스크립트 사용
        webSettings.setSupportMultipleWindows(true);    // 새창 띄우기 허용
        webSettings.setJavaScriptCanOpenWindowsAutomatically(true); // 자바스크립트 새창 띄우기 허용
        webSettings.setLoadWithOverviewMode(true);      // 메타태그 허용
        webSettings.setUseWideViewPort(true);           // 화면 사이즈 맞추기 허용
        webSettings.setSupportZoom(false);              // 화면줌 허용 여부
        webSettings.setBuiltInZoomControls(false);      // 화면 확대 축소 허용 여부
//        webSettings.setLayoutAlgorithm(WebSettings.LayoutAlgorithm.SINGLE_COLUMN); // 컨텐츠 사이즈 맞추기
        webSettings.setCacheMode(WebSettings.LOAD_NO_CACHE);        // 브라우저 노캐쉬
        webSettings.setDomStorageEnabled(true);         // 로컬저장소 허용

        webView.loadUrl("https://k5d106.p.ssafy.io");
        webView.setWebChromeClient(new MyWebChromeClient());  //웹뷰에 크롬 사용 허용. 이 부분이 없으면 크롬에서 alert가 뜨지 않음
        webView.setWebViewClient(new WebViewClientClass());
    }


    @Override
    public void onBackPressed() {
        //super.onBackPressed();
        if(System.currentTimeMillis()-time>=2000){
            time=System.currentTimeMillis();
            Toast.makeText(getApplicationContext(),"뒤로 버튼을 한번 더 누르면 종료합니다.", Toast.LENGTH_SHORT).show();
        }else if(System.currentTimeMillis()-time<2000){
            finish();
            return;
        }
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {

        if ((keyCode==KeyEvent.KEYCODE_BACK) && webView.canGoBack()) {
            webView.goBack();
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data){
        super.onActivityResult(requestCode, resultCode, data);
        switch(requestCode) {
            case FILECHOOSER_NORMAL_REQ_CODE:
                //fileChooser 로 파일 선택 후 onActivityResult 에서 결과를 받아 처리함
                if(resultCode == RESULT_OK) {
                    //파일 선택 완료 했을 경우
                    if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                        mFilePathCallback.onReceiveValue(WebChromeClient.FileChooserParams.parseResult(resultCode, data));
                    }else{
                        mFilePathCallback.onReceiveValue(new Uri[]{data.getData()});
                    }
                    mFilePathCallback = null;
                } else {
                    //cancel 했을 경우
                    if(mFilePathCallback != null) {
                        mFilePathCallback.onReceiveValue(null);
                        mFilePathCallback = null;
                    }
                }
                break;
            default:
                break;
        }
        super.onActivityResult(requestCode, resultCode, data);
    }

    private class MyWebChromeClient extends WebChromeClient{
        @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
        @Override
        public boolean onShowFileChooser(WebView webView, ValueCallback<Uri[]> filePathCallback, FileChooserParams fileChooserParams) {
            Log.d(TAG, "***** onShowFileChooser()");
            //Callback 초기화
            //return super.onShowFileChooser(webView, filePathCallback, fileChooserParams);

            /* 파일 업로드 */
            if (mFilePathCallback != null) {
                //파일을 한번 오픈했으면 mFilePathCallback 를 초기화를 해줘야함
                // -- 그렇지 않으면 다시 파일 오픈 시 열리지 않는 경우 발생
                mFilePathCallback.onReceiveValue(null);
                mFilePathCallback = null;
            }
            mFilePathCallback = filePathCallback;

            //권한 체크
//            if(권한 여부) {

            //권한이 있으면 처리
            Intent intent = new Intent(Intent.ACTION_GET_CONTENT);
            intent.addCategory(Intent.CATEGORY_OPENABLE);
            intent.setType("*/*");  //모든 contentType 파일 표시
//            intent.setType("image/*");  //contentType 이 image 인 파일만 표시
            startActivityForResult(intent, 0);

//            } else {

            //권한이 없으면 처리

//            }

            return true;
        }
    }

    private class WebViewClientClass extends WebViewClient {
        @Override
        public boolean shouldOverrideUrlLoading(WebView view, WebResourceRequest request) {
            //Log.d("WebViewClient URL : " , request.getUrl().toString());
            view.loadUrl(request.getUrl().toString());
            return true;
            //return super.shouldOverrideUrlLoading(view, request);
        }
    }
}