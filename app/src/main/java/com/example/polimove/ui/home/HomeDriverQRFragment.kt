package com.example.polimove.ui.home

import android.app.Activity
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.fragment.app.Fragment
import com.example.polimove.databinding.FragmentHomeDriverqrBinding
import com.google.zxing.integration.android.IntentIntegrator


class HomeDriverQRFragment: Fragment() {
    private var _binding: FragmentHomeDriverqrBinding? = null

    private val binding get() = _binding!!

    //INICIALIZACIÓN DE VARIABLES
    private lateinit var textViewQRCode: TextView
    private lateinit var textViewNameStd: TextView
    private lateinit var textViewNumberAsiento: TextView
    private lateinit var buttonContinuar: Button

     override fun onCreate(savedInstanteSate: Bundle?) {
        super.onCreate(savedInstanteSate)

    }
    /*
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        //new IntentIntegrator(this).initiateScan();
    }*/

    /*
    @override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable,  Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        IntentResult result = IntentIntegrator.parseActivityResult(requestCode, resultCode, data);
        String datos = result.getContents();

    }

 */

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentHomeDriverqrBinding.inflate(inflater,container,false)
        val root: View = binding.root
        buttonContinuar = binding.buttonContinuar
        textViewNameStd = binding.textViewNameStd

        buttonContinuar.setOnClickListener{
            fun onClick(view: View?) {

                val integrador = IntentIntegrator(this.activity)
                integrador.setDesireBarcodeFormats(IntentIntegrator.ALL_CODE_TYPES)
                integrador.setPrompt("Lecctor - CDP")
                integrador.setCameraId(0)
                integrador.setBeepEnabled(true)
                integrador.setBarcodeImageEnabled(true)
                integrador.initiateScan()
            }
        })

/*
        val QR: TextView = binding.textViewQRCODE
        homeViewModel.QRCODEDRIVER.observe(viewLifecycleOwner) {
            QR.text = it
        }
        val numberAsient:TextView = binding.textViewNumberAsiento
        homeViewModel.numberAsiento.observe(viewLifecycleOwner){
            numberAsient.text = it
        }
        val nameStudent:TextView=binding.textViewNameStd
        homeViewModel.nameStd.observe(viewLifecycleOwner){
            nameStudent.text = it
        }

 */
        return root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}