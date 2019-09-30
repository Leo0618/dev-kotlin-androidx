package vip.okfood.ktx.ui

import android.os.Bundle
import android.text.TextUtils
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import kotlinx.android.synthetic.main.fragment_author_info.*
import kotlinx.coroutines.*
import vip.okfood.ktx.R
import vip.okfood.ktx.net.API
import vip.okfood.ktx.net.RetrofitWrap

/**
 * function:AuthorInfoFragment
 *
 * <p></p>
 * Created by Leo on 2019/9/29.
 */
class AuthorInfoFragment : Fragment(), CoroutineScope by MainScope() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_author_info, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        content.text = "加载中..."
        launch {
            val data = withContext(Dispatchers.IO) {
                RetrofitWrap.service(API::class.java).info()
            }
            content.text = if (TextUtils.isEmpty(data)) "error" else data
        }
    }


    override fun onDestroyView() {
        super.onDestroyView()
        cancel()
    }

}
