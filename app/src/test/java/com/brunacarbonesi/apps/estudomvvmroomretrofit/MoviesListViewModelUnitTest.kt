package com.brunacarbonesi.apps.estudomvvmroomretrofit

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.Observer
import com.brunacarbonesi.apps.estudomvvmroomretrofit.movieslistActivity.MoviesListViewModel
import com.brunacarbonesi.apps.estudomvvmroomretrofit.service.model.MovieVO
import com.brunacarbonesi.apps.estudomvvmroomretrofit.service.repository.Repository
import com.brunacarbonesi.apps.estudomvvmroomretrofit.utils.ViewData
import com.brunacarbonesi.apps.estudomvvmroomretrofit.utils.ViewStatus
import io.reactivex.Single
import io.reactivex.android.plugins.RxAndroidPlugins
import io.reactivex.schedulers.Schedulers
import org.junit.Test

import org.junit.Assert.*
import org.junit.Before
import org.junit.Rule
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.MockitoAnnotations

class MoviesListViewModelUnitTest {
    @get:Rule
    val rule = InstantTaskExecutorRule()

    @Before
    fun setUp() {
        MockitoAnnotations.initMocks(this)
        RxAndroidPlugins.setInitMainThreadSchedulerHandler { Schedulers.trampoline() }
    }

    private lateinit var viewModel: MoviesListViewModel

    @Mock
    private lateinit var liveDataMovieObserver: Observer<ViewData<List<MovieVO>>>
    private val isNetworkAvailable = true


    @Test
    fun `when view model preencheListaUsuarios then sets liveDataUser`() {
        //Arrange
        val users = listOf(
            MovieVO(1000, "Filme 1", "Overview do filme 1", "urlPoster 1","urlBackdrop 1", 9.6f, "2022-01-01"),
            MovieVO(1001, "Filme 2", "Overview do filme 2", "urlPoster 2","urlBackdrop 2", 7.3f, "2023-11-25"),
        )
        val viewDataUsers = ViewData(users, ViewStatus.SUCCESS)

        val resultSingle = MockRepository()
        viewModel = MoviesListViewModel(resultSingle)

        viewModel.liveDataMovie.observeForever(liveDataMovieObserver)

        //Act
        viewModel.fillMoviesList(isNetworkAvailable)

        //Assert
        Mockito.verify(liveDataMovieObserver).onChanged(viewDataUsers)
    }
}

class MockRepository : Repository {

    override fun getMovies(verifyAvailableNetwork: Boolean): Single<List<MovieVO>> {
        val users = listOf(
            MovieVO(1000, "Filme 1", "Overview do filme 1", "urlPoster 1","urlBackdrop 1", 9.6f, "2022-01-01"),
            MovieVO(1001, "Filme 2", "Overview do filme 2", "urlPoster 2","urlBackdrop 2", 7.3f, "2023-11-25"),
        )
        return Single.just(users)
    }
}