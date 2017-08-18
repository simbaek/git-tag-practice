package tech.thdev.kotlin_udemy_sample.util

import android.support.v4.app.Fragment
import android.support.v7.app.AppCompatActivity

/**
 * Created by tae-hwan on 10/3/16.
 */
// TODO Fragment replace 함수 생성
// 중위 표기법

// class를 따로 키워드로 쓰지 않아도 된다.
fun AppCompatActivity.replaceFragmentToActivity(fragment: Fragment, frameId: Int) {
    val transaction = this.supportFragmentManager.beginTransaction()
    transaction.replace(frameId, fragment)
    transaction.commit()
}