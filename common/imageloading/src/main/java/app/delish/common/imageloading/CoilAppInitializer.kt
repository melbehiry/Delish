package app.delish.common.imageloading

import android.app.Application
import coil.Coil
import coil.ImageLoader
import com.delish.appinitializers.AppInitializer
import okhttp3.OkHttpClient
import javax.inject.Inject

class CoilAppInitializer @Inject constructor(
    private val application: Application,
    private val okHttpClient: OkHttpClient

) : AppInitializer {
    override fun init() {
        Coil.setImageLoader {
            ImageLoader.Builder(application)
                .okHttpClient(okHttpClient)
                .build()
        }
    }
}