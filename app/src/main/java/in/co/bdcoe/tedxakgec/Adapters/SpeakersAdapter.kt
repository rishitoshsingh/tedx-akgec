package `in`.co.bdcoe.tedxakgec.Adapters

import `in`.co.bdcoe.tedxakgec.POJOs.Speaker
import `in`.co.bdcoe.tedxakgec.R
import android.content.Context
import android.graphics.drawable.Drawable
import android.support.v7.widget.CardView
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.DataSource
import com.bumptech.glide.load.engine.GlideException
import com.bumptech.glide.request.RequestListener
import com.bumptech.glide.request.RequestOptions

/**
 * Created by rishi on 12/2/19.
 */
abstract class SpeakersAdapter(speakers:MutableList<Speaker>, context:Context): RecyclerView.Adapter<SpeakersAdapter.ViewHolder>() {

    val mContext = context
    val mSpeakers: MutableList<Speaker> = speakers

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.speaker,parent,false)
        return ViewHolder(view)
    }

    override fun getItemCount() = mSpeakers.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val speaker = mSpeakers[position]

        holder.speakerName.text = speaker.name
        holder.speakerDesignation.text = speaker.designation
        Glide.with(mContext)
//                .load(imageUri)
//                .listener(object : RequestListener<Drawable> {
//                    override fun onResourceReady(resource: Drawable?, model: Any?, target: com.bumptech.glide.request.target.Target<Drawable>?, dataSource: DataSource?, isFirstResource: Boolean): Boolean {
//                        holder.imageProgressBar.visibility = View.GONE
//                        return false
//                    }
//
//                    override fun onLoadFailed(e: GlideException?, model: Any?, target: com.bumptech.glide.request.target.Target<Drawable>?, isFirstResource: Boolean): Boolean {
//                        holder.imageProgressBar.visibility = View.GONE
//                        return false
//                    }
//                })
//                .apply(RequestOptions()
//                        .error(R.drawable.header_placeholder)
//                        .centerCrop())
//                .into(newsViewHolder.newsImage)

        holder.speakerCardRoot.setOnClickListener { showDialog(speaker) }

    }

    abstract fun showDialog(speaker:Speaker)

    inner class ViewHolder(view: View): RecyclerView.ViewHolder(view) {
        val speakerImage: ImageView = view.findViewById(R.id.speaker_image)
        val speakerName: TextView = view.findViewById(R.id.speaker_name)
        val speakerDesignation: TextView = view.findViewById(R.id.speaker_designation)
        val speakerCardRoot:LinearLayout = view.findViewById(R.id.speaker_root)
    }

}