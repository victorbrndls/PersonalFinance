package com.victorbrndls.pfs.core.summary.usecase

import com.victorbrndls.pfs.core.summary.entity.Summary
import com.victorbrndls.pfs.infrastructure.date.DateRange
import com.victorbrndls.pfs.infrastructure.date.rangeLast12Months
import kotlinx.coroutines.flow.firstOrNull
import javax.inject.Inject

interface GetSummariesUseCase {
    /**
     * Summaries are returned ordered from newest to oldest
     */
    suspend fun getAll(
        range: DateRange = rangeLast12Months()
    ): List<Summary>
}

class GetSummariesUseCaseImpl @Inject constructor(
    private val observeSummariesUseCase: ObserveSummariesUseCase
) : GetSummariesUseCase {

    override suspend fun getAll(
        range: DateRange
    ): List<Summary> {
        return observeSummariesUseCase.observe(range).firstOrNull() ?: emptyList()
    }

}
