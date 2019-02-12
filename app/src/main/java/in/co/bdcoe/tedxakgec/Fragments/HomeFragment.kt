package `in`.co.bdcoe.tedxakgec.Fragments


import `in`.co.bdcoe.tedxakgec.Network.ServiceGenerator
import `in`.co.bdcoe.tedxakgec.Network.TedxAkgecClient
import `in`.co.bdcoe.tedxakgec.R
import android.content.Intent
import android.os.Bundle
import android.provider.CalendarContract
import android.provider.CalendarContract.Events
import android.support.v4.app.Fragment
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import kotlinx.android.synthetic.main.fragment_home.*
import java.text.SimpleDateFormat
import java.util.*


/**
 * A simple [Fragment] subclass.
 */
class HomeFragment : Fragment() {

    private var eventDate: Long = 0
    private lateinit var nextEvent: Calendar

    companion object {
        fun newInstance(eventDate: Long): HomeFragment {
            val fragment = HomeFragment()
            val args = Bundle()
            args.putLong("eventDate", eventDate)
            fragment.arguments = args
            return fragment
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        if (arguments != null)
            eventDate = arguments!!.getLong("eventDate")
        return inflater.inflate(R.layout.fragment_home, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        nextEvent = Calendar.getInstance()
        nextEvent.add(Calendar.DATE, eventDate.toInt())
        val format1 = SimpleDateFormat("dd MMMM yyyy")
        val nextEventDateString = format1.format(nextEvent.time)
        try {
            next_event.text = nextEventDateString
            days_left.text = eventDate.toString()
            createListner()
        } catch (ex: Exception) {
            Log.d("updateDateAndUI()", "Error", ex)
        }
    }

    private fun createListner() {
        create_event_button.setOnClickListener {
            val beginTime = nextEvent
            beginTime.set(Calendar.HOUR, 9)
            beginTime.set(Calendar.MINUTE, 0)
            val endTime = nextEvent
            endTime.set(Calendar.HOUR, 11)
            endTime.set(Calendar.MINUTE, 0)
            val intent = Intent(Intent.ACTION_INSERT)
                    .setData(Events.CONTENT_URI)
                    .putExtra(CalendarContract.EXTRA_EVENT_BEGIN_TIME,
                            beginTime.timeInMillis)
                    .putExtra(CalendarContract.EXTRA_EVENT_END_TIME,
                            endTime.timeInMillis)
                    .putExtra(Events.TITLE, "TedxAkgec")
                    .putExtra(Events.EVENT_LOCATION, "Ajay Kumar Garg Engineering College")
                    .putExtra(Events.AVAILABILITY, Events.AVAILABILITY_BUSY)
//                    .putExtra(Intent.EXTRA_EMAIL, "rowan@example.com,trevor@example.com")
            startActivity(intent)
        }
    }
}
