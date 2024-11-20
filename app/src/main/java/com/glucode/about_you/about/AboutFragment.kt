package com.glucode.about_you.about

import android.app.Activity
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.provider.MediaStore
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.result.contract.ActivityResultContracts
import androidx.fragment.app.Fragment
import com.glucode.about_you.about.views.ProfileCardView
import com.glucode.about_you.about.views.QuestionCardView
import com.glucode.about_you.data.EngineersRepository
import com.glucode.about_you.databinding.FragmentAboutBinding
import com.glucode.about_you.engineers.models.Engineer

class AboutFragment : Fragment() {
    private lateinit var binding: FragmentAboutBinding

    private val pickImageLauncher = registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
        if (result.resultCode == Activity.RESULT_OK && result.data != null) {
            binding.container.removeAllViews()
            val selectedImageUri: Uri? = result.data?.data
            val engineerName = arguments?.getString("name")
            EngineersRepository.updateEngineerImageName(engineerName.toString(), selectedImageUri.toString())
            setUpContent()
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

        setUpContent()
    }

    private fun setUpContent() {
        val engineerName = arguments?.getString("name")
        val engineer = EngineersRepository.engineers.first { it.name == engineerName }

        setUpProfileHeader(engineer)
        setUpQuestions(engineer)
    }

    private fun setUpProfileHeader(engineer: Engineer) {
        val profileView = ProfileCardView(requireContext())
        profileView.engineer = engineer
        profileView.registerImageClickListener {
            val intent = Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI)
            pickImageLauncher.launch(intent)
        }

        binding.container.addView(profileView)
    }

    private fun setUpQuestions(engineer: Engineer) {
        engineer.questions.forEach { question ->
            val questionView = QuestionCardView(requireContext())
            questionView.title = question.questionText
            questionView.answers = question.answerOptions
            questionView.selection = question.answer.index

            binding.container.addView(questionView)
        }
    }
}