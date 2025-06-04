// AppModule.kt
import com.eranio.fidoapp.Consts.BASE_URL
import com.eranio.fidoapp.data.NewsRepositoryImpl
import com.eranio.fidoapp.data.remote.NewsApiService
import com.eranio.fidoapp.domain.repository.NewsRepository
import com.eranio.fidoapp.domain.usecase.GetArticlesUseCase
import com.eranio.fidoapp.presentation.ArticleViewModel
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory

val appModule = module {

    single {
        Moshi.Builder()
            .add(KotlinJsonAdapterFactory())
            .build()
    }

    single {
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(MoshiConverterFactory.create(get()))
            .build()
    }

    single {
        get<Retrofit>().create(NewsApiService::class.java)
    }

    single<NewsRepository> {
        NewsRepositoryImpl(get())
    }

    single {
        GetArticlesUseCase(get())
    }

    viewModel {
        ArticleViewModel(get())
    }
}
