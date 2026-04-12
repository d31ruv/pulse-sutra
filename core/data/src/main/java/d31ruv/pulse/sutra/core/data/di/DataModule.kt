package d31ruv.pulse.sutra.core.data.di

import d31ruv.pulse.sutra.core.data.utils.network.NetworkMonitor
import d31ruv.pulse.sutra.core.data.utils.network.NetworkMonitorImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
abstract class DataModule {
    @Binds
    internal abstract fun bindsNetworkMonitor(impl: NetworkMonitorImpl): NetworkMonitor
}
