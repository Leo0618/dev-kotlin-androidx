package vip.okfood.ktx.ui

import android.content.Context
import android.content.Intent
import android.graphics.Color
import android.graphics.Paint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.layout_titlebar.*




/**
 * function:MainActivity
 *
 * <p></p>
 * Created by Leo on 2019/9/29.
 */
class MainActivity: AppCompatActivity() {
    private var mDataList: ArrayList<ItemData> = ArrayList()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(vip.okfood.ktx.R.layout.activity_main)
        buttonTitleBack.visibility = View.GONE
        textTitle.text = "开发-".plus(getString(vip.okfood.ktx.R.string.app_name))
        mDataList.add(ItemData("Android基础(W3C)", "http://www.runoob.com/w3cnote/android-tutorial-intro.html"))
        mDataList.add(ItemData("Android知识收录(简书)", "https://www.jianshu.com/c/3fde3b545a35"))
        mDataList.add(ItemData("Kolin基础(W3C)", "https://www.runoob.com/kotlin/kotlin-tutorial.html"))
        mDataList.add(ItemData("Kolin语言中文站", "https://www.kotlincn.net/docs/reference/basic-syntax.html"))
        mDataList.add(ItemData("掘金-android", "https://juejin.im/timeline/android"))

        listUI.adapter = MyAdapter(this, android.R.layout.simple_list_item_1, mDataList)
        listUI.setOnItemClickListener { _, _, position, _ ->
            val itemData: ItemData = mDataList[position]
            val intent = Intent(this, CommWebActivity::class.java)
            intent.putExtra("title", itemData.title)
            intent.putExtra("url", itemData.url)
            startActivity(intent)
        }
    }

    class MyAdapter(context: Context, resource: Int, objects: ArrayList<ItemData>): ArrayAdapter<ItemData>(context, resource, objects) {
        override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
            val view: View = convertView ?: LayoutInflater.from(parent.context).inflate(android.R.layout.simple_list_item_1, parent, false)

            val colorBg: Int = if (position%2 == 0) {
                Color.parseColor("#88CCCCCC")
            } else {
                Color.parseColor("#55CCCCCC")
            }

            view.setBackgroundColor(colorBg)
            val text: TextView = view.findViewById(android.R.id.text1)
            val titleContent = getItem(position)!!.title
            text.paint.flags = Paint.UNDERLINE_TEXT_FLAG
            text.paint.isAntiAlias = true
            text.text = titleContent
            return view
        }
    }

    class ItemData(var title: String, var url: String)
}
