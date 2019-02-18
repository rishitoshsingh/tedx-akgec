package `in`.co.bdcoe.tedxakgec.Fragments


import `in`.co.bdcoe.tedxakgec.POJOs.AboutUs
import `in`.co.bdcoe.tedxakgec.R
import android.content.Context
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.support.v4.app.Fragment
import android.text.Html
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import kotlinx.android.synthetic.main.fragment_about_us.*
import android.content.pm.PackageManager



/**
 * A simple [Fragment] subclass.
 */
class AboutUsFragment : Fragment() {

    private lateinit var aboutUs: AboutUs

    companion object {
        fun newInstance(aboutUs: AboutUs): AboutUsFragment {
            val fragment = AboutUsFragment()
            val args = Bundle()
            args.putSerializable("aboutUs", aboutUs)
            fragment.arguments = args
            return fragment
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        if (arguments != null) {
            aboutUs = arguments!!.getSerializable("aboutUs") as AboutUs
        }
        return inflater.inflate(R.layout.fragment_about_us, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        about_ted.text = aboutUs.ted.trim()
        about_tedx.text = aboutUs.tedx.trim()
        about_tedx_akgec.text = aboutUs.tedxAkgec.trim()
        love_text_view.text = Html.fromHtml("&#10084;")

        instagram_tedxakgec.setOnClickListener {
            val newIntent = Intent(Intent.ACTION_VIEW,
                    Uri.parse("https://www.instagram.com/tedxakgec/"))
            startActivity(newIntent)
        }
        linkedin_tedxakgec.setOnClickListener {
            val newIntent = Intent(Intent.ACTION_VIEW,
                    Uri.parse("https://www.linkedin.com/company/tedx-akgec/about/"))
            startActivity(newIntent)
        }
        facebook_tedxakgec.setOnClickListener {
            val facebookUrl = getFacebookPageURL()
            val newIntent = Intent(Intent.ACTION_VIEW)
            newIntent.data = Uri.parse(facebookUrl)
            startActivity(newIntent)
        }
        link_tedxakgec.setOnClickListener {
            val newIntent = Intent(Intent.ACTION_VIEW,
                    Uri.parse("https://tedxakgec.com/"))
            startActivity(newIntent)
        }
    }

    fun getFacebookPageURL(): String {
        val FACEBOOK_URL = "https://www.facebook.com/TEDXAKGEC2019"
        val FACEBOOK_PAGE_ID = "TEDXAKGEC2019"

        return try {
            val packageManager = context!!.packageManager
            val versionCode = packageManager.getPackageInfo("com.facebook.katana", 0).versionCode
            if (versionCode >= 3002850) { //newer versions of fb app
                "fb://facewebmodal/f?href=$FACEBOOK_URL"
            } else { //older versions of fb app
                "fb://page/$FACEBOOK_PAGE_ID"
            }
        } catch (e: PackageManager.NameNotFoundException) {
            FACEBOOK_URL
        }

    }

}