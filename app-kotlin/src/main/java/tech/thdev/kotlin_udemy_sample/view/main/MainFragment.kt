package tech.thdev.kotlin_udemy_sample.view.main

import android.os.Bundle
import android.support.v4.app.Fragment
import android.text.TextUtils
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import tech.thdev.kotlin_udemy_sample.R

/**
 * Created by tae-hwan on 10/3/16.
 */
// 상속은 : 으로 구분한다.
class MainFragment : Fragment() {

    // lazy키워드를 통해 etNumberOne함수 호출시,
    // findViewById를 호출한다.
    private val etNumberOne by lazy {
        view?.findViewById(R.id.number_one) as EditText
    }

    // null로 초기화 (이때, R.id.number_two를 찾을 수 없기 때문)
    // private var etNumberTwo: EditText? = view?.findViewById(R.id.number_two) as EditText
    // 로 호출 가능하며
    // private var etNumberTwo: EditText = view?.findViewById(R.id.number_two) as EditText
    // 는 RuntimeError 발생
    private var etNumberTwo: EditText? = null

    // Java 식의 static instance // static == companion
    companion object {
        fun getInstance() = MainFragment()
    }

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View?
            = inflater?.inflate(R.layout.fragment_main, container, false)

    override fun onViewCreated(view: View?, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        etNumberTwo = view?.findViewById(R.id.number_two) as EditText

        // TODO Text Message 변경
        val textView = view?.findViewById(R.id.text) as TextView

        // val button = view.findViewById(R.id.button) as Button
        // 인 경우 null safe하지 않음
        val button = view?.findViewById(R.id.button) as Button
        button.setOnClickListener {
            textView.text = getSum().toString()
        }
    }

    /**
     * 단순 TextUtils.isEmpty를 통한 값 체크하기
     */
    private fun getSumOld(): Int {
        val one = etNumberOne.text
        val two = etNumberTwo?.text

        var oneNumber = 0
        if (!TextUtils.isEmpty(one)) {
            oneNumber = one.toString().toInt()
        }
        var twoNumber = 0
        if (two != null && two.length > 0) {
            twoNumber = two.toString().toInt()
        }

        return oneNumber + twoNumber
    }

    private fun getSum(): Int {
        val one = try {
            etNumberOne.text.toString().toInt()

        } catch (e: Exception) {
            0 // 값이 없으면 0으로 초기화
        }

        /*
         * Two의 경우 etNumberTwo가 NULL이 될 수 있습니다.
         * 그래서 다음과 같이 NULL 체크가 포함된 when을 사용할 수 있습니다
         * NULL이 포함되기 때문에 return은 null 이루어집니다
         * ?: 을 사용하여 null 대신 0을 리턴 할 수 있습니다
         *
         * 5, 6강에서 배우는 안전한 널처리 부분을 참고하시면 되겠습니다
         */
        val two = when {
            etNumberTwo?.text?.isNullOrEmpty() as Boolean -> 0
            else -> etNumberTwo?.text?.toString()?.toInt()
        } ?: 0 // 안전한 null 처리(모두 null일 경우 0을 리턴턴)

       /*
         * EX) 안전한 NULL 처리를 위한 코드 살펴보기
         *
         * Java에서는 아래와 같이 처리 가능
         *  etNubmerTwo != null ? etNumberTwo.text : ""
         *
         * Kotlin에서는 다음과 같이 처리 가능
         * etNumber?.text
         *
         * 하지만 이 경우 result는 null
         *
         * null을 제거하기 위해서는 다음과 같이 처리 가능
         *  etNumber?.text ?: ""
         */

        return one + two
    }
}