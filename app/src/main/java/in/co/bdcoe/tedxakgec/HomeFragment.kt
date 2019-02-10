package `in`.co.bdcoe.tedxakgec


import android.content.Intent
import android.os.Bundle
import android.provider.CalendarContract
import android.provider.CalendarContract.Events
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import kotlinx.android.synthetic.main.fragment_home.*
import java.util.*


/**
 * A simple [Fragment] subclass.
 */
class HomeFragment : Fragment() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_home, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        create_event_button.setOnClickListener {
            val beginTime = Calendar.getInstance()
            beginTime.set(2019, 3, 19, 7, 30)

            val endTime = Calendar.getInstance()
            endTime.set(2012, 3, 19, 8, 30)

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