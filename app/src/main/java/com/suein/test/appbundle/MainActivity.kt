package com.suein.test.appbundle

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.list_item.view.*
import org.jetbrains.anko.toast
import retrofit2.Callback
import retrofit2.Response

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        rc_item_list.addItemDecoration(DividerItemDecoration(this, DividerItemDecoration.VERTICAL))
        callNet()
    }

    private fun callNet() {
        NetRetrofit.instance().service.list.enqueue(object : Callback<List<Photo>> {
            override fun onFailure(call: retrofit2.Call<List<Photo>>, t: Throwable) {
                toast("리스트를 호출 하는데 에러 발생")
            }

            override fun onResponse(call: retrofit2.Call<List<Photo>>, response: Response<List<Photo>>) {
                val responseData = response.body()
                checkNotNull(responseData).let {
                    pb_progress.visibility = View.GONE
                    initList(it)
//                    initList(it.subList(it.size - 10, it.size))
                }
            }
        })
    }

    fun initList(list: List<Photo>) {
        rc_item_list.post {
            rc_item_list.adapter = ItemListAdapter(list)
        }
    }
}


class ItemListAdapter(private val items: List<Photo>) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    override fun getItemCount() = items.size

    override fun onCreateViewHolder(parent: ViewGroup, position: Int): RecyclerView.ViewHolder {
        return ListItemViewHolder.create(parent)
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        (holder as ListItemViewHolder).view.run {
            LayoutBindingUtils.setImageResource(iv_photo, items[position])
            tv_text.text = items[position].author
        }
    }

    class ListItemViewHolder(val view: View) : RecyclerView.ViewHolder(view) {
        companion object {
            fun create(parent: ViewGroup): ListItemViewHolder {
                return ListItemViewHolder(
                        LayoutInflater.from(parent.context).inflate(
                                R.layout.list_item,
                                parent,
                                false
                        )
                )
            }
        }
    }
}