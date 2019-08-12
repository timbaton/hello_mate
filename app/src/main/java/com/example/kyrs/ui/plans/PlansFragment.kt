package com.example.kyrs.ui.plans

import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.view.View
import com.arellomobile.mvp.presenter.InjectPresenter
import com.arellomobile.mvp.presenter.ProvidePresenter
import com.example.kyrs.R
import com.example.kyrs.data.entity.Event
import com.example.kyrs.di.Scopes
import com.example.kyrs.presentation.plans.PlansPresenter
import com.example.kyrs.presentation.plans.PlansView
import com.example.kyrs.ui.base.BaseFragment
import com.example.kyrs.ui.event.EventActivity
import kotlinx.android.synthetic.main.fragment_plans.*
import toothpick.Toothpick
import com.google.gson.Gson


/**
 * Project HelloMate
 * Package com.example.kyrs.ui.plans
 *
 *
 *
 * Created by Timur Badretdinov (aka timurbadretdinov) 2019-07-10
 * Copyright © 2018 SuperEgo. All rights reserved.
 */
class PlansFragment : BaseFragment(), PlansView {

    override val layoutRes: Int = R.layout.fragment_plans

    lateinit var adapter: EventListAdapter

    @InjectPresenter
    lateinit var presenter: PlansPresenter

    @ProvidePresenter
    fun providePresenter(): PlansPresenter {
        return Toothpick.openScope(Scopes.Server).getInstance(PlansPresenter::class.java)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initList()

        swipeRefresh.setOnRefreshListener {
            presenter.onRefreshCalled()
        }
    }
    private fun initList() {
        adapter = EventListAdapter { event ->
            presenter.onEventClicked(event)
        }

        rvList.setHasFixedSize(true)
        rvList.layoutManager = LinearLayoutManager(context)
        rvList.adapter = adapter

    }

    override fun showEvents(events: List<Event>?) {
        if (events?.isNotEmpty()!!) {
            adapter.addList(events)
        } else {
            tvState.text = "list is empty"
        }
    }

    override fun updateEvents(events: List<Event>?) {
        if (events?.isNotEmpty()!!) {
            adapter.updateList(events)
        } else {
            tvState.text = "list is empty"
        }
    }

    override fun hideLoader() {
        swipeRefresh.isRefreshing = false
    }

    override fun openEventActivity(event: Event) {
        val event = Gson().toJson(event)
        startActivity(EventActivity.getIntent(context!!, event))
    }
}