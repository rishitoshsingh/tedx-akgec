package `in`.co.bdcoe.tedxakgec

import `in`.co.bdcoe.tedxakgec.POJOs.Speaker
import android.app.AlertDialog
import android.app.Dialog
import android.net.Uri
import android.os.Bundle
import android.support.v4.app.DialogFragment
import android.view.LayoutInflater
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import com.bumptech.glide.Glide

/**
 * Created by rishi on 13/2/19.
 */
class SpeakerDialog : DialogFragment() {

    private lateinit var speaker: Speaker
    private val UPLOAD_BASE_URL = "https://tedxakgec.com/api/uploads/"

    companion object {
        fun newInstance(speaker: Speaker): DialogFragment {
            val fragment = SpeakerDialog()
            val args = Bundle()
            args.putSerializable("speaker", speaker)
            fragment.arguments = args
            return fragment
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setStyle(android.support.v4.app.DialogFragment.STYLE_NORMAL, R.style.SpeakerDialogStyle)
        val bundle = this.arguments
        if (bundle != null) {
            if (arguments != null)
                speaker = arguments!!.getSerializable("speaker") as Speaker
        }

    }

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {

        val inflater = LayoutInflater.from(activity)
        val view = inflater.inflate(R.layout.dialog_speaker, null)
        val speakerNameView = view.findViewById<TextView>(R.id.speaker_dialog_name)
        val speakerDesignationView = view.findViewById<TextView>(R.id.speaker_dialog_designation)
        val speakerDescription = view.findViewById<TextView>(R.id.speaker_dialog_description)
        val speakerImageView = view.findViewById<ImageView>(R.id.speaker_dialog_image)
        val speakerCloseButton = view.findViewById<Button>(R.id.speaker_dialog_close)

        speakerNameView.text = speaker.name
        speakerDesignationView.text = speaker.designation
        speakerDescription.text = speaker.description

        val imageUri = Uri.parse(UPLOAD_BASE_URL+speaker.imgurl)
        Glide.with(context!!)
                .load(imageUri)
                .into(speakerImageView)

        speakerCloseButton.setOnClickListener {
            dialog.dismiss()
        }

        return AlertDialog.Builder(this.activity!!)
                .setView(view)
                .setCancelable(true)
                .create()
    }

}