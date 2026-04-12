package d31ruv.diecast.feature.profile

import android.text.format.DateFormat
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.PreviewScreenSizes
import androidx.compose.ui.unit.dp
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import coil.compose.AsyncImage
import d31ruv.diecast.core.data.profile.model.Profile

@Composable
fun ProfileRoute(
    isOffline: Boolean,
    modifier: Modifier = Modifier,
    viewModel: ProfileViewModel = hiltViewModel(),
) {
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()
    ProfileScreen(
        uiState = uiState,
        isOffline = isOffline,
        onQueryChange = viewModel::onQueryChange,
        onSearch = viewModel::submitSearch,
        modifier = modifier,
    )
}

@Composable
fun ProfileScreen(
    uiState: ProfileUiState,
    isOffline: Boolean,
    onQueryChange: (String) -> Unit,
    onSearch: () -> Unit,
    modifier: Modifier = Modifier,
) {
    LazyColumn(
        modifier = modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background),
        verticalArrangement = Arrangement.spacedBy(16.dp),
    ) {
        item {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 20.dp, vertical = 24.dp),
                verticalArrangement = Arrangement.spacedBy(12.dp),
            ) {
                Text(
                    text = "Template profile demo",
                    style = MaterialTheme.typography.headlineMedium,
                    fontWeight = FontWeight.SemiBold,
                )
                Text(
                    text = "Use a GitHub username to test the starter's network, cache, and UI state flow.",
                    style = MaterialTheme.typography.bodyLarge,
                    color = MaterialTheme.colorScheme.onSurfaceVariant,
                )
                if (isOffline) {
                    OfflineBanner()
                }
                OutlinedTextField(
                    value = uiState.query,
                    onValueChange = onQueryChange,
                    modifier = Modifier
                        .fillMaxWidth()
                        .testTag("profile_query_field"),
                    label = { Text("GitHub username") },
                    singleLine = true,
                    supportingText = uiState.validationMessage?.let { message ->
                        { Text(message) }
                    },
                    isError = uiState.validationMessage != null,
                )
                Button(
                    onClick = onSearch,
                    modifier = Modifier
                        .fillMaxWidth()
                        .testTag("profile_load_button"),
                ) {
                    Text("Load profile")
                }
            }
        }

        item {
            when (val screenState = uiState.screenState) {
                ProfileScreenState.Idle -> ProfileEmptyState()
                ProfileScreenState.Loading -> ProfileLoadingState()
                is ProfileScreenState.Error -> ProfileErrorState(
                    message = screenState.message,
                    onRetry = onSearch,
                )

                is ProfileScreenState.Success -> ProfileCard(
                    profile = screenState.profile,
                    modifier = Modifier.padding(horizontal = 20.dp),
                )
            }
        }
    }
}

@Composable
private fun OfflineBanner() {
    Surface(
        shape = RoundedCornerShape(18.dp),
        color = MaterialTheme.colorScheme.secondaryContainer,
    ) {
        Text(
            text = "You're offline. Cached data will be shown when available.",
            modifier = Modifier.padding(horizontal = 14.dp, vertical = 10.dp),
            style = MaterialTheme.typography.bodyMedium,
            color = MaterialTheme.colorScheme.onSecondaryContainer,
        )
    }
}

@Composable
private fun ProfileEmptyState() {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 20.dp),
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.surfaceContainerLow,
        ),
    ) {
        Text(
            text = "Search for a profile to exercise Retrofit, Room, Hilt, and Compose state handling.",
            modifier = Modifier.padding(20.dp),
            style = MaterialTheme.typography.bodyLarge,
            color = MaterialTheme.colorScheme.onSurfaceVariant,
        )
    }
}

@Composable
private fun ProfileLoadingState() {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 20.dp, vertical = 32.dp),
        contentAlignment = Alignment.Center,
    ) {
        CircularProgressIndicator(modifier = Modifier.testTag("profile_loading"))
    }
}

@Composable
private fun ProfileErrorState(
    message: String,
    onRetry: () -> Unit,
) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 20.dp),
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.errorContainer,
        ),
    ) {
        Column(
            modifier = Modifier.padding(20.dp),
            verticalArrangement = Arrangement.spacedBy(12.dp),
        ) {
            Text(
                text = message,
                style = MaterialTheme.typography.bodyLarge,
                color = MaterialTheme.colorScheme.onErrorContainer,
            )
            Button(onClick = onRetry) {
                Text("Retry")
            }
        }
    }
}

