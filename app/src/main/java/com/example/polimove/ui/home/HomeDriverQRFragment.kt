package com.example.polimove.ui.home

import android.content.ContextParams
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.annotation.Nullable
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentResultListener
import androidx.navigation.fragment.findNavController
import com.example.polimove.databinding.FragmentHomeDriverqrBinding
import com.example.polimove.services.routes.UserService
import com.google.zxing.integration.android.IntentIntegrator


class HomeDriverQRFragment: Fragment() {
    private var _binding: FragmentHomeDriverqrBinding? = null
    private var cedulaStudent:String?=""
    private val binding get() = _binding!!

    //INICIALIZACIÓN DE VARIABLES
    private lateinit var textViewNameStd: TextView
    private lateinit var buttonContinuar: Button

     override fun onCreate(savedInstanteSate: Bundle?) {
        super.onCreate(savedInstanteSate)
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

        integrador()
        parentFragmentManager.setFragmentResultListener("key",this, FragmentResultListener {
                Key, result ->
            cedulaStudent = result.getString("cedula")
            Log.d("CI", "La cédula: $cedulaStudent")
            UserService.getDriverData(cedulaStudent as String) {nameUser->
                textViewNameStd.text= nameUser.name+" "+nameUser.lastName
            }
        })
        return root
    }

    override fun onActivityResult(
        requestCode: Int,
        resultCode: Int,
        @Nullable data: Intent?
    ) {
        super.onActivityResult(requestCode, resultCode, data)
        val result = IntentIntegrator.parseActivityResult(requestCode, resultCode, data)
        cedulaStudent = result.contents
        //textViewNameStd.setText(datos)
    }

    fun integrador(){
        val integrador = IntentIntegrator(this.activity)
        integrador.setDesiredBarcodeFormats(IntentIntegrator.QR_CODE)
        integrador.setPrompt("Escaneando código QR")
        integrador.setCameraId(0)
        integrador.setOrientationLocked(true)
        integrador.setBeepEnabled(true)
        integrador.setBarcodeImageEnabled(true)
        integrador.setTimeout(10000)
        integrador.initiateScan()
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        buttonContinuar.setOnClickListener{
            findNavController().navigate(com.example.polimove.R.id.action_home_driverqr_to_home_conductor)
        }
    }
    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }



}

