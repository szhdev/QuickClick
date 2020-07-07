# QuickClick
Android 防止重复点击或者快速点击 只要引入此库就能统一处理所有系统的点击事件。


## 使用方式


## 1.在project的build.gradle里配置

```
dependencies {
        ...
        classpath 'com.hujiang.aspectjx:gradle-android-plugin-aspectjx:2.0.8'
	 }

allprojects {
	repositories {
	    ...
	    maven { url 'https://jitpack.io' }
	   }
	}
```
## 2.在app的build.gradle里配置
```
//在文件的头部加上以下这句配置
  apply plugin: 'android-aspectjx'

//在dependencies内部引入库
  dependencies {
     implementation 'com.github.szhdev:QuickClick:v1.0'
 }
```
#### 支持系统的onclick回调
#### 支持接口的onclick回调
#### 支持在xml中的点击事件
比如:
```
<TextView
    android:id="@+id/tv_click"
    android:layout_width="wrap_content"
    android:layout_height="wrap_content"
    android:onClick="onClick"/>

@SingleClick
public void onClick(View view) {
    if (view.getId() == R.id.tv_click) {
        
        }
    }
```
## 有问题反馈
在使用中有任何问题，欢迎反馈给我，可以联系跟我交流

