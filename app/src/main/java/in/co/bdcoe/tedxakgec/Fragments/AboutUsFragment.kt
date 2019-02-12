package `in`.co.bdcoe.tedxakgec.Fragments


import `in`.co.bdcoe.tedxakgec.POJOs.AboutUs
import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import `in`.co.bdcoe.tedxakgec.R
import kotlinx.android.synthetic.main.fragment_about_us.*

/**
 * A simple [Fragment] subclass.
 */
class AboutUsFragment : Fragment() {

    private lateinit var aboutUs:AboutUs

    companion object {
        fun newInstance(aboutUs: AboutUs): AboutUsFragment {
            val fragment = AboutUsFragment()
            val args = Bundle()
            args.putSerializable("aboutUs",aboutUs)
            fragment.arguments = args
            return fragment
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        if (arguments != null){
            aboutUs = arguments!!.getSerializable("aboutUs") as AboutUs
        }
        return inflater.inflate(R.layout.fragment_about_us, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        about_ted.text = aboutUs.ted
        about_tedx.text = aboutUs.tedx
        about_tedx_akgec.text = aboutUs.tedxAkgec
    }
}