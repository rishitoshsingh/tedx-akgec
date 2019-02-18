package `in`.co.bdcoe.tedxakgec.Fragments


import `in`.co.bdcoe.tedxakgec.Adapters.SpeakersAdapter
import `in`.co.bdcoe.tedxakgec.POJOs.Speaker
import `in`.co.bdcoe.tedxakgec.R
import `in`.co.bdcoe.tedxakgec.SpeakerDialog
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v4.view.ViewCompat
import android.support.v7.widget.DefaultItemAnimator
import android.support.v7.widget.GridLayoutManager
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import kotlinx.android.synthetic.main.fragment_speakers.*
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

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val viewManager = GridLayoutManager(context,2)
        val speakersAdapter = object :SpeakersAdapter(speakers, context!!){
            override fun showDialog(speaker: Speaker) {
                val ft: android.support.v4.app.FragmentTransaction = fragmentManager!!.beginTransaction()
                val dialogFragment = SpeakerDialog.newInstance(speaker)
                dialogFragment.show(ft,"dialog")
            }
        }
        speakers_recycler_view.apply {
            setHasFixedSize(true)
            layoutManager = viewManager
            adapter = speakersAdapter
            itemAnimator = DefaultItemAnimator()
            isNestedScrollingEnabled = false
        }
    }
}