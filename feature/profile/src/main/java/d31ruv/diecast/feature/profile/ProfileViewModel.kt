package d31ruv.diecast.feature.profile

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import d31ruv.diecast.core.data.profile.GithubProfileRepository
import d31ruv.diecast.core.data.profile.ProfileLookupResult
import d31ruv.diecast.core.data.profile.model.Profile
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ProfileViewModel @Inject constructor(
    private val githubProfileRepository: GithubProfileRepository,
) : ViewModel() {
    private val mutableUiState = MutableStateFlow(ProfileUiState())
    val uiState: StateFlow<ProfileUiState> = mutableUiState.asStateFlow()

    fun onQueryChange(query: String) {
        mutableUiState.update {
            it.copy(
                query = query,
                validationMessage = null,
            )
        }
    }

    fun submitSearch() {
        val query = uiState.value.query.trim()
        if (query.isBlank()) {
            mutableUiState.update {
                it.copy(validationMessage = "Enter a username to load a profile")
            }
            return
        }

        viewModelScope.launch {
            mutableUiState.update {
                it.copy(
                    query = query,
                    validationMessage = null,
                    screenState = ProfileScreenState.Loading,
                )
            }

            val result = githubProfileRepository.getProfile(query)
            mutableUiState.update { currentState ->
                when (result) {
                    is ProfileLookupResult.Success -> currentState.copy(
                        screenState = ProfileScreenState.Success(profile = result.profile),
                    )

                    ProfileLookupResult.NotFound -> currentState.copy(
                        screenState = ProfileScreenState.Error(
                            "No profile found for \"$query\"",
                        ),
                    )

                    ProfileLookupResult.Error -> currentState.copy(
                        screenState = ProfileScreenState.Error(
                            "We couldn't load that profile right now. Try again in a moment.",
                        ),
                    )
                }
            }
        }
    }
}

data class ProfileUiState(
    val query: String = "",
    val validationMessage: String? = null,
    val screenState: ProfileScreenState = ProfileScreenState.Idle,
)

sealed interface ProfileScreenState {
    data object Idle : ProfileScreenState
    data object Loading : ProfileScreenState
    data class Success(val profile: Profile) : ProfileScreenState
    data class Error(val message: String) : ProfileScreenState
}
