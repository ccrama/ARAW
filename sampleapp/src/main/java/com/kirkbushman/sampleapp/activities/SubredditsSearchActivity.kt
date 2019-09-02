package com.kirkbushman.sampleapp.activities

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.kirkbushman.araw.models.Subreddit
import com.kirkbushman.araw.models.SubredditSearchResult
import com.kirkbushman.sampleapp.R
import com.kirkbushman.sampleapp.TestApplication
import com.kirkbushman.sampleapp.controllers.SubredditController
import com.kirkbushman.sampleapp.doAsync
import kotlinx.android.synthetic.main.activity_subreddits_search.*

class SubredditsSearchActivity : AppCompatActivity() {

    private val client by lazy { TestApplication.instance.getClient() }

    private var searchResult: SubredditSearchResult? = null
    private val subreddits = ArrayList<Subreddit>()
    private val controller by lazy {
        SubredditController(object : SubredditController.SubredditCallback {

            override fun subscribeClick(index: Int) {

                val subreddit = subreddits[index]
                doAsync(doWork = {

                    client?.subscribe(subreddit)
                }, onPost = {

                    subreddits[index] = subreddit.copy(isSubscriber = !subreddit.isSubscriber)
                    refresh()
                })
            }
        })
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_subreddits_search)

        setSupportActionBar(toolbar)
        supportActionBar?.let {
            it.setDisplayHomeAsUpEnabled(true)
            it.setDisplayShowHomeEnabled(true)
        }

        list.setHasFixedSize(true)
        list.setController(controller)

        search_bttn.setOnClickListener {

            val query = query.text.toString().trim()

            if (!starts_with.isChecked) {

                doAsync(doWork = {

                    val fetcher = client?.fetchSubredditsSearch(query)

                    subreddits.clear()
                    subreddits.addAll(fetcher?.fetchNext() ?: listOf())
                }, onPost = {

                    controller.setSubreddits(subreddits)
                })
            } else {

                doAsync(doWork = {

                    searchResult = client?.searchSubreddits(
                        query = query,
                        includeOver18 = true
                    )
                }, onPost = {

                    if (searchResult != null) {
                        controller.setSearchResult(searchResult!!)
                    }
                })
            }
        }
    }

    private fun refresh() {
        controller.setSubreddits(subreddits)
    }
}