package app.delish.common.imageloading

import com.delish.appinitializers.AppInitializer
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import dagger.multibindings.IntoSet

@Module
@InstallIn(SingletonComponent::class)
abstract class ImageLoadingModule {

    @Binds
    @IntoSet
    abstract fun provideCoilInitializer(bind: CoilAppInitializer): AppInitializer
}