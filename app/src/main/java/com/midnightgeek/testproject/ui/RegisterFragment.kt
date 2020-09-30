package com.midnightgeek.testproject.ui

import android.app.Activity.RESULT_OK
import android.content.Intent
import android.graphics.Bitmap
import android.graphics.ImageDecoder
import android.net.Uri
import android.os.Build
import android.os.Bundle
import android.provider.MediaStore
import android.util.Base64
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.midnightgeek.testproject.MainActivity
import com.midnightgeek.testproject.R
import com.midnightgeek.testproject.databinding.RegisterFragmentBinding
import com.midnightgeek.testproject.utils.ViewModelFactory
import dagger.android.support.DaggerFragment
import java.io.ByteArrayOutputStream
import java.util.*
import javax.inject.Inject


class RegisterFragment : DaggerFragment() {
    lateinit var binder: RegisterFragmentBinding
    var photo:String=""
    @Inject
    lateinit var viewModelFactory: ViewModelFactory
    private lateinit var viewModel: RegisterFragmentViewModel

    companion object {
        val TAG = "RegisterFragment"
        fun newInstance() = RegisterFragment()
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binder = DataBindingUtil.inflate(inflater, R.layout.register_fragment, container, false)
        val view = binder.root
        viewModel =
            ViewModelProviders.of(this, viewModelFactory).get(RegisterFragmentViewModel::class.java)
        binder.viewmodel = viewModel
        initObserver()
        return view
    }

    fun initObserver() {
        viewModel.onClick.observe(this, Observer {
            if (it == 2) {
                if (isValidData()) {
                    binder.prgBar.visibility = View.VISIBLE
                    viewModel.regiser(
                        binder.edtName.text.toString(),
                        binder.edtFamily.text.toString(),
                        binder.edtEmail.text.toString(),
                        binder.edtPass.text.toString(),
                        photo
                    )
                } else {
                    Toast.makeText(
                        context,
                        getString(R.string.toast_fill_all_data),
                        Toast.LENGTH_LONG
                    ).show()
                }
            }else if(it == 1){
                val intent = Intent(Intent.ACTION_GET_CONTENT)
                intent.type = "*/*"
                startActivityForResult(intent, 1000)

            }
        })

        viewModel.registerData.observe(this, Observer {
            binder.prgBar.visibility = View.GONE
            if (it.success) {
                Toast.makeText(context, getString(R.string.toast_reg_success), Toast.LENGTH_LONG)
                    .show()
                (activity as MainActivity).onBackPressed()
            } else {
                if (it.errors.size > 0 && it.errors.contains("email"))
                    Toast.makeText(
                        context,
                        getString(R.string.toast_reg_fail_email),
                        Toast.LENGTH_LONG
                    ).show()
                else
                    Toast.makeText(context, getString(R.string.toast_reg_fail), Toast.LENGTH_LONG)
                        .show()

            }
        })
    }

    fun isValidData(): Boolean {
        if (binder.edtName.text.isNullOrEmpty())
            return false
        if (binder.edtFamily.text.isNullOrEmpty())
            return false
        if (binder.edtPass.text.isNullOrEmpty())
            return false
        if (binder.edtEmail.text.isNullOrEmpty())
            return false
        if (!binder.edtEmail.text.toString().contains("@"))
            return false
        if (!Regex("[a-zA-Z0-9._-]+@[a-z]+\\.[a-z]+").containsMatchIn(binder.edtEmail.text.toString()))
            return false

        return true
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode==1000 && resultCode == RESULT_OK && data != null){

            val selectedImage: Uri? = data.data
            binder.imgAvatar.setImageURI(selectedImage.toString())
            val bitmap = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.P) {
                ImageDecoder.decodeBitmap(ImageDecoder.createSource(requireContext().contentResolver,
                    selectedImage!!
                ))
            } else {
                MediaStore.Images.Media.getBitmap(requireContext().contentResolver, selectedImage)
            }

            var cbitmap=Bitmap.createScaledBitmap(bitmap, 32, 32, false)

            val baos = ByteArrayOutputStream()
            cbitmap.compress(Bitmap.CompressFormat.JPEG, 50, baos)
            val b = baos.toByteArray()
            photo= Base64.encodeToString(b,Base64.DEFAULT)
            Log.e("photo",photo)
        }
    }

}