package `in`.co.bdcoe.tedxakgec.Fragments


import `in`.co.bdcoe.tedxakgec.POJOs.Speaker
import `in`.co.bdcoe.tedxakgec.R
import android.os.Bundle
import android.support.v4.app.Fragment
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import java.io.Serializable


/**
 * A simple [Fragment] subclass.
 */
class SpeakersFragment : Fragment() {

    private lateinit var speakers: MutableList<Speaker>

    companion object {
        fun newInstance(speakers: MutableList<Speaker>): SpeakersFragment {
            val fragment = SpeakersFragment()
            val args = Bundle()
            args.putSerializable("speakers",speakers as Serializable)
            fragment.arguments = args
            return fragment
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        if (arguments != null){
            speakers = arguments!!.getSerializable("speakers") as MutableList<Speaker>
        }
        Log.d("Speakers",speakers.toString())
        return inflater.inflate(R.layout.fragment_speakers, container, false)


    }

}