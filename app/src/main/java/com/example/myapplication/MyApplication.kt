package com.example.myapplication

import android.app.Application
import com.example.myapplication.di.ApplicationComponent
import com.example.myapplication.di.DaggerApplicationComponent
import com.example.myapplication.di.NewsModule

class MyApplication : Application()/*, HasAndroidInjector*/ {

//@Inject
//lateinit var mInjector : DispatchingAndroidInjector<Any>
lateinit var appComponent: ApplicationComponent
    private set

    override fun onCreate() {
        super.onCreate()
        appComponent = DaggerApplicationComponent.builder().applicationBind(this).build()
//        DaggerApplicationComponent.builder().applicationBind(this).build().inject(this)

    }

//    override fun androidInjector(): AndroidInjector<Any> {
//       return  mInjector;
//    }

//    @Inject
//    lateinit var injector = DispatchingAndroidInjector<Any>
//    override fun androidInjector(): AndroidInjector<Any> {
//    }
}