package com.example.kyrs.presentation.search

import com.arellomobile.mvp.InjectViewState
import com.example.kyrs.data.entity.Event
import com.example.kyrs.data.repository.EventRepository
import com.example.kyrs.presentation.base.BasePresenter
import javax.inject.Inject

/**
 * Project HelloMate
 * Package com.example.kyrs.presentation.search
 *
 *
 *
 * Created by Timur Badretdinov (aka timurbadretdinov) 2019-08-10
 * Copyright © 2018 SuperEgo. All rights reserved.
 */
@InjectViewState
class SearchPresenter @Inject constructor(
    private var eventRepository: EventRepository
) : BasePresenter<SearchView>() {

    override fun onFirstViewAttach() {
        super.onFirstViewAttach()

        loadFutureEvents()
    }

    private fun loadFutureEvents() {
        eventRepository.getFutureEvents()
            .subscribe({
                viewState.showEvents(it)
            },{

            }).connect()
    }

    fun onEventClicked(event: Event) {
        viewState.openEventActivity()
    }
}