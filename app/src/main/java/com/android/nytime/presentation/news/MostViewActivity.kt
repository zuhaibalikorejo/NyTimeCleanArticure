package com.android.nytime.presentation.news

import android.os.Bundle
import android.view.View.GONE
import android.view.View.VISIBLE
import android.widget.Toast
import android.widget.Toast.LENGTH_LONG
import android.widget.Toast.LENGTH_SHORT
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import com.android.nytime.R
import com.android.nytime.databinding.ActivityNewsBinding
import com.android.nytime.utils.isNetworkAvailable
import kotlinx.android.synthetic.main.activity_news.*

import org.koin.android.viewmodel.ext.android.viewModel

class MostViewActivity : AppCompatActivity() {

    private lateinit var activityPostsBinding: ActivityNewsBinding
    private var mAdapter: MostAdapter? = MostAdapter()
    private val postViewModel: MostViewModel by viewModel()
    private  var apiKey : String = "GlIAg9LWk0AO3olNCqAWRtjVUHsfx25V"


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        activityPostsBinding = DataBindingUtil.setContentView(this, R.layout.activity_news)

        activityPostsBinding.postsRecyclerView.adapter = mAdapter

        if (isNetworkAvailable()) {
            postViewModel.getMost(apiKey)
        } else {
            Toast.makeText(
                this,
                getString(R.string.no_internet_connection),
                LENGTH_SHORT
            ).show()
        }

        with(postViewModel) {

            postsData.observe(this@MostViewActivity, Observer {
                activityPostsBinding.postsProgressBar.visibility = GONE
                mAdapter?.mPostList = it
            })

            messageData.observe(this@MostViewActivity, Observer {
                Toast.makeText(this@MostViewActivity, it, LENGTH_LONG).show()
            })

            showProgressbar.observe(this@MostViewActivity, Observer { isVisible ->
                posts_progress_bar.visibility = if (isVisible) VISIBLE else GONE
            })
        }
    }


    override fun onDestroy() {
        mAdapter = null
        super.onDestroy()
    }

}
