package cl.blackmind.aldoapp.ui.home

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import cl.blackmind.aldoapp.databinding.ActivityHomeBinding
import cl.blackmind.aldoapp.ui.viewmodel.SharedViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel

class HomeActivity : AppCompatActivity() {

    private val sharedViewModel by viewModel<SharedViewModel>()

    private lateinit var binding: ActivityHomeBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityHomeBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }
}