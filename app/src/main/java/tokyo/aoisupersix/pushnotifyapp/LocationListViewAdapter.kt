package tokyo.aoisupersix.pushnotifyapp

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.TextView
import kotlinx.android.synthetic.main.location_list_view_item.view.*

/**
 * LocationListViewに値を代入するアダプタクラスです。
 * @param context アプリケーションのコンテキスト
 * @param items ListViewで表示するLocationListViewItemクラスのリスト
 * @param inflater LayoutInflater
 */
class LocationListViewAdapter(
        var context: Context,
        var items: MutableList<LocationListViewItem>,
        private val inflater: LayoutInflater = context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
): BaseAdapter(){

    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
        var v = convertView
        var holder: LocationListViewHolder? = null

        v?.let {
            holder = it.tag as LocationListViewHolder?
        } ?: run {
            v = inflater.inflate(R.layout.location_list_view_item, null)
            holder = LocationListViewHolder(v?.findViewById(R.id.userName) as TextView, v?.findViewById(R.id.location) as TextView, v?.findViewById(R.id.time) as TextView)
            v?.tag = holder
        }

        holder?.let {
            //it.textView.userName = items.get(position).userName
            it.userNameTextView.text = items.get(position).userName
            it.locationTextView.text = items.get(position).location
            it.timeTextView.text = items.get(position).time
        }

        return v as View
    }

    override fun getItem(position: Int): Any {
        return items[position]
    }

    override fun getItemId(position: Int): Long {
        return position.toLong()
    }

    override fun getCount(): Int {
        return items.size
    }

    class LocationListViewHolder(var userNameTextView: TextView, var locationTextView: TextView, var timeTextView: TextView)
}