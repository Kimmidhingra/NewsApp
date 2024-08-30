package com.example.myapplication.di

import android.app.Application
import com.example.myapplication.ui.DetailNewsFragment
import com.example.myapplication.ui.home.HomeFragment
import dagger.BindsInstance
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [NewsModule::class, HomeModule::class])
interface ApplicationComponent {

    fun inject(homeFragment: HomeFragment)
    fun inject(detailNewsFragment: DetailNewsFragment)

    @Component.Builder
    interface Builders {
        fun build(): ApplicationComponent

        @BindsInstance
        fun applicationBind(application: Application): Builders
    }

}