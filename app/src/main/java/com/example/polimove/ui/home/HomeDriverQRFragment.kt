package com.example.polimove.ui.home

import android.R
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.annotation.Nullable
import androidx.fragment.app.Fragment
import com.example.polimove.databinding.FragmentHomeDriverqrBinding
import com.google.zxing.integration.android.IntentIntegrator


class HomeDriverQRFragment: Fragment() {
    private var _binding: FragmentHomeDriverqrBinding? = null

    private val binding get() = _binding!!

    //INICIALIZACIÓN DE VARIABLES
    private lateinit var textViewNameStd: TextView
    private lateinit var buttonContinuar: Button

     override fun onCreate(savedInstanteSate: Bundle?) {
        super.onCreate(savedInstanteSate)
         IntentIntegrator(activity).initiateScan()
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentHomeDriverqrBinding.inflate(inflater,container,false)
        val root: View = binding.root
        buttonContinuar = binding.buttonContinuar
        textViewNameStd = binding.textViewNameStd

        buttonContinuar.setOnClickListener {
            //getActivity()?.onBackPressed()

        }

        return root
    }

    override fun onActivityResult(
        requestCode: Int,
        resultCode: Int,
        @Nullable data: Intent?
    ) {
        super.onActivityResult(requestCode, resultCode, data)
        val result = IntentIntegrator.parseActivityResult(requestCode, resultCode, data)
        val datos = result.contents
        textViewNameStd.setText(datos)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }



}