@OptIn(ExperimentalLayoutApi::class)
@Composable
private fun ProfileCard(
    profile: Profile,
    modifier: Modifier = Modifier,
) {
    val context = LocalContext.current
    val lastUpdated = DateFormat.getMediumDateFormat(context)
        .format(profile.cachedAtEpochMillis) + " " + DateFormat.getTimeFormat(context)
        .format(profile.cachedAtEpochMillis)

    Card(
        modifier = modifier
            .fillMaxWidth()
            .testTag("profile_card"),
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.surfaceContainer,
        ),
    ) {
        Column(
            modifier = Modifier.padding(20.dp),
            verticalArrangement = Arrangement.spacedBy(18.dp),
        ) {
            Row(
                horizontalArrangement = Arrangement.spacedBy(16.dp),
                verticalAlignment = Alignment.CenterVertically,
            ) {
                AsyncImage(
                    model = profile.avatarUrl,
                    contentDescription = "${profile.username} avatar",
                    modifier = Modifier
                        .size(72.dp)
                        .clip(CircleShape),
                    contentScale = ContentScale.Crop,
                )
                Column(verticalArrangement = Arrangement.spacedBy(4.dp)) {
                    Text(
                        text = profile.displayName ?: profile.username,
                        style = MaterialTheme.typography.headlineSmall,
                        fontWeight = FontWeight.SemiBold,
                    )
                    Text(
                        text = "@${profile.username}",
                        style = MaterialTheme.typography.bodyLarge,
                        color = MaterialTheme.colorScheme.onSurfaceVariant,
                    )
                    Text(
                        text = if (profile.source == Profile.DataSource.Cache) {
                            "Showing cached data"
                        } else {
                            "Fresh network result"
                        },
                        style = MaterialTheme.typography.labelLarge,
                        color = MaterialTheme.colorScheme.primary,
                    )
                }
            }

            val bio = profile.bio
            if (!bio.isNullOrBlank()) {
                Text(
                    text = bio,
                    style = MaterialTheme.typography.bodyLarge,
                    overflow = TextOverflow.Ellipsis,
                )
            }

            FlowRow(
                horizontalArrangement = Arrangement.spacedBy(10.dp),
                verticalArrangement = Arrangement.spacedBy(10.dp),
            ) {
                ProfileStat(label = "Followers", value = profile.followers.toString())
                ProfileStat(label = "Following", value = profile.following.toString())
                ProfileStat(label = "Repos", value = profile.publicRepos.toString())
            }

            Column(verticalArrangement = Arrangement.spacedBy(6.dp)) {
                Text(
                    text = "Profile URL",
                    style = MaterialTheme.typography.labelMedium,
                    color = MaterialTheme.colorScheme.onSurfaceVariant,
                )
                Text(
                    text = profile.profileUrl,
                    style = MaterialTheme.typography.bodyMedium,
                )
                Text(
                    text = "Cached at $lastUpdated",
                    style = MaterialTheme.typography.bodySmall,
                    color = MaterialTheme.colorScheme.onSurfaceVariant,
                )
            }
        }
    }
}

@Composable
private fun ProfileStat(
    label: String,
    value: String,
) {
    Surface(
        color = MaterialTheme.colorScheme.surfaceBright,
        shape = RoundedCornerShape(20.dp),
    ) {
        Column(
            modifier = Modifier.padding(horizontal = 14.dp, vertical = 10.dp),
            verticalArrangement = Arrangement.spacedBy(2.dp),
        ) {
            Text(
                text = value,
                style = MaterialTheme.typography.titleMedium,
                fontWeight = FontWeight.Bold,
            )
            Text(
                text = label,
                style = MaterialTheme.typography.bodySmall,
                color = MaterialTheme.colorScheme.onSurfaceVariant,
            )
        }
    }
}

@PreviewScreenSizes
@Composable
private fun ProfileScreenPreview() {
    ProfileScreen(
        uiState = ProfileUiState(
            query = "octocat",
            screenState = ProfileScreenState.Success(
                profile = Profile(
                    username = "octocat",
                    displayName = "The Octocat",
                    avatarUrl = "",
                    bio = "Friendly sample profile for the starter template.",
                    followers = 9380,
                    following = 9,
                    publicRepos = 8,
                    profileUrl = "https://github.com/octocat",
                    cachedAtEpochMillis = 1_742_378_400_000,
                    source = Profile.DataSource.Remote,
                ),
            ),
        ),
        isOffline = false,
        onQueryChange = {},
        onSearch = {},
    )
}