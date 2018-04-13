package tokyo.aoisupersix.pushnotifyapp

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.TextView

class LocationListViewAdapter(var context: Context, var items: MutableList<LocationListViewItem>): BaseAdapter(){
    val inflater: LayoutInflater

    init {
        inflater = context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
    }

    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
        var v = convertView
        var holder: LocationListViewHolder? = null

        v?.let {
            holder = it.tag as LocationListViewHolder?
        } ?: run {
            v = inflater.inflate(R.layout.location_list_view_item, null)
            holder = LocationListViewHolder(v?.findViewById(R.id.text) as TextView)
            v?.tag = holder
        }

        holder?.let {
            it.textView.text = items.get(position).text
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

    class LocationListViewHolder(var textView: TextView)
}