package com.jjoh.presentation.view.chat

import android.content.Intent
import android.net.Uri
import android.provider.MediaStore
import android.util.Log
import android.view.View
import android.view.inputmethod.EditorInfo
import android.view.inputmethod.InputMethodManager
import androidx.activity.result.ActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat.getSystemService
import androidx.fragment.app.activityViewModels
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.google.firebase.auth.ktx.auth
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import com.google.firebase.database.ktx.getValue
import com.google.firebase.ktx.Firebase
import com.jjoh.presentation.base.BaseFragment
import com.jjoh.presentation.R
import com.jjoh.presentation.databinding.FragmentProfileBinding
import com.jjoh.presentation.view.model.Friend
import com.jjoh.presentation.viewmodel.ProfileViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ProfileFragment : BaseFragment<FragmentProfileBinding>(R.layout.fragment_profile) {
    private val viewModel by activityViewModels<ProfileViewModel>()

    companion object {
        private var imageUri: Uri? = null
        private val uid = Firebase.auth.currentUser?.uid.toString()
        private val fireDatabase = FirebaseDatabase.getInstance().reference
        var changedName: String? = null

        fun newInstance(): ProfileFragment {
            return ProfileFragment()
        }
    }

    private val getContent =
        registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result: ActivityResult ->
            if (result.resultCode == AppCompatActivity.RESULT_OK) {
                imageUri = result.data?.data
                binding.profileImageview.setImageURI(imageUri)
                viewModel.uploadNewImage(uid, imageUri)
            }
        }

    override fun init() {
        binding.fragment = this

        setFirstScreen()
        observe()
    }

    private fun setFirstScreen() {
        fireDatabase.child("users").child(uid)
            .addListenerForSingleValueEvent(object : ValueEventListener {
                override fun onCancelled(error: DatabaseError) {}
                override fun onDataChange(snapshot: DataSnapshot) {
                    val userProfile = snapshot.getValue<Friend>()
                    println(userProfile)

                    Glide.with(requireContext()).load(userProfile?.profileImageUrl)
                        .apply(RequestOptions().circleCrop())
                        .into(binding.profileImageview)

                    binding.profileTextviewName.text = userProfile?.userName
                    binding.profileTextviewEmail.text = userProfile?.email

                }
            })
    }

    fun changeImage(view: View) {
        val intentImage = Intent(Intent.ACTION_PICK)
        intentImage.type = MediaStore.Images.Media.CONTENT_TYPE
        getContent.launch(intentImage)
    }

    fun changeName(view: View) {
        binding.profileTextviewName.visibility = View.INVISIBLE
        binding.profileEdittextName.visibility = View.VISIBLE

        binding.profileButton.setOnClickListener {
            binding.loading.visibility = View.VISIBLE
            changedName = binding.profileEdittextName.text.toString()

            if (changedName != null) {
                viewModel.uploadNewName(changedName!!, uid)
            } else {
                binding.loading.visibility = View.INVISIBLE
                shortShowToast("바꿀 이름을 입력해주세요")
            }
        }
    }

    private fun observe() {
        viewModel.successImage.observe(this) {
            Log.d("UploadImage", "observe: success")
        }
        viewModel.failureImage.observe(this) {
            shortShowToast("이미지를 업로드하는데 실패했어요")
        }
        viewModel.successName.observe(this) {
            binding.loading.visibility = View.INVISIBLE
            setName(changedName!!)
        }
        viewModel.failureName.observe(this) {
            shortShowToast("이름 변경을 실패했어요")
        }
    } 

    private fun setName(str: String) {
        binding.profileTextviewName.text = str
        binding.profileEdittextName.visibility = View.INVISIBLE
        binding.profileTextviewName.visibility = View.VISIBLE

        setFirstScreen()
    }

}