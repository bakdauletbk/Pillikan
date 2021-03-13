package kz.smartideagroup.pillikan.content.welcome

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import kz.smartideagroup.pillikan.R

class WelcomeFragment: Fragment() {

    companion object {
        const val TAG = "SignInFragment"
    }


    private var root: View? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        root = inflater.inflate(R.layout.fragment_sign_in, container, false)
        return root
    }

}