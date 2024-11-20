package com.glucode.about_you.about

import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.activity.result.contract.ActivityResultContracts
import androidx.fragment.app.Fragment
import com.glucode.about_you.R
import com.glucode.about_you.about.views.QuestionCardView
import com.glucode.about_you.databinding.FragmentAboutBinding
import com.glucode.about_you.engineers.models.Engineer
import com.glucode.about_you.mockdata.MockData

class AboutFragment: Fragment() {
    private lateinit var binding: FragmentAboutBinding
    private lateinit var imageView: ImageView
    private lateinit var engineer: Engineer

    private val pickImageLauncher =
        registerForActivityResult(ActivityResultContracts.GetContent()) { uri: Uri? ->
            uri?.let {
                engineer.profileImageUri.value = it
                imageView.setImageURI(engineer.profileImageUri.value)
            }
        }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentAboutBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        imageView = view.findViewById(R.id.profile_image)

        imageView.setOnClickListener {
            pickImage()
        }

        setUpQuestions()
    }

    private fun setUpQuestions() {
        val engineerName = arguments?.getString("name")
        engineer = MockData.engineers.first { it.name == engineerName }

        binding.engineerProfileCard.engineerName.text = engineer.name
        binding.engineerProfileCard.engineerRole.text = engineer.role

        engineer.questions.forEach { question ->
            val questionView = QuestionCardView(requireContext())
            questionView.title = question.questionText
            questionView.answers = question.answerOptions
            questionView.selection = question.answer.index

            binding.container.addView(questionView)
        }
    }

    private fun pickImage() {
        pickImageLauncher.launch("image/*")
    }
}