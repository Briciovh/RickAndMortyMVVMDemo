package com.example.harvesterapidemo.ui

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.example.harvesterapidemo.model.rickmorty.*
import com.example.harvesterapidemo.repository.MainRepository
import com.example.harvesterapidemo.repository.PickRepository
import com.example.harvesterapidemo.rules.TestCoroutineRule
import junit.framework.Assert.*
import okhttp3.ResponseBody.Companion.toResponseBody
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito.*
import org.mockito.junit.MockitoJUnitRunner
import retrofit2.Response

@RunWith(MockitoJUnitRunner::class)
class SharedViewModelTest {

    //Rules
    @get:Rule
    val instantExecutor = InstantTaskExecutorRule()

    @get:Rule
    val testCoroutine = TestCoroutineRule()

    //Mocks
    @Mock
    lateinit var mainRepository: MainRepository

    @Mock
    lateinit var pickRepository: PickRepository

    //Subject
    lateinit var sharedViewModel: SharedViewModel

    //Testing values
    private val testId = 123

    private val testUrl = "https://url.com"

    private val testCharacter = Results(
        testId,
        "Any name",
        "Any status",
        "Any species",
        "Any type",
        "Any gender",
        Origin("Any Name", testUrl),
        Location("Any Location", testUrl),
        "Any image",
        emptyList(),
        testUrl,
        "Any date"
    )

    private val testCharacterResponse = CharacterResponse(
        Info(1, 1, testUrl, testUrl),
        listOf(testCharacter, testCharacter.copy(id = 124), testCharacter.copy(id = 125))
    )

    private val errorCharacterResponse: Response<CharacterResponse> =
        Response.error(404, "Not found".toResponseBody(null))

    private val errorResults: Response<Results> =
        Response.error(404, "Not found".toResponseBody(null))

    //Preparation
    @Before
    fun setUp() {
        sharedViewModel = SharedViewModel(mainRepository, pickRepository)
    }

    //Tests
    @Test
    fun getCharacters() {
        testCoroutine.runBlockingTest {
            doReturn(Response.success(testCharacterResponse))
                .`when`(mainRepository)
                .getCharacters(anyInt())
            sharedViewModel.getCharacters()
            verify(mainRepository).getCharacters(anyInt())
            assertNotNull(sharedViewModel.characterList.value)
            assertEquals(3, sharedViewModel.characterList.value?.size)
        }
    }

    @Test
    fun getCharactersFail() {
        testCoroutine.runBlockingTest {
            doReturn(errorCharacterResponse)
                .`when`(mainRepository)
                .getCharacters(anyInt())
            sharedViewModel.getCharacters()
            verify(mainRepository).getCharacters(anyInt())
            assertNull(sharedViewModel.characterList.value)
        }
    }

    @Test
    fun setCurrentId() {
        sharedViewModel.setCurrentId(testId)
        assertEquals(testId, sharedViewModel.currentId.value)
        assertNull(sharedViewModel.currentCharacter.value)
    }

    @Test
    fun getCharacterById() {
        testCoroutine.runBlockingTest {
            doReturn(Response.success(testCharacter))
                .`when`(mainRepository)
                .getCharacterById(anyInt())
            sharedViewModel.getCharacterById(testId)
            verify(mainRepository).getCharacterById(testId)
            assertEquals(testCharacter, sharedViewModel.currentCharacter.value)
        }
    }

    @Test
    fun getCharacterByIdFail() {
        testCoroutine.runBlockingTest {
            doReturn(errorResults)
                .`when`(mainRepository)
                .getCharacterById(anyInt())
            sharedViewModel.getCharacterById(testId)
            verify(mainRepository).getCharacterById(testId)
            assertEquals(null, sharedViewModel.currentCharacter.value)
        }
    }

    @Test
    fun setLoadingStateEnabled() {
        sharedViewModel.setLoadingState(true)
        val currentState = sharedViewModel.loadingState.value ?: false
        assertTrue(currentState)
    }

    @Test
    fun setLoadingStateDisabled() {
        sharedViewModel.setLoadingState(false)
        val currentState = sharedViewModel.loadingState.value ?: true
        assertFalse(currentState)
    }

    @Test
    fun lastPageDefault() {
        assertTrue(sharedViewModel.lastPage())
    }

    @Test
    fun lastPageSinglePage() {
        testCoroutine.runBlockingTest {
            doReturn(Response.success(testCharacterResponse))
                .`when`(mainRepository)
                .getCharacters(anyInt())
            sharedViewModel.getCharacters()
        }
        assertTrue(sharedViewModel.lastPage())
    }

    @Test
    fun lastPageMultiplePages() {
        testCoroutine.runBlockingTest {
            doReturn(
                Response.success(
                    testCharacterResponse.copy(
                        info = Info(
                            5,
                            5,
                            testUrl,
                            testUrl
                        )
                    )
                )
            )
                .`when`(mainRepository)
                .getCharacters(anyInt())
            sharedViewModel.getCharacters()
        }
        assertFalse(sharedViewModel.lastPage())
    }
}